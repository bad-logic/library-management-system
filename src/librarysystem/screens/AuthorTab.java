package librarysystem.screens;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import business.Address;
import business.Author;
import business.SystemController;
import java.util.List;

public class AuthorTab extends JPanel {
	private SystemController controller;
    private JPanel tablePanel;
    
    private static final String[] headers = new String[] {
            "ID", "Name", "Mobile", "Address", "Bio"
     };
    
    AuthorTab(){
    	this.controller = new SystemController();
        this.init();
    }
    
    private Object[][] getTableRows(){
        List<Author> authors = this.controller.allAuthors();
        Object[][] rows = new Object[authors.size()][5];
        int count = 0;
        for(Author author : authors){
            Address add  = author.getAddress();
            rows[count] = new String[] {String.valueOf(author.getAuthorId()), author.fullName(), author.getTelephone(), author.getAddress().toString(), author.getBio()};
            count++;
        }
        return rows;
    }

    void reload(){
        this.setTable();
    }

    void setTable(){
        tablePanel = new JPanel();
        tablePanel.setBounds(10, 31, 774, 422);
        tablePanel.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 0, 774, 422);
        tablePanel.add(scrollPane);

        JTable table = new JTable() {
        	public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false;
             }
        };
        table.setModel(new DefaultTableModel(
                this.getTableRows(),
                this.headers
        ));

        scrollPane.setViewportView(table);
        this.add(tablePanel);
    }

    void init(){
    	
    	this.setTable();

    }
}
