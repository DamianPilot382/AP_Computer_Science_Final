package me.stockMarket.main;

import java.math.BigDecimal;
import java.util.ArrayList;

public class AccountCheck {
	
	private static FileManager fm = new FileManager("account.txt");
	private static ArrayList<Object> raw = fm.readFile();
	private static ArrayList<String> username = readUserNames();
	private static ArrayList<String> password = readPassword();
	
	public static boolean checkUserName(String str){
		
		for(String s : username){
			if(s.equals(str)){
				return false;
			}
		}
		
		return true;
		
	}
	
	public static boolean checkPassword(String user, String pass){
		for(int i = 0; i < username.size(); i++){
			if(username.get(i).equals(user)){
				if(password.get(i).equals(pass)){
					return true;
				}
			}
		}
		
		return false;
	}
	
	private static ArrayList<String> readUserNames(){
		ArrayList<String> temp = new ArrayList<String>();
		for(int i = 0; i < raw.size(); i += 2){
			temp.add(raw.get(i).toString());
		}
		
		return temp;
	}
	
	private static ArrayList<String> readPassword(){
		ArrayList<String> temp = new ArrayList<String>();
		for(int i = 1; i < raw.size(); i += 2){
			temp.add(raw.get(i).toString());
		}
		
		return temp;
		
	}
	
	public static void addUser(String user, String pass){
		ArrayList<Object> temp = new ArrayList<Object>();
		for(Object s : raw){
			temp.add(s);
		}
		
		temp.add((Object)user);
		temp.add((Object)pass);
		
		fm.writeFile(temp);
		FileManager fmBalance = new FileManager("balance.txt");
		temp = fmBalance.readFile();
		temp.add("!" + user);
		temp.add(10000 + "");
		
		fmBalance.writeFile(temp);
		
		fmBalance = new FileManager("stocks.txt");
		temp = fmBalance.readFile();
		temp.add("!" + user);
		
		fmBalance.writeFile(temp);
		
	}
	
	public static void removeUser(String user){
		for(int i = 0; i < raw.size(); i++){
			if(raw.get(i).toString().equals(user)){
				raw.remove(i);
				raw.remove(i);
				return;
			}
		}
		
		for(int i = 0; i < username.size(); i++){
			if(raw.get(i).toString().equals(user)){
				raw.remove(i);
				password.remove(i);
				return;
			}
		}
	}
	
	public static int getAccountsSize(){
		return username.size();
	}
	
	public static ArrayList<String> getUsernames(){
		return username;
	}
	
	protected static ArrayList<String> getPasswords(){
		return password;
	}
	
	protected static ArrayList<Object> getUserPassRaw(){
		return raw;
	}
	
	protected static BigDecimal getUserBalance(){
		FileManager fmBalance = new FileManager("balance.txt");
		ArrayList<Object> temp = fmBalance.readFile();
		for(int i = 0; i < temp.size(); i++){
			if(temp.get(i).equals("!" + StockMarket.sessionName)){
				return new BigDecimal(Double.parseDouble((String)temp.get(i + 1)));
			}
		}
		
		return new BigDecimal(404);
		
	}
	
	protected static void setUserBalance(BigDecimal balance){
		FileManager fmBalance = new FileManager("balance.txt");
		ArrayList<Object> temp = fmBalance.readFile();
		for(int i = 0; i < temp.size(); i++){
			if(temp.get(i).equals("!" + StockMarket.sessionName)){
				temp.set(i + 1, balance);
				fmBalance.writeFile(temp);
				return;
			}
		}
	}
	
	protected static ArrayList<String> getUserStocks(){
		FileManager fms = new FileManager("stocks.txt");
		ArrayList<String> temp = new ArrayList<String>();
		ArrayList<Object> full = fms.readFile();
		for(int i = 0; i < full.size(); i++){
			if(((String)(full.get(i))).equals("!" + StockMarket.sessionName)){
				for(int j = i + 1; j < full.size(); j++){
					if(((String) full.get(j)).startsWith("!")){
						return temp;
					}
					temp.add((String)full.get(j));
				}
			}
		}
		return temp;
	}
	
	protected static void addUserStocks(ArrayList<String> adding){
		
		FileManager fms = new FileManager("stocks.txt");
		ArrayList<Object> temp = fms.readFile();
		
		for(int i = 0; i < temp.size(); i++){
			if(((String)(temp.get(i))).equals("!" + StockMarket.sessionName)){
				for(int j = i + 1; j < temp.size(); j++){
					
					if(((String)(temp.get(j))).equals(adding.get(0))){
						temp.set(j + 1, Integer.parseInt(((String)(temp.get(j + 1)))) + Integer.parseInt(adding.get(1)));
						fms.writeFile(temp);
						return;
					}
					
					if(((String)(temp.get(j))).startsWith("!")){
						for(String s : adding){
							temp.add(j, s);
						}
						fms.writeFile(temp);
						return;
					}
				}
				for(String s : adding){
					temp.add(s);
				}
				fms.writeFile(temp);
				return;
			}
		}
		
		if(temp.size() == 1){
			temp.add(adding.get(0));
			temp.add(adding.get(1));
		}
		
		fms.writeFile(temp);
		
	}
	
	protected static void sellUserStocks(ArrayList<String> selling){
		System.out.println("sellUserStockCALLED");
		FileManager fms = new FileManager("stocks.txt");
		ArrayList<Object> temp = fms.readFile();
		for(int i = 0; i < temp.size(); i++){
			if(((String)(temp.get(i))).equals("!" + StockMarket.sessionName)){
				for(int j = i + 1; j < temp.size(); j++){
					
					if(((String)(temp.get(j))).equals(selling.get(0))){
						if(Integer.parseInt(((String)(temp.get(j + 1)))) > Integer.parseInt(selling.get(1))){
							temp.set(j + 1, Integer.parseInt((String)(temp.get(j - 1))) - Integer.parseInt(selling.get(1)));
							fms.writeFile(temp);
							return;
						}else if(Integer.parseInt(((String)(temp.get(j + 1)))) == Integer.parseInt(selling.get(1))){
							temp.remove(j);
							temp.remove(j);
							fms.writeFile(temp);
							return;
						}else{
							throw new IllegalArgumentException("No funds");
						}
					}
				}
			}
		}
		
		fms.writeFile(temp);
		
	}
	

}
