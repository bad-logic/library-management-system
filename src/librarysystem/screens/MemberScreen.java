package librarysystem.screens;

import librarysystem.Util;

import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JComponent;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class MemberScreen extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField iFirstName;
	private JTextField ilastName;
	private JTextField iContact;
	private JTextField iStreet;
	private JTextField iCity;
	private JTextField iZipCode;
	private JComboBox<String> iState;
	private JButton saveButton;
	private JButton cancelButton;
	
	Color defaultBorderColor = Color.WHITE;
	Color errorBorderColor = Color.red;

	
	private void setEventListener() {	
		for(JTextField f: new JTextField[] {iFirstName,ilastName,iContact,iStreet,iCity,iZipCode,}) {
			f.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					iFirstName.setBorder(new LineBorder(defaultBorderColor,1));
					ilastName.setBorder(new LineBorder(defaultBorderColor,1));
					iContact.setBorder(new LineBorder(defaultBorderColor,1));
					iStreet.setBorder(new LineBorder(defaultBorderColor,1));
					iCity.setBorder(new LineBorder(defaultBorderColor,1));
					iZipCode.setBorder(new LineBorder(defaultBorderColor,1));
				}
			});
		}
		
		cancelButton.addActionListener((ActionEvent e)-> {
//			this.setVisible(false);
			this.dispose();
		});
		
		saveButton.addActionListener((ActionEvent e)-> {
			String fName = iFirstName.getText();
			String lName = ilastName.getText();
			String contact = iContact.getText();
			String street = iStreet.getText();
			String city = iCity.getText();
			String zip = iZipCode.getText();
			String state = String.valueOf(iState.getSelectedItem());
			
			
			if(fName == null || fName.isEmpty() || fName.isBlank()) {
				iFirstName.setBorder(new LineBorder(errorBorderColor,1));
			}
			if(lName == null || lName.isEmpty() || lName.isBlank()) {
				ilastName.setBorder(new LineBorder(errorBorderColor,1));
			}
			if(contact == null || contact.isEmpty() || contact.isBlank()) {
				iContact.setBorder(new LineBorder(errorBorderColor,1));
			}
			try {
				Integer.parseInt(contact);
			}catch(NumberFormatException ex) {
				iContact.setBorder(new LineBorder(errorBorderColor,1));
			}
			if(street == null || street.isEmpty() || street.isBlank()) {
				iStreet.setBorder(new LineBorder(errorBorderColor,1));
			}
			if(city == null || city.isEmpty() || city.isBlank()) {
				iCity.setBorder(new LineBorder(errorBorderColor,1));
			}
			if(zip == null || zip.isEmpty() || zip.isBlank()) {
				iZipCode.setBorder(new LineBorder(errorBorderColor,1));
			}
			if(state == null || state.isEmpty() || state.isBlank()) {
				iState.setBorder(new LineBorder(errorBorderColor,1));
			}
			
			//@TODO create Member using system controller
		});
		
	}

	/**
	 * Create the frame.
	 */
	public MemberScreen() {
		
		
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setBounds(300,300, 450, 450);
		
		JPanel contentPane = new JPanel();
		contentPane.setForeground(new Color(255, 255, 255));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		int xLabel = 75;
		int xInput = xLabel + 150;
		int y = 30;
		int yGap = 40;
		int boxWidth = 150;
		int boxHeight = 35;
		int labelWidth = 150;
		int labelHeight = 35;
		
		JLabel lblFName = new JLabel("First Name");
		lblFName.setBounds(xLabel,y,labelWidth, labelHeight);
		contentPane.add(lblFName);
		
		iFirstName = new JTextField();
		iFirstName.setBounds(xInput, y, boxWidth, boxHeight);
		contentPane.add(iFirstName);
		iFirstName.setColumns(10);
		
		JLabel lblLName = new JLabel("Last Name");
		lblLName.setBounds(xLabel, y + yGap,labelWidth, labelHeight);
		contentPane.add(lblLName);
		
		ilastName = new JTextField();
		ilastName.setBounds(xInput, y + yGap, boxWidth, boxHeight);
		contentPane.add(ilastName);
		ilastName.setColumns(10);
		
		JLabel lblContact = new JLabel("Phone");
		lblContact.setBounds(xLabel, y + 2*yGap, labelWidth, labelHeight);
		contentPane.add(lblContact);
		
		iContact = new JTextField();
		iContact.setBounds(xInput, y + 2*yGap, boxWidth, boxHeight);
		contentPane.add(iContact);
		iContact.setColumns(10);
		
		JLabel lblStreet = new JLabel("Street");
		lblStreet.setBounds(xLabel, y + 3*yGap, labelWidth, labelHeight);
		contentPane.add(lblStreet);
		
		iStreet = new JTextField();
		iStreet.setBounds(xInput, y + 3*yGap, boxWidth, boxHeight);
		contentPane.add(iStreet);
		iStreet.setColumns(10);
		
		JLabel lblCity = new JLabel("City");
		lblCity.setBounds(xLabel, y + 4*yGap, labelWidth, labelHeight);
		contentPane.add(lblCity);
		
		iCity = new JTextField();
		iCity.setBounds(xInput, y + 4*yGap, boxWidth, boxHeight);
		contentPane.add(iCity);
		iCity.setColumns(10);
		
		JLabel lblState = new JLabel("State");
		lblState.setBounds(xLabel, y + 5*yGap, labelWidth, labelHeight);
		contentPane.add(lblState);

        DefaultComboBoxModel<String> defaultComboBox = new DefaultComboBoxModel<String>(Util.states);
        iState = new JComboBox<String>();
        iState.setMaximumSize(new Dimension(boxWidth, boxHeight));
        iState.setModel(defaultComboBox);
        iState.setSelectedIndex(0);
        iState.setBounds(xInput, y + 5*yGap, boxWidth, boxHeight);
		contentPane.add(iState);
		iState.addActionListener(iState);
		
		JLabel lblZipCode = new JLabel("Zip Code");
		lblZipCode.setBounds(xLabel, y + 6*yGap,labelWidth, labelHeight);
		contentPane.add(lblZipCode);
		
		iZipCode = new JTextField();
		iZipCode.setBounds(xInput, y + 6*yGap, boxWidth, boxHeight);
		iZipCode.setColumns(10);
		contentPane.add(iZipCode);
		
		
		cancelButton = new JButton("Cancel");
		cancelButton.setBounds(xLabel, y + 8*yGap, 117, 29);
		contentPane.add(cancelButton);
		
		
		saveButton = new JButton("Save");
		saveButton.setBounds(xInput, y + 8*yGap, 117, 29);
		contentPane.add(saveButton);
		
		this.setEventListener();
		
		
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MemberScreen frame = new MemberScreen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
