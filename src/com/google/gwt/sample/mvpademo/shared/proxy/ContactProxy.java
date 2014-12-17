package com.google.gwt.sample.mvpademo.shared.proxy;

import com.google.gwt.sample.mvpademo.server.domain.Contact;
import com.google.gwt.sample.mvpademo.server.locator.ContactLocator;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;

@ProxyFor(value= Contact.class, locator=ContactLocator.class)
public interface ContactProxy extends EntityProxy{
	public String getName();
	public void setName(String name);
	//public String getPhone();
	//public void setPhone(String phone);
}
