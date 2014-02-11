package com.llorieruo.projects.oauth2Login.exceptions;

import java.io.IOException;

public class UnexpectedResponseException extends IOException
{
	private static final long	serialVersionUID	= 1521137085912787160L;

	public UnexpectedResponseException(String message)
	{
		super(message);
	}
	
	public UnexpectedResponseException(Throwable cause)
	{
		super(cause);
	}
	
	public UnexpectedResponseException(String message, Throwable cause)
	{
		super(message, cause);
	}
}
