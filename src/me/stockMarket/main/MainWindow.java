package me.stockMarket.main;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import yahoofinance.YahooFinance;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.math.BigDecimal;

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
		frame.setResizable(false);
		
		JLabel lblStockMarketSimulator = new JLabel("Stock Market Simulator");
		lblStockMarketSimulator.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblStockMarketSimulator.setBounds(96, 11, 257, 31);
		frame.getContentPane().add(lblStockMarketSimulator);
		
		JLabel lblCreatedByTaylor = new JLabel("Created by: Taylor Mijangos, Alec English, and Damian Ugalde");
		lblCreatedByTaylor.setBounds(44, 37, 366, 14);
		frame.getContentPane().add(lblCreatedByTaylor);
		
		JButton btnMyStocks = new JButton("My Stocks");
		btnMyStocks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Object[] temp = new Object[AccountCheck.getUserStocks().size() / 2];
				int j = 0;
				for(int i = 0; i < AccountCheck.getUserStocks().size(); i += 2){
					temp[j] = AccountCheck.getUserStocks().get(i);
					j++;
				}
				String option = (String) JOptionPane.showInputDialog(null, "Choose an existing file.",
					        null, JOptionPane.QUESTION_MESSAGE, null, temp, AccountCheck.getUserStocks().get(0));
				YahooFinance.get(option).print();

				
			}
		});
		btnMyStocks.setBounds(96, 160, 212, 23);
		frame.getContentPane().add(btnMyStocks);
		
		JButton btnCheckBalance = new JButton("Check Balance");
		btnCheckBalance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "Your balance is $" + StockMarket.portfolio.getBalance().setScale(2, BigDecimal.ROUND_HALF_UP));
			}
		});
		btnCheckBalance.setBounds(96, 126, 212, 23);
		frame.getContentPane().add(btnCheckBalance);
		
		JButton btnCheckStockInfo = new JButton("Check Stock Info");
		btnCheckStockInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String input = JOptionPane.showInputDialog("Enter a Stock Symbol:").toUpperCase();
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
		
		JButton btnTransactions = new JButton("Transactions");
		btnTransactions.setBounds(96, 92, 212, 23);
		frame.getContentPane().add(btnTransactions);
		
		JLabel lblWelcome = new JLabel("Welcome, " + StockMarket.sessionName);
		lblWelcome.setBounds(10, 253, 366, 14);
		frame.getContentPane().add(lblWelcome);
		btnTransactions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int option = JOptionPane
						.showOptionDialog(
								null,
								"Do you want to Buy or sell?",
								null, JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE, null, new Object[] {
										"Buy", "Sell" },
								"Buy");
				String input = JOptionPane.showInputDialog("Enter a Stock Symbol:").toUpperCase();
				String amount = JOptionPane.showInputDialog("Enter an amount of stocks:").toUpperCase();

				
				if(option == 0){
					try {
						StockMarket.portfolio.buyStock(input, Integer.parseInt(amount));
					} catch (NoBalanceException e) {
						JOptionPane.showMessageDialog(null, "Not enough funds to complete request.");
					}
				}else{
					try{
						StockMarket.portfolio.sellStock(input, Integer.parseInt(amount));
					}catch(Exception e){
						JOptionPane.showMessageDialog(null, "You don't own enough stocks to complete request.");
					}
				}
			}
		});

	}
}
