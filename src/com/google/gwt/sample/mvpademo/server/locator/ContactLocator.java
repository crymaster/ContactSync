package com.google.gwt.sample.mvpademo.server.locator;

import com.google.gwt.sample.mvpademo.server.domain.Contact;
import com.google.web.bindery.requestfactory.shared.Locator;
import com.googlecode.objectify.ObjectifyService;

public class ContactLocator extends Locator<Contact, String>{
	@Override
	public Contact create(Class<? extends Contact> clazz) {
		try{
			return clazz.newInstance();
		} catch(InstantiationException ex){
			throw new RuntimeException(ex);
		} catch(IllegalAccessException ex){
			throw new RuntimeException(ex);
		}
	}
	 
	@Override
	public Contact find(Class<? extends Contact> clazz, String id) {
		return ObjectifyService.ofy().load().type(clazz).id(id).get();
	}
	
	@Override
	public Class<Contact> getDomainType() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String getId(Contact domainObject) {
		return ObjectifyService.ofy().load().entity(domainObject).getKey().getName();
	}
	
	@Override
	public Class<String> getIdType() {
		return String.class;
	}

	@Override
	public Object getVersion(Contact domainObject) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
