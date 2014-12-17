package com.google.gwt.sample.mvpademo.shared.proxy;

import com.google.gwt.sample.mvpademo.server.domain.Phone;
import com.google.gwt.sample.mvpademo.server.locator.PhoneLocator;
import com.google.web.bindery.requestfactory.shared.ProxyFor;

@ProxyFor(value= Phone.class, locator=PhoneLocator.class)
public class PhoneProxy {

}
