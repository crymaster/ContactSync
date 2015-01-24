package com.google.gwt.sample.mvpademo.client.activities.main;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.place.shared.Place;
import com.google.gwt.sample.mvpademo.client.ClientFactory;
import com.google.gwt.sample.mvpademo.client.ClientFactoryImpl;
import com.google.gwt.sample.mvpademo.client.activities.main.MainView.Content;
import com.google.gwt.sample.mvpademo.client.activities.main.MainView.Header;
import com.google.gwt.sample.mvpademo.client.util.CSConverter;
import com.google.gwt.sample.mvpademo.client.util.FileContent;
import com.google.gwt.sample.mvpademo.client.util.FileUtil;
import com.google.gwt.sample.mvpademo.rpcobject.CSContact;
import com.google.gwt.sample.mvpademo.rpcobject.CSEmail;
import com.google.gwt.sample.mvpademo.rpcobject.CSPhone;
import com.google.gwt.sample.mvpademo.rpcobject.CSUser;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.web.bindery.event.shared.EventBus;
import com.googlecode.gwtphonegap.client.contacts.Contact;
import com.googlecode.gwtphonegap.client.contacts.ContactError;
import com.googlecode.gwtphonegap.client.contacts.ContactFindCallback;
import com.googlecode.gwtphonegap.client.contacts.ContactFindOptions;
import com.googlecode.gwtphonegap.client.contacts.Contacts;
import com.googlecode.gwtphonegap.collection.shared.CollectionFactory;
import com.googlecode.gwtphonegap.collection.shared.LightArray;
import com.googlecode.mgwt.mvp.client.MGWTAbstractActivity;
import com.googlecode.mgwt.ui.client.widget.GroupingCellList.CellGroup;
import com.googlecode.mgwt.ui.client.widget.GroupingCellList.StandardCellGroup;

public class MainActivity extends MGWTAbstractActivity {
	private ClientFactory clientFactory;
	private Place place;
	private ArrayList<CSContact> contactList = new ArrayList<CSContact>(); // Contact
																			// list
																			// of
																			// the
																			// phone
	// private final String FILE_CONTENT =
	// "1\nson nguyen hoang\tson\thson\t1023456\t01234567\nTest\tb\tc\t123";
	private final String FILE_CONTENT = "0\n ";

	public MainActivity(ClientFactory clientFactory, Place place) {
		super();
		this.clientFactory = clientFactory;
		this.place = place;
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		MainView mainView = (MainViewImpl) clientFactory.getMainView();
		panel.setWidget(mainView);
		// addHandlerRegistration(mainView.getBackButton().addTapHandler(new
		// TapHandler() {
		//
		// @Override
		// public void onTap(TapEvent event) {
		// clientFactory.getLoginView().reset();
		// clientFactory.getPlaceController().goTo(new LoginPlace());
		// }
		// }));
		// fakeContactOnWeb();
		// ArrayList<CSContact> csContacts = (ArrayList<CSContact>) ContactUtil
		// .phoneContactToCSContact(clientFactory.getPhoneGap()
		// .getContacts(), "", true);
		// FileUtil fileUtil = new FileUtil();
		// fileUtil.write(clientFactory.getPhoneGap().getFile(),"contactsync.txt","version:0");

		CSUser user = new CSUser();
		user.setUsername("hoangson2@gmail.com");
		clientFactory.setUser(user);
		clientFactory.getClientService().login(user,
				new AsyncCallback<CSUser>() {

					@Override
					public void onSuccess(CSUser user) {
						clientFactory.getMainView().setText(
								"Login OK: " + user.getUsername()
										+ "\nVersion: " + user.getVersion());
						clientFactory.setUser(user);

						LightArray<String> fields = CollectionFactory
								.<String> constructArray();
						fields.push("*");
						// Get all contact in phone and convert to contactList
						ContactFindOptions findOptions = new ContactFindOptions(
								"", true);
						clientFactory.getPhoneGap().getContacts()
								.find(fields, new ContactFindCallback() {
									@Override
									public void onSuccess(
											LightArray<Contact> contacts) {
										String str = "";
										str += contacts.length() + "\n";
										CSContact csContact;
										Contact contact;
										// Convert to contactList
										for (int i = 0; i < contacts.length(); i++) {
											contact = contacts.get(i);
											if (!contact.getName()
													.getGivenName()
													.equals("undefined")
													&& !contact.getName()
															.getGivenName()
															.equals("")
													&& contact
															.getPhoneNumbers()
															.length() > 0) {
												csContact = CSConverter
														.toCSContact(contact);
												contactList.add(csContact);
											}
										}

										str += contactList.size() + "\n";
										// for (int i = 0; i <
										// contactList.size(); i++) {
										// csContact = contactList.get(i);
										// str += csContact.getGivenName();
										// CSPhone phone;
										// for (int j = 0; j <
										// csContact.getPhones().size();
										// j++) {
										// phone = csContact.getPhones().get(j);
										// str += " " + phone.getPhone();
										// }
										// str += "\n";
										// }
										clientFactory.getMainView()
												.setText(str);
										// clientFactory.getMainView().render(buildList(contactList));

										FileUtil fileUtil = new FileUtil();
										FileContent fileContent = fileUtil
												.process1(clientFactory
														.getPhoneGap()
														.getFile(),
														ClientFactoryImpl.user.getUsername()+".txt",
														FILE_CONTENT,
														contactList, contacts);

										// clientFactory.getMainView().alert(fileContent.getVersion()
										// + "");
									}

									@Override
									public void onFailure(ContactError error) {
										// something went wrong, doh!
										clientFactory.getMainView().alert(
												"Fail");
									}
								}, findOptions);
					}

					@Override
					public void onFailure(Throwable caught) {
						clientFactory.getMainView().setText("Login ERROR");
					}
				});

		// List contact to textbox

		// List contact to screen
		// mainView.getHeader().setText(
		// "Welcome " + clientFactory.getUser().getEmailAddress());
	}

	public void fakeContactOnWeb() {
		Contacts contacts = clientFactory.getPhoneGap().getContacts();
		Contact contact = contacts.create();
		contact.getPhoneNumbers().push(
				contacts.getFactory().createContactField("home", "012345 678",
						true));
		contact.getName().setFamilyName("Nguyen");
		contact.getName().setGivenName("Kich");
		contact.save();
		contact = contacts.create();
		contact.getPhoneNumbers().push(
				contacts.getFactory().createContactField("home", "012345 678",
						true));
		contact.getName().setFamilyName("Nguyen1");
		contact.getName().setGivenName("NT");
		contact.save();
		contact = contacts.create();
		contact.getPhoneNumbers().push(
				contacts.getFactory().createContactField("home", "012345 678",
						true));
		contact.getName().setFamilyName("Nguyen2");
		contact.getName().setGivenName("Ngu");
		contact.save();
		contact = contacts.create();
		contact.getPhoneNumbers().push(
				contacts.getFactory().createContactField("home", "012345 678",
						true));
		contact.getName().setFamilyName("Nguyen3");
		contact.getName().setGivenName("Dan");
		contact.save();
	}

	private List<CellGroup<Header, Content>> buildList(
			ArrayList<CSContact> contactList) {
		ArrayList<CellGroup<Header, Content>> list = new ArrayList<CellGroup<Header, Content>>();

		for (int i = 0; i < contactList.size(); i++) {
			CSContact contact = contactList.get(i);
			final Header header = new Header(contact.getName());
			final ArrayList<Content> arrayList = new ArrayList<Content>();

			// int max = (int) (Math.random() * 5);

			for (int j = 0; j < contact.getPhones().size(); j++) {
				arrayList.add(new Content(""
						+ contact.getPhones().get(j).getPhone()));
			}

			CellGroup<Header, Content> cellGroup = new StandardCellGroup<Header, Content>(
					header.getName(), header, arrayList);

			list.add(cellGroup);

		}

		return list;
	}
}