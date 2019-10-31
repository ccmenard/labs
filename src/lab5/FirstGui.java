//Claire Menard
//Cat Lab5

package lab5;
//import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
//import java.util.*; 

public class FirstGui extends JFrame 
{
	private static final long serialVersionUID = 7528590832927859432L;
	private JTextField aTextField = new JTextField();
	private JButton doubleButton = new JButton("Calculate!");
	//private int numOfCats = 3;
	JTextField txtInput = new JTextField("");
	//Scanner scn = new Scanner(System.in);
	private int inputNumofCats = Integer.parseInt(JOptionPane.showInputDialog("Enter how many cats you have: "));
	private int inputAgeofUser = Integer.parseInt(JOptionPane.showInputDialog("Enter age in years?: "));
	
//	private Random random = new Random();
	
	private class DoubleActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			
			if(inputNumofCats < 4  || (e.getModifiers() & ActionEvent.SHIFT_MASK) !=0)
			{
				if(inputAgeofUser < 25 || (e.getModifiers() & ActionEvent.SHIFT_MASK) !=0)
				{
					updateTextField("You are not a cat lady!");
					//String answer = "You are not a cat lady!"
				}
			}
			else
			{
			updateTextField("LOL You are a cat lady!");
			
			}
		}
	}
	private void updateTextField(String answer)
	{
		
		aTextField.setText(answer);
		validate();
	}
	public FirstGui() 
	{
		super("Are you a cat lady?");
		
		//JFrame aFrame = new JFrame("Are you a cat lady?");
		setSize(300,300);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(doubleButton, BorderLayout.SOUTH);
		doubleButton.addActionListener(new DoubleActionListener());
		getContentPane().add(aTextField, BorderLayout.CENTER);
		//aTextField.setText("How many cats do you have?: " + inputNumofCats);
		aTextField.setText("You have " + inputNumofCats + " cats. And are " + inputAgeofUser + " years old.");
	}
	
	public static void main(String[] args)
	{
		new FirstGui();
		
	}
 
}


