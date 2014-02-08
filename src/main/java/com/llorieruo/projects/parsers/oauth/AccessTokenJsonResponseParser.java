package com.llorieruo.projects.parsers.oauth;

import com.llorieruo.projects.dataObjects.AccessTokenSuccessfulResponse;
import com.llorieruo.projects.parsers.JsonParser;

/**
 * This type parses the received stream trying to find the parameters described in the OAuth2.0 framework.
 * @see http://tools.ietf.org/html/rfc6749#section-5.1
 */
public class AccessTokenJsonResponseParser extends JsonParser<AccessTokenSuccessfulResponse>
{
	public AccessTokenJsonResponseParser()
	{
		super(AccessTokenSuccessfulResponse.class);
	}
}
