package librarysystem.screens;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.nio.file.Paths;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.JPasswordField;

public class login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField usernameField;
	private JPasswordField passwordField;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login frame = new login();
					frame.setVisible(true);
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 753, 548);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 254, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		centerFrameOnDesktop(this);
		setResizable(false);
		setContentPane(contentPane);
		
		
		 String currDirectory = System.getProperty("user.dir");
         String pathToImage = Paths.get(currDirectory, "src", "librarysystem", "loginimage.jpg").toString();
         Image background = Toolkit.getDefaultToolkit().createImage(pathToImage);
        
         System.out.println(pathToImage);
		
		JLabel layout = new JLabel("New label");
		layout.setBounds(5, 5, 347, 510);
		layout.setIcon(new ImageIcon(login.class.getResource("/librarysystem/loginbg.jpg")));
		
		JLabel header = new JLabel("Log in to your account");
		header.setBounds(391, 11, 302, 46);
		header.setHorizontalAlignment(SwingConstants.LEFT);
		header.setVerticalAlignment(SwingConstants.TOP);
		header.setFont(new Font("Tahoma", Font.PLAIN, 27));
		
		JLabel username = new JLabel("Username");
		username.setBounds(391, 87, 99, 17);
		username.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		usernameField = new JTextField();
		usernameField.setBounds(391, 135, 319, 59);
		usernameField.setBackground(new Color(255, 255, 255));
		usernameField.setHorizontalAlignment(SwingConstants.LEFT);
		usernameField.setColumns(10);
		
		JLabel password = new JLabel("Password");
		password.setBounds(391, 217, 89, 27);
		password.setBackground(new Color(255, 146, 0));
		password.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JButton btnNewButton = new JButton("Log in");
		btnNewButton.setBounds(391, 411, 319, 59);
		btnNewButton.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0), 1, true), null));
		btnNewButton.setOpaque(true);
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setBackground(new Color(170, 121, 65));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
                  dashboard dashboardFrame = new dashboard();
                  dashboardFrame.setVisible(true);
//                 Open the dashboard frame
//                JFrame dashboardFrame = new JFrame("Dashboard");
//                JLabel welcomeLabel = new JLabel("Welcome to Dashboard");
//                welcomeLabel.setFont(new Font("Tahoma", Font.PLAIN, 27));
//                dashboardFrame.add(welcomeLabel);
//                dashboardFrame.setSize(400, 300);
//                dashboardFrame.setLocationRelativeTo(null);
//                dashboardFrame.setVisible(true);
			}
		});

		contentPane.setLayout(null);
		contentPane.add(layout);
		contentPane.add(header);
		contentPane.add(username);
		contentPane.add(usernameField);
		contentPane.add(password);
		contentPane.add(btnNewButton);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(391, 284, 319, 59);
		contentPane.add(passwordField);
	}

	public void centerFrameOnDesktop(JFrame frame) {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		int height = toolkit.getScreenSize().height;
		int width = toolkit.getScreenSize().width;
		int frameHeight = frame.getSize().height;
		int frameWidth = frame.getSize().width;
		int xpos = (width - frameWidth) / 2;
		int ypos = (height - frameHeight) / 3;
		frame.setLocation(xpos, ypos);
	}
}
