package business;

import java.io.Serializable;

import dataaccess.StorageId;


final public class LibraryMember extends Person implements Serializable {
	private static final long serialVersionUID = -2226197306790714013L;
	private int memberId;
	private CheckoutRecord checkoutRecord;

	public LibraryMember(int id, String fname, String lname, String tel,Address add) {
		super(fname,lname, tel, add);
		this.memberId = id;
	}

	public LibraryMember(String fname, String lname, String tel,Address add) {
		this(StorageId.getMemberID(),fname,lname, tel, add);
	}

	public int getMemberId() {
		return memberId;
	}

	public void addCheckoutRecord(Book book){
		if(this.checkoutRecord == null){
			this.checkoutRecord = new CheckoutRecord(this, book);
		}else{
			this.checkoutRecord.addRecord(book);
		}
	}

	public CheckoutRecord getCheckoutRecord() {
		return this.checkoutRecord;
	}
	
	@Override
	public String toString() {
		return "Member: " + "ID: " + this.memberId + ", name: " + this.getFirstName() + " " + this.getLastName() +
				", phone: " + this.getTelephone() + ", address: " + this.getAddress();
	}
	
	

}
