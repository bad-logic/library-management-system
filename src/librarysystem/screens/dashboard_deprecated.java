package librarysystem.screens;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class dashboard_deprecated extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel topPanel;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JButton addButton;
    private JPanel formPanel;
    private JLabel clickedLabel;
    private boolean formVisible;
    private JTable table  = new JTable();
    private DefaultTableModel model = new DefaultTableModel();
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
            	dashboard_deprecated frame = new dashboard_deprecated();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public dashboard_deprecated() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setTitle("Dashboard");

        getContentPane().setLayout(new BorderLayout());

        createTopPanel();
        createLeftPanel();
        createRightPanel();

        add(topPanel, BorderLayout.NORTH);
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);

        formVisible = false; 

        setVisible(true);
    }

    private void createTopPanel() {
        topPanel = new JPanel();
        topPanel.setBackground(new Color(139, 69, 19));
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        JLabel label = new JLabel("Welcome to SFR Library");
        label.setForeground(Color.WHITE);
        topPanel.add(label);
    }

    private void createLeftPanel() {
        leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.PAGE_AXIS));
        leftPanel.setBackground(Color.LIGHT_GRAY);
        leftPanel.setPreferredSize(new Dimension(350, getHeight()));
        leftPanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        String[] items = {"Books", "Members", "Authors", "Librarians", "Logout"};
        for (String item : items) {
            JLabel label = new JLabel(item);
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            label.setMaximumSize(new Dimension(Integer.MAX_VALUE, label.getPreferredSize().height));
            label.setFont(new Font("Tahoma", Font.PLAIN, 30));
            label.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));

            label.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (item.equals("Logout")) {
                        clearForm();
                        setFormVisibility(false);
                        clickedLabel.setText("You clicked on " + item);
                        clickedLabel.setVisible(true);
                    } else {
                        addButton.setText("Add " + item.substring(0, item.length() - 1)); 
                        createFormPanel(item); 
                        setFormVisibility(false);
                        clickedLabel.setText("You clicked on " + item);
                        clickedLabel.setVisible(false); 
                    }
                }
            });
            leftPanel.add(Box.createVerticalStrut(10));
            leftPanel.add(label);
            leftPanel.add(label);
        }

        leftPanel.add(Box.createVerticalGlue());
    }

    private void createRightPanel() {
        rightPanel = new JPanel();
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setLayout(new BorderLayout());

        JPanel addPanel = new JPanel();
        addPanel.setBackground(Color.WHITE);
        addButton = new JButton("");
        addButton.setVisible(false);
        
        addButton.addPropertyChangeListener("text", evt -> {
            String newText = evt.getNewValue().toString();
            if (newText.isEmpty()) {
                addButton.setVisible(false); 
            } else {
                addButton.setVisible(true); 
            }
        });
        
        addButton.addActionListener(e -> {
            setFormVisibility(!formVisible); 
            clickedLabel.setVisible(!formVisible); 
        });
        addPanel.add(addButton, BorderLayout.CENTER);

        formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        formPanel.setVisible(false); 
        rightPanel.add(addPanel, BorderLayout.NORTH);
        rightPanel.add(formPanel, BorderLayout.CENTER); 

        clickedLabel = new JLabel();
        clickedLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        clickedLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        clickedLabel.setHorizontalAlignment(SwingConstants.CENTER);
        clickedLabel.setVisible(false); 
        rightPanel.add(clickedLabel, BorderLayout.SOUTH); 
        rightPanel.setPreferredSize(new Dimension(650, getHeight())); 
    }

    private void createFormPanel(String itemType) {
        formPanel.removeAll(); 
        table = new JTable();
		JScrollPane scrollPane = new JScrollPane();
		model = new DefaultTableModel(0, 0);
		String header[] = new String[] {"S.N", "Name", "Mobile", "Address"};
		model.setColumnIdentifiers(header);
		table.setModel(model);
		for(int i = 0; i < 5; i++) {
			model.addRow(new Object[] {
					i + 1, "Folah", "641-211-23455", "Tetst"});
		}
        JPanel form = new JPanel(new GridLayout(0, 2, 10, 10));
        form.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Create labels and text fields based on the item type
        if (itemType.equals("Books")) {
            form.add(new JLabel("Title:"));
            form.add(new JTextField());
            form.add(new JLabel("ISBN:"));
            form.add(new JTextField());
            form.add(new JLabel("Book Copies:"));
            form.add(new JTextField());
//            form.add(table);
        } else if (itemType.equals("Members") || itemType.equals("Authors") || itemType.equals("Librarians")) {
            form.add(new JLabel("First Name:"));
            form.add(new JTextField());
            form.add(new JLabel("Last Name:"));
            form.add(new JTextField());
            form.add(new JLabel("Phone Number:"));
            form.add(new JTextField());
            form.add(new JLabel("Address:"));
            form.add(new JTextField());
        }
        
        

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(form, gbc);
        formPanel.revalidate();
        formPanel.repaint();
        
        
		
		
    }

    private void clearForm() {
        formPanel.removeAll();
        formPanel.revalidate();
        formPanel.repaint();
    }

    private void setFormVisibility(boolean visible) {
        formVisible = visible;
        formPanel.setVisible(visible);
        rightPanel.revalidate();
        rightPanel.repaint();
    }
}