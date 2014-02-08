package com.llorieruo.projects.handlers.asyncResponses;

import java.io.IOException;
import java.io.InputStream;

import org.jboss.netty.handler.codec.http.HttpHeaders;
import org.jboss.netty.handler.codec.http.HttpResponseStatus;

import com.llorieruo.projects.dataObjects.facebook.FacebookApiErrorResponse;
import com.llorieruo.projects.exceptions.ApiRequestException;
import com.llorieruo.projects.exceptions.UnexpectedResponseException;
import com.llorieruo.projects.parsers.IParser;
import com.llorieruo.projects.parsers.JsonParser;
import com.ning.http.client.AsyncCompletionHandler;
import com.ning.http.client.Response;

public abstract class FacebookAbstractAsyncApiResponseHandler<V> extends AsyncCompletionHandler<V>
{
	private final IParser<InputStream, FacebookApiErrorResponse> ERROR_PARSER = new JsonParser<FacebookApiErrorResponse>(FacebookApiErrorResponse.class);
	
	@Override
	public V onCompleted(Response response) throws Exception
	{
		// Handle response
		if(response.getStatusCode() == HttpResponseStatus.BAD_REQUEST.getCode())
		{
			throw new ApiRequestException(ERROR_PARSER.parse(response.getResponseBodyAsStream()).getError());
		}
		if(response.getStatusCode() != HttpResponseStatus.OK.getCode())
		{
			throw new UnexpectedResponseException(response.getStatusText());
		}
		if("0".equals(response.getHeader(HttpHeaders.Names.CONTENT_LENGTH)))
		{
			throw new UnexpectedResponseException("The API server answered with an empty response.");
		}
		
		return parseEntityBody(response.getResponseBodyAsStream());
	}
	
	protected abstract V parseEntityBody(InputStream responseBody) throws IOException;
}
