package com.llorieruo.projects.oauth2Login.handlers.asyncResponses;

import com.llorieruo.projects.oauth2Login.dataObjects.facebook.FacebookApiErrorResponse;
import com.llorieruo.projects.oauth2Login.parsers.JsonParser;

public class FactoryAsyncResponseHandler
{
	public static <V extends FacebookApiErrorResponse> FacebookAsyncApiResponseHandler<V> createFacebookAsyncHandler(Class<V> clazz)
	{
		// It's recommended to create a new instance for each call because com.ning.http.client.AsyncHandler aren't thread-safe.
		// See http://sonatype.github.io/async-http-client/apidocs/reference/com/ning/http/client/AsyncHandler.html
		return new FacebookAsyncApiResponseHandler<V>(clazz, new JsonParser<V>(clazz));
	}
}
