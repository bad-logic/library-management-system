package librarysystem.screens;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;



public class HomePanel {
	public void homeView(JPanel homePanel) {
		JPanel memberStatPanel = new JPanel();
		memberStatPanel.setBackground(new Color(121, 76, 138));
		memberStatPanel.setBounds(10, 42, 230, 170);
		homePanel.add(memberStatPanel);
		memberStatPanel.setLayout(null);
		
		JLabel memberSubLabel = new JLabel("Members");
		memberSubLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		memberSubLabel.setForeground(new Color(255, 255, 255));
		memberSubLabel.setBounds(60, 121, 104, 19);
		memberStatPanel.add(memberSubLabel);
		
		JLabel memberMainTitle = new JLabel("9999");
		memberMainTitle.setFont(new Font("Tahoma", Font.BOLD, 40));
		memberMainTitle.setBackground(new Color(240, 240, 240));
		memberMainTitle.setForeground(new Color(255, 255, 255));
		memberMainTitle.setBounds(60, 41, 104, 49);
		memberStatPanel.add(memberMainTitle);
		
		JPanel authorStatPanel = new JPanel();
		authorStatPanel.setBackground(new Color(22, 170, 255));
		authorStatPanel.setBounds(280, 42, 230, 170);
		homePanel.add(authorStatPanel);
		authorStatPanel.setLayout(null);
		
		JLabel authorSubLabel = new JLabel("Authors");
		authorSubLabel.setBounds(71, 117, 78, 25);
		authorSubLabel.setForeground(Color.WHITE);
		authorSubLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		authorStatPanel.add(authorSubLabel);
		
		JLabel authorMainTitle = new JLabel("9999");
		authorMainTitle.setForeground(new Color(255, 255, 255));
		authorMainTitle.setFont(new Font("Tahoma", Font.BOLD, 40));
		authorMainTitle.setBounds(66, 51, 102, 35);
		authorStatPanel.add(authorMainTitle);
		
		JPanel bookStatPanel = new JPanel();
		bookStatPanel.setBackground(new Color(40, 167, 69));
		bookStatPanel.setBounds(554, 42, 230, 170);
		homePanel.add(bookStatPanel);
		bookStatPanel.setLayout(null);
		
		JLabel bookSubLabel = new JLabel("Books");
		bookSubLabel.setBounds(80, 118, 60, 25);
		bookSubLabel.setForeground(Color.WHITE);
		bookSubLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		bookStatPanel.add(bookSubLabel);
		
		JLabel bookMainTitle = new JLabel("9999");
		bookMainTitle.setForeground(new Color(255, 255, 255));
		bookMainTitle.setFont(new Font("Tahoma", Font.BOLD, 40));
		bookMainTitle.setBounds(62, 53, 102, 36);
		bookStatPanel.add(bookMainTitle);
	}
}