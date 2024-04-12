package librarysystem.screens;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import java.awt.Font;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Rectangle;
import javax.swing.JList;
import java.awt.List;
import javax.swing.ListSelectionModel;
import javax.swing.AbstractListModel;
import java.awt.CardLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class DashboardScreen extends JFrame {
	public static final DashboardScreen INSTANCE = new DashboardScreen();
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JList linkList;
	private JPanel cards;
	private JTextField fNameField;
	private JTextField lNameField;
	private JTextField mobileField;
	private JTextField streetField;
	private JTextField cityField;
	private JTextField stateField;
	private JTextField zipField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DashboardScreen frame = new DashboardScreen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public DashboardScreen() {
		init();
	}

	/**
	 * Create the frame.
	 */
	public void init() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 984, 752);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel topPanel = new JPanel();
		topPanel.setBounds(10, 10, 960, 60);
		topPanel.setBackground(new Color(233, 185, 22));
		contentPane.add(topPanel);
		topPanel.setLayout(null);
		
		JLabel dashboardTitle = DefaultComponentFactory.getInstance().createTitle("MIU Library");
		dashboardTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
		dashboardTitle.setBounds(419, 10, 134, 23);
		topPanel.add(dashboardTitle);
		
		JPanel leftPanel = new JPanel();
		leftPanel.setBackground(new Color(255, 255, 255));
		leftPanel.setBounds(10, 80, 156, 625);
		contentPane.add(leftPanel);
		leftPanel.setLayout(null);
		
		linkList = new JList();
		linkList.setFont(new Font("Tahoma", Font.BOLD, 14));
		linkList.setModel(new AbstractListModel() {
			String[] values = new String[] {"Home", "Member", "Author", "Book", "Checkout History", "Logout"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		linkList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		linkList.setBounds(10, 10, 136, 605);
		linkList.setFixedCellHeight(50);
		leftPanel.add(linkList);
		
		createPanels();
		
		
		
	}
	
	public void createPanels() {
//		JPanel cards = new JPanel();
//		cards.setBackground(new Color(255, 255, 255));
//		cards.setBounds(176, 80, 794, 625);
//		contentPane.add(cards);
//		cards.setLayout(null);
		
		JPanel homePanel = new JPanel();
		homePanel.setBackground(new Color(255, 255, 255));
		homePanel.setBounds(0, 0, 794, 625);
		new HomePanel().homeView(homePanel);
		
		
		JPanel memberPanel = new JPanel();
		memberPanel.setBackground(new Color(255, 255, 255));
		memberPanel.setBounds(0, 0, 794, 625);
		
		JPanel authorPanel = new JPanel();
		authorPanel.setBackground(new Color(255, 255, 255));
		authorPanel.setBounds(0, 0, 794, 625);
		
		JPanel bookPanel = new JPanel();
		bookPanel.setBackground(new Color(255, 255, 255));
		bookPanel.setBounds(0, 0, 794, 625);
		
		JPanel historyPanel = new JPanel();
		historyPanel.setBackground(new Color(255, 255, 255));
		historyPanel.setBounds(0, 0, 794, 625);
		
		cards = new JPanel(new CardLayout());
		cards.setBackground(new Color(255, 255, 255));
		cards.setBounds(176, 80, 794, 625);
		contentPane.add(cards);
		cards.add(homePanel, "Home");
		homePanel.setLayout(null);
		
		
		
		cards.add(memberPanel, "Member");
		memberPanel.setLayout(null);
		
		JButton addMemberButton = new JButton("Add Member");
		addMemberButton.setBounds(654, 10, 130, 21);
		addMemberButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		memberPanel.add(addMemberButton);
		
		JPanel formPanel = new JPanel();
		formPanel.setBounds(10, 41, 774, 142);
		memberPanel.add(formPanel);
		formPanel.setLayout(null);
		
		JLabel fNameLabel = new JLabel("First Name");
		fNameLabel.setBounds(10, 10, 75, 13);
		fNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		formPanel.add(fNameLabel);
		
		fNameField = new JTextField();
		fNameField.setBounds(10, 25, 114, 28);
		formPanel.add(fNameField);
		fNameField.setColumns(10);
		
		JLabel lNameLabel = new JLabel("Last Name");
		lNameLabel.setBounds(134, 10, 75, 13);
		lNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		formPanel.add(lNameLabel);
		
		lNameField = new JTextField();
		lNameField.setBounds(134, 25, 114, 28);
		lNameField.setColumns(10);
		formPanel.add(lNameField);
		
		JLabel mobileLabel = new JLabel("Mobile");
		mobileLabel.setBounds(258, 10, 75, 13);
		mobileLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		formPanel.add(mobileLabel);
		
		mobileField = new JTextField();
		mobileField.setBounds(258, 25, 114, 28);
		mobileField.setColumns(10);
		formPanel.add(mobileField);
		
		JLabel streetLabel = new JLabel("Street");
		streetLabel.setBounds(382, 10, 75, 13);
		streetLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		formPanel.add(streetLabel);
		
		streetField = new JTextField();
		streetField.setBounds(382, 25, 114, 28);
		streetField.setColumns(10);
		formPanel.add(streetField);
		
		JLabel cityLabel = new JLabel("City");
		cityLabel.setBounds(506, 10, 75, 13);
		cityLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		formPanel.add(cityLabel);
		
		cityField = new JTextField();
		cityField.setBounds(506, 25, 114, 28);
		cityField.setColumns(10);
		formPanel.add(cityField);
		
		JLabel stateLabel = new JLabel("State");
		stateLabel.setBounds(630, 10, 75, 13);
		stateLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		formPanel.add(stateLabel);
		
		stateField = new JTextField();
		stateField.setBounds(630, 25, 114, 28);
		stateField.setColumns(10);
		formPanel.add(stateField);
		
		JLabel zipLabel = new JLabel("Zip");
		zipLabel.setBounds(10, 67, 75, 13);
		zipLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		formPanel.add(zipLabel);
		
		zipField = new JTextField();
		zipField.setBounds(10, 82, 114, 28);
		zipField.setColumns(10);
		formPanel.add(zipField);
		
		JButton addButton = new JButton("Add");
		addButton.setBounds(206, 111, 85, 21);
		formPanel.add(addButton);
		
		JButton editButton = new JButton("Edit");
		editButton.setBounds(301, 111, 85, 21);
		formPanel.add(editButton);
		
		JButton deleteButton = new JButton("Delete");
		deleteButton.setBounds(396, 111, 85, 21);
		formPanel.add(deleteButton);		
		
		cards.add(authorPanel, "Author");
		cards.add(bookPanel, "Book");
		cards.add(historyPanel, "Checkout History");
		
		//connect JList elements to CardLayout panels
		linkList.addListSelectionListener(event -> {
			String value = linkList.getSelectedValue().toString();
			/*The CardLayout class manages two or more components
				(usually JPanel instances) that share the same display space.*/
			CardLayout cl = (CardLayout) (cards.getLayout());
			cl.show(cards, value);
		});
	}
}
