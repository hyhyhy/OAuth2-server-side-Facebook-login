package com.llorieruo.projects.oauth2Login.providers.proxy;

import java.io.IOException;

import com.llorieruo.projects.oauth2Login.dataObjects.AccessTokenSuccessfulResponse;
import com.llorieruo.projects.oauth2Login.exceptions.TokenValidationException;
import com.llorieruo.projects.oauth2Login.exceptions.UnexpectedResponseException;
import com.llorieruo.projects.oauth2Login.providers.FacebookOAuth2;
import com.llorieruo.projects.oauth2Login.providers.IOAuth2;

/**
 * Virtual proxy pattern: http://www.oodesign.com/proxy-pattern.html.
 * 
 */
public class FacebookOAuth2Proxy extends AbstractProxy<FacebookOAuth2> implements IOAuth2
{
	private final String	redirectUri;
	private FacebookOAuth2	realInstance;
	
	public FacebookOAuth2Proxy(String redirectUri)
	{
		this.redirectUri = redirectUri;
	}
	
	@Override
	protected FacebookOAuth2 getInstance()
	{
		// Lazy initialization using double locking mechanism.
		if(this.realInstance == null)
		{
			synchronized(FacebookOAuth2Proxy.class)
			{
				if(this.realInstance == null)
				{
					try
					{
						realInstance = new FacebookOAuth2(redirectUri);
					}
					catch(InstantiationException e)
					{
						throw new RuntimeException(e);
					}
				}
			}
		}
		
		return this.realInstance;
	}
	
	@Override
	public String getAuthenticationUrl()
	{
		return getInstance().getAuthenticationUrl();
	}
	
	@Override
	public AccessTokenSuccessfulResponse exchangeAuthCode(String authorizationCode) throws IOException
	{
		return getInstance().exchangeAuthCode(authorizationCode);
	}
	
	@Override
	public String validateToken(AccessTokenSuccessfulResponse token, String userId) throws UnexpectedResponseException,
			TokenValidationException, IOException
	{
		return getInstance().validateToken(token, userId);
	}
	
	@Override
	public void revokeToken(String token) throws IOException
	{
		getInstance().revokeToken(token);
	}
}

