package view;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Lemming;
import model.Obstacle;
import model.Walker;

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
			jobField.setPickOnBounds(false);
			
			Pane pane = new Pane();
			pane.getChildren().addAll(gameField);
			root.setCenter(pane);
			pane.setPickOnBounds(false);
			
			pane.getChildren().addAll(jobField);			
			root.setBottom(jobField);
			
			Scene scene = new Scene(root);	
			primaryStage.setScene(scene);
			primaryStage.setTitle("Lemmings");
			primaryStage.show();
			
			//good debug thing
//			scene.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> System.out.println(e.getPickResult())); 
			
			startGame();
		} catch(Exception e) 
		{
			e.printStackTrace();
		}
	}

    private void startGame() {
    	Obstacle ob = new Obstacle(0, Settings.SCENE_HEIGHT - Settings.SCENE_HEIGHT/8, Settings.SCENE_WIDTH, Settings.SCENE_HEIGHT - Settings.SCENE_HEIGHT/8, gameField);
    	Lemming lem = new Lemming(150, 30, gameField);
		
        // start game
        timer = new AnimationTimer() {

            @Override
            public void handle(long now) {
    			lem.move();
    			lem.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> lem.setJob(Walker.getInstance(), gameField));
            }
        };
        timer.start();
    }
    
	public static void main(String[] args) {
		launch(args);
	}
}
