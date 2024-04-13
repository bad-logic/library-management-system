package librarysystem.screens;

import business.*;
import librarysystem.Util;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class BookCheckoutTab extends JPanel {

    private final SystemController controller;
    private JTable table;
    private int memberId = 0;

    private static final String[] headers = new String[] {
            "S.N","Book", "copyNum", "dueDate", "dateOfCheckout", "Fine"
    };

    BookCheckoutTab(){
        this.controller = new SystemController();
        this.init();
    }

    private String[] getMembersId(){
         List<LibraryMember> lists =  this.controller.allMember();
         String[] ids = new String[lists.size()];
         for(int i = 0; i < lists.size(); i++){
             LibraryMember mem = lists.get(i);
             ids[i] = mem.getMemberId()+"->"+mem.getFirstName()+" "+mem.getLastName();
         }
        return ids;
    }

    private Object[][] getTableRows(){
        CheckoutRecord record = this.controller.getMembersCheckoutRecord(this.memberId);

        System.out.println("checkout record: " + record);

        if(record == null){
            return new Object[0][0];
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Util.DATE_PATTERN);
        List<CheckoutEntry> entries = record.getRecords();
        System.out.println("checkout entries: " + entries.size());
        Object[][] rows = new Object[entries.size()][5];
        int count = 0;
        for(CheckoutEntry entry : entries){
            BookCopy bc = entry.getBookCopy();
            Book b  = bc.getBook();
            rows[count] = new String[] {
                    String.valueOf(count + 1),b.getTitle(),String.valueOf(bc.getCopyNum()),
                    entry.getDueDate().format(formatter),
                    entry.getDateOfCheckout().format(formatter),
                    String.valueOf(entry.getFine())
            };
            count++;
        }

        return rows;
    }

    private void setUpTable(){
        JPanel tablePanel = new JPanel();
        tablePanel.setBounds(10, 100, 774, 422);
        tablePanel.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 0, 774, 422);
        tablePanel.add(scrollPane);

        table = new JTable() {
        	public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false;
             }
        };
        table.setModel(new DefaultTableModel(
                new Object[][]{},
                BookCheckoutTab.headers
        ));

        scrollPane.setViewportView(table);
        this.add(tablePanel);
    }

    private void reloadTable(){
        table.setModel(new DefaultTableModel(
                this.getTableRows(),
                BookCheckoutTab.headers
        ));
    }

    void init(){
        JButton checkoutButton = new JButton("Checkout");
        checkoutButton.setBounds(650, 40, 130, 21);
        checkoutButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
        this.add(checkoutButton);
        if(this.memberId == 0){
            checkoutButton.setEnabled(false);
        }

        JLabel lblMember = new JLabel("Select Member");
        lblMember.setBounds(20, 40,150, 35);
        this.add(lblMember);

        DefaultComboBoxModel<String> defaultComboBox = new DefaultComboBoxModel<String>(this.getMembersId());
        JComboBox<String> membersSelectBox = new JComboBox<String>();
        membersSelectBox.setMaximumSize(new Dimension(150, 35));
        membersSelectBox.setModel(defaultComboBox);
        membersSelectBox.setSelectedIndex(-1);
        membersSelectBox.setBounds(170, 40, 150, 35);
        this.add(membersSelectBox);

        membersSelectBox.addActionListener((ActionEvent e) ->{
            this.memberId = Integer.parseInt(String.valueOf(membersSelectBox.getSelectedItem()).split("->")[0]);
            checkoutButton.setEnabled(true);
            System.out.println("member: " + this.memberId);
            this.reloadTable();
        });

        checkoutButton.addActionListener((ActionEvent e) -> {
               // @TODO open new modal to add checkout records
                AddCheckoutEntry.INSTANCE.init(this.memberId);
                Util.centerFrameOnDesktop(AddCheckoutEntry.INSTANCE);
//                AddCheckoutEntry.INSTANCE.setMemberId(this.memberId);
                AddCheckoutEntry.INSTANCE.setVisible(true);
        });

        this.setUpTable();
    }
}
