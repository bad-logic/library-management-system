package librarysystem.screens;

import business.Address;
import business.LibraryMember;
import business.SystemController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MemberTab extends JPanel {
    private SystemController controller;
    private List<LibraryMember> members;

    private static final String[] headers = new String[] {
        "ID","Name", "Mobile", "City", "State"
    };

    MemberTab(){
        this.controller = new SystemController();
        this.members = this.controller.allMember();
        this.init();
    }

    private Object[][] getTableRows(){
        Object[][] rows = new Object[this.members.size()][5];
        int count = 0;
        for(LibraryMember member : this.members){
            Address add  = member.getAddress();
            rows[count] = new String[] {member.getMemberId(), member.fullName(), member.getTelephone(), add.getCity(), add.getState()};
            count++;
        }
        return rows;
    }


    void init(){
        JButton addMemberButton = new JButton("Add Member");
        addMemberButton.setBounds(654, 10, 130, 21);
        addMemberButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
        this.add(addMemberButton);

        addMemberButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddMemberModal.INSTANCE.init();
                AddMemberModal.INSTANCE.setVisible(true);
            }
        });

        JPanel panel = new JPanel();
        panel.setBounds(10, 193, 774, 422);
        panel.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 0, 774, 422);
        panel.add(scrollPane);

        JTable table = new JTable();
        table.setModel(new DefaultTableModel(
                this.getTableRows(),
                MemberTab.headers
        ));

        scrollPane.setViewportView(table);
        this.add(panel);
    }
}
