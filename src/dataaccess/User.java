package dataaccess;

import java.io.Serializable;
import java.util.Arrays;

final public class User implements Serializable {
	
	private static final long serialVersionUID = 5147265048973262104L;

	private int id;
	
	private String password;
	private Auth[] authorizations = new Auth[2];
	User(int id, String pass, Auth[] auth) {
		this.id = id;
		this.password = pass;
		this.authorizations = auth;
	}
	
	public int getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}
	public Auth[] getAuthorization() {
		return authorizations;
	}
	@Override
	public String toString() {
		return "[" + id + ":" + password + ", " + Arrays.toString(authorizations) + "]";
	}
	
}
