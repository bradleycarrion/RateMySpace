package UserInterface;

import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public abstract class ProfileScreen extends JFrame implements ActionListener {
	
	private static final int DEFAULT_WIDTH = 800;
	private static final int DEFAULT_HEIGHT = 500;
	
	// subviews
	private JLabel title;
	private JLabel overall_rating;
	private JPanel main;
	private Font title_font;
	protected int rating_val;
	protected JButton review;
	
	
	protected ProfileScreen(String title) {
		super();
		this.title = new JLabel(title);
		this.rating_val = 0;	// figure out how to get the rating of an object
		this.initializeGUI();
		this.addSubviews();
		this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		this.setVisible(true);
		this.setResizable(false);
	}

	private void addSubviews() {
		this.main.add(title);
	}

	private void initializeGUI() {
		// the main panel get's created and added
    	main = new JPanel();
		main.setLayout(null);
		this.add(main);
		
		// creation of the title
		title_font = new Font("Lucida Calligraphy", 0, 45);
		title.setFont(title_font);
		title.setBounds((DEFAULT_WIDTH/4)+20, 30, DEFAULT_WIDTH/2, 70);
		
		overall_rating = new JLabel("The overall rating is: " + rating_val);
		overall_rating.setBounds((DEFAULT_WIDTH/4)+20, 100, DEFAULT_WIDTH/2, 70);
		
		review = new JButton("Review this Landlord");
		review.setBounds(DEFAULT_WIDTH/2-40, DEFAULT_HEIGHT-50, 80, 30);
		review.addActionListener(this);
	}
	
	protected abstract void populateReviews();
	
	
	
}
