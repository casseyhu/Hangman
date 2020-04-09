import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class SaveLoad extends Hangman{
	/**
	 * Load a game via a openDialog
	 */
	public static void loadGame() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Load Game");
		ExtensionFilter filter = new ExtensionFilter("Hangman Files (*.hng)", "*.hng");
		fileChooser.getExtensionFilters().add(filter);
		File f = fileChooser.showOpenDialog(window);
		if (f != null)
			openFile(f);
	}
	
	/**
	 * Save the current contents of the game to a .hng file via a saveDialog
	 */
	public static void saveGame() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Save Game");
		ExtensionFilter filter = new ExtensionFilter("Hangman Files (*.hng)", "*.hng");
		fileChooser.getExtensionFilters().add(filter);
		File f = fileChooser.showSaveDialog(window);
		if (f != null)
			saveFile(f);
	}
	
	/**
	 * Writes the current contents of the game to a .hng file. 
	 * First line contains the remaining guesses left, second line contains a string of binary 0's and 1's
	 * (1 if alphabet button not disabled, 0 otherwise), third line contains a string of binary 0's and 1's
	 * (1 if the part of man is visible, 0 otherwise), fourth line contains the word to guess, and
	 * the fifth line contains a string of binary 0's and 1's (1 if the letter of the word has been guessed
	 * and is visible, 0 otherwise)
	 */
	public static void saveFile(File f) {
		tools[2].setDisable(true);
		try {
			FileWriter fw =  new FileWriter(f);
			fw.write(rg + "\n" + word + "\n" + AlphDisabled() + "\n" + wordDisabled() + "\n");
			fw.close();
		} catch (IOException e) { System.out.println("Invalid file."); }
	}
	
	/**
	 * Opens the hng file, reads the values, and sets the value to current game
	 */
	public static void openFile(File f) {
		Initialize.newGame();
		List<String> items = new ArrayList<>();
		try {
			Scanner scanner = new Scanner(f);
			while (scanner.hasNext()) 
				items.add(scanner.nextLine());
			scanner.close();
			Initialize.initializeValues();
			rg = Integer.parseInt(items.get(0));
			word = items.get(1);
			Alphabet.setAlphabet(items.get(2));
			Man.setMan(rg);
			s = items.get(3);
			Initialize.initializeGame();
			layout.setOnKeyPressed(e -> Alphabet.handler(e));
		} catch (FileNotFoundException e) { System.out.println("Invalid file."); }
	}
	
	/**
	 * Returns a string of binary 0's and 1's (1 if alphabet button not disabled, 0 otherwise)
	 */
	public static String AlphDisabled() {
		String s = "";
		for (Button a : alphabet) {
			if (a.isDisabled()) s += '0';
			else s += '1';
		} return s;
	}
	
	/**
	 * Returns a string of binary 0's and 1's (1 if letter of word has been guessed and is visible, 0 otherwise)
	 */
	public static String wordDisabled() {
		String s = "";
		for (Text t : wordletter) {
			if (t.isVisible()) s += '1';
			else s += '0';
		} return s;
	}
	
}
