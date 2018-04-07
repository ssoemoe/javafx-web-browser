package controller;
import java.util.ArrayList;

import model.History;
import model.Model;

public class Testing {
	public static void main(String[] args) {
		Model m = new Model();
		ArrayList<History> his = m.getHistories();
		
		for(History h : his) {
			System.out.println(h);
		}
	}
}
