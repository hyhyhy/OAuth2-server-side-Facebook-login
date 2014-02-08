package com.llorieruo.projects.providers;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Properties;
import java.util.Scanner;

import org.jboss.netty.handler.codec.http.HttpHeaders;

import com.llorieruo.projects.dataObjects.AccessTokenSuccessfulResponse;
import com.llorieruo.projects.dataObjects.facebook.FacebookInspectedToken;
import com.llorieruo.projects.dataObjects.facebook.FacebookUser;
import com.llorieruo.projects.exceptions.TokenValidationException;
import com.llorieruo.projects.exceptions.UnexpectedResponseException;
import com.llorieruo.projects.handlers.asyncResponses.FacebookAbstractAsyncApiResponseHandler;
import com.llorieruo.projects.handlers.asyncResponses.FactoryAsyncResponseHandler;
import com.llorieruo.projects.parsers.IParser;
import com.llorieruo.projects.parsers.oauth.FacebookAccessTokenResponseParser;
import com.ning.http.client.AsyncHttpClient;

public class FacebookOAuth2 extends AbstractOAuth2
{
	private final String	CLIENT_ID, CLIENT_ID_QUERY_PARAM,
			CLIENT_SECRET, CLIENT_SECRET_QUERY_PARAM,
			REDIRECT_URI, REDIRECT_URI_ENCODED, REDIRECT_URI_QUERY_PARAM,
			AUTH_CODE_QUERY_PARAM,
			DEBUG_INPUT_TOKEN_PARAM,
			ACCESS_TOKEN_PARAM, DEBUG_ACCESS_TOKEN,
			GRAPH_URL, EXCHANGE_URL, DEBUG_URL, AUTHENTICATION_URL;
	private GraphAPI	graphAPI;
	
	public FacebookOAuth2(String redirectUri, IParser<InputStream, AccessTokenSuccessfulResponse> parser) throws InstantiationException
	{
		try
		{
			Properties properties = new Properties();
			properties.load(new FileReader("conf/application.properties"));
			
			this.CLIENT_ID = properties.getProperty("FACEBOOK_CLIENT_ID");
			this.CLIENT_SECRET = properties.getProperty("FACEBOOK_CLIENT_SECRET");
			this.AUTHENTICATION_URL = properties.getProperty("FACEBOOK_AUTHENTICATION_URL", "https://www.facebook.com/dialog/oauth");
			if(this.CLIENT_ID == null || this.CLIENT_ID.isEmpty() || this.CLIENT_SECRET == null || this.CLIENT_SECRET.isEmpty())
			{
				throw new IOException("There's no FACEBOOK_CLIENT_ID or FACEBOOK_CLIENT_SECRET in the properties file.");
			}
			this.CLIENT_ID_QUERY_PARAM = "client_id";
			this.CLIENT_SECRET_QUERY_PARAM = "client_secret";
			this.REDIRECT_URI = redirectUri;
			this.REDIRECT_URI_ENCODED = URLEncoder.encode(redirectUri, UTF_8.displayName());
			this.REDIRECT_URI_QUERY_PARAM = "redirect_uri";
			this.AUTH_CODE_QUERY_PARAM = "code";
			this.GRAPH_URL = "https://graph.facebook.com";
			this.EXCHANGE_URL = String.format("%s/oauth/access_token", this.GRAPH_URL);
			this.DEBUG_URL = String.format("%s/debug_token", this.GRAPH_URL);
			this.DEBUG_INPUT_TOKEN_PARAM = "input_token";
			this.ACCESS_TOKEN_PARAM = "access_token";
			// https://developers.facebook.com/docs/facebook-login/access-tokens/#apptokens
			// Using method that doesn't require using a generated app access token.
			this.DEBUG_ACCESS_TOKEN = String.format("%s|%s", this.CLIENT_ID,this.CLIENT_SECRET);
		}
		catch(IOException e)
		{
			throw new InstantiationException(e.getMessage());
		}
	}
	
	@Override
	public String getAuthenticationUrl()
	{
		return String.format("%s?%s=%s&%s=%s&response_type=%s", this.AUTHENTICATION_URL
				, this.CLIENT_ID_QUERY_PARAM, this.CLIENT_ID
				, this.REDIRECT_URI_QUERY_PARAM, this.REDIRECT_URI_ENCODED
				, this.AUTH_CODE_QUERY_PARAM);
	}
	
	@Override
	public AccessTokenSuccessfulResponse exchangeAuthCode(String authorizationCode) throws IOException
	{
		AsyncHttpClient client = new AsyncHttpClient();
		return templateExecute(client.prepareGet(EXCHANGE_URL)
				.addQueryParameter(CLIENT_ID_QUERY_PARAM, CLIENT_ID)
				.addQueryParameter(CLIENT_SECRET_QUERY_PARAM, CLIENT_SECRET)
				.addQueryParameter(REDIRECT_URI_QUERY_PARAM, REDIRECT_URI)
				.addQueryParameter(AUTH_CODE_QUERY_PARAM, authorizationCode)
				.addHeader(HttpHeaders.Names.ACCEPT_CHARSET, UTF_8.displayName())
				.execute(new FacebookAbstractAsyncApiResponseHandler<AccessTokenSuccessfulResponse>() {
					@Override
					protected AccessTokenSuccessfulResponse parseEntityBody(InputStream responseBody) throws IOException
					{
						return new FacebookAccessTokenResponseParser().parse(responseBody);
					}
				}) // Perform a GET on the request asynchronously.
			, client);
	}
	
	@Override
	public String validateToken(AccessTokenSuccessfulResponse token, String userId) throws IOException, TokenValidationException
	{
		AsyncHttpClient client = new AsyncHttpClient();
		FacebookInspectedToken validationResponse = templateExecute(client.prepareGet(DEBUG_URL)
				.addQueryParameter(DEBUG_INPUT_TOKEN_PARAM, token.getAccess_token())
				.addQueryParameter(ACCESS_TOKEN_PARAM, DEBUG_ACCESS_TOKEN)
				.addHeader(HttpHeaders.Names.ACCEPT_CHARSET, UTF_8.displayName())
				.execute(FactoryAsyncResponseHandler.createFacebookAsyncHandler(FacebookInspectedToken.class)) // Perform a GET on the request asynchronously.
			, client);
		
		if(!validationResponse.getData().isIs_valid())
		{
			throw new TokenValidationException("The access token is not valid. Sign in again.");
		}
		// Validate received values.
		if(!this.CLIENT_ID.equals(validationResponse.getData().getApp_id())
				// User info does not exist in the request when using server-side login flow. So, userId is null!
				|| (userId != null && !userId.equals(validationResponse.getData().getUser_id())))
		{
			throw new TokenValidationException("The access_token does not belongs to the requested user or app.");
		}
		
		return validationResponse.getData().getUser_id();
	}
	
	@Override
	public void revokeToken(String token) throws IOException
	{
		getGraphAPI().deleteUserPermissions(token);
	}
	
	public GraphAPI getGraphAPI()
	{
		// Lazy initialization using double locking mechanism.
		if(this.graphAPI == null)
		{
			synchronized(FacebookOAuth2.class)
			{
				if(this.graphAPI == null)
					this.graphAPI = new GraphAPI();
			}
		}
		
		return this.graphAPI;
	}
	
	public class GraphAPI
	{
		private final String	USER_NODE_ID, PERMISSIONS_EDGE_NAME;
		
		private GraphAPI()
		{
			this.USER_NODE_ID = "me";
			this.PERMISSIONS_EDGE_NAME = "permissions";
		}
		
		public FacebookUser getUser(String accessToken) throws IOException
		{
			AsyncHttpClient client = new AsyncHttpClient();
			return templateExecute(client.prepareGet(String.format("%s/%s", GRAPH_URL, USER_NODE_ID))
					.addQueryParameter(ACCESS_TOKEN_PARAM, accessToken)
					.addHeader(HttpHeaders.Names.ACCEPT_CHARSET, UTF_8.displayName())
					.execute(FactoryAsyncResponseHandler.createFacebookAsyncHandler(FacebookUser.class)) // Perform a GET on the request asynchronously.
				, client);
		}
		
		protected boolean deleteUserPermissions(String accessToken) throws IOException
		{
			AsyncHttpClient client = new AsyncHttpClient();
			return templateExecute(client.prepareDelete(String.format("%s/%s/%s", GRAPH_URL, USER_NODE_ID, PERMISSIONS_EDGE_NAME))
					.addQueryParameter(ACCESS_TOKEN_PARAM, accessToken)
					.addHeader(HttpHeaders.Names.ACCEPT_CHARSET, UTF_8.displayName())
					.execute(new FacebookAbstractAsyncApiResponseHandler<Boolean>() {
						@Override
						protected Boolean parseEntityBody(InputStream responseBody) throws IOException
						{
							try(Scanner scanner = new Scanner(responseBody, UTF_8.displayName()))
							{
								if(scanner.hasNextBoolean())
									return scanner.nextBoolean();
								else
									throw new UnexpectedResponseException("Expecting 'true' in the entity-body of the HTTP response. Instead received: " + scanner.next());
							}
						}
					})
				, client); // Perform a DELETE on the request asynchronously.
		}
	}
}
