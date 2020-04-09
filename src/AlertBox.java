import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {
	private static int result;
	
	/**
	 * Displays a new window to display game results with 1 button to close window
	 */
	public static void endGameAlert(int rg, String word) {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Game Over");
		Label message = new Label();
		if (rg > 0)
			message.setText("Congratulations! You Win!");
		else
			message.setText("You lost. The word was " + word);
		Button close = new Button("Close");
		close.setOnAction(e -> window.close());
		VBox layout = new VBox(5, message, close);
		layout.setAlignment(Pos.CENTER);
		Scene scene = new Scene(layout, 400, 200);
		window.setScene(scene);
		window.showAndWait();
	}
	
	/**
	 * Displays a confirmation window to ask user if they want to save the game. 
	 * Returns 0 if yes, 1 if no, 2 if cancel
	 */
	public static int confirmBox() {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Save Game");
		Label message = new Label("Would you like to save the game?");
		
		Button yes = new Button("Yes");
		Button no = new Button("No");
		Button cancel = new Button("Cancel");
		yes.setOnAction(e -> {result = 0; window.close();});
		no.setOnAction(e -> {result = 1; window.close();});
		cancel.setOnAction(e -> {result = 2; window.close();});
		
		HBox buttons = new HBox(5, yes, no, cancel);
		VBox layout = new VBox(5, message, buttons);
		layout.setAlignment(Pos.CENTER);
		buttons.setAlignment(Pos.CENTER);
		Scene scene = new Scene(layout, 300, 200);
		window.setScene(scene);
		window.showAndWait();;
		return result;
	}
	
}
