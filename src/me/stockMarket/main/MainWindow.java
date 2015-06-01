package me.stockMarket.main;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import yahoofinance.YahooFinance;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class MainWindow {

	JFrame frame;

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
		lblStockMarketSimulator.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblStockMarketSimulator.setBounds(96, 11, 257, 31);
		frame.getContentPane().add(lblStockMarketSimulator);
		
		JLabel lblCreatedByTaylor = new JLabel("Created by: Taylor Mijangos, Alec English, and Damian Ugalde");
		lblCreatedByTaylor.setBounds(30, 38, 366, 14);
		frame.getContentPane().add(lblCreatedByTaylor);
		
		JButton btnMyStocks = new JButton("My Stocks");
		btnMyStocks.setBounds(96, 160, 212, 23);
		frame.getContentPane().add(btnMyStocks);
		
		JButton btnCheckBalance = new JButton("Check Balance");
		btnCheckBalance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "Your balance is $" + StockMarket.portfolio.getBalance());
			}
		});
		btnCheckBalance.setBounds(96, 126, 212, 23);
		frame.getContentPane().add(btnCheckBalance);
		
		JButton btnCheckStockInfo = new JButton("Check Stock Info");
		btnCheckStockInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String input = JOptionPane.showInputDialog("Please enter a Stock Symbol").toUpperCase();
				YahooFinance.get(input).print();
			}
		});
		btnCheckStockInfo.setBounds(96, 194, 212, 23);
		frame.getContentPane().add(btnCheckStockInfo);
		
		JButton btnQuit = new JButton("Quit");
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnQuit.setBounds(96, 228, 212, 23);
		frame.getContentPane().add(btnQuit);
	}
}
