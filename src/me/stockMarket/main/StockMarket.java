package me.stockMarket.main;

import java.awt.EventQueue;
import java.util.logging.LogManager;

import javax.swing.JOptionPane;


public class StockMarket {
	
	public static Portfolio portfolio;
	
	public static void main(String[] args) {
		LogManager.getLogManager().reset();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					int option = JOptionPane.showConfirmDialog(null, "Do you want to create a new file?");
					switch(option){
						case 0:
							portfolio = new Portfolio(Double.parseDouble(JOptionPane.showInputDialog("Please input an initial balance.")));
							break;
						case 1:
							portfolio = new Portfolio();
							break;
						case 2:
							System.exit(0);
						default:
							System.exit(0);
					};
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
}
