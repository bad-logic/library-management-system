import librarysystem.Util;
import librarysystem.screens.LoginScreen;

import javax.swing.*;
import java.awt.*;

public class Main {

        public static void main(String[] args) {
            EventQueue.invokeLater(() ->
            {
            	LoginScreen loginScreen = new LoginScreen();
            	loginScreen.setTitle("Library Application");
            	loginScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            	loginScreen.init();
                centerFrameOnDesktop(loginScreen);
                loginScreen.setVisible(true);
            });
        }

        public static void centerFrameOnDesktop(Component f) {
            Util.centerFrameOnDesktop(f);
        }

}
