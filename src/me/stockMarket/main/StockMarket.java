package me.stockMarket.main;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.util.logging.LogManager;

public class StockMarket {

	public static Portfolio portfolio;
	protected static String sessionName;
	protected static String sessionPass;

	public static void main(String[] args) {
		LogManager.getLogManager().reset();

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					if (ask() == 0) {
						registerUser();
						portfolio = new Portfolio(new BigDecimal(10000));
					} else {
						selectUser();
						portfolio = new Portfolio(AccountCheck.getUserBalance());
					}

					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	private static int ask() {
		int option = -1;
		if(AccountCheck.getAccountsSize() == 0){
			return 0;
		}
		while (option < 0) {
			option = JOptionPane
					.showOptionDialog(
							null,
							"Do you want to create a new file or use an existing session?",
							null, JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE, null, new Object[] {
									"Create a file", "Use existing" },
							"Use existing");
		}
		return option;
	}

	private static void registerUser() {
		boolean done = false;
		while (!done) {
			String name = JOptionPane.showInputDialog(null,
					"Enter a name for the new file:");
			if (AccountCheck.checkUserName(name)) {
				done = true;
				
				JPanel panel = new JPanel();
				JLabel label = new JLabel("Enter a password:");
				JPasswordField pass = new JPasswordField(10);
				panel.add(label);
				panel.add(pass);
				String[] options = new String[]{"OK", "Cancel"};
				int option = JOptionPane.showOptionDialog(null, panel, null,
				                         JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
				                         null, options, options[0]);
				if(option == 0)
				{
				    char[] password = pass.getPassword();
				    String passcode = "";
				    for(char s : password){
				    	passcode = passcode + s;
				    }
					AccountCheck.addUser(name, passcode);
					sessionName = name;
					sessionPass = passcode;
										
				}else{
					System.exit(0);
				}
			}
		}
	}

	private static void selectUser() {
		Object[] temp = new Object[AccountCheck.getUsernames().size()];
		temp = AccountCheck.getUsernames().toArray();
	    sessionName = (String) JOptionPane.showInputDialog(null, "Choose an existing file.",
	        null, JOptionPane.QUESTION_MESSAGE, null, temp, AccountCheck.getUsernames().get(0));
	    System.out.println(sessionName);
	    if(sessionName == null){
	    	System.exit(0);
	    }
	    	while(true){
	    	JPanel panel = new JPanel();
	    	JLabel label = new JLabel("Enter a password:");
	    	JPasswordField pass = new JPasswordField(10);
	    	panel.add(label);
	    	panel.add(pass);
	    	String[] options = new String[]{"OK", "Cancel"};
	    	int option = JOptionPane.showOptionDialog(null, panel, null,
	    			JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
	    			null, options, options[0]);
	    	if(option == 0)
	    	{
	    		char[] password = pass.getPassword();
		    	String passcode = "";
			    for(char s : password){
		    	passcode = passcode + s;
			    }
			    if(AccountCheck.checkPassword(sessionName, passcode)){
			    	sessionPass = passcode;
			    	return;
			    }
								
	    	}else{
	    		System.exit(0);
	    	}
	    	}
	}
}
