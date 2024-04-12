package business;

import java.io.Serializable;
import java.util.UUID;


final public class LibraryMember extends Person implements Serializable {
	private static final long serialVersionUID = -2226197306790714013L;
	private String memberId;
	private CheckoutRecord checkoutRecord;

	public LibraryMember(String fname, String lname, String tel,Address add) {
		super(fname,lname, tel, add);
		this.memberId = UUID.randomUUID().toString();
	}

	public String getMemberId() {
		return memberId;
	}

	public CheckoutRecord addCheckoutRecord(Book book){
		if(this.checkoutRecord == null){
			this.checkoutRecord = new CheckoutRecord(this, book);
		}else{
			this.checkoutRecord.addRecord(book);
		}
		return this.checkoutRecord;
	}
	
	@Override
	public String toString() {
		return "Member: " + "ID: " + this.memberId + ", name: " + this.getFirstName() + " " + this.getLastName() +
				", phone: " + this.getTelephone() + ", address: " + this.getAddress();
	}
	
	

}
