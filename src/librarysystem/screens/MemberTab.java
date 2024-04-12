package librarysystem.screens;

import business.Address;
import business.LibraryMember;
import business.SystemController;
import librarysystem.Util;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MemberTab extends JPanel {
    private SystemController controller;
    private JPanel tablePanel;

    private static final String[] headers = new String[] {
        "ID","Name", "Mobile", "City", "State"
    };

    MemberTab(){
        this.controller = new SystemController();
        this.init();
    }

    private Object[][] getTableRows(){
        List<LibraryMember> members = this.controller.allMember();
        Object[][] rows = new Object[members.size()][5];
        int count = 0;
        for(LibraryMember member : members){
            Address add  = member.getAddress();
            rows[count] = new String[] {String.valueOf(member.getMemberId()), member.fullName(), member.getTelephone(), add.getCity(), add.getState()};
            count++;
        }
        return rows;
    }

    void reload(){
        this.setTable();
    }

    void setTable(){
        tablePanel = new JPanel();
        tablePanel.setBounds(10, 80, 774, 422);
        tablePanel.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 0, 774, 422);
        tablePanel.add(scrollPane);

        JTable table = new JTable();
        table.setModel(new DefaultTableModel(
                this.getTableRows(),
                MemberTab.headers
        ));

        scrollPane.setViewportView(table);
        this.add(tablePanel);
    }


    void init(){
        JButton addMemberButton = new JButton("Add Member");
        addMemberButton.setBounds(514, 31, 130, 21);
        addMemberButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
        this.add(addMemberButton);

        JButton reloadButton = new JButton("Reload");
        reloadButton.setBounds(654, 31, 130, 21);
        reloadButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
        this.add(reloadButton);

        addMemberButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddMemberModal.INSTANCE.init();
                Util.centerFrameOnDesktop(AddMemberModal.INSTANCE);
                AddMemberModal.INSTANCE.setVisible(true);
            }
        });

        reloadButton.addActionListener((ActionEvent e) -> {
                this.reload();
        });

        this.setTable();
    }
}
