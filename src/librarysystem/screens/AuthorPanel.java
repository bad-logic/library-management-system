package librarysystem.screens;

import java.awt.Font;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import business.LibraryMember;
import business.SystemController;
import dataaccess.DataAccessFacade;

public class AuthorPanel {
	SystemController sc = new SystemController();
	DataAccessFacade daf = new DataAccessFacade();
	JTable tblAuthor;
	private JTextField fName;
	private JTextField lName;
	private JTextField mobile;
	private JTextField bio;
	private JTable table;
	private DefaultTableModel model;
	Boolean isAdd = true;
	Boolean isEdit = false;
	
	public void authorView(JPanel member) {
		JLabel fNameLabel = new JLabel("First Name");
		fNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		fNameLabel.setBounds(10, 37, 109, 13);
		member.add(fNameLabel);
		
		fName = new JTextField();
		fName.setBounds(10, 60, 159, 28);
		member.add(fName);
		fName.setColumns(10);
		
		JLabel lNameLabel = new JLabel("Last Name");
		lNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lNameLabel.setBounds(188, 37, 109, 13);
		member.add(lNameLabel);
		
		lName = new JTextField();
		lName.setColumns(10);
		lName.setBounds(187, 60, 159, 28);
		member.add(lName);
		
		JLabel mobileLabel = new JLabel("Mobile");
		mobileLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		mobileLabel.setBounds(369, 37, 109, 13);
		member.add(mobileLabel);
		
		mobile = new JTextField();
		mobile.setColumns(10);
		mobile.setBounds(369, 60, 159, 28);
		member.add(mobile);
		
		JLabel bioLabel = new JLabel("Bio");
		mobileLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		mobileLabel.setBounds(369, 37, 109, 13);
		member.add(bioLabel);
		
		bio = new JTextField();
		bio.setColumns(10);
		bio.setBounds(369, 60, 159, 28);
		member.add(bio);
		
		JButton addButton = new JButton("Add");
		addButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		addButton.setBounds(572, 59, 85, 28);
		member.add(addButton);
//		addMemberButtonListener(addButton);
		
		JButton editButton = new JButton("Edit");
		editButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		editButton.setBounds(572, 97, 85, 28);
		member.add(editButton);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDelete.setBounds(572, 135, 85, 28);
		member.add(btnDelete);
		
		List<LibraryMember> members = sc.allMember();
		
		table = new JTable();
		JScrollPane scrollPane = new JScrollPane();
		model = new DefaultTableModel(0, 0);
		String header[] = new String[] {"S.N", "Name", "Mobile", "Address"};
		model.setColumnIdentifiers(header);
		table.setModel(model);
		for(int i = 0; i < members.size(); i++) {
			model.addRow(new Object[] {
					i+1, members.get(i).fullName(), members.get(i).getTelephone(), members.get(i).getAddress()
			});
		}
		
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	            // do some actions here, for example
	            // print first column value from selected row
	        	int rowSelected = table.getSelectedRow();
	        	String[] fullName = table.getValueAt(rowSelected, 1).toString().split(" ");
	        	fName.setText(fullName[0]);
	        	lName.setText(fullName[1]);
	        	mobile.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
	        }
	    });
		table.setBounds(10, 225, 647, 221);
		scrollPane.setViewportView(table);
		member.add(table);
	}
	
}
