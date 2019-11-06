package lab6;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Random;

import javax.swing.*;

public class AAQuizGUI extends JFrame
{
	public String[] SHORT_NAMES = {"A","R","N","D","C","Q","E","G","H",
			"I","L","K","M","F","P","S","T","W","Y","V"};
	public String[] FULL_NAMES = {"alanine","arginine","asparagine","aspartic acid","cysteine",
			"glutamine","glutamic acid","glycine","histidine","isoleucine","leucine","lysine","methionine",
			"phenylalanine","proline","serine","threonine","tryptophan","tyrosine","valine"} ;
	Random random = new Random();
	int randomnumber = random.nextInt(FULL_NAMES.length);
	private static final long serialVersionUID = 7528590832927859569L;
	private JTextField aTextField = new JTextField();
	private JButton doubleButton2 = new JButton("End Quiz");
	private JButton doubleButton1 = new JButton("Start Quiz");
	private String questionFullAA = FULL_NAMES[randomnumber];
	private String questionShortAA = SHORT_NAMES[randomnumber];
	private String inputAAanswer = String.valueOf(JOptionPane.showInputDialog("Enter one letter uppercase abrrevation for " + questionFullAA));
	int numCorrect = 0;
	int numWrong = 0;
	int total = numCorrect + numWrong;
	private String response = ""; 
	boolean boo = true;
	
	
	private class DoubleActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(inputAAanswer == questionShortAA || (e.getModifiers() & ActionEvent.SHIFT_MASK) !=0)
			{
				String response = "Correct!";
				updateTextField(response);
				numCorrect++;
				
				
			}
			else
			{
				String response = "Wrong!";
				updateTextField(response);
				numWrong++;
				
			}
			
		}
	}
		private void updateTextField(String response)
		{
			
			aTextField.setText("You entered " + inputAAanswer + ". The correct answer is :" + questionShortAA + ".");
			validate();
			
			JOptionPane.showMessageDialog(null, numCorrect + "Correct out of " + total);
		}
		public AAQuizGUI() 
		{
			super("Claire's Amino Acid Quiz");
			setSize(300,300);
			setLocationRelativeTo(null);
			setVisible(true);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			getContentPane().setLayout(new BorderLayout());
			getContentPane().add(doubleButton1, BorderLayout.EAST);
			getContentPane().add(doubleButton2, BorderLayout.WEST);
			doubleButton1.addActionListener(new DoubleActionListener());
			//doubleButton2.close;
			getContentPane().add(aTextField, BorderLayout.CENTER);
			updateTextField(this.response);
			
		}
		
		public static void main(String[] args)
		{
			new AAQuizGUI();
	
		}

}


