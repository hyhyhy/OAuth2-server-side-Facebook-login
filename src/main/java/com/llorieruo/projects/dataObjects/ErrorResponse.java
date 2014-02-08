package com.llorieruo.projects.dataObjects;

/**
 * Represents a generic error response received from the authorization server or the resource server.
 * @see http://tools.ietf.org/html/rfc6749#section-5.2
 * @see http://tools.ietf.org/html/rfc6749#section-7.2
 */
public class ErrorResponse
{
	private String	error, error_description, error_uri;

	public String getError()
	{
		return error;
	}
	public void setError(String error)
	{
		this.error = error;
	}
	public String getError_description()
	{
		return error_description;
	}
	public void setError_description(String error_description)
	{
		this.error_description = error_description;
	}
	public String getError_uri()
	{
		return error_uri;
	}
	public void setError_uri(String error_uri)
	{
		this.error_uri = error_uri;
	}
}
