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

    private SystemController controller;
    private JTable table;

    private static final String[] headers = new String[] {
            "S.N","Book", "dueDate", "dateOfCheckout", "Fine"
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

    private Object[][] getTableRows(int memberId){
        CheckoutRecord record = this.controller.getMembersCheckoutRecord(memberId);

        if(record == null){
            return new Object[0][0];
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Util.DATE_PATTERN);
        List<CheckoutEntry> entries = record.getRecords();
        Object[][] rows = new Object[entries.size()][5];
        int count = 0;
        for(CheckoutEntry entry : entries){
            Book b  = entry.getBook();
            rows[count] = new String[] {
                    String.valueOf(count + 1),b.getTitle(),
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

        table = new JTable();
        table.setModel(new DefaultTableModel(
                new Object[][]{},
                BookCheckoutTab.headers
        ));

        scrollPane.setViewportView(table);
        this.add(tablePanel);
    }

    private void reloadTable(int memberId){
        table.setModel(new DefaultTableModel(
                this.getTableRows(memberId),
                BookCheckoutTab.headers
        ));
    }

    void init(){
        JButton checkoutButton = new JButton("Checkout");
        checkoutButton.setBounds(650, 40, 130, 21);
        checkoutButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
        this.add(checkoutButton);

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
            int memberId = Integer.parseInt(String.valueOf(membersSelectBox.getSelectedItem()).split("->")[0]);
            System.out.println("member: " + memberId);
            this.reloadTable(memberId);
        });

        checkoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               // @TODO open new modal to add checkout records
                AddCheckoutEntry.INSTANCE.init();
                Util.centerFrameOnDesktop(AddCheckoutEntry.INSTANCE);
                AddCheckoutEntry.INSTANCE.setVisible(true);
            }
        });

        this.setUpTable();
    }
}
