package view;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class App extends Application {

	private Layer field;
	private Layer jobField;
	private AnimationTimer timer;
	
	@Override
	public void start(Stage primaryStage) {
		try 
		{
			BorderPane root = new BorderPane();
			field = new Layer(Settings.SCENE_WIDTH, Settings.SCENE_HEIGHT);
			jobField = new Layer(Settings.SCENE_HEIGHT, Settings.SCENE_HEIGHT/4);
			jobField.setBackground("FFFF00");
			
			Pane pane = new Pane();
			pane.getChildren().addAll(field);
			root.setCenter(pane);
			
			pane.getChildren().addAll(jobField);			
			root.setBottom(jobField);
			
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
