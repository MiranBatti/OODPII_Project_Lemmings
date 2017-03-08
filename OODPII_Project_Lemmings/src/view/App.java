package view;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Lemming;

public class App extends Application {

	private Layer gameField;
	private Layer jobField;
	private AnimationTimer timer;
	
	@Override
	public void start(Stage primaryStage) {
		try 
		{
			BorderPane root = new BorderPane();
			gameField = new Layer(Settings.SCENE_WIDTH, Settings.SCENE_HEIGHT);
			jobField = new Layer(Settings.SCENE_HEIGHT, Settings.SCENE_HEIGHT/4);
			jobField.setBackground("FFFF00");
			
			Pane pane = new Pane();
			pane.getChildren().addAll(gameField);
			root.setCenter(pane);
			
			pane.getChildren().addAll(jobField);			
			root.setBottom(jobField);
			
			Lemming lem = new Lemming(30, 30, gameField);
			lem.move();
			
			Scene scene = new Scene(root);			
			primaryStage.setScene(scene);
			primaryStage.setTitle("Lemmings");
			primaryStage.show();
		} catch(Exception e) 
		{
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
