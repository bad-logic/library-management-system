package librarysystem;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSplitPane;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Rectangle;

public class Dashboard extends JFrame {
	public static final Dashboard INSTANCE = new Dashboard();
	private static final long serialVersionUID = 1L;
	String[] links;
	JList<String> linkList;
	//context for CardLayout
	JPanel cards;
	JPanel buttonBar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dashboard frame = new Dashboard();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	public Dashboard() {
//	public void init() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 737, 495);
		String[] items = {"Home", "Member", "Author", "Book"};
		linkList = new JList<String>(items);				
		createPanels();	
		// set up split panes
		// arguments are orientation, left component, right component
		JSplitPane splitPane = new JSplitPane(
			JSplitPane.HORIZONTAL_SPLIT, linkList, cards);
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
