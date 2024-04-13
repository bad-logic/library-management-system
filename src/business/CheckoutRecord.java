package business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CheckoutRecord implements Serializable{
    private static final long serialVersionUID = -4639760228084869815L;
    private List<CheckoutEntry> records;
    private LibraryMember member;

    public CheckoutRecord(LibraryMember mem, BookCopy copy){
        this.member = mem;
        records = new ArrayList<CheckoutEntry>();
        addRecord(copy);
    }

    public int getMemberId(){
        return member.getMemberId();
    }

    public void addRecord(BookCopy book){
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
