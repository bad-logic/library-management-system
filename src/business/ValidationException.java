package business;

import javax.swing.*;
import java.io.Serializable;

public class ValidationException extends Exception implements Serializable {
    private static final long serialVersionUID = 7665004348398932420L;
    private JTextField field;

    public ValidationException() {
        super();
    }

    public ValidationException(String msg) {
        super(msg);

    }
    public ValidationException(String msg,JTextField field) {
        super(msg);
        this.field = field;
    }
    public ValidationException(Throwable t) {
        super(t);
    }

    public void setField(JTextField field){
        this.field = field;
    }
    public JTextField getField() {
        return this.field;
    }
}
