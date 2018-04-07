package controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import model.History;
import model.Model;

public class Controller {
	private ArrayList<History> histories;
	private Model model;
	private static int historyPos;
	private static Controller instance = new Controller();

	private Controller() {
		model = new Model();
		histories = model.getHistories();
		historyPos = histories.size() - 1;
	}
	
	public static Controller getController() {
		return instance;
	}
	
	public void addHistory(String urlString) {
		Date date = new Date();
		String addStr = urlString + ";" +date.toString();
		
		File file = new File("history.txt");
		try {
			FileWriter writer = new FileWriter(file, true);
			writer.write(addStr + "\n");
			writer.close();
		} 
		catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
	}
	
	public String getHistoryUrl(String direction) {
		
		if (direction.equalsIgnoreCase("next") && historyPos < histories.size()) 
			historyPos++;

		else if(direction.equalsIgnoreCase("prev") && historyPos >= 0) 
			historyPos--;

		if(historyPos < 0 || historyPos >= histories.size())
			return "about:blank";
		
		else {
			String urlStr = histories.get(historyPos).getUrlString();
			return urlStr;
		}
	}

}
