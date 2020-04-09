import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.*;

public class Alphabet extends Hangman {
	
	/**
	 * Creates the alphabet buttons and initializes them to not disabled
	 */
	public static void createAlphabet() {
		int i = 0;
		for (char c = 'A'; c <= 'Z'; c++) {
			alphabet[i] = new Button(String.valueOf(c));
			alphabet[i].setPrefSize(60, 60);
			alphabet[i].setDisable(false);
			alphabet[i].setFont(Font.font("Optima", FontWeight.BOLD, 25));
			alphabet[i].setStyle("-fx-background-color: lightsteelblue;");
			i++;
		}
	}
	
	/**
	 * Displays the alphabet buttons via a flowpane
	 */
	public static FlowPane displayAlphabet() {
		FlowPane f = new FlowPane(Orientation.HORIZONTAL, 10, 10);
		f.setPrefWidth(650);
		f.getChildren().addAll(alphabet);
		layout.setOnKeyPressed(e -> handler(e));
		return f;
	}
	
	/**
	 * Event handler for when a user clicks a valid key (a-z or A-Z). If a valid key was pressed
	 * and is a letter of the word to guess, display the letter and disable the alphabet letter
	 * else just disable the alphabet letter and decrements guesses. Ignores repeats
	 */
	public static void handler(KeyEvent event) {
		try {
			tools[0].setOnAction(e -> Initialize.checkSave());
			tools[2].setOnAction(e -> Initialize.saveOnly());
			tools[3].setOnMouseClicked(e -> closeProgram());
			char key = event.getText().toUpperCase().charAt(0);
			if (key >= 'A' && key <= 'Z' && !alphabet[key-65].isDisabled()) {
				tools[2].setDisable(false);
				alphabet[key-65].setDisable(true);
				if (validKey(key)) displayValid(key);
				else {
					man[10-rg].setVisible(true);
					Initialize.setGuesses(--rg);
					if (rg == 0) {
						tools[2].setDisable(true);
						displayRest();
						AlertBox.endGameAlert(rg, word);
						setAlphabet("00000000000000000000000000");
					}
				}
			}
		} catch (Exception e) {}
	}
	
	/**
	 * Checks to see if the word to guess contains the character
	 */
	public static boolean validKey(char k) {
		boolean key = false;
		char[] letters = word.toUpperCase().toCharArray();
		for (char c : letters)
			if (c == k) return true;
		return key;
	}
	
	/**
	 * Displays the characters guessed in the word on the screen. If all letters of the word has
	 * been guessed, display the alert box to end the game.
	 */
	public static void displayValid(char c) {
		char[] letters = word.toUpperCase().toCharArray();
		for (int i = 0; i < letters.length; i++)
			if (letters[i] == c) wordletter.get(i).setVisible(true);
		boolean done = true;
		for (Text t : wordletter)
			if (!t.isVisible()) { done = false; break;}
		if (done) {
			tools[2].setDisable(true);
			AlertBox.endGameAlert(rg, word);
			setAlphabet("00000000000000000000000000");
		}
	}
	
	/**
	 * Displays the rest of the letters of the word that the user has not guessed. Slightly opaque
	 * to differentiate from the words the user guessed correctly
	 */
	public static void displayRest() {
		for (int i = 0; i < wordletter.size(); i++)
			if (!wordletter.get(i).isVisible()) { 
				wordletter.get(i).setVisible(true);
				wordletter.get(i).setOpacity(0.5);
				wordBox.get(i).setOpacity(0.5);
			}
	}
	
	/**
	 * Sets the alphabet view on the screen depending on if the button is disabled or not.
	 */
	public static void setAlphabet(String s) {
		createAlphabet();
		for (int i = 0; i < s.length(); i++)
			if (s.charAt(i) == '0') alphabet[i].setDisable(true);
	}
	
}
