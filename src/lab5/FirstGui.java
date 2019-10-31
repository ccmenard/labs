//Claire Menard
//Cat Lab5   

package lab5;
//import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import javax.swing.*;
//import java.util.*; 

public class FirstGui extends JFrame 
{
	private static final long serialVersionUID = 7528590832927859432L;
	private JTextField aTextField = new JTextField();
	private JButton doubleButton = new JButton("Does this make me a cat lady?!");
	//private int numOfCats = 3;
	JTextField txtInput = new JTextField("");
	//Scanner scn = new Scanner(System.in);
	private int inputNumofCats = Integer.parseInt(JOptionPane.showInputDialog("Enter how many cats you have: "));
	private int inputAgeofUser = Integer.parseInt(JOptionPane.showInputDialog("Enter age in years?: "));
	private String answer = "";
	
//	private Random random = new Random();
	
	private class DoubleActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(inputNumofCats < 4  || (e.getModifiers() & ActionEvent.SHIFT_MASK) !=0)
			{
				if(inputAgeofUser < 25 || (e.getModifiers() & ActionEvent.SHIFT_MASK) !=0)
				{
					String answer = "You are not a cat lady!";
					updateTextField(answer);
				}
			}
			else
			{
			String answer = "LOL You are a cat lady!";
			updateTextField(answer);
			
			}
		} 
	}
	private void updateTextField(String answer)
	{
		
		aTextField.setText("You have " + inputNumofCats + " cats. And are " + inputAgeofUser + " years old." + "\n" + answer);
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
		aTextField.setText("You have " + inputNumofCats + " cats. And are " + inputAgeofUser + " years old.");
		setJMenuBar(getMyMenuBar());
		updateTextField(this.answer);
		
	}
	private JMenuBar getMyMenuBar()
	{
		JMenuBar jmenuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic('F');
		jmenuBar.add(fileMenu);
		
		JMenuItem saveItem = new JMenuItem("Save");
		fileMenu.add(saveItem);
		saveItem.setMnemonic('S');
		
		saveItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				saveToFile();
			}
		});
		
		JMenuItem openItem = new JMenuItem("Open");
		openItem.setMnemonic('O');
		fileMenu.add(openItem);
		return jmenuBar;
	}
	private void saveToFile()
	{
		JFileChooser jfc = new JFileChooser();
		if (jfc.showSaveDialog(this) != JFileChooser.APPROVE_OPTION)
			return;
		if(jfc.getSelectedFile() == null)
				return; 
		File chosenFile = jfc.getSelectedFile();
		if (jfc.getSelectedFile().exists())
		{
			String message = "File " + jfc.getSelectedFile().getName() + " exists. Overwrite?";
			if (JOptionPane.showConfirmDialog(this, message) != JOptionPane.YES_OPTION)
				return;
		}
		try 
		{
			BufferedWriter writer = new BufferedWriter(new FileWriter(chosenFile));
			writer.write("You have " + inputNumofCats + " cats. And are " + inputAgeofUser + " years old. " + this.answer);
			writer.flush();
			writer.close();
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			JOptionPane.showMessageDialog(this, ex.getMessage(), "Could not write file", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	public static void main(String[] args)
	{
		new FirstGui();
		
	}
 
}


