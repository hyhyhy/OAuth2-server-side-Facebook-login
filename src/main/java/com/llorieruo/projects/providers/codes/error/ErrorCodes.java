package com.llorieruo.projects.providers.codes.error;

public enum ErrorCodes
{
	API_SERVICE(2), API_SESSION(102), OAUTH(190);
	
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
