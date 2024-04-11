package business;

import java.io.Serializable;

public class Person implements Serializable {
	private static final long serialVersionUID = 3665880920647848288L;
	private String firstName;
	private String lastName;
	private String telephone;
	private Address address;
	public Person(String f, String l, String t, Address a) {
		firstName = f;
		lastName = l;
		telephone = t;
		address = a;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getTelephone() {
		return telephone;
	}
	public Address getAddress() {
		return address;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj.getClass() != this.getClass()){
			return false;
		}
		Person person = (Person) obj;
		return person.getFirstName().equals(this.getFirstName())  &&
				person.getLastName().equals(this.getLastName()) &&
				person.getTelephone().equals(this.getTelephone()) &&
				person.getAddress().equals(this.getAddress());
	}

	@Override
	public String toString(){
		return "Person( " + "name: " + this.getFirstName() + " " + this.getLastName() +
				", phone: " + this.getTelephone() + ", address: " + this.getAddress() + " )";
	}
}
