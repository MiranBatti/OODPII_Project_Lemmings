package view;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.CollisionDetector;
import model.Goal;
import model.Jobs;
import model.Lemming;
import model.Obstacle;
import model.Parachute;
import model.Selectable;
import model.Stopper;
import model.Walker;

public class App extends Application {

	private Layer gameField;
	private FlowPane jobField;
	private AnimationTimer timer;
	private LinkedList<Obstacle> obstacles;
	private Goal goal;
	private List<Selectable> selectableJobs;
	private Selectable selected;
	private List<Button> buttons;
	private Button addLemmingBtn;
	private List<Lemming> lemmings;
	private int maxLemmings = Settings.LEMMINGS_LIMIT;
	private int currentLemmings = 0;
	private CollisionDetector cd;
	private double startX = Settings.LEMMINGS_START_X;
	private double startY = Settings.LEMMINGS_START_Y;
	
	@Override
	public void start(Stage primaryStage) {
		try 
		{
			BorderPane root = new BorderPane();
			gameField = new Layer(Settings.SCENE_WIDTH, Settings.SCENE_HEIGHT);
			jobField = new FlowPane();
//			jobField.setBackground("FFFF00");
//			jobField.setPickOnBounds(false);
			
//			Pane pane = new Pane();
//			pane.getChildren().add(gameField);
			root.setCenter(gameField);
			
			root.setBottom(jobField);
			
			gameField.setPickOnBounds(false);
			jobField.setPickOnBounds(false);
			
			Scene scene = new Scene(root);	
			primaryStage.setScene(scene);
			primaryStage.setTitle("Lemmings");
			primaryStage.show();
			
			selectableJobs = new ArrayList<>();
			populateJobField();
//			scene.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> System.out.println(e.getPickResult().toString())); //good debug thing
			createLevel();
			startGame();
		} catch(Exception e) 
		{
			e.printStackTrace();
		}
	}

    private void startGame()
    {
    	lemmings = new ArrayList<>();
    	List<Lemming> toRemove = new ArrayList<>(); // to avoid ConcurrentModificationException

//    	while(currentLemmings < maxLemmings)
//    	{
//    		lemmings.add(new Lemming(150, 0, gameField));
//    		currentLemmings++;
//    	}
    	
    	lemmings.add(new Lemming(startX, startY, gameField));
    	currentLemmings++;

		cd = new CollisionDetector(lemmings, obstacles, goal);
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
					if(lemming.getIfHasTeleported())
					{
						toRemove.add(lemming);
						gameField.getChildren().remove(lemming);
						currentLemmings--;
						System.out.println("lem is win!");
					}
					if(lemming.isDead())
					{
						toRemove.add(lemming);
						gameField.getChildren().remove(lemming);
						currentLemmings--;
						System.out.println("lem is ded >:)");
					}
					lemming.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
						if(selected != null)
						{
							lemming.setJob((Jobs) selected);
						}
					});
				}
            	lemmings.removeAll(toRemove);
            }
        };
        timer.start();
    }
    
    private void createLevel()
    {
    	obstacles = new LinkedList<Obstacle>();
    	obstacles.add(new Obstacle(150, 200, 200, 400, gameField));
    	obstacles.add(new Obstacle(250, 300, 200, 500, gameField));
    	obstacles.add(new Obstacle(450, 200, 200, 500, gameField));
    	goal = new Goal(400, 275, 20, 25, gameField);
    }
    
    public void populateJobField()
    {   	
    	selectableJobs.add(Walker.getInstance());
    	selectableJobs.add(Stopper.getInstance());
    	selectableJobs.add(Parachute.getInstance());
//    	for (Selectable selected : selectableJobs) 
//    	{
//			jobField.getChildren().add(selected.getImage());
//		}
    	buttons = new ArrayList<>();
    	buttons.add(new Button("Walker"));
    	buttons.add(new Button("Stopper"));
    	buttons.add(new Button("Parachute"));
    	
    	for (Button button : buttons) {
			jobField.getChildren().add(button);
			jobField.setPadding(new Insets(10, 10, 10, 10));
			button.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {selected = selectableJobs.get(buttons.indexOf(button));});
		}
    	
    	addLemmingBtn = new Button("Add Lemming");
    	jobField.getChildren().add(addLemmingBtn);
    	addLemmingBtn.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
    		if(currentLemmings < maxLemmings)
    		{
    			lemmings.add(new Lemming(startX, startY, gameField));
    			addObservers(cd);
    			currentLemmings++;
    		}
    	});
    }
    
    public void addObservers(CollisionDetector cd)
    {
		for (Lemming lemming : lemmings)
		{
			lemming.attach(cd);		
    		Platform.runLater(lemming);			
		}
    }
    
	public static void main(String[] args) {
		launch(args);
	}
}
