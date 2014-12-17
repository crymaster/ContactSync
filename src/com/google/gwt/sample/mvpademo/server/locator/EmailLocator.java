package com.google.gwt.sample.mvpademo.server.locator;

import com.google.gwt.sample.mvpademo.server.domain.Email;
import com.google.web.bindery.requestfactory.shared.Locator;
import com.googlecode.objectify.ObjectifyService;

public class EmailLocator extends Locator<Email, Long>{
	@Override
	public Email create(Class<? extends Email> clazz) {
		try{
			return clazz.newInstance();
		} catch(InstantiationException ex){
			throw new RuntimeException(ex);
		} catch(IllegalAccessException ex){
			throw new RuntimeException(ex);
		}
	}
	
	@Override
	public Email find(Class<? extends Email> clazz, Long id) {
		return ObjectifyService.ofy().load().type(clazz).id(id).get();
	}
	
	@Override
	public Class<Email> getDomainType() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Long getId(Email domainObject) {
		return ObjectifyService.ofy().load().entity(domainObject).getKey().getId();
	}
	
	@Override
	public Class<Long> getIdType() {
		return Long.class;
	}

	@Override
	public Object getVersion(Email domainObject) {
		// TODO Auto-generated method stub
		return null;
	}
}
