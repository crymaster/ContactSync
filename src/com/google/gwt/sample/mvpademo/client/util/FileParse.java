package com.google.gwt.sample.mvpademo.client.util;

import java.util.ArrayList;

import com.google.gwt.sample.mvpademo.client.ClientFactoryImpl;
import com.google.gwt.sample.mvpademo.rpcobject.CSContact;
import com.google.gwt.sample.mvpademo.rpcobject.CSPhone;

public class FileParse {
	public static FileContent parse(String content) {
		FileContent fileContent = new FileContent();
		String[] contents = content.split("\n");
		// First line is version number
		ClientFactoryImpl.mainView.setText(ClientFactoryImpl.mainView.getText() + "First line:" + contents[0] + "\n");
		int version = Integer.parseInt(contents[0]);
		fileContent.version = version;
		if(version == 0)
			return fileContent;
		// Contact format: name \t displayName \t nickName \t phone1 \t phone2 \t ...
		String[] fields;
		CSContact contact;
		ArrayList<CSContact> contacts = new ArrayList<CSContact>();
		ArrayList<CSPhone> phones;
		CSPhone phone;
		for (int i = 1; i < contents.length; i++) {
			fields = contents[i].split("\t");
			contact = new CSContact();
			contact.setName(fields[0]);
//			ClientFactoryImpl.mainView.setText(ClientFactoryImpl.mainView.getText() + fields[0]+ " ");
			contact.setDisplayName(fields[1]);
//			ClientFactoryImpl.mainView.setText(ClientFactoryImpl.mainView.getText() + fields[1]+ " ");
			contact.setNickname(fields[2]);
//			ClientFactoryImpl.mainView.setText(ClientFactoryImpl.mainView.getText() + fields[2]+ " ");
			phones = new ArrayList<CSPhone>();
			for(int j = 3; j < fields.length; j++){
				phone = new CSPhone();
				phone.setPhone(fields[j]);
//				ClientFactoryImpl.mainView.setText(ClientFactoryImpl.mainView.getText() + fields[j]+ " ");
				phones.add(phone);
			}
			contact.setPhones(phones);
			contacts.add(contact);
		}
		fileContent.setContacts(contacts);
		
		return fileContent;
	}
}