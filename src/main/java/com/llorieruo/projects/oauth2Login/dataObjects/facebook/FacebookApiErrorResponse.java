package com.llorieruo.projects.oauth2Login.dataObjects.facebook;

public class FacebookApiErrorResponse
{
	private FacebookError error;

	public FacebookError getError()
	{
		return error;
	}

	public void setError(FacebookError error)
	{
		this.error = error;
	}
}
