import java.io.*;
import java.net.URL;
import java.util.*;
import javafx.geometry.Orientation;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.*;

public class Word extends Hangman{
	
	/**
	 * Generates a random word from a "words.txt" file
	 */
	public static String randWord() {
		List<String> words = new ArrayList<>();
		try {
			URL filepath = Word.class.getResource("words.txt");
			Scanner scanner = new Scanner(new File(filepath.toURI()));
		    while(scanner.hasNext())
		    	words.add(scanner.nextLine());
		    scanner.close();
		} catch(Exception ex) {
		    System.out.println("File is not found!");
		}
		word = words.get((int) (Math.random() * words.size()));
		String s = "";
		for (int i = 0; i < word.length(); i++) 
			s += "0";
		return s;
	}
	
	/**
	 * Displays the characters of the word in individual boxes via a flowpane on the screen.
	 * The characters of the word are all initialized to be not visible unless a user
	 * has loaded a game.
	 */
	public static FlowPane displayWord(char[] w, String s) {
		FlowPane r = new FlowPane(Orientation.HORIZONTAL, 10, 10);
		r.setPrefWidth(650);
		for (int i = 0; i < w.length; i++) {
        	wordBox.add(new Rectangle(40, 40));
			wordBox.get(i).setFill(Color.ROSYBROWN);
			wordletter.add(new Text(String.valueOf(w[i]).toUpperCase()));
			wordletter.get(i).setFont(Font.font("Optima", 30));
			if (s.charAt(i) == '0')
				wordletter.get(i).setVisible(false);
			else
				wordletter.get(i).setVisible(true);
			r.getChildren().add(new StackPane(wordBox.get(i), wordletter.get(i)));
        } return r;
	}
	
}
