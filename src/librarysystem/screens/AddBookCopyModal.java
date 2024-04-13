package librarysystem.screens;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import business.Book;
import business.SystemController;
import business.ValidationException;
import librarysystem.Util;

public class AddBookCopyModal extends JFrame {
	public final static AddBookCopyModal INSTANCE = new AddBookCopyModal();
	private static final long serialVersionUID = 1L;
	private JComboBox<String> iIsbn;
	private JTextField iNumOfCopies;
	private JButton saveButton;
	private JButton cancelButton;
	

	private SystemController controller;

	private AddBookCopyModal(){
		this.controller = new SystemController();
		
	}
	
	private String[] getIsbn() {
		List<Book> books = this.controller.allBook();
		String[] arr = new String[books.size()];
		
		for(int i = 0; i < arr.length; i++) {
			arr[i] = books.get(i).getIsbn();
		}
		
		return arr;
	}
	

	private void validateInputField(String key, String value,JTextField field) throws ValidationException{
		if(value == null || value.isEmpty() || value.isBlank()) {
			throw new ValidationException(key + " is empty", field);
		}
	}

	private void validatNoOfCopiesField(String key, String value, JTextField field) throws ValidationException {
		validateInputField(key,value, field);
		String pattern = "^[1-9][0-9]*$";

		if(!value.matches(pattern)){
			throw new ValidationException(key + " must be number from 0 - 9",field);
		}
	}
	
	
	private void setEventListener() {
		cancelButton.addActionListener((ActionEvent e)-> {
			this.dispose();
		});
		
		saveButton.addActionListener((ActionEvent e)-> {
			String numberOfCopies = iNumOfCopies.getText();

			try{
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
			this.controller.createBookCopies(String.valueOf(iIsbn.getSelectedItem()),Integer.parseInt(numberOfCopies));
			// close the modal
			this.dispose();
			JOptionPane.showMessageDialog(saveButton, "Added Book Successfully");
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
		
		DefaultComboBoxModel<String> defaultComboBox = new DefaultComboBoxModel<String>(getIsbn());
        iIsbn = new JComboBox<String>();
        iIsbn.setMaximumSize(new Dimension(boxWidth, boxHeight));
        iIsbn.setModel(defaultComboBox);
        iIsbn.setSelectedIndex(0);
        iIsbn.setBounds(xInput, y, boxWidth, boxHeight);
		contentPane.add(iIsbn);
		iIsbn.addActionListener(iIsbn);	
		
		JLabel lblCopies = new JLabel("Number Of Copies");
		lblCopies.setBounds(xLabel, y + yGap,labelWidth, labelHeight);
		contentPane.add(lblCopies);
		
		iNumOfCopies= new JTextField();
		iNumOfCopies.setBounds(xInput, y + yGap, boxWidth, boxHeight);
		contentPane.add(iNumOfCopies);
		iNumOfCopies.setColumns(10);
		
		
		cancelButton = new JButton("Cancel");
		cancelButton.setBounds(xLabel, y + 2*yGap, 117, 29);
		contentPane.add(cancelButton);
		
		
		saveButton = new JButton("Save");
		saveButton.setBounds(xInput, y + 2*yGap, 117, 29);
		contentPane.add(saveButton);
		
		this.setEventListener();
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddBookCopyModal frame = new AddBookCopyModal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
