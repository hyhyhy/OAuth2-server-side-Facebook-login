package com.llorieruo.projects.handlers.asyncResponses;

import java.io.IOException;
import java.io.InputStream;

import com.llorieruo.projects.dataObjects.facebook.FacebookApiErrorResponse;
import com.llorieruo.projects.parsers.IParser;

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
