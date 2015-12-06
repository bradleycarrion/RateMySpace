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
	private JTextField address;
	private JTextField landlord;
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
		
		address = new JTextField();
		address.setBounds(DEFAULT_WIDTH/6, DEFAULT_HEIGHT/5, (DEFAULT_WIDTH*2)/3, 30);
		
		landlord = new JTextField();
		landlord.setBounds(DEFAULT_WIDTH/6, DEFAULT_HEIGHT/5-40, (DEFAULT_WIDTH*2)/3, 30);
		
		add = new JButton("Add Landlord");
		add.setBounds((DEFAULT_WIDTH/2)-50, DEFAULT_HEIGHT-200, 100, 50);
		add.addActionListener(this);
		
		
		
	}
	
	private void addSubviews() {
		this.main.add(address);
		this.main.add(landlord);
		this.main.add(add);
		
		
	}
	
	private void close()
	{
		setVisible(false);
		dispose();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		close();
		
	}
	
	
}
