package com.google.gwt.sample.mvpademo.server.locator;

import com.google.gwt.sample.mvpademo.server.domain.Phone;
import com.google.web.bindery.requestfactory.shared.Locator;
import com.googlecode.objectify.ObjectifyService;

public class PhoneLocator extends Locator<Phone, String>{
	@Override
	public Phone create(Class<? extends Phone> clazz) {
		try{
			return clazz.newInstance();
		} catch(InstantiationException ex){
			throw new RuntimeException(ex);
		} catch(IllegalAccessException ex){
			throw new RuntimeException(ex);
		}
	}
	
	@Override
	public Phone find(Class<? extends Phone> clazz, String id) {
		return ObjectifyService.ofy().load().type(clazz).id(id).get();
	}
	
	@Override
	public Class<Phone> getDomainType() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String getId(Phone domainObject) {
		return ObjectifyService.ofy().load().entity(domainObject).getKey().getName();
	}
	
	@Override
	public Class<String> getIdType() {
		return String.class;
	}

	@Override
	public Object getVersion(Phone domainObject) {
		// TODO Auto-generated method stub
		return null;
	}

}
