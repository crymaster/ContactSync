package com.google.gwt.sample.mvpademo.shared.proxy;

import com.google.gwt.sample.mvpademo.server.domain.User;
import com.google.gwt.sample.mvpademo.server.locator.UserLocator;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;

@ProxyFor(value= User.class, locator=UserLocator.class)
public interface UserProxy extends EntityProxy{
	String getName();
	void setName(String name);
	void setPassword(String password);
}
