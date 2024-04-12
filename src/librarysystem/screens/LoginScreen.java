package librarysystem.screens;

import business.LoginException;
import business.SystemController;

import java.awt.Image;
import java.awt.Toolkit;
import java.nio.file.Paths;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;

import librarysystem.Util;

import javax.swing.border.CompoundBorder;

public class LoginScreen extends JFrame {

	public final static LoginScreen INSTANCE = new LoginScreen();
	
	private static final long serialVersionUID = 1L;
	
//	private boolean isInitialized = false;

	private JTextField emailTextField;
	private JPasswordField passwordField;
	
	private LoginScreen() {
//		init();
	}

//	@Override
//	public boolean isInitialized() {
//		return isInitialized;
//	}
//
//	@Override
//	public void isInitialized(boolean val) {
//		isInitialized = val;
//	}
//
	public void init() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 753, 548);

		JPanel contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 254, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setResizable(false);
		setContentPane(contentPane);
		
		
		 String currDirectory = System.getProperty("user.dir");
         String pathToImage = Paths.get(currDirectory, "src", "librarysystem", "loginimage.jpg").toString();
         Image background = Toolkit.getDefaultToolkit().createImage(pathToImage);
		
		JLabel layout = new JLabel("New label");
		layout.setBounds(5, 5, 347, 510);
		layout.setIcon(new ImageIcon(LoginScreen.class.getResource("/librarysystem/loginbg.jpg")));
		
		JLabel header = new JLabel("Log in to your account");
		header.setBounds(391, 11, 302, 46);
		header.setHorizontalAlignment(SwingConstants.LEFT);
		header.setVerticalAlignment(SwingConstants.TOP);
		header.setFont(new Font("Tahoma", Font.PLAIN, 27));
		
		JLabel email = new JLabel("Username");
		email.setBounds(391, 87, 99, 17);
		email.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		emailTextField = new JTextField();
		emailTextField.setBounds(391, 135, 319, 56);
		emailTextField.setBackground(new Color(255, 255, 255));
		emailTextField.setHorizontalAlignment(SwingConstants.LEFT);
		emailTextField.setColumns(10);
		
		JLabel password = new JLabel("Password");
		password.setBounds(391, 217, 89, 27);
		password.setBackground(new Color(255, 146, 0));
		password.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		passwordField = new JPasswordField();
		passwordField.setBounds(391, 271, 319, 55);
		passwordField.setOpaque(true);
		passwordField.setColumns(10);
		
		JButton btnNewButton = new JButton("Log in");
		btnNewButton.setBounds(391, 411, 319, 59);
		btnNewButton.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0), 1, true), null));
		btnNewButton.setOpaque(true);
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setBackground(new Color(170, 121, 65));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 22));
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String userId = emailTextField.getText();
					String password = new String(passwordField.getPassword());
					new SystemController().login(userId, password);
					LoginScreen.INSTANCE.setVisible(false);
					Util.centerFrameOnDesktop(DashBoardScreen.INSTANCE);
					DashBoardScreen.INSTANCE.setVisible(true);
				}catch(LoginException ex) {
					JOptionPane.showMessageDialog(btnNewButton,ex.getMessage());
				}
			}
		});
		contentPane.setLayout(null);
		contentPane.add(layout);
		contentPane.add(header);
		contentPane.add(email);
		contentPane.add(emailTextField);
		contentPane.add(password);
		contentPane.add(passwordField);
		contentPane.add(btnNewButton);
	}

	
	
}