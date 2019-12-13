//worked with Lauren and Jon
// Dev also helped me
package lab8;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import java.util.List;
import java.util.concurrent.Semaphore;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;


public class MultiThread extends JFrame 
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3276104691742818948L;
	
	private volatile boolean cancel = false;
	private static List <Integer> primeNumList = Collections.synchronizedList(new ArrayList<Integer>());
	private static final int g = Runtime.getRuntime().availableProcessors();
	private static long startTime;
	private static long time;
	private final MultiThread thisFrame;
	
	private final JTextArea threadArea = new JTextArea();
	private final JButton startButton = new JButton("Start");
	private final JButton cancelButton = new JButton("Cancel");
	private final JButton stopButton = new JButton("Stop and Close");
	
	
	private class FindPrime implements Runnable
	{
		private final int i;
		private final int inputInt;
		private final Semaphore sema;
		
		private FindPrime(int i, int inputInt, Semaphore s) 
		{
			this.i = i;
			this.inputInt = inputInt;
			this.sema = s;
		}
		
		public void run() throws RuntimeException
		{
			try
			{
			long startTime = System.currentTimeMillis();
			for(int x = i; x < inputInt && !cancel; x = x + g) 
			{
				if(isItPrime(x)) 
				{
					primeNumList.add(x);
					if(System.currentTimeMillis() - startTime > 500) 
					{
						float time = (System.currentTimeMillis() - startTime) /1000f;
						final String output = "Total: " + primeNumList.size() + " prime numbers. Time: " + time + " seconds.";
						SwingUtilities.invokeLater(new Runnable() 
						{
							@Override
							public void run() 
							{
								threadArea.setText(output);
							}
						});
	
					}
				}
			}
			}
			catch(Exception e)
			{
				e.printStackTrace();
				System.out.println("Exit");
				System.exit(1);
			}
			finally
			{
			sema.release();
			}
		
		}
	}
	private void updateInput(float time) 
	{
		final StringBuffer string = new StringBuffer();
		synchronized(primeNumList) 
		{
			Collections.sort(primeNumList);
			for(Integer w : primeNumList) 
			{
				string.append(w + "\n");
			}
		}
		if(cancel) 
		{
			string.append("Canceled calculations\n");
		
		}
		
		string.append("Elapsed Time: " + time + "seconds.");
		SwingUtilities.invokeLater(new Runnable() 
		{
			@Override
			public void run() 
			{
				cancel = false;
				startButton.setEnabled(true);
				cancelButton.setEnabled(true);
				threadArea.setText((cancel ? "Closed" : "")+string.toString());
			}
		});
	}
	
	
	private class Processing implements Runnable
	{
		 private final int inputInt;
		 private Semaphore sema = new Semaphore(g);
		 
		 private Processing(int inputInt) 
		 {
			 this.inputInt = inputInt;
		 }
		 public void run() 
		 {
			 startTime = System.currentTimeMillis();
			 for(int i = 0; i < g; i ++) 
			 {
				 try 
				 {
					 sema.acquire();
				 }
				 catch(InterruptedException exception) 
				 {
					 exception.printStackTrace();
				 }
			 FindPrime findPrime = new FindPrime(i+1, inputInt, sema);
			 new Thread(findPrime).start();
			 }
		 for(int j = 0; j < g; j++) 
		 {
			 try 
			 {
				 sema.acquire();
			 }
			 catch(InterruptedException exception) 
			 {
				 exception.printStackTrace();
			 }
		 }
		time = (long) ((System.currentTimeMillis()-startTime) /1000f);
		 updateInput(time);
		
		 }

	}
	private boolean isItPrime(int a)
	{
		if(a > 0)
		{
			for( int x=2; x < a -1; x++)
		
			if( a % x == 0)
				return false;
		
		return true;
		}
		else
		{
			updateNegativeInput();
			return false;
		}
		
	}
	private void updateNegativeInput()
	{
		threadArea.setText("You entered a negative number or invalid char. Please enter postive large number.");
	}
	private class CancelOpt implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0)
		{
			cancel = true;
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
	private void addActionListeners()
	{
		stopButton.addActionListener(new CloseListener());
		cancelButton.addActionListener(new CancelOpt());
	
		startButton.addActionListener(new ActionListener() 
		{
				public void actionPerformed(ActionEvent e)
				{
					
					String inputIntStr = JOptionPane.showInputDialog("Enter a large integer: ");
					Integer inputInt =null;
					primeNumList.clear();
					try
					{
						inputInt = Integer.parseInt(inputIntStr);
					}
					catch(Exception ex)
					{
						JOptionPane.showMessageDialog(
								thisFrame, ex.getMessage(), "Error, Input Integer", JOptionPane.ERROR_MESSAGE);
						ex.printStackTrace();
					}
					
					if( inputInt != null)
					{
						threadArea.setText("");
						startButton.setEnabled(false);
						cancelButton.setEnabled(true);
						cancel = false;
						new Thread(new Processing(inputInt)).start();
					}
				}});
		}
	
	public MultiThread() 
	{
		super("Claire's Fast Prime Number Generator");
		this.thisFrame = this;
		cancelButton.setEnabled(false);
		threadArea.setEditable(false);
		setSize(500,500);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(startButton, BorderLayout.EAST);
		getContentPane().add(cancelButton, BorderLayout.WEST);
		getContentPane().add(stopButton, BorderLayout.SOUTH);
		getContentPane().add( new JScrollPane(threadArea),  BorderLayout.CENTER);
	}	
	
	public static void main(String[] args)
	{
		MultiThread mult = new MultiThread();
		mult.addActionListeners();
		mult.setVisible(true);
	}
}