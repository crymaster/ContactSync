package com.google.gwt.sample.mvpademo.shared.service;

import com.google.gwt.sample.mvpademo.server.locator.DaoServiceLocator;
import com.google.gwt.sample.mvpademo.server.service.UserDao;
import com.google.gwt.sample.mvpademo.shared.proxy.UserProxy;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;

@Service(value=UserDao.class, locator=DaoServiceLocator.class)
public interface UserRequestContext extends RequestContext{
	Request<String>	 addUser(UserProxy user);
} 