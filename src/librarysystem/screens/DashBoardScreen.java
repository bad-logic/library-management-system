package librarysystem.screens;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import business.SystemController;
import dataaccess.Auth;
import librarysystem.Util;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.AbstractListModel;
import java.awt.CardLayout;
import javax.swing.JButton;
import java.awt.event.ActionEvent;


public class DashBoardScreen extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JList linkList;
	private JPanel cards;

	public DashBoardScreen() {
		init();
	}

	/**
	 * Create the frame.
	 */
	public void init() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 984, 752);
		contentPane = new JPanel();
		this.setResizable(false);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel topPanel = new JPanel();
		topPanel.setBounds(10, 10, 960, 60);
		topPanel.setBackground(new Color(233, 185, 22));
		contentPane.add(topPanel);
		topPanel.setLayout(null);
		
		JLabel mainLabel = new JLabel("Library MS");
		mainLabel.setBounds(375, 10, 196, 40);
		mainLabel.setForeground(new Color(0, 0, 0));
		mainLabel.setFont(new Font("Tahoma", Font.BOLD, 32));
		topPanel.add(mainLabel);
		
		JButton logoutButton = new JButton("Logout");
		logoutButton.addActionListener((ActionEvent e) -> {
				SystemController.currentAuth = null;
				this.dispose();
				LoginScreen loginScreen = new LoginScreen();
            	loginScreen.setTitle("Library Application");
            	loginScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            	loginScreen.init();
                Util.centerFrameOnDesktop(loginScreen);
                loginScreen.setVisible(true);
		});
		logoutButton.setForeground(new Color(96, 66, 66));
		logoutButton.setBackground(new Color(218, 51, 51));
		logoutButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		logoutButton.setBounds(866, 20, 85, 21);
		topPanel.add(logoutButton);

		
		JPanel leftPanel = new JPanel();
		leftPanel.setBackground(new Color(255, 255, 255));
		leftPanel.setBounds(10, 80, 156, 625);
		contentPane.add(leftPanel);
		leftPanel.setLayout(null);
		
		
		
		linkList = new JList();
		linkList.setFont(new Font("Tahoma", Font.BOLD, 14));
		linkList.setModel(new AbstractListModel() {
			
			String[] values = getMenus();
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

		// HOME TAB CONTENT
		JPanel homePanel = new HomeTab();
		homePanel.setBackground(new Color(255, 255, 255));
		homePanel.setBounds(0, 0, 794, 625);
		homePanel.setLayout(null);

		// MEMBER TAB CONTENT
		JPanel memberTab = new MemberTab();
		memberTab.setBackground(new Color(255, 255, 255));
		memberTab.setBounds(0, 0, 794, 625);
		memberTab.setLayout(null);

		// AUTHOR TAB CONTENT
		JPanel authorTab = new AuthorTab();
		authorTab.setBackground(new Color(255, 255, 255));
		authorTab.setBounds(0, 0, 794, 625);
		authorTab.setLayout(null);

		// BOOK TAB CONTENT
		JPanel bookTab= new BookTab();
		bookTab.setBackground(new Color(255, 255, 255));
		bookTab.setBounds(0, 0, 794, 625);
		bookTab.setLayout(null);

		// CHECKOUT TAB CONTENT
		JPanel checkoutHistoryPanel = new BookCheckoutTab();
		checkoutHistoryPanel.setBackground(new Color(255, 255, 255));
		checkoutHistoryPanel.setBounds(0, 0, 794, 625);
		checkoutHistoryPanel.setLayout(null);
		
		// adding tabs to the card
		cards = new JPanel(new CardLayout());
		cards.setBackground(new Color(255, 255, 255));
		cards.setBounds(176, 80, 794, 625);
		contentPane.add(cards);

		cards.add(homePanel, "Home");
		cards.add(memberTab, "Member");
		
		cards.add(authorTab, "Author");
		cards.add(bookTab, "Book");
		cards.add(checkoutHistoryPanel, "Checkout");
		
		//connect JList elements to CardLayout panels
		linkList.addListSelectionListener(event -> {
			String value = linkList.getSelectedValue().toString();
			/*The CardLayout class manages two or more components
				(usually JPanel instances) that share the same display space.*/
			CardLayout cl = (CardLayout) (cards.getLayout());
			cl.show(cards, value);
		});
	}
	
	private String[] getMenus() {
		Auth[] auth= SystemController.currentAuth;
		
		if (auth.length == 2) {
			return new String[] {"Home", "Member", "Author", "Book", "Checkout"};
		}
		
		if(auth[0] == Auth.LIBRARIAN) {
			return new String[] {"Home", "Book" ,"Checkout"};
		}
		
		return new String[] {"Home", "Member", "Author", "Book"}; 
	}
}
