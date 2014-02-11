package com.llorieruo.projects.providers.proxy;

public abstract class AbstractProxy<T>
{
	/**
	* The purpose of this method is to lazy initialize an instance of the parametrized type.
	* When using a multi-thread application, classes implementing this method should use the Lazy initialization pattern with double locking mechanism.
	*
	* @return an instance of the class parameterized type.
	*/
	protected abstract T getInstance();
}
