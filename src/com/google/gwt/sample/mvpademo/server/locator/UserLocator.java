package com.google.gwt.sample.mvpademo.server.locator;

import com.google.gwt.sample.mvpademo.server.domain.User;
import com.google.web.bindery.requestfactory.shared.Locator;
import com.googlecode.objectify.ObjectifyService;

public class UserLocator extends Locator<User, String>{
	@Override
	public User create(Class<? extends User> clazz) {
		try{
			return clazz.newInstance();
		} catch(InstantiationException ex){
			throw new RuntimeException(ex);
		} catch(IllegalAccessException ex){
			throw new RuntimeException(ex);
		}
	}
	
	@Override
	public User find(Class<? extends User> clazz, String id) {
		return ObjectifyService.ofy().load().type(clazz).id(id).get();
	}
	
	@Override
	public Class<User> getDomainType() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String getId(User domainObject) {
		return ObjectifyService.ofy().load().entity(domainObject).getKey().getName();
	}
	
	@Override
	public Class<String> getIdType() {
		return String.class;
	}

	@Override
	public Object getVersion(User domainObject) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
