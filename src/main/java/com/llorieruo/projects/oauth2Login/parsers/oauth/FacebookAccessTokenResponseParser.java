package com.llorieruo.projects.oauth2Login.parsers.oauth;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.InputStream;
import java.util.Scanner;

import com.llorieruo.projects.oauth2Login.dataObjects.AccessTokenSuccessfulResponse;
import com.llorieruo.projects.oauth2Login.parsers.IParser;

/**
 * The Facebook implementation of the access token successful response.
 * @see <a href="https://developers.facebook.com/docs/facebook-login/manually-build-a-login-flow/#confirm">Facebook Login - manually build a login flow</a>
 * @see <a href="http://tools.ietf.org/html/rfc6749#section-5.1">RFC 6749 - section 5.1</a>
 */
public class FacebookAccessTokenResponseParser implements IParser<InputStream, AccessTokenSuccessfulResponse>
{
	/**
	 * The name of an expected key.
	 */
	protected String	accessToken, refreshToken, expiresIn, tokenType, scope;
	
	public FacebookAccessTokenResponseParser()
	{
		this.accessToken = "access_token";
		this.refreshToken = "refresh_token";
		this.tokenType = "token_type";
		this.scope = "scope";
		this.expiresIn = "expires";
	}
	
	/**
	 * This method parses this InputStream as a String with form-urlencoded format.
	 */
	@Override
	public AccessTokenSuccessfulResponse parse(InputStream stream)
	{
		AccessTokenSuccessfulResponse instance = new AccessTokenSuccessfulResponse();
		
		try(Scanner scanner = new Scanner(stream, UTF_8.displayName()))
		{
			scanner.useDelimiter("&");
			while(scanner.hasNext())
			{
				String nextToken = scanner.next();
				String[] keyValue = nextToken.split("=");
				if(keyValue.length != 2)
					continue;
				
				String key = keyValue[0], value = keyValue[1];
				if(key.equals(accessToken))
					instance.setAccess_token(value);
				else if(key.equals(refreshToken))
					instance.setRefresh_token(value);
				else if(key.equals(expiresIn))
					instance.setExpires_in(Long.parseLong(value));
				else if(key.equals(tokenType))
					instance.setToken_type(value);
				else if(key.equals(scope))
					instance.setScope(value);
			}
		}
		
		return instance;
	}
}
