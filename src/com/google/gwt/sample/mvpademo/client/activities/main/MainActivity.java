package com.google.gwt.sample.mvpademo.client.activities.main;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.place.shared.Place;
import com.google.gwt.sample.mvpademo.client.ClientFactory;
import com.google.gwt.sample.mvpademo.client.activities.home.HomePlace;
import com.google.gwt.sample.mvpademo.client.activities.login.LoginPlace;
import com.google.gwt.sample.mvpademo.domain.Contact;
import com.google.gwt.sample.mvpademo.domain.User;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.web.bindery.event.shared.EventBus;
import com.googlecode.gwtphonegap.client.contacts.ContactError;
import com.googlecode.gwtphonegap.client.contacts.ContactFindCallback;
import com.googlecode.gwtphonegap.client.contacts.ContactFindOptions;
import com.googlecode.gwtphonegap.collection.shared.CollectionFactory;
import com.googlecode.gwtphonegap.collection.shared.LightArray;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.mvp.client.MGWTAbstractActivity;

public class MainActivity extends MGWTAbstractActivity{
	private ClientFactory clientFactory;
	private Place place;
	private ArrayList<Contact> contactList = new ArrayList<Contact>();
	
	public MainActivity(ClientFactory clientFactory, Place place) {
		super();
		this.clientFactory = clientFactory;
		this.place = place;
	}
	
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		MainView mainView = (MainViewImpl)clientFactory.getMainView();
		panel.setWidget(mainView);
		addHandlerRegistration(mainView.getBackButton().addTapHandler(new TapHandler() {
			
			@Override
			public void onTap(TapEvent event) {
				clientFactory.getLoginView().reset();
				clientFactory.getPlaceController().goTo(new LoginPlace());
			}
		}));
		LightArray<String> fields = CollectionFactory.<String> constructArray();
		fields.push("*");;
		ContactFindOptions findOptions = new ContactFindOptions("", true);

		clientFactory.getPhoneGap().getContacts().find(fields, new ContactFindCallback() {

		        @Override
		        public void onSuccess(LightArray<com.googlecode.gwtphonegap.client.contacts.Contact> contacts) {
		                //read contacts here..
		        	Contact contact;
		        	clientFactory.getMainView().setText(contacts.length()+"\n");
		        	for(int i=0; i<contacts.length(); i++){
		        		com.googlecode.gwtphonegap.client.contacts.Contact pContact = contacts.get(i);
		        		if(!pContact.getName().getGivenName().equals("undefined") && !pContact.getName().getGivenName().equals("") && pContact.getPhoneNumbers().length()>0 ){
		        			contact = new Contact();
		        			contact.setName(contacts.get(i).getName().getGivenName());
		        			contact.setPhone(contacts.get(i).getPhoneNumbers().get(0).getValue());
		        			contactList.add(contact);
		        		}
		        	}
		        	clientFactory.getUserService().addContact(clientFactory.getUser(), contactList, new AsyncCallback<Boolean>() {
		    			
		    			@Override
		    			public void onSuccess(Boolean result) {
		    				// TODO Auto-generated method stub
		    				if(result){
		    					clientFactory.getMainView().alert("Add contact successfully");
		    				}
		    				else 
		    					clientFactory.getMainView().alert("Add contact fail");
		    				clientFactory.getUserService().getContacts(clientFactory.getUser(), new AsyncCallback<ArrayList<Contact>>() {
	    						
	    						@Override
	    						public void onSuccess(ArrayList<Contact> result) {
	    							// TODO Auto-generated method stub
	    							com.googlecode.gwtphonegap.client.contacts.Contact contact;
	    							clientFactory.getMainView().alert("Result: "+result.size());
	    							for(int i = 0; i< result.size(); i++){
	    								contact = clientFactory.getPhoneGap().getContacts().create();
	    								contact.getPhoneNumbers().push(clientFactory.getPhoneGap().getContacts().getFactory().createContactField("home", result.get(i).getPhone(), true));
	    								contact.getName().setFamilyName(result.get(i).getName());
	    								contact.save();
	    							}
	    							
	    						}
	    						
	    						@Override
	    						public void onFailure(Throwable caught) {
	    							// TODO Auto-generated method stub
	    							clientFactory.getMainView().alert("Fail");
	    						}
	    					});
		    			}
		    			
		    			@Override
		    			public void onFailure(Throwable caught) {
		    				// TODO Auto-generated method stub
		    					
		    			}
		    		});
		        }

		        @Override
		        public void onFailure(ContactError error) {
		                //something went wrong, doh!
		        	clientFactory.getMainView().alert("Fail");
		        }
		}, findOptions);

	}
}
