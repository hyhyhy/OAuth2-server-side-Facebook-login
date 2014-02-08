package com.llorieruo.projects.providers.codes.error;

public enum ErrorSubCodes
{
	APP_NOT_INSTALLED(458), PASSWORD_CHANGED(460), EXPIRED(463), INVALID_ACCESS_TOKEN(467);
	
	private int subCode;
	
	private ErrorSubCodes(int subCode)
	{
		this.subCode = subCode;
	}
	
	public int getSubCode()
	{
		return this.subCode;
	}
}
