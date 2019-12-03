package lab6;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.*;

public class AAQuizGUI extends JFrame
{
	public static String[] SHORT_NAMES = {"A","R","N","D","C","Q","E","G","H",
			"I","L","K","M","F","P","S","T","W","Y","V"};
	public static String[] FULL_NAMES = {"alanine","arginine","asparagine","aspartic acid","cysteine",
			"glutamine","glutamic acid","glycine","histidine","isoleucine","leucine","lysine","methionine",
			"phenylalanine","proline","serine","threonine","tryptophan","tyrosine","valine"} ;
	private static final long serialVersionUID = 7528590832927859569L;
	private JLabel textLabel = new JLabel();
	private JButton doubleButton2 = new JButton("End Quiz");
	private JButton doubleButton1 = new JButton("Start Quiz");
	int numCorrect = 0;
	int numWrong = 0;
	private class DoubleActionListener implements ActionListener
	{
		
		public void actionPerformed(ActionEvent e)
		{
			
			long startTime = System.currentTimeMillis();
			double elapsedSeconds = 0; 
			double setTimer = 10;
			
			while ( elapsedSeconds <= setTimer)
			{
				Random random = new Random();
				int randomnumber = random.nextInt(FULL_NAMES.length);
				String questionFullAA = FULL_NAMES[randomnumber];
				String questionShortAA = SHORT_NAMES[randomnumber];
				String inputAAanswer = JOptionPane.showInputDialog("Enter one letter uppercase abrrevation for " + questionFullAA);
				if(inputAAanswer != null)
				{
					if(inputAAanswer.toUpperCase().contentEquals(questionShortAA))
					{
						 
						numCorrect++;
						
					}
					else
					{
						
						numWrong++;
						
					}
					elapsedSeconds = (int) ((System.currentTimeMillis() - startTime) / 1000f);
					double timeLeft = setTimer - elapsedSeconds;
					updateTextField(numCorrect,numWrong,timeLeft);
				}
				else
				{
					elapsedSeconds = 0;
					numCorrect = 0;
					numWrong = 0;
					updateTextFieldCancel();
					break;
					
				}
				
			}
		
		}
			
	}
		private class CloseListener implements ActionListener
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
		}
		private void updateTextField(int numCorrect, int numWrong, double timeLeft)
		{
			
			if(timeLeft > 0)
			{
				int total = numCorrect + numWrong;
				textLabel.setText(numCorrect + " correct out of " + total + "\n" + "Countdown: " + timeLeft + " seconds");
				validate();	
			}
			
			else
			{
				int total = numCorrect + numWrong;
				textLabel.setText("Out of time!" + "\n" + numCorrect + " correct out of " + total);
				validate();
			}
		}
		private void updateTextFieldCancel()
		{
			textLabel.setText("You cancelled the quiz.");
		}
		
		private JPanel getNewPanel()
		{
			JPanel newPanel = new JPanel();
			newPanel.setLayout(new GridLayout(1,2));
			newPanel.add(doubleButton1);
			newPanel.add(doubleButton2);
			doubleButton1.addActionListener(new DoubleActionListener());
			return newPanel;
			
		}
		public AAQuizGUI() 
		{
			super("Claire's Amino Acid Quiz");
			setSize(500,500);
			setLocationRelativeTo(null);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			getContentPane().setLayout(new BorderLayout());
			getContentPane().add(doubleButton1, BorderLayout.EAST);
			getContentPane().add(doubleButton2, BorderLayout.WEST);
			doubleButton2.addActionListener(new CloseListener());
			getContentPane().add(textLabel, BorderLayout.CENTER);
			getContentPane().add(getNewPanel(), BorderLayout.SOUTH);
			textLabel.setHorizontalAlignment(SwingConstants.CENTER);
			textLabel.setVerticalAlignment(SwingConstants.TOP);
			
			setVisible(true);
			
			
		}
		
		public static void main(String[] args)
		{
			new AAQuizGUI();
	
		}

}


