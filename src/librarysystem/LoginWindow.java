package librarysystem;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Paths;
import java.util.Arrays;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JOptionPane;

import business.ControllerInterface;
import business.LoginException;
import business.SystemController;
import librarysystem.screens.login;


public class LoginWindow extends JFrame implements LibWindow {
	ControllerInterface ci = new SystemController();
    public static final LoginWindow INSTANCE = new LoginWindow();
	
	private boolean isInitialized = false;
	
	private JPanel contentPane;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JButton btnNewButton;
	
	
	public boolean isInitialized() {
		return isInitialized;
	}
	public void isInitialized(boolean val) {
		isInitialized = val;
	}
	private JTextField messageBar = new JTextField();
	public void clear() {
		messageBar.setText("");
	}
	
	/* This class is a singleton */
    private LoginWindow () {}
    
    public void init() {     		
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 753, 548);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 254, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

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
		
		btnNewButton = new JButton("Log in");
		btnNewButton.setBounds(391, 411, 319, 59);
		btnNewButton.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0), 1, true), null));
		btnNewButton.setOpaque(true);
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setBackground(new Color(170, 121, 65));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 22));
		addLoginButtonListener(btnNewButton);
		
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
    	
	private void addBackButtonListener(JButton butn) {
		butn.addActionListener(evt -> {
			LibrarySystem.hideAllWindows();
			LibrarySystem.INSTANCE.setVisible(true);
		});
	}
	
	private void addLoginButtonListener(JButton butn) {
		btnNewButton.addActionListener(evt -> {
			try {
				String password = new String(passwordField.getPassword());
				
				ci.login(usernameField.getText(), password);
					Dashboard.INSTANCE.init();
				Util.centerFrameOnDesktop(Dashboard.INSTANCE);					
				Dashboard.INSTANCE.setVisible(true);
				LibrarySystem.hideAllWindows();;
//					LoginWindow.INSTANCE.setVisible(false);
				JOptionPane.showMessageDialog(this,"Successful Login");
			} catch (LoginException e) {
				// TODO Auto-generated catch block
//					e.printStackTrace();
				System.out.println(passwordField.getPassword());
				JOptionPane.showMessageDialog(this, e.getMessage());
			}
				
		});
	}
	
        
    
}
