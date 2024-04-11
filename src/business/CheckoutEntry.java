package business;

import java.io.Serializable;
import java.time.LocalDate;

public class CheckoutEntry implements Serializable {
    private static final long serialVersionUID = -78463974084869815L;
    private Book book;
    private LocalDate dueDate;
    private LocalDate dateOfCheckout;
    double fine;

    CheckoutEntry(Book book) {
        this.book = book;
        this.dateOfCheckout = LocalDate.now();
        this.dueDate = this.dateOfCheckout.plusDays(book.getMaxCheckoutLength());
    }

    public void setFine(double fine){
        this.fine = fine;
    }

    public String toString(){
        return "CheckoutEntry: " + this.book.toString() +", Checkout Date:" + this.dateOfCheckout + ", Due Date: " + this.dueDate.toString() + ", Fine: " + this.fine;
    }

}
