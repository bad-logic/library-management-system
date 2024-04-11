package dataaccess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import business.*;

/**
 * This class loads data into the data repository and also
 * sets up the storage units that are used in the application.
 * The main method in this class must be run once (and only
 * once) before the rest of the application can work properly.
 * It will create three serialized objects in the dataaccess.storage
 * folder.
 * 
 *
 */
public class Seeder {

	private Seeder(){}

	private static List<Address> createAddresses(){
		return new ArrayList<Address>() {
			{
				add(new Address("101 S. Main", "Fairfield", "IA", "52556"));
				add(new Address("51 S. George", "Georgetown", "MI", "65434"));
				add(new Address("23 Headley Ave", "Seville", "Georgia", "41234"));
				add(new Address("1 N. Baton", "Baton Rouge", "LA", "33556"));
				add(new Address("5001 Venice Dr.", "Los Angeles", "CA", "93736"));
				add(new Address("1435 Channing Ave", "Palo Alto", "CA", "94301"));
				add(new Address("42 Dogwood Dr.", "Fairfield", "IA", "52556"));
				add(new Address("501 Central", "Mountain View", "CA", "94707"));
			}
		};
	};

	private static List<Author> createAuthors(List<Address> addresses){
		 return new ArrayList<Author>() {
			{
				add(new Author("Joe", "Thomas", "641-445-2123", addresses.get(0), "A happy man is he."));
				add(new Author("Sandra", "Thomas", "641-445-2123", addresses.get(0), "A happy wife is she."));
				add(new Author("Nirmal", "Pugh", "641-919-3223", addresses.get(1), "Thinker of thoughts."));
				add(new Author("Andrew", "Cleveland", "976-445-2232", addresses.get(2), "Author of childrens' books."));
				add(new Author("Sarah", "Connor", "123-422-2663", addresses.get(3), "Known for her clever style."));
			}
		};
	}

	private static ArrayList<User> createUsers(){
		return new ArrayList<User>() {
			{
				add(new User("101", "Librarian@101", new Auth[] {Auth.LIBRARIAN}));
				add(new User("102", "Admin@102", new Auth[] {Auth.ADMIN}));
				add(new User("103", "SuperAdmin@103", new Auth[] {Auth.LIBRARIAN,Auth.ADMIN}));
			}
		};
	}

	private static List<LibraryMember> createMembers(List<Address> addresses){
		List<LibraryMember> members = new ArrayList<LibraryMember>();
		LibraryMember libraryMember = new LibraryMember("1001", "Andy", "Rogers", "641-223-2211", addresses.get(4));
		members.add(libraryMember);
		libraryMember = new LibraryMember("1002", "Drew", "Stevens", "702-998-2414", addresses.get(5));
		members.add(libraryMember);

		libraryMember = new LibraryMember("1003", "Sarah", "Eagleton", "451-234-8811", addresses.get(6));
		members.add(libraryMember);

		libraryMember = new LibraryMember("1004", "Ricardo", "Montalbahn", "641-472-2871", addresses.get(7));
		members.add(libraryMember);
		return members;
	}

	private static ArrayList<Book> createBooks( List<Author> authors){
		ArrayList<Book> allBooks = new ArrayList<Book>() {
			{
				add(new Book("23-11451", "The Big Fish", 21, Arrays.asList(authors.get(0), authors.get(1))));
				add(new Book("28-12331", "Antartica", 7, Arrays.asList(authors.get(2))));
				add(new Book("99-22223", "Thinking Java", 21, Arrays.asList(authors.get(3))));
				add(new Book("48-56882", "Jimmy's First Day of School", 7, Arrays.asList(authors.get(4))));
			}
		};
		// add copies
		allBooks.get(0).addCopy();
		allBooks.get(0).addCopy();
		allBooks.get(1).addCopy();
		allBooks.get(3).addCopy();
		allBooks.get(2).addCopy();
		allBooks.get(2).addCopy();
		return allBooks;
	}

	private static ArrayList<CheckoutRecord> createCheckoutRecord(List<LibraryMember> members, List<Book> books){
		ArrayList<CheckoutRecord> checkoutRecord = new ArrayList<CheckoutRecord>();
		checkoutRecord.add(new CheckoutRecord(members.get(0),books.get(0)));
		checkoutRecord.add(new CheckoutRecord(members.get(1),books.get(1)));
		checkoutRecord.getFirst().addRecord(books.get(3));
		return checkoutRecord;
	}

	public static void seed(){
		List<Address> addresses = createAddresses();
		List<LibraryMember> members = createMembers(addresses);
		List<User> users = createUsers();
		List<Author> authors = createAuthors(addresses);
		List<Book> books = createBooks(authors);
		List<CheckoutRecord> checkoutRecords = createCheckoutRecord(members,books);

		DataAccessFacade.loadUserMap(users);
		DataAccessFacade.loadMemberMap(members);
		DataAccessFacade.loadBookMap(books);
		DataAccessFacade.loadCheckoutRecord(checkoutRecords);
	}

	
	public static void main(String[] args) {
		Seeder.seed();
		DataAccess da = new DataAccessFacade();
		System.out.println(da.readBooksMap());
		System.out.println(da.readUserMap());
		System.out.println(da.readCheckoutRecordMap());
	}
}
