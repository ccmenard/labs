
package lab5;
//import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.*;
import java.util.*;

public class FirstGui extends JFrame 
{
	private static final long serialVersionUID = 7528590832927859432L;
	private JTextField aTextField = new JTextField();
	private JButton doubleButton = new JButton("Calculate!");
	private int numOfCats = 3;
	private Random random = new Random();
	
	private class DoubleActionListener implements ActionListener
	{
	
	}
	public FirstGui() 
	{
		super("Are you a cat lady?");
		
		//JFrame aFrame = new JFrame("Are you a cat lady?");
		setSize(200,200);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(doubleButton, BorderLayout.SOUTH);
		getContentPane().add(aTextField, BorderLayout.CENTER);
		aTextField.setText("How many cats do you have?: " + numOfCats);
	}
	
	public static void main(String[] args)
	{
		new FirstGui();
		
	}
 
}

