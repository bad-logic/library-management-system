package dataaccess;

import java.util.HashMap;

import business.Book;
import business.CheckoutRecord;
import business.LibraryMember;

public interface DataAccess { 
	public HashMap<String,Book> readBooksMap();
	public HashMap<String,User> readUserMap();
	public HashMap<String, LibraryMember> readMemberMap();
	public HashMap<String, CheckoutRecord> readCheckoutRecordMap();
	public void saveNewMember(LibraryMember member);
	public void saveNewBook(Book book);
	public void saveCheckoutRecord(CheckoutRecord checkoutRecord);
}
