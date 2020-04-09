import java.util.*;

import javafx.application.Application;
import javafx.geometry.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.shape.*;
import javafx.scene.text.*;


public class Hangman extends Application  {
	protected static Stage window;
	protected static BorderPane layout = new BorderPane();
	protected static FlowPane r;
	protected static Button[] tools = new Button[4];
	protected static Shape[] man = new Shape[10];
	protected static Button[] alphabet = new Button[26];
	protected static Button newgame = new Button("Start Game");
	protected static int rg;
	protected static String word;
	protected static String s;
	protected static List<Text> wordletter;
	protected static List<Rectangle> wordBox;
	
	/**
	 * Main method to run the hangman application
	 */
	public static void main(String[] args) {
		launch(args);
	}
	
	/**
	 * Starts the application by creating the layout and adding the toolbar buttons
	 */
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		window.getIcons().add(new Image("hangman.jpg"));
		window.setTitle("Hangman");
		layout.setStyle("-fx-background-color: seashell;");
		
		Man.makeMan();
		setToolbar();
		
		Text name = new Text(400, 130, "HANGMAN");
		name.setFont(Font.font("Optima", FontWeight.BOLD, 80));
		layout.getChildren().add(name);
		
		Scene scene = new Scene(layout, 1200, 750);
		window.setScene(scene);
		window.show();
	}
	
	/**
	 * Creates the 4 button toolbar at the top of the application
	 */
	public void setToolbar() {
		HBox toolbar = new HBox(7);
		tools[0] = new Button("New", new ImageView(new Image("New.png", 20, 20, false, false)));
		tools[1] = new Button("Load", new ImageView(new Image("Load.png", 20, 20, false, false)));
		tools[2] = new Button("Save", new ImageView(new Image("Save.png", 20, 20, false, false)));
		tools[3] = new Button("Exit", new ImageView(new Image("Exit.png", 20, 20, false, false)));
		toolbar.getChildren().addAll(tools[0], tools[1], tools[2], tools[3]);
		toolbar.setStyle("-fx-background-color: rosybrown;");
		toolbar.setPadding(new Insets(10, 10, 10, 10));
		layout.setTop(toolbar);
		tools[2].setDisable(true);
		tools[0].setOnMouseClicked(e -> Initialize.newGame());
		tools[1].setOnMouseClicked(e -> SaveLoad.loadGame());
		tools[3].setOnMouseClicked(e -> window.close());
	}
	
	/**
	 * When the user presses the exit button in the toolbar, the user is given the option to save the 
	 * current game. If yes, then save the game file and exit the application. Else, exit the application.
	 */
	public static void closeProgram() {
		if (tools[2].isDisabled()) {
			window.close();
			return;
		}
		int answer = AlertBox.confirmBox();
		if (answer == 0)
			SaveLoad.saveGame();
		window.close();
	}
	
}
