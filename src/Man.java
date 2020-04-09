import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

public class Man extends Hangman{
	
	/**
	 * Creates the 10 shapes of the man
	 */
	public static void makeMan() {
		man[0] = makeLine(70, 600, 450, 600);
		man[1] = makeLine(70, 150, 70, 600);
		man[2] = makeLine(70, 150, 300, 150);
		man[3] = makeLine(300, 150, 300, 250);
		man[4] = new Circle(300, 300, 50);
		man[4].setStroke(Color.BLACK);
		man[5] = makeLine(300, 350, 300, 470);
		man[6] = makeLine(300, 400, 250, 370);
		man[7] = makeLine(300, 400, 350, 370);
		man[8] = makeLine(300, 470, 250, 520);
		man[9] = makeLine(300, 470, 350, 520);
		resetMan();
	}
	
	/**
	 * Displays the parts of the man via a borderpane.
	 */
	public static BorderPane displayMan() {
		BorderPane f = new BorderPane();
		f.setPrefWidth(450);
		f.getChildren().addAll(man);
		return f;
	}
	
	/**
	 * Resets the man so all its parts are not visible to the user
	 */
	public static void resetMan() {
		for (Shape s : man) s.setVisible(false);
	}
	
	/**
	 * Sets the visibility of the parts of the man depending on contents of string 
	 * when a user loads a file
	 */
	public static void setMan(int rg) {
		for (int i = 0; i < 10-rg; i++)
			man[i].setVisible(true);
	}
	
	/**
	 * Helper function that makes a thick line for the parts of the man
	 */
	public static Line makeLine(int a, int b, int c, int d) {
		Line l = new Line(a,b,c,d);
		l.setStroke(Color.BLACK);
		l.setStrokeWidth(5);
		return l;
	}
}
