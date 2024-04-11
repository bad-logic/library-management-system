package business;

import java.io.Serializable;

/* Immutable */
final public class Address implements Serializable {
	
	private static final long serialVersionUID = -891229800414574888L;
	private String street;
	private String city;
	private String state;
	private String zip;

	public Address(String street, String city, String state, String zip) {
		this.street = street;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}
	
	public String getStreet() {
		return this.street;
	}
	public String getCity() {
		return this.city;
	}
	public String getState() {
		return this.state;
	}
	public String getZip() {
		return this.zip;
	}

	@Override
	public String toString() {
		return "(" + this.street + ", " + this.city + ", "  + this.state + ", " + this.zip + ")";
	}

	@Override
	public boolean equals(Object obj) {
		if (obj.getClass() != this.getClass()){
			return false;
		}
		Address address = (Address) obj;
		return address.getState().equals(this.getState())  &&
				address.getCity().equals(this.getCity()) &&
				address.getZip().equals(this.getZip()) &&
				address.getStreet().equals(this.getStreet());
	}
}
