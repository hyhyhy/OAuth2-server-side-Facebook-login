package com.llorieruo.projects.oauth2Login;

import java.util.HashMap;
import java.util.Map;

import com.llorieruo.projects.oauth2Login.providers.IOAuth2;

public final class OAuth2Factory
{
	private static final Map<String, IOAuth2> INSTANCES = new HashMap<String, IOAuth2>();
	
	public static IOAuth2 getInstance(String provider)
	{
		return INSTANCES.get(provider);
	}
	
	public static void registerInstance(String provider, IOAuth2 instance)
	{
		INSTANCES.put(provider, instance);
	}
}
