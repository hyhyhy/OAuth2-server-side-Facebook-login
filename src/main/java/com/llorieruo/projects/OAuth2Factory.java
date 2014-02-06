package com.llorieruo.projects;

import java.util.HashMap;
import java.util.Map;

public final class OAuth2Factory
{
    private static final Map<String, OAuth2Factory> INSTANCES = new HashMap<String, OAuth2Factory>();
    public static OAuth2Factory getInstance(String provider)
    {
        return INSTANCES.get(provider);
    }
    public static void registerInstance(String provider, OAuth2Factory instance)
    {
        INSTANCES.put(provider, instance);
    }
}
