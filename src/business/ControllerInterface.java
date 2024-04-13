package business;

import java.util.HashMap;
import java.util.List;

public interface ControllerInterface {
	public void login(String id, String password) throws LoginException;
	public void logout();
	public void createAuthor(String firstName, String lastName, String contact, String bio,String street, String city,String state, int zipCode);
	public void createMember(String firstName, String lastName, String contact,String street, String city,String state, int zipCode);
	public void createBook(String isbn, String title, int maxCheckoutLength, int copyCount, List<String> authorId);
	public void createBookCopies(String isbn, int copyCount);
	public void addCheckoutRecord(String isbn,int memberId) throws LoginException;
	public List<Integer> allMemberIds();
	public List<String> allBookIds();
	public List<Book> allBooks();
	public HashMap<String,Book> booksHashMap();
	public List<Author> allAuthors();
	public CheckoutRecord getMembersCheckoutRecord(int memberId);
}
