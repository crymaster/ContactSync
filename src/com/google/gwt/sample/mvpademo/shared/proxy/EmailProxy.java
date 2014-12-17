package com.google.gwt.sample.mvpademo.shared.proxy;

import com.google.gwt.sample.mvpademo.server.domain.Email;
import com.google.gwt.sample.mvpademo.server.locator.EmailLocator;
import com.google.web.bindery.requestfactory.shared.ProxyFor;

@ProxyFor(value= Email.class, locator=EmailLocator.class)
public class EmailProxy {

}
