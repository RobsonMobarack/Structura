package com.msoft.structura;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class CRD {	
	public void command(JTextField textField, AVLTree tree) {
		String userInput = textField.getText();
		
		String[] userInputArray = userInput.split(" ");
		userInput = userInputArray[0].toLowerCase();
		int value = Integer.parseInt(userInputArray[1]);
		
		switch(userInput) {
			case "insert":
				tree.insert(tree.getRoot(), value);
				tree.printTree(tree.getRoot());
				break;
			case "remove":
				tree.remove(value);
				break;
			case "search":
				Node searchResult = tree.search(tree.getRoot(), value);
				if(searchResult != null)
					JOptionPane.showMessageDialog(null, "Resultado da busca do valor " + value + " = " + searchResult.getValue());
				else
					JOptionPane.showMessageDialog(null, "Resultado da busca do valor " + value + " = null");
				break;
		}
	}
}
