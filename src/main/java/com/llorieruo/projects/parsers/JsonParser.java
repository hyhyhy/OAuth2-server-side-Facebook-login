package com.llorieruo.projects.parsers;

import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonParser<V> implements IParser<InputStream, V>
{
	private final ObjectMapper MAPPER;
	private final Class<V> CLAZZ;
	
	public JsonParser(Class<V> clazz)
	{
		this.MAPPER = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		this.CLAZZ = clazz;
	}
	
	@Override
	public V parse(InputStream stream) throws IOException
	{
		return this.MAPPER.readValue(stream, this.CLAZZ);
	}
}
