import librarysystem.Util;
import librarysystem.screens.LoginScreen;

import javax.swing.*;
import java.awt.*;

public class Main {

        public static void main(String[] args) {
            EventQueue.invokeLater(() ->
            {
                LoginScreen.INSTANCE.setTitle("Library Application");
                LoginScreen.INSTANCE.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                LoginScreen.INSTANCE.init();
                centerFrameOnDesktop(LoginScreen.INSTANCE);
                LoginScreen.INSTANCE.setVisible(true);
            });
        }

        public static void centerFrameOnDesktop(Component f) {
            Util.centerFrameOnDesktop(f);
        }


}
