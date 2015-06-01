package me.stockMarket.main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class MainWindow {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblStockMarketSimulator = new JLabel("Stock Market Simulator");
		lblStockMarketSimulator.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblStockMarketSimulator.setBounds(156, 11, 116, 14);
		frame.getContentPane().add(lblStockMarketSimulator);
		
		JLabel lblCreatedByTaylor = new JLabel("Created by: Taylor Mijangos, Alec English, and Damian Ugalde");
		lblCreatedByTaylor.setBounds(58, 36, 321, 14);
		frame.getContentPane().add(lblCreatedByTaylor);
		
		JButton btnMyStocks = new JButton("My Stocks");
		btnMyStocks.setBounds(96, 160, 212, 23);
		frame.getContentPane().add(btnMyStocks);
		
		JButton btnCheckBalance = new JButton("Check Balance");
		btnCheckBalance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "Your balance is $1000");
			}
		});
		btnCheckBalance.setBounds(96, 126, 212, 23);
		frame.getContentPane().add(btnCheckBalance);
		
		JButton btnCheckStockInfo = new JButton("Check Stock Info");
		btnCheckStockInfo.setBounds(96, 194, 212, 23);
		frame.getContentPane().add(btnCheckStockInfo);
		
		JButton btnQuit = new JButton("Quit");
		btnQuit.setBounds(96, 228, 212, 23);
		frame.getContentPane().add(btnQuit);
	}
}
