package librarysystem.screens;

import business.SystemController;
import librarysystem.Util;

import javax.swing.*;

import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class AddMemberModal extends JFrame {

	public final static AddMemberModal INSTANCE = new AddMemberModal();
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

	private SystemController controller;

	private AddMemberModal(){
		this.controller = new SystemController();
	}

	private boolean setInputError(String value,JTextField field){
		boolean isErrorSet = false;
		if(value == null || value.isEmpty() || value.isBlank()) {
			isErrorSet = true;
			field.setBorder(new LineBorder(Util.ERROR_MESSAGE_COLOR,1));
			field.requestFocus();
		}
		field.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				field.setBorder(new LineBorder(Util.BORDER_COLOR,1));
			}
		});
		return isErrorSet;
	}

	private boolean setInputError(String value, JTextField field, boolean parseInteger) {
		boolean isErrorSet = setInputError(value,field);
		if(!isErrorSet) {
			try {
				Integer.parseInt(value);
			}catch(NumberFormatException ex) {
				isErrorSet = true;
				field.setBorder(new LineBorder(Util.ERROR_MESSAGE_COLOR,1));
			}
		}

		return isErrorSet;
	}

	private boolean setContactError(String value, JTextField field){
		boolean isErrorSet = setInputError(value,field);
		if(!isErrorSet) {
			Pattern pattern = Pattern.compile("\\b\\d{3}-?\\d{3}-?\\d{4}\\b");
			Matcher matcher = pattern.matcher(value);

			if(!matcher.find()){
				isErrorSet = true;
				field.setBorder(new LineBorder(Util.ERROR_MESSAGE_COLOR,1));
			}
		}
		return isErrorSet;

	}

	private void setEventListener() {
		cancelButton.addActionListener((ActionEvent e)-> {
			this.dispose();
		});
		
		saveButton.addActionListener((ActionEvent e)-> {
			String fName = iFirstName.getText();
			String lName = ilastName.getText();
			String contact = iContact.getText();
			String street = iStreet.getText();
			String city = iCity.getText();
			String zip = iZipCode.getText();
			if(
					setInputError(fName,iFirstName) ||
					setInputError(lName,ilastName) ||
					setContactError(contact,iContact) ||
					setInputError(street,iStreet) ||
					setInputError(city,iCity) ||
					setInputError(zip,iZipCode,true)
			){
				JOptionPane.showMessageDialog(saveButton,"Invalid Data");
			}else{
				String state = String.valueOf(iState.getSelectedItem());
				this.controller.createMember(fName,lName,contact,street,city,state,Integer.parseInt(zip));
				// close the modal
				this.dispose();
			}


		});
		
	}

	/**
	 * Create the frame.
	 */
	public void init() {
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
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
					AddMemberModal frame = new AddMemberModal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
