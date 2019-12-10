package lab7;

import java.awt.*;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;

public class PrimeNumbers extends JFrame
{
	private static final long serialVersionUID = 7984289289284038248L;
	private JLabel textLabel = new JLabel();
	private JTextArea threadArea = new JTextArea(5,10);
	//private final int threadID;
	private JButton startButton = new JButton("Start");
	private JButton stopButton = new JButton("Stop");
	private JButton cancelButton = new JButton("Cancel");
	private boolean cancel = false;
	ArrayList<Integer> primeNumList = new ArrayList<Integer>();
	Thread newThread = new Thread();
	
	int i = 0;
	
	public class SlowActionRunnable implements Runnable
	{
		
		@Override
		public void run()
		{
			try
			{
				int numTimes = 0;
				while (! cancel && numTimes <100)
				{
					numTimes++;
					threadArea.append("Time " + numTimes + "\n");
					Thread.sleep(1000);
					
				}
			
			}
		catch(Exception ex)
			{
			threadArea.setText(ex.getMessage());
			ex.printStackTrace();
			}
			
		try
		{
			SwingUtilities.invokeAndWait( new Runnable()
			{
				public void run()
				{
					startButton.setEnabled(true);
					cancelButton.setEnabled(false);
					
				}
			});
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		}
	}
	private class DoubleActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			cancel = false;
			startButton.setEnabled(false);
			cancelButton.setEnabled(true);
			threadArea.setText("Doing something slow\n");
			new Thread( new SlowActionRunnable()).start();
			String inputIntStr = JOptionPane.showInputDialog("Enter a large integer: ");
			calculatePrimeNumbers(inputIntStr);
			//int inputInteger = Integer.parseInt(inputIntStr);
			
			
			
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
	private class CancelActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			cancel = true;
		}
	}
	public void updateTextField(List<Integer> primeNumList)
	{
		textLabel.setText("Prime numbers :" + primeNumList);
		validate();
	}
	private List<Integer> calculatePrimeNumbers(String inputIntStr)
	{
		int inp = Integer.parseInt(inputIntStr);
		if(inputIntStr != null)
		{
			primeNumList.add(2);
			if( inp > 0)
			{
			for(int i=1; i < inp; i++)
			{
				primeNumList.add(i);
				for (int y : primeNumList)
				{
					if (i % y ==0)
					{
						primeNumList.remove(primeNumList.size() -1);
						break;
					}
					else if(i/y ==1)
					{
						break;
					}
				}
				int lastPrime = primeNumList.get(primeNumList.size()-1);
				if (i==lastPrime)
				{
					updateTextField(primeNumList);
				}
					
			}
				
				//return primeNumList;
				System.out.println(primeNumList);
				
			}
			else
			{
				updateTextFieldNegative();
			}
		}
		else
		{
			updateTextFieldCancel();
		}
		return primeNumList;
		}
		
	

	private void updateTextFieldNegative()
	{
		textLabel.setText("You entered a negative number.");
	}
	private void updateTextFieldCancel()
	{
		textLabel.setText("You canceled this program");
	}
	private JPanel getNewPanel()
	{
		JPanel newPanel = new JPanel();
		newPanel.setLayout(new GridLayout(1,2));
		newPanel.add(startButton);
		newPanel.add(stopButton);
		newPanel.add(cancelButton);
		cancelButton.addActionListener(new CancelActionListener());
		startButton.addActionListener(new DoubleActionListener());
		return newPanel;
		
		
		
	}
	/*private JLabel getNewThreadPanel()
	{
		JLabel threadPanel = new JLabel();
		//threadPanel.setLayout(new GridLayout(,));
		return threadPanel;
	}*/
	public PrimeNumbers()
	{
		super("Claire's Prime Number Generator");
		setSize(500,500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(startButton, BorderLayout.EAST);
		getContentPane().add(stopButton, BorderLayout.WEST);
		getContentPane().add(new JScrollPane(textLabel), BorderLayout.SOUTH);
		getContentPane().add(threadArea,BorderLayout.NORTH);
		getContentPane().add(getNewPanel(), BorderLayout.SOUTH);
		//getContentPane().add(getNewPanel(), BorderLayout.NORTH);
		textLabel.setHorizontalAlignment(SwingConstants.CENTER);
		textLabel.setVerticalAlignment(SwingConstants.CENTER);
		//threadLabel.setHorizontalAlignment(SwingConstants.EAST);
		//threadLabel.setVerticalAlignment(SwingConstants.EAST);

		
		
		
		stopButton.addActionListener(new CloseListener());
		
		setVisible(true);
		
	}
	public static void main(String[] args)
	{
		new PrimeNumbers();
	}
}
