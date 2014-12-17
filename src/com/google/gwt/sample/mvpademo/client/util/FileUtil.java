package com.google.gwt.sample.mvpademo.client.util;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.sample.mvpademo.client.ClientFactoryImpl;
import com.google.gwt.sample.mvpademo.rpcobject.CSContact;
import com.google.gwt.sample.mvpademo.rpcobject.CSUser;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.googlecode.gwtphonegap.client.file.DirectoryEntry;
import com.googlecode.gwtphonegap.client.file.DirectoryReader;
import com.googlecode.gwtphonegap.client.file.EntryBase;
import com.googlecode.gwtphonegap.client.file.File;
import com.googlecode.gwtphonegap.client.file.FileCallback;
import com.googlecode.gwtphonegap.client.file.FileEntry;
import com.googlecode.gwtphonegap.client.file.FileError;
import com.googlecode.gwtphonegap.client.file.FileReader;
import com.googlecode.gwtphonegap.client.file.FileSystem;
import com.googlecode.gwtphonegap.client.file.FileWriter;
import com.googlecode.gwtphonegap.client.file.Flags;
import com.googlecode.gwtphonegap.client.file.ReaderCallback;
import com.googlecode.gwtphonegap.client.file.WriterCallback;
import com.googlecode.gwtphonegap.collection.shared.LightArray;

public class FileUtil {
	FileContent fileContent;
	ArrayList<CSContact> contactList; // Contact list of the phone
	ArrayList<CSContact> changedList; // List of contact change between stable
										// version on file and current contact
										// list on the phone
	ArrayList<CSContact> svContactList;
	
	public FileContent process1(File file, String fileName, String content,
			ArrayList<CSContact> contactList) {
		this.contactList = contactList;
		FileCallback1 fileCallback = new FileCallback1(file, fileName, content);
		// Access to file system
		file.requestFileSystem(FileSystem.LocalFileSystem_PERSISTENT, 0,
				fileCallback);
		return fileContent;
	}

	public class FileCallback1 implements FileCallback<FileSystem, FileError> {
		final String FILE_NAME;
		final String CONTENT;
		final File FILE;
		public String returned;

		public FileCallback1(File file, String fileName, String content) {
			FILE = file;
			CONTENT = content;
			FILE_NAME = fileName;
		}

		@Override
		public void onSuccess(FileSystem entry) {
			returned = "FileSystem OK";
			final DirectoryEntry root = entry.getRoot();
			DirectoryReader reader = root.createReader();
			FileCallback2 fileCallback = new FileCallback2(FILE, FILE_NAME,
					CONTENT, root);
			// Read file system
			reader.readEntries(fileCallback);
			returned = fileCallback.returned;
		}

		@Override
		public void onFailure(FileError error) {
			returned = "FileSystem ERROR";
		}
	}

	public class FileCallback2 implements
			FileCallback<LightArray<EntryBase>, FileError> {
		final String FILE_NAME;
		final String CONTENT;
		final File FILE;
		final DirectoryEntry ROOT;
		public String returned;

		public FileCallback2(File file, String fileName, String content,
				DirectoryEntry root) {
			FILE = file;
			CONTENT = content;
			FILE_NAME = fileName;
			ROOT = root;
		}

		@Override
		public void onSuccess(LightArray<EntryBase> entries) {
			EntryBase entryBase;
			FileEntry file = null;
			boolean isExist = false;
			String str = "";
			// Look for FILE_NAME
			for (int i = 0; i < entries.length(); i++) {
				entryBase = entries.get(i);
				if (entryBase.isFile()) {
					file = entryBase.getAsFileEntry();
					String name = file.getName();

					if (name.equalsIgnoreCase(FILE_NAME)) {
						str += "FILE:" + name + "\n";
						isExist = true;
						break;
					}
				}
			}
			ClientFactoryImpl.mainView.setText(ClientFactoryImpl.mainView
					.getText() + str);
			// If file not exist, create file. If file exist, read file
			if (!isExist) {
				FileCallback3 fileCallback = new FileCallback3(CONTENT);
				ROOT.getFile(FILE_NAME, new Flags(true, false), fileCallback);
			} else {
				FileCallback4 fileCallback = new FileCallback4(FILE);
				ROOT.getFile(FILE_NAME, new Flags(false, false), fileCallback);
			}
		}

		@Override
		public void onFailure(FileError error) {
			// TODO Auto-generated method stub
			returned = "Read Directory ERROR";
			ClientFactoryImpl.mainView.setText("Read Directory ERROR");
			// System.out.println("Read Directory ERROR");
		}
	}

	public class FileCallback3 implements FileCallback<FileEntry, FileError> {
		public String CONTENT;

		public FileCallback3(String content) {
			CONTENT = content;
		}

		@Override
		public void onSuccess(FileEntry entry) {

			// ////////////Write content into the file///////////////////////
			entry.createWriter(new FileCallback<FileWriter, FileError>() {

				@Override
				public void onSuccess(FileWriter writer) {
					writer.setOnWriteEndCallback(new WriterCallback<FileWriter>() {

						@Override
						public void onCallback(FileWriter result) {
						}
					});

					writer.setOnErrorCallback(new WriterCallback<FileWriter>() {

						@Override
						public void onCallback(FileWriter result) {
						}
					});
					writer.write(CONTENT);
				}

				@Override
				public void onFailure(FileError error) {
				}
			});
		}

		@Override
		public void onFailure(FileError error) {
		}
	}

	public class FileCallback4 implements FileCallback<FileEntry, FileError> {
		File FILE;
		FileEntry file;

		public FileCallback4(File file) {
			FILE = file;
		}

		@Override
		public void onSuccess(FileEntry entry) {
			FileReader reader = FILE.createReader();
			this.file = entry;
			reader.setOnloadCallback(new ReaderCallback<FileReader>() {

				@Override
				public void onCallback(FileReader result) {
					// ///// Read the file and parse content to object//////////
					String content = result.getResult();
					fileContent = FileParse.parse(content);
					ClientFactoryImpl.mainView
							.setText(ClientFactoryImpl.mainView.getText()
									+ "Version:" + fileContent.version + "\n");
					// //Version = 0, all are changes
					if (fileContent.version == 0) {
						if (changedList != null) {
							for (int i = 0; i < changedList.size(); i++) {
								changedList.get(i).setStatus(1);
							}
						}

					} else { // /Version > 0 changes are listed out by comparing
								// contact list in phone and in file
						changedList = CompareContactUtil.compare(contactList,
								fileContent.getContacts());

						ClientFactoryImpl.mainView
								.setText(ClientFactoryImpl.mainView.getText()
										+ "CHANGES\n");
						for (int i = 0; i < changedList.size(); i++) {
							CSContact contact = changedList.get(i);
							ClientFactoryImpl.mainView
									.setText(ClientFactoryImpl.mainView
											.getText()
											+ contact.getStatus()
											+ " " + contact.getName() + "\n");
						}
					}

					CSUser user = ClientFactoryImpl.user;

					// // Both phone and server in initial state
					if (fileContent.version == user.getVersion()
							&& changedList.size() == 0)
						return;
					// ///Server version = client version and there are changes in phone, up
					// all to server
					if (fileContent.version == user.getVersion()) {
						ClientFactoryImpl.mainView
								.setText(ClientFactoryImpl.mainView.getText()
										+ "Server VS = Client VS, Client changes\n");
						push(user.getEmailAddress());
						return;
					}

					// ////Server version is higher or changes in client, pull
					// everything to phone
					pull(user.getEmailAddress());
					return;
				}
			});

			reader.setOnErrorCallback(new ReaderCallback<FileReader>() {

				@Override
				public void onCallback(FileReader result) {
					ClientFactoryImpl.mainView
							.setText(ClientFactoryImpl.mainView.getText()
									+ "Error reading file" + "\n");
				}
			});

			reader.readAsText(entry);
		}

		@Override
		public void onFailure(FileError error) {

		}

		public void pull(String name) {
			ClientFactoryImpl.loginService.pull(name,
					new AsyncCallback<List<CSContact>>() {

						@Override
						public void onSuccess(List<CSContact> result) {
							ClientFactoryImpl.mainView
									.setText(ClientFactoryImpl.mainView
											.getText() + "Pull Success\n");
						}

						@Override
						public void onFailure(Throwable caught) {
							ClientFactoryImpl.mainView
									.setText(ClientFactoryImpl.mainView
											.getText() + "Pull Error\n");
						}
					});
		}

		public void push(String name) {
			CSUser user = new CSUser();
			user.setEmailAddress(name);
			user.setContacts(changedList);
			ClientFactoryImpl.loginService.push(user,
					new AsyncCallback<Integer>() {

						@Override
						public void onSuccess(Integer result) {
							ClientFactoryImpl.mainView
									.setText(ClientFactoryImpl.mainView
											.getText() + "Push success\n");
							CSUser user = ClientFactoryImpl.user;
							user.setVersion(ClientFactoryImpl.user.getVersion() + 1);
							ClientFactoryImpl.mainView
									.setText(ClientFactoryImpl.mainView.getText()
											+ "Write VS" + user.getVersion()
											+ "to file\n");
							write(file, CSConverter.toFileContent(
									user.getVersion(), contactList));
							return;
						}

						@Override
						public void onFailure(Throwable caught) {
							ClientFactoryImpl.mainView
									.setText(ClientFactoryImpl.mainView
											.getText() + "Push error\n");
						}
					});
			;
		}

		public void write(FileEntry file, String content) {
			final String CONTENT = content;
			file.createWriter(new FileCallback<FileWriter, FileError>() {

				@Override
				public void onSuccess(FileWriter writer) {
					writer.setOnWriteEndCallback(new WriterCallback<FileWriter>() {

						@Override
						public void onCallback(FileWriter result) {

						}
					});

					writer.setOnErrorCallback(new WriterCallback<FileWriter>() {

						@Override
						public void onCallback(FileWriter result) {

						}
					});
					writer.write(CONTENT);
				}

				@Override
				public void onFailure(FileError error) {
				}
			});
		}
	}
}
