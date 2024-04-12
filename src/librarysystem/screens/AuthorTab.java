package librarysystem.screens;

import javax.swing.*;
import java.awt.*;

public class AuthorTab extends JPanel {

    AuthorTab(){
        this.init();
    }

    void init(){

        JLabel memberMainTitle = new JLabel("Author Tab");
        memberMainTitle.setFont(new Font("Tahoma", Font.BOLD, 40));
        this.add(memberMainTitle);

    }
}
