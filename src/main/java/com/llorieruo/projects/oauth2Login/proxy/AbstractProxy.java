package com.llorieruo.projects.oauth2Login.proxy;

/**
 * The purpose of this method is to lazy initialize an instance of the parametrized type.
 *
 * @param <T>
 */
public abstract class AbstractProxy<T>
{
	/**
	 * When using a multi-thread application, classes implementing this method should use the Lazy initialization pattern with double locking mechanism.
	 *
	 * @return an instance of the class parameterized type.
	 */
	protected abstract T getInstance();
}
