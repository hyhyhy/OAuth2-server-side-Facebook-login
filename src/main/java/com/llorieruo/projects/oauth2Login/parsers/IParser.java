package com.llorieruo.projects.oauth2Login.parsers;

import java.io.IOException;

public interface IParser<T, V>
{
	/**
	 * 
	 * @param stream The stream from which the values should be read.
	 * @return A new instance of the receive class filled with values from the parsed stream.
	 */
	public V parse(T stream) throws IOException;
}
