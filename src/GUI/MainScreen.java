package GUI;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MainScreen extends JFrame {
	
	private static final long serialVersionUID = 1L;
	static final int DEFAULT_WIDTH = 854;
	static final int DEFAULT_HEIGHT = 480;
	
	//GUI
	JButton button;

	public MainScreen() {
		super();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.initalizeGUI();
		this.addSubviews();
		this.pack();
		this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		this.setVisible(true);
	}
	
	private void addSubviews() {
		this.addButton();
	}
	
	private void initalizeGUI() {
		button = new JButton();
	}
	
	private void addButton() {
		//Style
		int width  = 50;
		int height = 50;
		int x      = 0;
		int y      = 0;
		String text = "Review";
		
		//Add to subview
		this.button.setSize(width, height);
		this.button.setLocation(x, y);
		this.button.setText(text);
		this.add(this.button);
	}
}
