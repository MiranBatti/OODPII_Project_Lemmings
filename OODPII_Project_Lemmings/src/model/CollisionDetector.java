package model;

import java.util.List;
import view.Settings;

public class CollisionDetector extends Observer
{
	private List<Obstacle> obstacles;
	private final int RIGHT = 0;
	private final int LEFT = 1;
	private Goal goal;

	public CollisionDetector(List<Lemming> lemmings, List<Obstacle> obstacles, Goal goal)
	{
		this.lemmings = lemmings;
		this.obstacles = obstacles;
		this.goal = goal;
	}
	
	public void screenEdgeCollisionDetection()
	{
		for (Lemming lemming : lemmings)
		{
			if(lemming.getX() == Settings.SCENE_WIDTH)
				lemming.setDirection(LEFT);
			if(lemming.getX() == 0)
				lemming.setDirection(RIGHT);
			fallOutOfBoundsDetection(lemming);
			goalCollided(goal, lemming);
		}		
	}

	public void platormCollisionResolver(List<Obstacle> obstacles)
	{
		boolean falling = true;
		for (Lemming lemming : lemmings) 
		{
	        for (Obstacle obstacle : obstacles) {
                if (lemming.getX() < obstacle.getX() + obstacle.getWidth()
                    && lemming.getX() + Settings.LEMMINGS_WIDTH >= obstacle.getX()
                    && lemming.getY() < obstacle.getY() + obstacle.getHeight()
                    && lemming.getY() + Settings.LEMMINGS_HEIGHT >= obstacle.getY()) 
                {
                	if(lemming.getJob() == Faller.getInstance() || lemming.getJob() == Parachute.getInstance())
                		lemming.setJob(Walker.getInstance());
                	falling = false;
                }
                detectObstacleEdge(lemming, obstacle, falling);
	        }
		}
	}
	
	private void detectObstacleEdge(Lemming lemming, Obstacle obstacle, boolean falling)
	{
        if(lemming.getX() > (obstacle.getX()+obstacle.getWidth()) || lemming.getX()+Settings.LEMMINGS_WIDTH < obstacle.getX() && falling) //if at edge
        {
        	if(lemming.getJob() != Parachute.getInstance())
        		lemming.setJob(Faller.getInstance());
        	falling = true;
        }
	}
	
	private void goalCollided(Goal goal, Lemming lemming)
	{
		if(goal.contains(lemming.getX(), lemming.getY()))
			lemming.setInGoal();
	}
	
	private void fallOutOfBoundsDetection(Lemming lemming)
	{
		if(lemming.getY() > Settings.SCENE_HEIGHT)
			lemming.setIsDead();
	}
	
	@Override
	public void update()
	{
		screenEdgeCollisionDetection();
		platormCollisionResolver(obstacles);
	}
	
}