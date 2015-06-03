package me.stockMarket.main;

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
	}
	
	public static void removeUser(String user){
		for(int i = 0; i < raw.size(); i++){
			if(raw.get(i).toString().equals(user)){
				raw.remove(i);
				raw.remove(i);
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

}
