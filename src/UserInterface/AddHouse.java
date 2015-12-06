package UserInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import objects.Address;
import objects.House;
import objects.Landlord;
import mainpackage.QueryManager;

public class AddHouse extends JFrame implements ActionListener {

	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 400;
	
	private JPanel main;
	private JTextField street;
	private JTextField city;
	private JTextField state;
	private JTextField zipcode;
	private JTextField landlord;
	private JButton add;
	
	public AddHouse() {
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
		
		street = new JTextField("Street");
		street.setBounds(DEFAULT_WIDTH/6, DEFAULT_HEIGHT/5-40, (DEFAULT_WIDTH*2)/3, 30);
		
		city = new JTextField("City");
		city.setBounds(DEFAULT_WIDTH/6, (DEFAULT_HEIGHT/5), (DEFAULT_WIDTH*2)/3, 30);
		
		state = new JTextField("State");
		state.setBounds(DEFAULT_WIDTH/6, DEFAULT_HEIGHT/5+40, (DEFAULT_WIDTH*2)/3, 30);
		
		zipcode = new JTextField("Zip");
		zipcode.setBounds(DEFAULT_WIDTH/6, DEFAULT_HEIGHT/5+80, (DEFAULT_WIDTH*2)/3, 30);
		
		landlord = new JTextField("Landlord");
		landlord.setBounds(DEFAULT_WIDTH/6, DEFAULT_HEIGHT/5+120, (DEFAULT_WIDTH*2)/3, 30);
		
		add = new JButton("Add House");
		add.setBounds((DEFAULT_WIDTH/2)-50, DEFAULT_HEIGHT-100, 100, 50);
		add.addActionListener(this);
		
		
		
	}
	
	private void addSubviews() {
		this.main.add(street);
		this.main.add(city);
		this.main.add(state);
		this.main.add(zipcode);
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
		try {
			QueryManager.getInstance().addHouse(new House(new Address(street.getText(), city.getText(), state.getText(), Integer.parseInt(zipcode.getText()))), new Landlord(landlord.getText()));
			close();
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this,
				    e.getMessage(),
				    "Inane error",
				    JOptionPane.ERROR_MESSAGE);
		}	
	}
}
