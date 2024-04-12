package librarysystem.screens;

import java.awt.Font;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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

public class MemberPanel_deprecated {
	SystemController sc = new SystemController();

	JTable tblMember;
	private JTextField fName;
	private JTextField lName;
	private JTextField mobile;
	private JTextField street;
	private JTextField city;
	private JTextField state;
	private JTextField zip;
	private JTable table;
	private DefaultTableModel model;
	Boolean isAdd = true;
	Boolean isEdit = false;
	
	public void memberView(JPanel member) {
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
		
		JLabel streetLabel = new JLabel("Street");
		streetLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		streetLabel.setBounds(10, 103, 109, 13);
		member.add(streetLabel);
		
		street = new JTextField();
		street.setColumns(10);
		street.setBounds(10, 126, 159, 28);
		member.add(street);
		
		JLabel cityLabel = new JLabel("City");
		cityLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cityLabel.setBounds(188, 98, 109, 13);
		member.add(cityLabel);
		
		city = new JTextField();
		city.setColumns(10);
		city.setBounds(188, 126, 159, 28);
		member.add(city);
		
		JLabel stateLabel = new JLabel("State");
		stateLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		stateLabel.setBounds(369, 103, 109, 13);
		member.add(stateLabel);
		
		state = new JTextField();
		state.setColumns(10);
		state.setBounds(369, 126, 159, 28);
		member.add(state);
		
		JLabel zipLabel = new JLabel("Zip");
		zipLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		zipLabel.setBounds(10, 164, 109, 13);
		member.add(zipLabel);
		
		zip = new JTextField();
		zip.setColumns(10);
		zip.setBounds(10, 187, 159, 28);
		member.add(zip);
		
		JButton addButton = new JButton("Add");
		addButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		addButton.setBounds(572, 59, 85, 28);
		member.add(addButton);
		addMemberButtonListener(addButton);
		
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
	        	String address = table.getValueAt(rowSelected, 3).toString();
	        	address = address.substring(1, address.length() - 1);
	        	String[] addressArr = address.split(", ");
	        	street.setText(addressArr[0]);
	        	city.setText(addressArr[1]);
	        	state.setText(addressArr[2]);
	        	zip.setText(addressArr[3]);
	        }
	    });
		table.setBounds(10, 225, 647, 221);
		scrollPane.setViewportView(table);
		member.add(table);
	}
	
	
	private void addMemberButtonListener(JButton btn) {
		btn.addActionListener(evt -> {
			// some code
				String fNameField = fName.getText();
				String lNameField = lName.getText();
				String mobileField = mobile.getText();
				String streetField = street.getText();
				String cityField = city.getText();
				String stateField = state.getText();
				String zipField = zip.getText();
				
				if (fNameField.equals("") ||
						lNameField.equals("") ||
						mobileField.equals("") ||
						streetField.equals("") ||
						cityField.equals("") ||
						stateField.equals("") ||
						zipField.equals("")) {
					JOptionPane.showMessageDialog(btn, "All the fields are required.");
					return;
					
				}

				sc.createMember(fNameField,lNameField,mobileField,streetField,cityField,stateField,Integer.parseInt(zipField));
				clearFields();
				
		});
	}
	
	private void clearFields() {
		fName.setText("");
		lName.setText("");
		mobile.setText("");
		street.setText("");
		city.setText("");
		state.setText("");
		zip.setText("");
	}
}
