package com.llorieruo.projects.oauth2Login.providers.codes.error;

public enum ErrorCodes
{
	/**
	 * Code number 2.
	 */
	API_SERVICE(2),
	/**
	 * Code number 102.
	 */
	API_SESSION(102),
	/**
	 * Code number 190.
	 */
	OAUTH(190);
	
	private int code;
	
	private ErrorCodes(int code)
	{
		this.code = code;
	}
	
	public int getCode()
	{
		return this.code;
	}
}
