package UserInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddLandlord extends JFrame implements ActionListener {
	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 400;
	
	private JPanel main;
	private JTextField first_name;
	private JTextField last_name;
	private JButton add;
	
	
	public AddLandlord() {
		super();
		this.initializeGUI();
		this.addSubviews();
		this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		this.setVisible(true);
		this.setResizable(false);
		
	}
	
	private void initializeGUI() {
		main = new JPanel();
		main.setLayout(null);
		this.add(main);
		
		first_name = new JTextField("First Name");
		first_name.setBounds(DEFAULT_WIDTH/6, DEFAULT_HEIGHT/5-40, (DEFAULT_WIDTH*2)/3, 30);
		
		last_name = new JTextField("Last Name");
		last_name.setBounds(DEFAULT_WIDTH/6, DEFAULT_HEIGHT/5, (DEFAULT_WIDTH*2)/3, 30);
		
		add = new JButton("Add Landlord");
		add.setBounds((DEFAULT_WIDTH/2)-50, DEFAULT_HEIGHT-200, 100, 50);
		add.addActionListener(this);		
	}
	
	private void addSubviews() {
		this.main.add(first_name);
		this.main.add(last_name);
		this.main.add(add);
	}
	
	private void close()
	{
		setVisible(false);
		dispose();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			// use query manager here to insert a landlord
			close();
		} catch (Exception e) {
			
			
		}
	}
	
	
}
