package business;

import java.io.Serializable;


final public class LibraryMember extends Person implements Serializable {
	private static final long serialVersionUID = -2226197306790714013L;
	private String memberId;
	private CheckoutRecord checkoutRecord;

	public LibraryMember(String memberId, String fname, String lname, String tel,Address add) {
		super(fname,lname, tel, add);
		this.memberId = memberId;
	}

	public String getMemberId() {
		return memberId;
	}

	public void addCheckoutRecord(Book book){
		if(this.checkoutRecord == null){
			this.checkoutRecord = new CheckoutRecord(this, book);
		}else{
			this.checkoutRecord.addRecord(book);
		}
	}

	@Override
	public String toString() {
		return "Member: " + "ID: " + memberId + ", name: " + getFirstName() + " " + getLastName() +
				", phone: " + getTelephone() + ", address: " + getAddress();
	}

}
