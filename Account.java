package hw06;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Account extends JPanel implements Runnable,ActionListener  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private double balance;
	private JTextField amountField;
	JFrame frame;
	JPanel subPanel;
	JPanel subPanel2;
	JPanel panel;
	JLabel label;
	

	
	public Account() {
		super();

		balance = 0;
		
		setLayout(new BorderLayout());
	    label = new JLabel("Current Balance is : "+ " " + balance + "$" );
		label.setFont(new Font("TimesRoman", Font.PLAIN, 18));

	    
	    add(label, BorderLayout.NORTH);

	    JPanel subPanel = new JPanel();
	    subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.Y_AXIS));
	
	    
	    Button depositButton = new Button("Deposit");   /// Creating the Buttons
	    Button withdrawButton = new Button("Withdraw");
	    Button exitButton = new Button("Exit");
	    
	    
	    depositButton.setBackground(Color.RED);  // Coloring the Buttons
	    withdrawButton.setBackground(Color.RED);  
	    exitButton.setBackground(Color.RED);

	    
	    subPanel.add(depositButton );   /// Adding Buttons to the SubPanel
	    subPanel.add( withdrawButton);
	    subPanel.add( exitButton );
	    
	    
	    depositButton.addActionListener(this);  //Invoking the ActionListener  
	    withdrawButton.addActionListener(this);
	    exitButton.addActionListener(this);
	    
	    add(subPanel,BorderLayout.EAST);
	    
	    
	    JPanel subPanel2 = new JPanel();
	    JLabel label2 = new JLabel("Amount: ");
		label2.setFont(new Font("TimesRoman", Font.BOLD, 18));

	    amountField = new JTextField(String.format("%.02f", balance),10);

	    subPanel2.add(label2);
	    subPanel2.add(  amountField );
	    add(subPanel2,BorderLayout.CENTER); 

	    
	    
	    javax.swing.SwingUtilities.invokeLater(this);
	

	}
	public void withdraw(double amount) {
		
		if(amount <= balance) {
				
			balance = balance - amount;
			label.setText("Current Balance is : " + balance + "$" );
		}	
		else {
			JOptionPane.showMessageDialog(frame,
				    "You do not have enough money to withdraw! ",
				    "Inane warning",
				    JOptionPane.ERROR_MESSAGE);
		}
	}
		
	public void deposit(double amount) {
			
		balance = balance + amount;
		label.setText("Current Balance is : " + balance + "$" );			
	}

	 
	@Override
	public void run() {
		frame = new JFrame("Account");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(this);
		frame.pack();
		frame.setSize(350, 200);
		frame.setVisible(true);



	}

		
	@Override
	   public void actionPerformed(ActionEvent e) {
		
	   	String ac=e.getActionCommand();
	   
	   	
	   	
	   	if (ac.equals("Exit")) 
	   	{
	   		frame.dispose(); // handle Exit button
	   	} 
	   	else if (ac.equals("Deposit")) 
	   		
	   	{ 	
	   		String amtStr = amountField.getText();
	   		double amount=Double.parseDouble(amtStr);
	   		deposit(amount);
	   		} 
	   	else if (ac.equals("Withdraw")) 
	   	{ 	
	   		String amtStr = amountField.getText();
	   		double amount=Double.parseDouble(amtStr);
	   		withdraw(amount);  // handle Withdraw button
	   	} 
	   	else System.out.println("Button " + ac + " press ignored");		
	   }
	
	
	
	public static void main(String[] args) {

		new Account(); 
		
	}
}
