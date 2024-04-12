package librarysystem.screens;

import business.Book;
import business.LibraryMember;
import business.SystemController;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


public class AddCheckoutEntry extends JFrame {

	public final static AddCheckoutEntry INSTANCE = new AddCheckoutEntry();
	private static final long serialVersionUID = 1L;

	private SystemController controller;

	private AddCheckoutEntry(){
		this.controller = new SystemController();
	}

	private String[] getMemberOptions(){
		List<LibraryMember> lists =  this.controller.allMember();
		String[] ids = new String[lists.size()];
		for(int i = 0; i < lists.size(); i++){
			LibraryMember mem = lists.get(i);
			ids[i] = mem.getMemberId()+"->"+mem.getFirstName()+" "+mem.getLastName();
		}
		return ids;
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
	public void init() {
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		this.setBounds(300,300, 450, 300);
		
		JPanel contentPane = new JPanel();
		contentPane.setForeground(new Color(255, 255, 255));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		int xLabel = 75;
		int xInput = xLabel + 150;
		int y = 30;
		int yGap = 40;

		JLabel lblMember = new JLabel("Select Member");
		lblMember.setBounds(xLabel, y,150, 35);
		this.add(lblMember);

		DefaultComboBoxModel<String> defaultMembersComboBox = new DefaultComboBoxModel<String>(this.getMemberOptions());
		JComboBox<String> membersSelectBox = new JComboBox<String>();
		membersSelectBox.setMaximumSize(new Dimension(150, 35));
		membersSelectBox.setModel(defaultMembersComboBox);
		membersSelectBox.setSelectedIndex(-1);
		membersSelectBox.setBounds(xInput, y, 150, 35);
		this.add(membersSelectBox);

		JLabel lblBook = new JLabel("Select Book");
		lblBook.setBounds(xLabel, y + yGap,150, 35);
		this.add(lblBook);

		DefaultComboBoxModel<String> defaultBooksComboBox = new DefaultComboBoxModel<String>(this.getBookOptions());
		JComboBox<String> booksSelectBox = new JComboBox<String>();
		booksSelectBox.setMaximumSize(new Dimension(150, 35));
		booksSelectBox.setModel(defaultBooksComboBox);
		booksSelectBox.setSelectedIndex(-1);
		booksSelectBox.setBounds(xInput, y + yGap, 150, 35);
		this.add(booksSelectBox);


		JButton checkoutButton = new JButton("Checkout");
		checkoutButton.setBounds(xInput - 50, y + 3 * yGap, 117, 29);
		contentPane.add(checkoutButton);

		checkoutButton.addActionListener(e -> {
			Object memberIdObj = membersSelectBox.getSelectedItem();
			if(memberIdObj == null){
				JOptionPane.showMessageDialog(this, "Please select a member");
				return;
			}

			Object isbnObj = booksSelectBox.getSelectedItem();
			if(isbnObj == null){
				JOptionPane.showMessageDialog(this, "Please select a book");
				return;
			}

			String isbn = String.valueOf(isbnObj).split("->")[0];
			String memberId = String.valueOf(memberIdObj).split("->")[0];

			System.out.println("isbn: " +isbn + " memberId: " +memberId);

			if(!this.controller.booksHashMap().get(isbn).isAvailable()){
				JOptionPane.showMessageDialog(this, "This book is not available for checkout");
				return;
			}
			this.controller.addCheckoutRecord(isbn, Integer.parseInt(memberId));
			this.dispose();
		});
	}
}
