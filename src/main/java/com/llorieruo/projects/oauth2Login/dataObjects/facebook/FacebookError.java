package com.llorieruo.projects.oauth2Login.dataObjects.facebook;

public class FacebookError
{
	private String	message, type;
	private int		code, error_subcode;

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public int getCode()
	{
		return code;
	}

	public void setCode(int code)
	{
		this.code = code;
	}

	public int getError_subcode()
	{
		return error_subcode;
	}

	public void setError_subcode(int error_subcode)
	{
		this.error_subcode = error_subcode;
	}
}
