package business;

import java.io.Serializable;

/**
 * Immutable class
 */
final public class BookCopy implements Serializable {
	
	private static final long serialVersionUID = -63976228084869815L;
	private Book book;
	private int copyNum;
	private boolean isAvailable;
	BookCopy(Book book, int copyNum, boolean isAvailable) {
		this.book = book;
		this.copyNum = copyNum;
		this.isAvailable = isAvailable;
	}
	
	BookCopy(Book book, int copyNum) {
		this.book = book;
		this.copyNum = copyNum;
	}
	
	
	public boolean isAvailable() {
		return this.isAvailable;
	}

	public int getCopyNum() {
		return this.copyNum;
	}
	
	public Book getBook() {
		return this.book;
	}
	
	public void changeAvailability() {
		this.isAvailable = !isAvailable;
	}
	
	@Override
	public boolean equals(Object ob) {
		if(ob == null) return false;
		if(!(ob instanceof BookCopy)) return false;
		BookCopy copy = (BookCopy)ob;
		return copy.book.getIsbn().equals(this.book.getIsbn()) && copy.copyNum == this.copyNum;
	}

	public String toString(){
		return "BookCopy: { Book " + this.book +", copy:" + this.copyNum + ", isAvailable: " + this.isAvailable;
	}
	
}
