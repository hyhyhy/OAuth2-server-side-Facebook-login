package com.llorieruo.projects.oauth2Login.providers;

import java.io.IOException;

import com.llorieruo.projects.oauth2Login.dataObjects.AccessTokenSuccessfulResponse;
import com.llorieruo.projects.oauth2Login.exceptions.TokenValidationException;

public interface IOAuth2
{
	/**
	 * The returned Url does not include the <em>state</em> and <em>scope</em> parameters.
	 * If the <em>scope</em> parameter is not set, the application will only have access to the basic info of the user.
	 * The best practices say that we should only ask for the permissions that we need.
	 * <p>
	 * The <em>scope</em> parameter is a recommended parameter but should be used, for security reasons to avoid CSRF attacks.
	 * @return the authentication Url of the OAuth2 provider. The redirect_uri query parameter returns encoded.
	 */
	public String getAuthenticationUrl();
	
	/**
	 * Exchange the authorization code for an access token and a refresh token.
	 * 
	 * @param authorizationCode
	 *            the code received from the client.
	 * @return the object containing the access and refresh tokens.
	 * @throws java.io.IOException
	 */
	public AccessTokenSuccessfulResponse exchangeAuthCode(String authorizationCode) throws IOException;
	
	/**
	 * Check that the token is valid.
	 * <p>
	 * Make sure the token we got is for the intended user.
	 * <p>
	 * Make sure the token we got is for our application.
	 * <p>
	 * If the validation has errors an Exception is thrown.
	 * 
	 * @param token
	 *            the object containing the access and refresh tokens.
	 * @param userId
	 *            the identification of the current user.
	 * @return the user_id returned from the OAuth2 provider.
	 * @throws java.io.IOException when the authorization server returns an unexpected response or when there's an error binding the response to JSON.
	 * @throws com.llorieruo.projects.oauth2Login.exceptions.TokenValidationException when the validation fails.
	 */
	public String validateToken(AccessTokenSuccessfulResponse token, String userId) throws TokenValidationException, IOException;
	
	/**
	 * Revoke the delegated access to the user data.
	 * 
	 * @param token
	 *            the access token stored when the user logged in.
	 * @throws java.io.IOException when the authorization server returns an unexpected response or when there's an error binding the response.
	 */
	public void revokeToken(String token) throws IOException;
}
