package com.llorieruo.projects.oauth2Login.exceptions;

public class TokenValidationException extends Exception
{
	private static final long	serialVersionUID	= 5087673968379198877L;

	public TokenValidationException(String message)
	{
		super(message);
	}
	
	public TokenValidationException(Throwable cause)
	{
		super(cause);
	}
	
	public TokenValidationException(String message, Throwable cause)
	{
		super(message, cause);
	}
}
