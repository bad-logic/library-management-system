package business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import dataaccess.Auth;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import dataaccess.User;


public class SystemController implements ControllerInterface {
	public static Auth[] currentAuth = null;
	
	public static void main(String[] args) {
//		System.out.println(allMember());
	}

	@Override
	public void login(String id, String password) throws LoginException {
		DataAccess da = new DataAccessFacade();
		HashMap<Integer, User> map = da.readUserMap();
		Integer key;
		try{
			key = Integer.parseInt(id);
		}catch (Exception ex){
			throw new LoginException("ID " + id + " not found");
		}

		if(!map.containsKey(key)) {
			throw new LoginException("ID " + id + " not found");
		}
		String passwordFound = map.get(key).getPassword();
		if(!passwordFound.equals(password)) {
			throw new LoginException("Password incorrect");
		}
		currentAuth = map.get(key).getAuthorization();
	}

	@Override
	public void logout() {
		currentAuth = null;
	}
	
	@Override
	public List<Integer> allMemberIds() {
		DataAccess da = new DataAccessFacade();
		List<Integer> retval = new ArrayList<>();
		retval.addAll(da.readMemberMap().keySet());
		return retval;
	}
	
	public List<LibraryMember> allMember() {
		DataAccess da = new DataAccessFacade();
		List<LibraryMember> memberList = new ArrayList<>();
		Iterator<LibraryMember> it= da.readMemberMap().values().iterator();
		while(it.hasNext()) {
			memberList.add(it.next());
		}
		
		return memberList;
	}
	
	@Override
	public List<String> allBookIds() {
		DataAccess da = new DataAccessFacade();
		List<String> retval = new ArrayList<>();
		retval.addAll(da.readBooksMap().keySet());
		return retval;
	}

	@Override
	public List<Book> allBooks() {
		DataAccess da = new DataAccessFacade();
		List<Book> booksList = new ArrayList<>();
		Iterator<Book> it= da.readBooksMap().values().iterator();
		while(it.hasNext()) {
			booksList.add(it.next());
		}

		return booksList;
	}

	@Override
	public HashMap<String,Book> booksHashMap() {
		DataAccess da = new DataAccessFacade();
		return da.readBooksMap();
	}
	
	public List<Book> allBook() {
		DataAccess da = new DataAccessFacade();
		List<Book> bookList = new ArrayList<>();
		Iterator<Book> it= da.readBooksMap().values().iterator();
		while(it.hasNext()) {
			bookList.add(it.next());
		}
		return bookList;
	}
	
	@Override
	public List<Author> allAuthors() {
		List<Author> ath = new ArrayList<Author>();
		Iterator<Author> it = new DataAccessFacade().readAuthorsMap().values().iterator();
		while(it.hasNext()) {
			ath.add(it.next());
		}
		return ath;
	}

	@Override
	public CheckoutRecord getMembersCheckoutRecord(int memberId) {
		LibraryMember rec = new DataAccessFacade().readMemberMap().get(memberId);
		return rec.getCheckoutRecord();
	}

	@Override
	public void createAuthor(String firstName, String lastName, String contact, String bio,String street, String city,String state, int zipCode) {
		DataAccess da = new DataAccessFacade();
		Address add = new Address(street,city,state, Integer.toString(zipCode));
		Author au = new Author(firstName, lastName,contact, add, bio);
		da.addAuthor(au);
	}
	
	@Override
	public void createMember(String firstName, String lastName, String contact,String street, String city,String state, int zipCode) {
		DataAccess da = new DataAccessFacade();
		Address add = new Address(street,city,state, Integer.toString(zipCode));
		LibraryMember mem = new LibraryMember(firstName, lastName, contact, add);
		da.addMember(mem);
	}
	
	@Override
	public void createBook(String isbn, String title, int maxCheckoutLength, int copyCount, List<String> authorId) {
		DataAccess da = new DataAccessFacade();
		List<Author> aths = new ArrayList<>();
		HashMap<Integer,Author> aMap = da.readAuthorsMap();
		for(String id: authorId) {
			if(aMap.containsKey(id)) {
				aths.add(aMap.get(id));
			}
		}
		
		Book book = new Book(isbn, title,maxCheckoutLength, copyCount, aths);
		da.addBook(book);
	}
	
	@Override
	public void createBookCopies(String isbn, int copyCount) {
		DataAccess da = new DataAccessFacade();
		HashMap<String,Book> mp = da.readBooksMap();
		if(mp.containsKey(isbn)){
			Book book = mp.get(isbn);
			book.addCopy(copyCount);			
			da.addBook(book);
		}
		
	}
	
	@Override
	public void addCheckoutRecord(String isbn,int memberId) {
		DataAccess da = new DataAccessFacade();
		HashMap<Integer, LibraryMember> mem = da.readMemberMap();
		HashMap<String,Book> books = da.readBooksMap();
		if(mem.containsKey(memberId) && books.containsKey(isbn)) {
			Book book = books.get(isbn);
			// set isAvailable false to one of the copies of the book
			int count = 0;
			for (BookCopy b : book.getCopies()){
				if(count>0) return;
				if(b.isAvailable()){
					b.changeAvailability();
					count++;
				}
			}

			LibraryMember memb = mem.get(memberId);
			memb.addCheckoutRecord(book);

			// update the book
			da.addBook(book);
			// update the member with checkout record
			da.addMember(memb);
		}
	}
	
}
