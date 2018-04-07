package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Model {
	public ArrayList<History> histories;
	
	public Model() {
		histories = new ArrayList<History>();
	}
	
	public ArrayList<History> getHistories() {
		loadHistories();
		return histories;
	}
	
	public void loadHistories() {
		File file = new File("history.txt");
		
		try {
			Scanner scan = new Scanner(file);
			String[] result = new String[2];
			while(scan.hasNext()) {
				result = scan.nextLine().split(";");
				History history = new History(result[0], result[1]);
				histories.add(history);
			}
			
		}
		catch (FileNotFoundException e) {
			System.out.println("Not found");
			JOptionPane.showMessageDialog(null, "History File Not Found:\n" + e.toString() + "\n");
		}
	}
}
