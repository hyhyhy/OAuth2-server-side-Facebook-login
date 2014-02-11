package com.llorieruo.projects.oauth2Login.dataObjects;

/**
 * Represents a successful response when issuing the authorization server for an access token.
 * @see http://tools.ietf.org/html/rfc6749#section-5.1
 */
public class AccessTokenSuccessfulResponse extends ErrorResponse
{
	private String	access_token, refresh_token, token_type, scope;
	private long	expires_in;
	
	public String getAccess_token()
	{
		return access_token;
	}
	public void setAccess_token(String access_token)
	{
		this.access_token = access_token;
	}
	public String getRefresh_token()
	{
		return refresh_token;
	}
	public void setRefresh_token(String refresh_token)
	{
		this.refresh_token = refresh_token;
	}
	public String getToken_type()
	{
		return token_type;
	}
	public void setToken_type(String token_type)
	{
		this.token_type = token_type;
	}
	public String getScope()
	{
		return scope;
	}
	public void setScope(String scope)
	{
		this.scope = scope;
	}
	public long getExpires_in()
	{
		return expires_in;
	}
	public void setExpires_in(long expires_in)
	{
		this.expires_in = expires_in;
	}
}
