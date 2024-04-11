package librarysystem.screens;

import javax.swing.JFrame;
import javax.swing.JPanel;

import librarysystem.LibWindow;
 
import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JLabel;
import javax.swing.JSplitPane;
import javax.swing.JList;
 
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
		add(splitPane, BorderLayout.CENTER);
	}
	
	/* Organize panels into a CardLayout */
	public void createPanels() {
 
        JPanel panel1 = new JPanel();
        JLabel label1 = new JLabel("Item 1 Panel");
        panel1.add(label1);
        
        JPanel panel2 = new JPanel();
        JLabel label2 = new JLabel("Item 2 Panel");
        panel2.add(label2);
        
        JPanel panel3 = new JPanel();
        JLabel label3 = new JLabel("Item 3 Panel");
        panel3.add(label3);
        
        JPanel panel4 = new JPanel();
        JLabel label4 = new JLabel("Item 4 Panel");
        panel4.add(label4);
        
		cards = new JPanel(new CardLayout());
		cards.add(panel1, "Home");
		cards.add(panel2, "Member");
		cards.add(panel3, "Author");
		cards.add(panel4, "Book");
		
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
 
