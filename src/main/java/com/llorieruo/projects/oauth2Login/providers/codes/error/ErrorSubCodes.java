package com.llorieruo.projects.oauth2Login.providers.codes.error;

public enum ErrorSubCodes
{
	/**
	 * Subcode number 458.
	 */
	APP_NOT_INSTALLED(458),
	/**
	 * Subcode number 460.
	 */
	PASSWORD_CHANGED(460),
	/**
	 * Subcode number 463.
	 */
	EXPIRED(463),
	/**
	 * Subcode number 467.
	 */
	INVALID_ACCESS_TOKEN(467);
	
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
