package com.google.gwt.sample.mvpademo.shared.service;

import com.google.web.bindery.requestfactory.shared.RequestFactory;

public interface UserRequestFactory extends RequestFactory{
	UserRequestContext userRequestContext();
} 