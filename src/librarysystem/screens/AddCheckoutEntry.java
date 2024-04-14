package librarysystem.screens;

import business.*;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Iterator;


public class AddCheckoutEntry extends JFrame {

	public final static AddCheckoutEntry INSTANCE = new AddCheckoutEntry();
	private static final long serialVersionUID = 1L;

	private SystemController controller;

	private AddCheckoutEntry(){
		this.controller = new SystemController();
	}

	private String[] getBookOptions(){
		HashMap<String,Book> books = this.controller.booksHashMap();
		String[] bookOptions = new String[books.size()];
		Iterator<Book> it = books.values().iterator();
		int count = 0;
		while(it.hasNext()) {
			Book book = it.next();
			bookOptions[count] = book.getIsbn() + "->" + book.getTitle();
			count++;
		}
		return bookOptions;
	}

	/**
	 * Create the frame.
	 */
	public void init(int memberId) {
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		this.setBounds(300,300, 450, 250);
		
		JPanel contentPane = new JPanel();
		contentPane.setForeground(new Color(255, 255, 255));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		int xLabel = 75;
		int xInput = xLabel + 150;
		int y = 30;
		int yGap = 40;

		JLabel lblBook = new JLabel("Select Book");
		lblBook.setBounds(xLabel, y,150, 35);
		this.add(lblBook);

		DefaultComboBoxModel<String> defaultBooksComboBox = new DefaultComboBoxModel<String>(this.getBookOptions());
		JComboBox<String> booksSelectBox = new JComboBox<String>();
		booksSelectBox.setMaximumSize(new Dimension(150, 35));
		booksSelectBox.setModel(defaultBooksComboBox);
		booksSelectBox.setSelectedIndex(-1);
		booksSelectBox.setBounds(xInput, y , 150, 35);
		this.add(booksSelectBox);


		JButton checkoutButton = new JButton("Checkout");
		checkoutButton.setBounds(xInput - 50, y + 2 * yGap, 117, 29);
		contentPane.add(checkoutButton);

		checkoutButton.addActionListener(e -> {

			Object isbnObj = booksSelectBox.getSelectedItem();
			if(isbnObj == null){
				JOptionPane.showMessageDialog(this, "Please select a book");
				return;
			}

			String isbn = String.valueOf(isbnObj).split("->")[0];

			System.out.println("isbn: " +isbn + " memberId: " +memberId);

			try{
				this.controller.addCheckoutRecord(isbn, memberId);
				JOptionPane.showMessageDialog(this,"Checkout entry added successfully!!!");
			}catch (ValidationException ex){
				JOptionPane.showMessageDialog(this, ex.getMessage());
				return;
			}
			this.dispose();
		});
	}
}
