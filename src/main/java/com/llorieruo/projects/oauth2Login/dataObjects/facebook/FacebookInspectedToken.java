package com.llorieruo.projects.oauth2Login.dataObjects.facebook;

public class FacebookInspectedToken extends FacebookApiErrorResponse
{
	private Data	data;
	
	public Data getData()
	{
		return data;
	}

	public void setData(Data data)
	{
		this.data = data;
	}
	
	public class Data
	{
		private String	app_id, user_id;
		private String[] scopes;
		private boolean is_valid;
		
		public String getApp_id()
		{
			return app_id;
		}
		public void setApp_id(String app_id)
		{
			this.app_id = app_id;
		}
		public String getUser_id()
		{
			return user_id;
		}
		public void setUser_id(String user_id)
		{
			this.user_id = user_id;
		}
		public String[] getScopes()
		{
			return scopes;
		}
		public void setScopes(String[] scopes)
		{
			this.scopes = scopes;
		}
		public boolean isIs_valid()
		{
			return is_valid;
		}
		public void setIs_valid(boolean is_valid)
		{
			this.is_valid = is_valid;
		}
	}
}
