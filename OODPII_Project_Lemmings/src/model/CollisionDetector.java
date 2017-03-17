package model;

import java.util.List;

import javafx.scene.shape.Rectangle;
import view.Settings;

public class CollisionDetector extends Observer
{
	public List<Obstacle> obstacles;
	private final int RIGHT = 0;
	private final int LEFT = 1;

	public CollisionDetector(Lemming lemming, List<Obstacle> obstacles)
	{
		this.lemming = lemming;
		this.obstacles = obstacles;
	}
	
	public void collisionDetection()
	{
		if(lemming.getX() == lemming.getRelativeX())
			lemming.setDirection(LEFT);
		if(lemming.getX() == lemming.getRelativeX()- Settings.SCENE_WIDTH)
			lemming.setDirection(RIGHT);
	}

	public void collisionResolver(List<Obstacle> obstacles)
	{
        for (Obstacle s : obstacles) {
            if (s instanceof Rectangle) {
                Rectangle r = s;
                if (lemming.getX()+r.getX() < r.getX() + r.getWidth()
                    && lemming.getX()+r.getX() + Settings.LEMMINGS_WIDTH >= r.getX()
                    && lemming.getY() < r.getY() + r.getHeight()
                    && lemming.getY() + Settings.LEMMINGS_HEIGHT >= r.getY()) 
                {
//                	lemming.setY((int) (r.getY() - Settings.LEMMINGS_HEIGHT));
                	lemming.setJob(Walker.getInstance());
                }
                else if(lemming.getX()+r.getX() > r.getX() + r.getWidth() || lemming.getX()+r.getX() + Settings.LEMMINGS_WIDTH < r.getX()) //if at edge
                	lemming.setJob(Faller.getInstance());
            }
        }
	}
	
	@Override
	public void update()
	{
		collisionDetection();
		collisionResolver(obstacles);
	}
	
}