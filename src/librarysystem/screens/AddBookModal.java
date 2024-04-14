package librarysystem.screens;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.border.LineBorder;

import business.Author;
import business.SystemController;
import business.ValidationException;
import librarysystem.Util;

public class AddBookModal extends JFrame {
	public final static AddBookModal INSTANCE = new AddBookModal();
	private static final long serialVersionUID = 1L;
	private JTextField iIsbn;
	private JTextField iTitle;
	private JComboBox<String> iAuthors;
	private JComboBox<String> iMaxCheckoutLength;
	private JTextField iNumOfCopies;
	private JButton saveButton;
	private JButton cancelButton;
	private JTextArea textArea;
	private List<String> authorIdList = new ArrayList<>();

	private SystemController controller;

	private AddBookModal(){
		this.controller = new SystemController();
	}

	private String[] getAuthorsList(){
		List<Author> authors = this.controller.allAuthors();
		String[] list = new String[authors.size()];
		int i = 0;
		for(Author author: authors) {
			list[i] = (author.getAuthorId() + "->" + author.getFirstName() + " " + author.getLastName());
			i++;
		}
		return list;
	}

	private void validateInputField(String key, String value,JTextField field) throws ValidationException{
		if(value == null || value.isEmpty() || value.isBlank()) {
			throw new ValidationException(key + " is empty", field);
		}
	}

	private void validateNoOfCopiesField(String key, String value, JTextField field) throws ValidationException {
		validateInputField(key,value, field);
		String pattern = "^[0-9]*$";

		if(!value.matches(pattern)){
			throw new ValidationException(key + " must be a valid number",field);
		}
	}
	
	
	private void setEventListener() {
		cancelButton.addActionListener((ActionEvent e)-> {
			this.dispose();
		});
		
		saveButton.addActionListener((ActionEvent e)-> {
			String isbn = iIsbn.getText();
			String title = iTitle.getText();
			String numberOfCopies = iNumOfCopies.getText();
			String authorValue = textArea.getText();

			try{

				validateInputField("ISBN",isbn,iIsbn);
				validateInputField("Title",title,iTitle);

				if (authorValue == null || authorValue.isEmpty() || authorValue.isBlank() ) {
					JOptionPane.showMessageDialog(saveButton, "Author field is required");
					return;
				}

				validateNoOfCopiesField("Copies",numberOfCopies,iNumOfCopies);

			}catch(ValidationException ex){
				JTextField field = ex.getField();
				field.setBorder(new LineBorder(Util.ERROR_MESSAGE_COLOR,2));
				field.requestFocus();
				field.addKeyListener(new KeyAdapter() {
					@Override
					public void keyTyped(KeyEvent e) {
						field.setBorder(new LineBorder(Util.BORDER_COLOR,1));
					}
				});
				JOptionPane.showMessageDialog(saveButton,ex.getMessage());
				return;
			}

			String maxCheckoutLength = String.valueOf(iMaxCheckoutLength.getSelectedItem());
			try{

				this.controller.createBook(isbn,title,Integer.parseInt(maxCheckoutLength), Integer.parseInt(numberOfCopies), authorIdList);
				JOptionPane.showMessageDialog(saveButton,"Book Added Successfully");
				authorIdList.clear();
			}catch(ValidationException ex){
				JOptionPane.showMessageDialog(saveButton,ex.getMessage());
				return;
			}
			// close the modal
			this.dispose();
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
		
		JLabel lblIsbn = new JLabel("ISBN");
		lblIsbn.setBounds(xLabel,y,labelWidth, labelHeight);
		contentPane.add(lblIsbn);
		
		iIsbn = new JTextField();
		iIsbn.setBounds(xInput, y, boxWidth, boxHeight);
		contentPane.add(iIsbn);
		iIsbn.setColumns(10);
		
		JLabel lblTitle = new JLabel("Title");
		lblTitle.setBounds(xLabel, y + yGap,labelWidth, labelHeight);
		contentPane.add(lblTitle);
		
		iTitle = new JTextField();
		iTitle.setBounds(xInput, y + yGap, boxWidth, boxHeight);
		contentPane.add(iTitle);
		iTitle.setColumns(10);
		
		JLabel lblAuthors = new JLabel("Authors");
		lblAuthors.setBounds(xLabel, y + 2*yGap,labelWidth, labelHeight);
		contentPane.add(lblAuthors);

		textArea = new JTextArea(5, 150);
		textArea.setBounds(xInput - 50, y + 3*yGap, 200, 105);
		textArea.setEditable(false);;
		contentPane.add(textArea);


		DefaultComboBoxModel<String> comboBox = new DefaultComboBoxModel<String>(this.getAuthorsList());
		iAuthors = new JComboBox<String>();
		iAuthors.setMaximumSize(new Dimension(boxWidth, boxHeight));
		iAuthors.setModel(comboBox);
		iAuthors.setSelectedIndex(-1);
		iAuthors.setBounds(xInput, y + 2*yGap, boxWidth, boxHeight);
		contentPane.add(iAuthors);

		iAuthors.addActionListener((ActionEvent e) ->{
				String selectedAuthor = String.valueOf(iAuthors.getSelectedItem());
				String selectedAuthorId = selectedAuthor.split("->")[0];

				if (!authorIdList.contains(selectedAuthorId)) {
					authorIdList.add(selectedAuthorId);
					String newText = textArea.getText().isEmpty() ? selectedAuthor : "\n"+selectedAuthor;
					textArea.setText(textArea.getText() + newText);
				}
			}
				
		);
		
		
		JLabel lblCheckoutLength = new JLabel("Maximum Checkout Length");
		lblCheckoutLength.setBounds(xLabel, y + 6*yGap, labelWidth, labelHeight);
		contentPane.add(lblCheckoutLength);

        DefaultComboBoxModel<String> defaultComboBox = new DefaultComboBoxModel<String>(new String[]{"7", "21"});
        iMaxCheckoutLength = new JComboBox<String>();
        iMaxCheckoutLength.setMaximumSize(new Dimension(boxWidth, boxHeight));
        iMaxCheckoutLength.setModel(defaultComboBox);
        iMaxCheckoutLength.setSelectedIndex(0);
        iMaxCheckoutLength.setBounds(xInput, y + 6*yGap, boxWidth, boxHeight);
		contentPane.add(iMaxCheckoutLength);
		iMaxCheckoutLength.addActionListener(iMaxCheckoutLength);
		
		JLabel lblCopies = new JLabel("Number Of Copies");
		lblCopies.setBounds(xLabel, y + 7*yGap,labelWidth, labelHeight);
		contentPane.add(lblCopies);
		
		iNumOfCopies= new JTextField();
		iNumOfCopies.setBounds(xInput, y + 7*yGap, boxWidth, boxHeight);
		contentPane.add(iNumOfCopies);
		iNumOfCopies.setText("1");
		iNumOfCopies.setColumns(10);
		
		
		cancelButton = new JButton("Cancel");
		cancelButton.setBounds(xLabel, y + 8*yGap, 117, 29);
		contentPane.add(cancelButton);
		
		
		saveButton = new JButton("Save");
		saveButton.setBounds(xInput, y + 8*yGap, 117, 29);
		contentPane.add(saveButton);
		
		this.setEventListener();
	}
}
