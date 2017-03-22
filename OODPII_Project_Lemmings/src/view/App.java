package view;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.CollisionDetector;
import model.Lemming;
import model.Obstacle;

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
    	List<Lemming> lemmings = new ArrayList<>();
    	int maxLemmings = 10;
    	int currentLemmings = 0;
    	while(currentLemmings < maxLemmings)
    	{
    		lemmings.add(new Lemming(150, 0, gameField));
    		currentLemmings++;
    	}

		CollisionDetector cd = new CollisionDetector(lemmings, obstacles);
		for (Lemming lemming : lemmings)
		{
			lemming.attach(cd);		
    		Platform.runLater(lemming);			
		}

        // start game
        timer = new AnimationTimer() 
        {
            @Override
            public void handle(long now) 
            {
            	for (Lemming lemming : lemmings)
				{
					lemming.run();
				}
            }
        };
        timer.start();
    }
    
    private void createLevel()
    {
    	obstacles = new LinkedList<Obstacle>();
    	obstacles.add(new Obstacle(150, 200, 200, 400, gameField));
    	obstacles.add(new Obstacle(250, 300, 200, 500, gameField));
    }
    
	public static void main(String[] args) {
		launch(args);
	}
}
