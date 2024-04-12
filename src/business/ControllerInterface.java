package business;

import java.util.List;

public interface ControllerInterface {
	public void login(String id, String password) throws LoginException;
	public void createAuthor(String firstName, String lastName, String contact, String bio,String street, String city,String state, int zipCode);
	public void createMember(String firstName, String lastName, String contact,String street, String city,String state, int zipCode);
	public void createBook(String isbn, String title, int maxCheckoutLength, List<String> authorId);
	public void createBookCopies(String isbn, int copyCount);
	public void addCheckoutRecord(String isbn,String memberId);
	public List<String> allMemberIds();
	public List<String> allBookIds();
	public List<Author> allAuthors();
}
