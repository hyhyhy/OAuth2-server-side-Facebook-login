package com.llorieruo.projects.oauth2Login.parsers.oauth;

import com.llorieruo.projects.oauth2Login.dataObjects.AccessTokenSuccessfulResponse;
import com.llorieruo.projects.oauth2Login.parsers.JsonParser;

/**
 * This type parses the received stream trying to find the parameters described in the OAuth2.0 framework.
 * @see <a href="http://tools.ietf.org/html/rfc6749#section-5.1">RFC 6749 - section 5.1</a>
 */
public class AccessTokenJsonResponseParser extends JsonParser<AccessTokenSuccessfulResponse>
{
	public AccessTokenJsonResponseParser()
	{
		super(AccessTokenSuccessfulResponse.class);
	}
}
