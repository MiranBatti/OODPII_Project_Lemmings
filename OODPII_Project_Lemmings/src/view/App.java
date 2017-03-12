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
import model.Obstacle;

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
			
			Scene scene = new Scene(root);			
			primaryStage.setScene(scene);
			primaryStage.setTitle("Lemmings");
			primaryStage.show();
			
			startGame();
		} catch(Exception e) 
		{
			e.printStackTrace();
		}
	}

    private void startGame() {
		Lemming lem = new Lemming(150, 30, gameField);
		Obstacle ob = new Obstacle(0, Settings.SCENE_HEIGHT - Settings.SCENE_HEIGHT/8, Settings.SCENE_WIDTH, Settings.SCENE_HEIGHT - Settings.SCENE_HEIGHT/8, gameField);
        // start game
        timer = new AnimationTimer() {

            @Override
            public void handle(long now) {
    			lem.move();
            }
        };

        timer.start();

    }
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
