import java.util.*;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;

public class Initialize extends Hangman{
	private static Label remainingGuesses = new Label();
	private static VBox col = new VBox(10);
	private static BorderPane manpane;
	
	/**
	 * Starts a new game by initializing all the game values and displays the "Start Game" button
	 */
	public static void newGame() {
		Man.resetMan();
		col.setVisible(false);
		newgame.setDisable(false);
		tools[2].setDisable(true);
		Alphabet.setAlphabet("00000000000000000000000000");
		HBox bottom = new HBox(newgame);
		bottom.setAlignment(Pos.CENTER);
		bottom.setStyle("-fx-background-color: rosybrown;");
		bottom.setPadding(new Insets(10, 10, 10, 10));
		layout.setBottom(bottom);
		newgame.setOnMouseClicked(event -> {
			initializeValues();
			initializeGame();
		});
	}
	
	/**
	 * Initializes the values of remaining guesses, alphabet buttons, wordBoxes, and the man
	 * for a new game
	 */
	public static void initializeValues() {
		rg = 10;
		s = Word.randWord();
		Alphabet.createAlphabet();
		wordletter = new ArrayList<>();
		wordBox = new ArrayList<>();
		Man.resetMan();
		manpane = Man.displayMan();
		layout.setLeft(manpane);
		manpane.setVisible(false);
	}
	
	/**
	 * Displays the initialized game values onto the screen via a VBox
	 */
	public static void initializeGame() {
		col = new VBox(10);
		col.setVisible(true);
		manpane.setVisible(true);
		newgame.setDisable(true);
		col.setAlignment(Pos.CENTER);
		setGuesses(rg);
		col.getChildren().addAll(remainingGuesses, Word.displayWord(word.toCharArray(), s), Alphabet.displayAlphabet());
		layout.setRight(col);
	}

	/**
	 * Updates the remaining guesses left on the screen
	 */
	public static void setGuesses(int rg) {
		remainingGuesses.setText("Remaining Guesses: " + rg);
		remainingGuesses.setFont(Font.font("Optima", FontWeight.BOLD, 20));
	}
	
	/**
	 * Checks to see if user wants to save the current game before starting a new game.
	 * If yes, save the game file and initialize a new game. If no, initialize a
	 * new game state. If cancel, continue with current game.
	 */
	public static void checkSave() {
		if (tools[2].isDisabled())
			return;
		int answer = AlertBox.confirmBox();
		if (answer == 0) {
			SaveLoad.saveGame();
			Initialize.newGame();
		}
		else if (answer == 1)
			Initialize.newGame();
		else
			layout.setOnKeyPressed(e -> Alphabet.handler(e));
	}
	
	/**
	 * Saves the current game and continue current game
	 */
	public static void saveOnly() {
		SaveLoad.saveGame();
		layout.setOnKeyPressed(e -> Alphabet.handler(e));
	}
	
}
