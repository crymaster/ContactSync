package com.google.gwt.sample.mvpademo.client.util;

import java.util.ArrayList;

import com.google.gwt.sample.mvpademo.client.ClientFactoryImpl;
import com.google.gwt.sample.mvpademo.rpcobject.CSContact;
import com.google.gwt.sample.mvpademo.rpcobject.CSPhone;

public class CompareContactUtil {
	public static ArrayList<CSContact> compare(ArrayList<CSContact> contacts1,
			ArrayList<CSContact> contacts2) {
		ArrayList<CSContact> c1 = new ArrayList<CSContact>();
		ArrayList<CSContact> c2 = new ArrayList<CSContact>();
		ArrayList<CSContact> changedList = new ArrayList<CSContact>();
		CSContact changed;
		CSContact contact1;
		CSContact contact2;
		ArrayList<CSPhone> phones1;
		ArrayList<CSPhone> phones2;
		CSPhone phone1;
		CSPhone phone2;
		boolean isNew;
		boolean isNewPhone;
		// Clone contacts1 and contacts2
		c1 = clone(contacts1);
		c2 = clone(contacts2);

		// Look for contact in c1 and not c2
		for (int i = 0; i < c1.size(); i++) {
			contact1 = c1.get(i);
			isNew = true;
			for (int j = 0; j < c2.size(); j++) {
				contact2 = c2.get(j);
				// Contact in c1 and c2
				if (contact1.getName().equalsIgnoreCase(contact2.getName())) {
					changed = new CSContact();
					changed.setName(contact1.getName());
					changed.setPhones(new ArrayList<CSPhone>());
					phones1 = (ArrayList<CSPhone>) contact1.getPhones();
					phones2 = (ArrayList<CSPhone>) contact2.getPhones();

					for (int k = 0; k < phones1.size(); k++) {
						phone1 = phones1.get(k);
						isNewPhone = true;
						for (int l = 0; l < phones2.size(); l++) {
							phone2 = phones2.get(l);
							if (phone1.getPhone().equals(phone2.getPhone())) {
								phones2.remove(l);
								isNewPhone = false;
								break;
							}
						}
						if (isNewPhone) {
							changed.setStatus(Status.UPDATE);
							phone1.setStatus(Status.NEW);
							ClientFactoryImpl.mainView
									.setText(ClientFactoryImpl.mainView
											.getText()
											+ phone1.getStatus()
											+ " " + phone1.getPhone() + "\n");
							changed.getPhones().add(phone1);
						}
					}
					for (int k = 0; k < phones2.size(); k++) {
						phone2 = phones2.get(k);
						if (phone2 != null) {
							changed.setStatus(Status.UPDATE);
							phone2.setStatus(Status.DELETE);
							ClientFactoryImpl.mainView
									.setText(ClientFactoryImpl.mainView
											.getText()
											+ phone2.getStatus()
											+ " " + phone2.getPhone() + "\n");
							changed.getPhones().add(phone2);
						}
					}
					if (changed.getPhones().size() > 0)
						changedList.add(changed);
					c2.remove(j);
					isNew = false;
					break;
				}
			}
			// Contact in c1, not in c2
			if (isNew) {
				contact1.setStatus(Status.NEW);
				changedList.add(contact1);
				// c1.remove(i);
				// i--;
			}
		}

		for (int i = 0; i < c2.size(); i++) {
			contact2 = c2.get(i);
			if (contact2 != null) {
				contact2.setStatus(Status.DELETE);
				changedList.add(contact2);
			}
		}

		return changedList;
	}

	public static ArrayList<CSContact> clone(ArrayList<CSContact> contacts) {
		ArrayList<CSContact> cloneContacts = new ArrayList<CSContact>();
		CSContact contact;
		CSContact cloneContact;
		ArrayList<CSPhone> phones;
		ArrayList<CSPhone> clonePhones;
		CSPhone phone;
		CSPhone clonePhone;
		for (int i = 0; i < contacts.size(); i++) {
			contact = contacts.get(i);
			cloneContact = new CSContact();
			cloneContact.setDisplayName(contact.getDisplayName());
			cloneContact.setNickname(contact.getNickname());
			cloneContact.setName(contact.getName());
			phones = (ArrayList<CSPhone>) contact.getPhones();
			clonePhones = new ArrayList<CSPhone>();
			for (int j = 0; j < phones.size(); j++) {
				phone = phones.get(j);
				clonePhone = new CSPhone();
				clonePhone.setPhone(phone.getPhone());
				clonePhone.setStatus(phone.getStatus());
				clonePhones.add(clonePhone);
			}
			cloneContact.setPhones(clonePhones);
			cloneContacts.add(cloneContact);
		}
		return cloneContacts;
	}

	public static void applyChange(ArrayList<CSContact> changes,
			ArrayList<CSContact> contacts) {
		CSContact contact1;
		CSContact contact2;
		ArrayList<CSPhone> phones1;
		ArrayList<CSPhone> phones2;
		CSPhone phone1;
		CSPhone phone2;
		boolean isNew;
		boolean isNewPhone;
		boolean isDeleted;
		boolean isDeletedPhone;
		boolean isUpdated;
		for (int i = 0; i < changes.size(); i++) {
			contact1 = changes.get(i);
			if (contact1.getStatus() == 1) {
				isNew = true;
				for (int j = 0; j < contacts.size(); j++) {
					contact2 = contacts.get(j);
					if (contact1.getName().equalsIgnoreCase(contact2.getName())) {
						// ClientFactoryImpl.mainView
						// .setText(ClientFactoryImpl.mainView.getText()
						// + contact1.getName() + " ");
						isNew = false;
						phones1 = (ArrayList<CSPhone>) contact1.getPhones();
						phones2 = (ArrayList<CSPhone>) contact2.getPhones();

						for (int k = 0; k < phones1.size(); k++) {
							phone1 = phones1.get(k);
							isNewPhone = true;
							for (int l = 0; l < phones2.size(); l++) {
								phone2 = phones2.get(l);
								if (phone1.getPhone().equals(phone2.getPhone())) {
									// ClientFactoryImpl.mainView
									// .setText(ClientFactoryImpl.mainView
									// .getText()
									// + "R "
									// + phone1.getPhone() + " ");
									phones1.remove(k);
									k--;
									contact1.setStatus(Status.UPDATE);
									isNewPhone = false;
									break;
								}
							}
							if (isNewPhone) {
								// ClientFactoryImpl.mainView
								// .setText(ClientFactoryImpl.mainView
								// .getText()
								// + "AP "
								// + phone1.getPhone() + " ");
								contact1.setStatus(Status.UPDATE);
								phone1.setStatus(Status.NEW);
								phones2.add(phone1);
							}
						}
						break;
					}
				}
				if (isNew) {
					// ClientFactoryImpl.mainView
					// .setText(ClientFactoryImpl.mainView.getText()
					// + "A " + contact1.getName() + " ");
					contacts.add(contact1);
				}
				if (contact1.getPhones().size() == 0) {
					changes.remove(contact1);
					i--;
					// ClientFactoryImpl.mainView
					// .setText(ClientFactoryImpl.mainView.getText()
					// + "RM");
				}
				// ClientFactoryImpl.mainView.setText(ClientFactoryImpl.mainView
				// .getText() + "\n");
			} else if (contact1.getStatus() == -1) {
				isDeleted = false;
				for (int j = 0; j < contacts.size(); j++) {
					contact2 = contacts.get(j);
					if (contact1.getName().equalsIgnoreCase(contact2.getName())) {
						isDeleted = true;
						phones1 = (ArrayList<CSPhone>) contact1.getPhones();
						phones2 = (ArrayList<CSPhone>) contact2.getPhones();

						for (int k = 0; k < phones1.size(); k++) {
							phone1 = phones1.get(k);
							isDeletedPhone = false;
							for (int l = 0; l < phones2.size(); l++) {
								phone2 = phones2.get(l);
								if (phone1.getPhone().equals(phone2.getPhone())) {
									contact1.setStatus(Status.UPDATE);
									phone1.setStatus(Status.DELETE);
									phones2.remove(l);
									isDeletedPhone = true;
									break;
								}
							}
							if (!isDeletedPhone) {
								contact1.setStatus(Status.UPDATE);
								phones1.remove(k);
								k--;
							}
						}
						if (phones2.size() == 0) {
							contact1.setStatus(Status.DELETE);
							contacts.remove(contact2);
						}
						break;
					}
				}
				if (!isDeleted) {
					changes.remove(contact1);
					i--;
				}
				if (contact1.getPhones().size() == 0) {
					changes.remove(contact1);
					i--;
				}
			} else if (contact1.getStatus() == 2) {
				isUpdated = false;
				for (int j = 0; j < contacts.size(); j++) {
					contact2 = contacts.get(j);
					if (contact1.getName().equalsIgnoreCase(contact2.getName())) {
						isUpdated = true;
						phones1 = (ArrayList<CSPhone>) contact1.getPhones();
						phones2 = (ArrayList<CSPhone>) contact2.getPhones();

						for (int k = 0; k < phones1.size(); k++) {
							phone1 = phones1.get(k);
							if (phone1.getStatus() == 1) {
								isNewPhone = true;
								for (int l = 0; l < phones2.size(); l++) {
									phone2 = phones2.get(l);
									if (phone1.getPhone().equals(
											phone2.getPhone())) {
										// ClientFactoryImpl.mainView
										// .setText(ClientFactoryImpl.mainView
										// .getText()
										// + "R "
										// + phone1.getPhone() + " ");
										phones1.remove(k);
										k--;
										isNewPhone = false;
										break;
									}
								}
								if (isNewPhone) {
									// ClientFactoryImpl.mainView
									// .setText(ClientFactoryImpl.mainView
									// .getText()
									// + "AP "
									// + phone1.getPhone() + " ");
									phones2.add(phone1);
								}
							} else {
								isDeletedPhone = false;
								for (int l = 0; l < phones2.size(); l++) {
									phone2 = phones2.get(l);
									if (phone1.getPhone().equals(
											phone2.getPhone())) {
										phones2.remove(l);
										isDeletedPhone = true;
										break;
									}
								}
								if (!isDeletedPhone) {
									phones1.remove(k);
									k--;
								}
							}
						}
						if (phones2.size() == 0) {
							contact1.setStatus(Status.DELETE);
							contacts.remove(contact2);
						}
						break;
					}
				}
				if (!isUpdated) {
					changes.remove(contact1);
					i--;
				}
				if (contact1.getPhones().size() == 0) {
					changes.remove(contact1);
					i--;
				}
			}
		}
	}
}
