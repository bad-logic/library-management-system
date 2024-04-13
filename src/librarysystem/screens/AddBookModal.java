package librarysystem.screens;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
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
//	private JComboBox<String> iAuthors;
	private JComboBox<String> iAuthors;
//	private JTextField iMaxCheckoutLength;
	private JComboBox<String> iMaxCheckoutLength;
	private JTextField iNumOfCopies;
	private JButton saveButton;
	private JButton cancelButton;
	private JTextArea textArea;
	private List<String> authorList = new ArrayList<>();
	private List<String> authorIdList = new ArrayList<>();

	private SystemController controller;

	private AddBookModal(){
		this.controller = new SystemController();
		List<Author> authors = this.controller.allAuthors();
		for(Author author: authors) {
			authorList.add(author.getAuthorId() + "->" + author.getFirstName() + " " + author.getLastName());
		}
	}
	

	private void validateInputField(String key, String value,JTextField field) throws ValidationException{
		if(value == null || value.isEmpty() || value.isBlank()) {
			throw new ValidationException(key + " is empty", field);
		}
	}

	private void validatNoOfCopiesField(String key, String value, JTextField field) throws ValidationException {
		validateInputField(key,value, field);
		String pattern = "^[0-9]*$";

		if(!value.matches(pattern)){
			throw new ValidationException(key + " must be number from 0 - 9",field);
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
				validatNoOfCopiesField("No of copies",numberOfCopies,iNumOfCopies);

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
			
			
			if (authorValue == null || authorValue.isEmpty() || authorValue.isBlank() ) {
				JOptionPane.showMessageDialog(saveButton, "Author field is required");
			}

			String maxCheckoutLength = String.valueOf(iMaxCheckoutLength.getSelectedItem());
			this.controller.createBook(isbn,title,Integer.parseInt(maxCheckoutLength), Integer.parseInt(numberOfCopies), authorIdList);
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
		
		textArea = new JTextArea(5, 105);
		JScrollPane scrollPane = new JScrollPane(textArea); 
		textArea.setBounds(xInput, y + 3*yGap, boxWidth, 105);
		textArea.setEditable(false);
		contentPane.add(textArea);
		
		String[] options = new String[authorList.size()];
		options = authorList.toArray(options);
        
		DefaultComboBoxModel<String> comboBox = new DefaultComboBoxModel<String>(options);
		iAuthors = new JComboBox<String>();
		iAuthors.setMaximumSize(new Dimension(boxWidth, boxHeight));
		iAuthors.setModel(comboBox);
		iAuthors.setSelectedIndex(-1);
		iAuthors.setBounds(xInput, y + 2*yGap, boxWidth, boxHeight);
		contentPane.add(iAuthors);
		StringBuilder str = new StringBuilder("");
		iAuthors.addActionListener(
				new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						String selectedAuthor = (String)iAuthors.getSelectedItem();					
						
						String[] selectedAuthorArr = selectedAuthor.split("->");
						
						if (authorIdList.size() == 0) {
							str.append(selectedAuthor + "\n");
							authorIdList.add(selectedAuthorArr[0]);
						} else {
							if (!authorIdList.contains(selectedAuthorArr[0])) {
								str.append(selectedAuthor + "\n");
								authorIdList.add(selectedAuthorArr[0]);
							}
						}
						
						textArea.setText(str.toString());
						
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
		iNumOfCopies.setColumns(10);
		
		
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
					AddBookModal frame = new AddBookModal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
