package librarysystem.screens;

import business.LoginException;
import business.SystemController;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;

import business.ValidationException;
import librarysystem.Util;

import javax.swing.border.CompoundBorder;

public class LoginScreen extends JFrame {

	public final static LoginScreen INSTANCE = new LoginScreen();
	
	private static final long serialVersionUID = 1L;

	private JTextField emailTextField;
	private JPasswordField passwordField;
	
	public LoginScreen() {}

	private void validateInputField(String key, String value,JTextField field) throws ValidationException {
		if(value == null || value.isEmpty() || value.isBlank()) {
			throw new ValidationException(key + " is empty", field);
		}
	}

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
		
		JLabel email = new JLabel("User ID");
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
		
		btnNewButton.addActionListener((ActionEvent e) ->{
				try {

					String userId = emailTextField.getText();
					String user_password = new String(passwordField.getPassword());

					// validation
					validateInputField("User ID",userId,emailTextField);
					validateInputField("Password", user_password,passwordField);

					new SystemController().login(userId, user_password);
					this.dispose();
					DashBoardScreen dashboard= new DashBoardScreen();
					Util.centerFrameOnDesktop(dashboard);
					dashboard.setVisible(true);

				}catch(ValidationException ex) {

					JTextField field = ex.getField();
					field.setBorder(new LineBorder(Util.ERROR_MESSAGE_COLOR,2));
					field.requestFocus();
					JOptionPane.showMessageDialog(btnNewButton,ex.getMessage());

					field.addKeyListener(new KeyAdapter() {
						@Override
						public void keyTyped(KeyEvent e) {
							field.setBorder(new LineBorder(Util.BORDER_COLOR,1));
						}
					});

				} catch(LoginException ex) {
					JOptionPane.showMessageDialog(btnNewButton,ex.getMessage());
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