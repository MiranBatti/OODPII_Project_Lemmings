package model;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.OverrunStyle;
import javafx.scene.shape.Rectangle;
import view.Settings;

public class CollisionDetector extends Observer
{
	public List<Obstacle> obstacles;
	private final int RIGHT = 0;
	private final int LEFT = 1;

	public CollisionDetector(List<Lemming> lemmings, List<Obstacle> obstacles)
	{
		this.lemmings = lemmings;
		this.obstacles = obstacles;
	}
	
	public void collisionDetection()
	{
		for (Lemming lemming : lemmings)
		{
			if(lemming.getX() == Settings.SCENE_WIDTH)
				lemming.setDirection(LEFT);
			if(lemming.getX() == 0)
				lemming.setDirection(RIGHT);
		}		
	}

	public void collisionResolver(List<Obstacle> obstacles)
	{
		boolean falling = true;
		for (Lemming lemming : lemmings) 
		{
	        for (Obstacle obstacle : obstacles) {
	            if (obstacle instanceof Rectangle) {
	                if (lemming.getX() < obstacle.getX() + obstacle.getWidth()
	                    && lemming.getX() + Settings.LEMMINGS_WIDTH >= obstacle.getX()
	                    && lemming.getY() < obstacle.getY() + obstacle.getHeight()
	                    && lemming.getY() + Settings.LEMMINGS_HEIGHT >= obstacle.getY()) 
	                {
	                	lemming.setJob(Walker.getInstance());
	                	falling = false;
	                }
	                detectObstacleEdge(lemming, obstacle, falling);
	            }
	        }
		}
	}
	
	private void detectObstacleEdge(Lemming lemming, Obstacle obstacle, boolean falling)
	{
        if(lemming.getX() > (obstacle.getX()+obstacle.getWidth()) || lemming.getX()+Settings.LEMMINGS_WIDTH < obstacle.getX() && falling) //if at edge
        {
        	lemming.setJob(Faller.getInstance());
        	falling = true;
        }
	}
	
	public void goalCollided()
	{
		
	}
	
	public boolean fallOutOfBoundsDetection(List<Lemming> lemmings)
	{
		for (Lemming lemming : lemmings)
		{
			if(lemming.getY() > Settings.SCENE_HEIGHT)
				return true;
		}
		return false;
	}
	
	@Override
	public void update()
	{
		collisionDetection();
		collisionResolver(obstacles);
	}
	
}