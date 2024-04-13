package business;

import java.io.Serializable;
import java.time.LocalDate;

public class CheckoutEntry implements Serializable {
    private static final long serialVersionUID = -78463974084869815L;
    private LocalDate dueDate;
    private LocalDate dateOfCheckout;
    private BookCopy bookCopy;
    double fine;

    CheckoutEntry(BookCopy book) {
        this.bookCopy = book;
        this.dateOfCheckout = LocalDate.now();
        this.dueDate = this.dateOfCheckout.plusDays(book.getBook().getMaxCheckoutLength());
    }

    public BookCopy getBookCopy() {
        return this.bookCopy;
    }

    public LocalDate   getDueDate(){
        return this.dueDate;
    }

    public LocalDate getDateOfCheckout() {
        return this.dateOfCheckout;
    }

    public void setFine(double fine){
        this.fine = fine;
    }

    public double getFine(){
        return this.fine;
    }

    @Override
    public String toString(){
        return "CheckoutEntry: " + this.bookCopy.toString() +", Checkout Date:" + this.dateOfCheckout + ", Due Date: " + this.dueDate.toString() + ", Fine: " + this.fine;
    }

}
