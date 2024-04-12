package dataaccess;
import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import business.Author;
import business.Book;
import business.CheckoutRecord;
import business.LibraryMember;


public class DataAccessFacade implements DataAccess {
	
	enum StorageType {
		BOOKS, MEMBERS, USERS, CHECKOUTRECORDS, AUTHORS;
	}

	public static final String OUTPUT_DIR = String.join(
			File.separator,
			new String[]{System.getProperty("user.dir"),"src","dataaccess","storage"}
	);
	
	public static final String DATE_PATTERN = "MM/dd/yyyy";

	// ##### PRIVILEGED USERS
	@SuppressWarnings("unchecked")
	public HashMap<String, User> readUserMap() {
		//Returns a Map with name/value pairs being
		//   userId -> User
		return (HashMap<String, User>)readFromStorage(StorageType.USERS);
	}


	// ##### LIBRARY MEMBERS
	@SuppressWarnings("unchecked")
	public HashMap<String, LibraryMember> readMemberMap() {
		//Returns a Map with name/value pairs being
		//   memberId -> LibraryMember
		return (HashMap<String, LibraryMember>) readFromStorage(
				StorageType.MEMBERS);
	}

	@Override
	public void addMember(LibraryMember member) {
		HashMap<String, LibraryMember> mems = readMemberMap();
		String memberId = member.getMemberId();
		mems.put(memberId, member);
		saveToStorage(StorageType.MEMBERS, mems);
	}


	//  ##### BOOKS
	@SuppressWarnings("unchecked")
	public  HashMap<String,Book> readBooksMap() {
		//Returns a Map with name/value pairs being
		//   isbn -> Book
		return (HashMap<String, Book>) readFromStorage(StorageType.BOOKS);
	}

	@Override
	public void addBook(Book book) {
		HashMap<String, Book> books = readBooksMap();
		books.put(book.getIsbn(),book);
		saveToStorage(StorageType.BOOKS,books);
	}

	// AUTHORS
	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String,Author> readAuthorsMap() {
		//Returns a Map with name/value pairs being
		//   isbn -> Book
		return (HashMap<String, Author>) readFromStorage(StorageType.AUTHORS);
	}

	public void addAuthor(Author author) {
		//Returns a Map with name/value pairs being
		//   isbn -> Book
		HashMap<String, Author> authors = readAuthorsMap();
		authors.put(author.getAuthorId(),author);
		saveToStorage(StorageType.AUTHORS,authors);
	}

	//  ##### CHECKOUT RECORD
	@SuppressWarnings("unchecked")
	public HashMap<String, CheckoutRecord> readCheckoutRecordMap() {
		//Returns a Map with name/value pairs being
		//   memberId -> CheckoutRecord
		return (HashMap<String, CheckoutRecord>)readFromStorage(StorageType.CHECKOUTRECORDS);
	}

	@Override
	public void addCheckoutRecord(CheckoutRecord checkoutRecord) {
		HashMap<String, CheckoutRecord> books = readCheckoutRecordMap();
		books.put(checkoutRecord.getMemberId(),checkoutRecord);
		saveToStorage(StorageType.CHECKOUTRECORDS,books);
	}
	
	/////load methods - these place test data into the storage area
	///// - used just once at startup
		
	static void loadBookMap(List<Book> bookList) {
		HashMap<String, Book> books = new HashMap<String, Book>();
		bookList.forEach(book -> books.put(book.getIsbn(), book));
		saveToStorage(StorageType.BOOKS, books);
	}

	static void loadUserMap(List<User> userList) {
		HashMap<String, User> users = new HashMap<String, User>();
		userList.forEach(user -> users.put(user.getId(), user));
		saveToStorage(StorageType.USERS, users);
	}
 
	static void loadMemberMap(List<LibraryMember> memberList) {
		HashMap<String, LibraryMember> members = new HashMap<String, LibraryMember>();
		memberList.forEach(member -> members.put(member.getMemberId(), member));
		saveToStorage(StorageType.MEMBERS, members);
	}

	static void loadAuthorMap(List<Author> authorsList) {
		HashMap<String, Author> authors = new HashMap<String, Author>();
		authorsList.forEach(author -> authors.put(author.getAuthorId(), author));
		saveToStorage(StorageType.AUTHORS, authors);
	}

	static void loadCheckoutRecord(List<CheckoutRecord> recordList){
		HashMap<String, CheckoutRecord> records = new HashMap<String, CheckoutRecord>();
		recordList.forEach(record -> records.put(record.getMemberId(), record));
		saveToStorage(StorageType.CHECKOUTRECORDS, records);
	}

	static Object readFromStorage(StorageType type) {
		ObjectInputStream in = null;
		Object retVal = null;
		try {
			Path path = FileSystems.getDefault().getPath(OUTPUT_DIR, type.toString());
			in = new ObjectInputStream(Files.newInputStream(path));
			retVal = in.readObject();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(in != null) {
				try {
					in.close();
				} catch(Exception e) {}
			}
		}
		return retVal;
	}

	static void saveToStorage(StorageType type, Object ob) {
		ObjectOutputStream out = null;
		try {
			Path path = FileSystems.getDefault().getPath(OUTPUT_DIR, type.toString());
			out = new ObjectOutputStream(Files.newOutputStream(path));
			out.writeObject(ob);
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			if(out != null) {
				try {
					out.close();
				} catch(Exception e) {}
			}
		}
	}
	
//	final static class Pair<S,T> implements Serializable{
//
//		S first;
//		T second;
//		Pair(S s, T t) {
//			first = s;
//			second = t;
//		}
//		@Override
//		public boolean equals(Object ob) {
//			if(ob == null) return false;
//			if(this == ob) return true;
//			if(ob.getClass() != getClass()) return false;
//			@SuppressWarnings("unchecked")
//			Pair<S,T> p = (Pair<S,T>)ob;
//			return p.first.equals(first) && p.second.equals(second);
//		}
//
//		@Override
//		public int hashCode() {
//			return first.hashCode() + 5 * second.hashCode();
//		}
//		@Override
//		public String toString() {
//			return "(" + first.toString() + ", " + second.toString() + ")";
//		}
//		private static final long serialVersionUID = 5399827794066637059L;
//	}
	
}
