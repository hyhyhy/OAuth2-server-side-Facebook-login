package com.llorieruo.projects.providers;

import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.llorieruo.projects.dataObjects.facebook.FacebookError;
import com.llorieruo.projects.exceptions.ApiRequestException;
import com.llorieruo.projects.providers.codes.error.ErrorCodes;

public abstract class AbstractOAuth2 implements IOAuth2
{
	protected <T> T templateExecute(Future<T> future, Closeable closeable) throws ApiRequestException
	{
		try
		{
			return future.get(10000, TimeUnit.MILLISECONDS);
		}
		catch(InterruptedException | ExecutionException | TimeoutException e)
		{
			FacebookError error = new FacebookError();
			if(e instanceof ExecutionException)
			{
				Throwable cause = e.getCause();
				if(cause instanceof ApiRequestException)
					throw (ApiRequestException)cause;
				
				error.setMessage(cause.getMessage());
				error.setType(cause.getClass().getName());
			}
			else
			{
				error.setMessage(e.getMessage());
				error.setType(e.getClass().getName());
			}
			error.setCode(ErrorCodes.API_SERVICE.getCode());
			throw new ApiRequestException(error);
		}
		finally
		{
			if(closeable != null)
				try
				{
					closeable.close();
				}
				catch(IOException e) {}
		}
	}
}
