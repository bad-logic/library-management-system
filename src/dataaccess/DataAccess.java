package dataaccess;

import java.util.HashMap;

import business.Author;
import business.Book;
import business.LibraryMember;

public interface DataAccess { 
	public HashMap<String,Book> readBooksMap();
	public HashMap<Integer,User> readUserMap();
	public HashMap<Integer, LibraryMember> readMemberMap();
	public HashMap<Integer,Author> readAuthorsMap();
	public void addMember(LibraryMember member);
	public void addBook(Book book);
	public void addAuthor(Author author);
}
