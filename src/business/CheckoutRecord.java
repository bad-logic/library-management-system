package business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CheckoutRecord implements Serializable{
    private static final long serialVersionUID = -4639760228084869815L;
    private List<CheckoutEntry> records;
    private LibraryMember member;

    public CheckoutRecord(LibraryMember mem, Book book){
        this.member = mem;
        records = new ArrayList<CheckoutEntry>();
        addRecord(book);
    }

    public String getMemberId(){
        return member.getMemberId();
    }

    public void addRecord(Book book){
        records.add(new CheckoutEntry(book));
    }

    public List<CheckoutEntry> getRecords(){
        return this.records;
    }

    public String toString(){
        StringBuilder out = new StringBuilder();
        out.append("CheckoutRecord: ");
        out.append(this.member.toString());
        out.append("\n, records: [ " );
        for(CheckoutEntry entry : records){
            out.append(entry.toString());
            out.append(", ");
        }
        out.append(" ]");
        return out.toString();
    }
}
