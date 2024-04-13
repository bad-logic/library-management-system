package librarysystem.screens;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import business.Book;
import business.BookCopy;
import business.SystemController;
import dataaccess.Auth;
import librarysystem.Util;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class BookTab extends JPanel {
	private final SystemController controller;
    private JTable table;
	
	 private static final String[] headers = new String[] {
		        "ISBN","Title", "Checkout Length", "Copies", "Available"};
	 
    BookTab(){
    	this.controller = new SystemController();
        this.init();
    }
    
    private Object[][] getTableRows(){
        List<Book> books = this.controller.allBook();
        Object[][] rows = new Object[books.size()][5];
        int count = 0;
        for(Book book : books){
        	int available = 0;
        	for(BookCopy b: book.getCopies()) {
        		if(b.isAvailable()) {
        			available++;
        		}
        	}

            rows[count] = new String[] {book.getIsbn(), book.getTitle(), "" + book.getMaxCheckoutLength(), "" + book.getCopyNums().size(),""+available};
            count++;
        }
        return rows;
    }

    private void reloadTable(){
        table.setModel(new DefaultTableModel(
                this.getTableRows(),
                BookTab.headers
        ));
    }
    
    void setTable(){
        JPanel tablePanel = new JPanel();
        tablePanel.setBounds(10, 80, 774, 422);
        tablePanel.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 0, 774, 422);
        tablePanel.add(scrollPane);
        System.out.println(this.getTableRows());
        table = new JTable() {
        	public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false;
             }
        };
        table.setModel(new DefaultTableModel(
                this.getTableRows(),
                BookTab.headers
        ));

        scrollPane.setViewportView(table);
        this.add(tablePanel);
    }

    void init(){

        if(SystemController.currentAuth.length == 2 || SystemController.currentAuth[0] == Auth.ADMIN){
            JButton addBookButton = new JButton("Add Book");
            addBookButton.setBounds(374, 31, 130, 21);
            addBookButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
            this.add(addBookButton);

            JButton addCopyBookButton = new JButton("Add Book Copy");
            addCopyBookButton.setBounds(514, 31, 130, 21);
            addCopyBookButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
            this.add(addCopyBookButton);

            addBookButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    AddBookModal.INSTANCE.init();
                    Util.centerFrameOnDesktop(AddBookModal.INSTANCE);
                    AddBookModal.INSTANCE.setVisible(true);
                }
            });

            addCopyBookButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    AddBookCopyModal.INSTANCE.init();
                    Util.centerFrameOnDesktop(AddBookCopyModal.INSTANCE);
                    AddBookCopyModal.INSTANCE.setVisible(true);
                }
            });

        }

        JButton reloadButton = new JButton("Reload");
        reloadButton.setBounds(654, 31, 130, 21);
        reloadButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
        this.add(reloadButton);

        reloadButton.addActionListener((ActionEvent e) -> {
                this.reloadTable();
        });

        this.setTable();
    }
    
    
    
   
}
