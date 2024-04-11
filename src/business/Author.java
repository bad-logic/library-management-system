package business;

import java.io.Serializable;

final public class Author extends Person implements Serializable {
	private String bio;
	public String getBio() {
		return this.bio;
	}
	
	public Author(String f, String l, String t, Address a, String bio) {
		super(f, l, t, a);
		this.bio = bio;
	}

	private static final long serialVersionUID = 7508481940058530471L;

	@Override
	public boolean equals(Object obj) {
		if (obj.getClass() != this.getClass()){
			return false;
		}
		if(!super.equals(obj)){
			return false;
		}

		Author author = (Author) obj;
		return author.getBio().equals(this.getBio());
	}

	@Override
	public String toString(){
		return "Author: " + super.toString() + " Bio: " + this.getBio();
	}
}
