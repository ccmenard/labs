package practice;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class timer extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	Timer tm;
	int i = 0;
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					timer frame = new timer();
					frame.setVisible(true);
					
				}
				catch(Exception e)
				{
					e.printStackTrace();
					
				}
			}
		});
	}
	public timer()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100,100,524,300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5,5,5,5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		
		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener()
				{
			public void actionPerformed(ActionEvent argo0) {
				tm.start();
				
			}
				});
		btnStart.setBounds(29,87,89,23);
		contentPane.add(btnStart);
		
		JButton buttonStop = new JButton("Stop");
		buttonStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tm.stop();
			}
		});
		buttonStop.setBounds(380,87,89,23);
		contentPane.add(buttonStop);
		
		JLabel label = new JLabel("........");
		label.setFont(new Font("Tahoma", Font.BOLD, 16));
		label.setBounds(220,73,76,50);
		contentPane.add(label);
		tm = new Timer(1000,new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				label.setText(Integer.toString(i));
				i++;
			}
		});
		
		

	}
}
