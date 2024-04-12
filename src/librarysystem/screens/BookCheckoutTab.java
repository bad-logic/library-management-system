package librarysystem.screens;

import javax.swing.*;
import java.awt.*;

public class BookCheckoutTab extends JPanel {

    BookCheckoutTab(){
        this.init();
    }

    void init(){

        JLabel memberMainTitle = new JLabel("Book Checkout Tab");
        memberMainTitle.setFont(new Font("Tahoma", Font.BOLD, 40));
        this.add(memberMainTitle);

    }
}
