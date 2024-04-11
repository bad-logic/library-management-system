package librarysystem.screens;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import librarysystem.LibWindow;
 
import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JLabel;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JList;
import javax.swing.JButton;

import java.awt.Font;
import java.util.Arrays;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import business.ControllerInterface;
import business.LibraryMember;
import business.SystemController;
 
public class DashBoardScreen extends JFrame implements LibWindow {
	
	public static final DashBoardScreen INSTANCE = new DashBoardScreen();
	private static final long serialVersionUID = 1L;
	
	private boolean isInitialized = false;
	
	String[] links;
	JList<String> linkList;
	JPanel cards;
	JPanel buttonBar;
	
	
	public DashBoardScreen() {
		init();
	}
	
	@Override
	public boolean isInitialized() {
		return isInitialized;
	}
	
	@Override
	public void isInitialized(boolean val) {
		isInitialized = val;
	}
	
	@Override
	public void init() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 737, 495);
		String[] items = {"Home", "Member", "Author", "Book"};
		linkList = new JList<String>(items);				
		createPanels();	
		// set up split panes
		// arguments are orientation, left component, right component
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, linkList, cards);
		splitPane.setDividerLocation(50);
		//default layout for JFrame is BorderLayout; add method
		//adds to contentpane
		getContentPane().add(splitPane, BorderLayout.CENTER);
	}
	
	/* Organize panels into a CardLayout */
	public void createPanels() {
 
        JPanel home = new JPanel();
        JLabel label1 = new JLabel("Item 1 Panel");
        home.add(label1);
        
        JPanel member = new JPanel();
        member.setLayout(null);
        JLabel label2 = new JLabel("Member");
        label2.setBounds(259, 9, 38, 13);
        member.add(label2);
        
        JPanel author = new JPanel();
        JLabel label3 = new JLabel("Item 3 Panel");
        author.add(label3);
        
        JPanel book = new JPanel();
        JLabel label4 = new JLabel("Item 4 Panel");
        book.add(label4);
        
		cards = new JPanel(new CardLayout());
		cards.add(home, "Home");
		cards.add(member, "Member");
		new MemberPanel().memberView(member);
		cards.add(author, "Author");
		cards.add(book, "Book");
		
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
 
