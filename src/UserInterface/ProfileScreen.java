package UserInterface;

import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public abstract class ProfileScreen extends JFrame implements ActionListener {
	
	private static final int DEFAULT_WIDTH = 800;
	private static final int DEFAULT_HEIGHT = 500;
	
	// subviews
	private JLabel title;
	private JPanel main;
	private Font title_font;
	
	
	protected ProfileScreen(String title) {
		super();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.initializeGUI();
		this.addSubviews();
		this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		this.setVisible(true);
		this.setResizable(false);
		
		
		this.title = new JLabel(title);
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
	}
	
	protected abstract void populateReviews();
	
	
	
}
