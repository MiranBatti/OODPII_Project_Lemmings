package view;

import java.util.LinkedList;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.CollisionDetector;
import model.Lemming;
import model.Obstacle;
import model.Walker;

public class App extends Application {

	private Layer gameField;
	private Layer jobField;
	private AnimationTimer timer;
	LinkedList<Obstacle> obstacles;
	
	@Override
	public void start(Stage primaryStage) {
		try 
		{
			BorderPane root = new BorderPane();
			gameField = new Layer(Settings.SCENE_WIDTH, Settings.SCENE_HEIGHT);
			jobField = new Layer(Settings.SCENE_WIDTH, Settings.SCENE_HEIGHT/4);
			jobField.setBackground("FFFF00");
			jobField.setPickOnBounds(false);
			
			Pane pane = new Pane();
			pane.getChildren().add(gameField);
			root.setCenter(pane);
			
			pane.getChildren().add(jobField);		
			root.setBottom(jobField);
			
			pane.setPickOnBounds(false);						
			
			Scene scene = new Scene(root);	
			primaryStage.setScene(scene);
			primaryStage.setTitle("Lemmings");
			primaryStage.show();
			//good debug thing
			scene.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> System.out.println(e.getPickResult())); 
			createLevel();
			startGame();
		} catch(Exception e) 
		{
			e.printStackTrace();
		}
	}

    private void startGame()
    {
//    	Obstacle ob = new Obstacle(0, Settings.SCENE_HEIGHT - Settings.SCENE_HEIGHT/8, Settings.SCENE_WIDTH, Settings.SCENE_HEIGHT - Settings.SCENE_HEIGHT/8, gameField);
    	Lemming lem = new Lemming(150, 0, gameField);
		lem.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> lem.setJob(Walker.getInstance()));
		CollisionDetector cd = new CollisionDetector(lem, obstacles);
		lem.attach(cd);
        // start game
        timer = new AnimationTimer() 
        {
            @Override
            public void handle(long now) 
            {
    			lem.move();
            }
        };
        timer.start();
    }
    
    private void createLevel()
    {
    	obstacles = new LinkedList<Obstacle>();
    	obstacles.add(new Obstacle(150, 200, 200, 500, gameField));
    	obstacles.add(new Obstacle(450, 300, 200, 500, gameField));
    }
    
	public static void main(String[] args) {
		launch(args);
	}
}
