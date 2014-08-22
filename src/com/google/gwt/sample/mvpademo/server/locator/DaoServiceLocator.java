package com.google.gwt.sample.mvpademo.server.locator;

import com.google.web.bindery.requestfactory.shared.ServiceLocator;

public class DaoServiceLocator implements ServiceLocator{
	@Override
	public Object getInstance(Class<?> clazz) {
		try {
			return clazz.newInstance();
		} catch (InstantiationException ex){
			throw new RuntimeException(ex);
		} catch (IllegalAccessException ex){
			throw new RuntimeException(ex);
		}
		
	}
}
