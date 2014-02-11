package com.llorieruo.projects.oauth2Login.handlers.asyncResponses;

import java.io.IOException;
import java.io.InputStream;

import com.llorieruo.projects.oauth2Login.dataObjects.facebook.FacebookApiErrorResponse;
import com.llorieruo.projects.oauth2Login.parsers.IParser;

public class FacebookAsyncApiResponseHandler<V extends FacebookApiErrorResponse> extends FacebookAbstractAsyncApiResponseHandler<V>
{
	private final IParser<InputStream, V> PARSER;
	
	FacebookAsyncApiResponseHandler(Class<V> clazz, IParser<InputStream, V> parser)
	{
		this.PARSER = parser;
	}

	@Override
	protected V parseEntityBody(InputStream responseBody) throws IOException
	{
		return this.PARSER.parse(responseBody);
	}
}
