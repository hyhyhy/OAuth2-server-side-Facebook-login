package com.llorieruo.projects.exceptions;

import com.llorieruo.projects.dataObjects.facebook.FacebookError;

import java.io.IOException;


public class ApiRequestException extends IOException
{
	private static final long	serialVersionUID	= -6402292022213181879L;
	private FacebookError error;

	public ApiRequestException(FacebookError error)
	{
		this.error = error;
	}

	public FacebookError getError()
	{
		return error;
	}

	public void setError(FacebookError error)
	{
		this.error = error;
	}
}
