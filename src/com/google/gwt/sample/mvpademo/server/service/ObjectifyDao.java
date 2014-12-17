package com.google.gwt.sample.mvpademo.server.service;

import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.PreparedQuery.TooManyResultsException;
import com.google.gwt.sample.mvpademo.server.domain.Contact;
import com.google.gwt.sample.mvpademo.server.domain.User;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.cmd.Query;

import static com.googlecode.objectify.ObjectifyService.ofy;

public class ObjectifyDao<T> {
	static final int BAD_MODIFIERS = Modifier.FINAL | Modifier.STATIC | Modifier.TRANSIENT;
	
	static {
		ObjectifyService.register(User.class);
		ObjectifyService.register(Contact.class);
	}
	
	protected Class<T> clazz;
	
	HttpServletRequest req;
	
	public ObjectifyDao() {
		Type genericSuperclass = getClass().getGenericSuperclass();
		if(genericSuperclass instanceof ParameterizedType){
			clazz = (Class<T>) ((ParameterizedType)genericSuperclass).getActualTypeArguments()[0];
		}
	}
	
	public Key<T> put(T entity){
		return ofy().save().entity(entity).now();
	}
	
	public Map<Key<T>,T> putAll(Iterable<T> entities){
		return ofy().save().entities(entities).now();
	}
	
	public void delete(T entity){
		ofy().delete().entity(entity);
	}
	
	public void deleteKey(Key<T> entityKey){
		ofy().delete().key(entityKey);
	}
	
	 public void deleteAll(Iterable<T> entities){
		 ofy().delete().entities(entities);
     }

     public void deleteKeys(Iterable<Key<T>> keys){
         ofy().delete().keys(keys);
     }

     public T get(Long id) throws EntityNotFoundException{
             return ofy().load().type(this.clazz).id(id).get();
     }

     public T get(Key<T> key) throws EntityNotFoundException{
             return ofy().load().key(key).get();
     }

     public Map<Key<T>, T> get(Iterable<Key<T>> keys){
             return ofy().load().keys(keys);
     }

     public List<T> listAll(){
             Query<T> q = ofy().load().type(clazz);
             return q.list();
     }

     public T getByProperty(String propName, Object propValue) throws TooManyResultsException{
	     Query<T> q = ofy().load().type(clazz);
	     q.filter(propName, propValue);
	     Iterator<T> fetch = q.limit(2).list().iterator();
	     if (!fetch.hasNext()){
	             return null;
	     }
	     T obj = fetch.next();
	     if (fetch.hasNext()){
	             throw new TooManyResultsException();
	     }
	     return obj;
	}
	
	public List<T> listByProperty(String propName, Object propValue){
	     Query<T> q = ofy().load().type(clazz);
	     q.filter(propName, propValue);
	     return q.list();
	}

}
