package me.stockMarket.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileManager {
	
	private String fileName;
	
	public FileManager(String fileName){
		this.fileName = fileName;
	}
	
	public ArrayList<Object> readFile(){
		
        String line = null;
        ArrayList<Object> temp = new ArrayList<Object>();

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
            	temp.add(line);
            }    

            // Always close files.
            bufferedReader.close();            
        }
        catch(FileNotFoundException ex) {
            ex.printStackTrace();              
        }
        catch(IOException ex) {
        	ex.printStackTrace();
        }
        
        return temp;
	}
	
	public void writeFile(ArrayList<Object> temp){
		try {
            FileWriter fileWriter = new FileWriter(fileName);

            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            
            for(Object s : temp){
            	bufferedWriter.write(s + "");
            	bufferedWriter.newLine();
            	
            }

            bufferedWriter.close();
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }
	}
	
}