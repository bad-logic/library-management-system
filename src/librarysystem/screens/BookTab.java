package librarysystem.screens;

import javax.swing.*;
import java.awt.*;

public class BookTab extends JPanel {

    BookTab(){
        this.init();
    }

    void init(){

        JLabel memberMainTitle = new JLabel("Book Tab");
        memberMainTitle.setFont(new Font("Tahoma", Font.BOLD, 40));
        this.add(memberMainTitle);

    }
}
