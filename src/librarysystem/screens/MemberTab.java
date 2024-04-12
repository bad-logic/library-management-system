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
    private JPanel tablePanel;

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

    void reload(){
        System.out.println("reload");
        this.tablePanel.repaint();
    }

    void setTable(){
        tablePanel = new JPanel();
        tablePanel.setBounds(10, 193, 774, 422);
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
        addMemberButton.setBounds(350, 10, 130, 21);
        addMemberButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
        this.add(addMemberButton);

        JButton reloadButton = new JButton("Reload");
        reloadButton.setBounds(500, 10, 130, 21);
        reloadButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
        this.add(reloadButton);

        addMemberButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddMemberModal.INSTANCE.init();
                AddMemberModal.INSTANCE.setVisible(true);
            }
        });

        reloadButton.addActionListener((ActionEvent e) -> {
                this.reload();
        });

        this.setTable();
    }
}
