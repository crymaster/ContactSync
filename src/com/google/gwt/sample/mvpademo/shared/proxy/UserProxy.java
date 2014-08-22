package com.google.gwt.sample.mvpademo.shared.proxy;

import com.google.gwt.sample.mvpademo.domain.User;
import com.google.gwt.sample.mvpademo.server.locator.ObjectifyLocator;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;

@ProxyFor(value= User.class, locator=ObjectifyLocator.class)
public interface UserProxy extends EntityProxy{
	String getName();
	void setName(String name);
	void setPassword(String password);
}
