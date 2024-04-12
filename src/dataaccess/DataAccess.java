package dataaccess;

import java.util.HashMap;

import business.Author;
import business.Book;
import business.CheckoutRecord;
import business.LibraryMember;

public interface DataAccess { 
	public HashMap<String,Book> readBooksMap();
	public HashMap<String,User> readUserMap();
	public HashMap<String, LibraryMember> readMemberMap();
	public HashMap<String, CheckoutRecord> readCheckoutRecordMap();
	public HashMap<String,Author> readAuthorsMap();
	public void addMember(LibraryMember member);
	public void addBook(Book book);
	public void addCheckoutRecord(CheckoutRecord checkoutRecord);
	public void addAuthor(Author author);
}
