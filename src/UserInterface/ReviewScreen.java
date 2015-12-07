package UserInterface;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public abstract class ReviewScreen extends JFrame implements ActionListener {

	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 400;
	
	protected JButton submit_review;
	protected JPanel main;
	protected JTextArea review;
	
	protected ReviewScreen() {
		super();
		this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		this.setVisible(true);
		this.setResizable(false);
		
		main = new JPanel();
		main.setBounds(0,0,DEFAULT_WIDTH, DEFAULT_HEIGHT);
		this.add(main);
		
		submit_review = new JButton("Submit");
		submit_review.setBounds(DEFAULT_WIDTH/2-150, DEFAULT_HEIGHT-100, 300, 50);
		submit_review.addActionListener(this);
		this.main.add(submit_review);
		
		review = new JTextArea();
		review.setBounds(0, 0, DEFAULT_WIDTH, DEFAULT_HEIGHT-150);
		review.setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT-150));
		review.setEditable(true);
		review.setText("Type your review here...");
		this.main.add(review);
		
    }
    
    protected void close()
	{
		setVisible(false);
		dispose();
	}
	
	
	
	
	
	
}
