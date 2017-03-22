package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javafx.scene.Node;
import javafx.scene.layout.Region;
import javafx.scene.shape.Rectangle;
import view.Layer;
import view.Settings;

public class Lemming extends Region implements Runnable
{
	private double x, y;
	private Jobs job;
	private Node node;
	private final int RIGHT = 0;
	private final int LEFT = 1;
	private int direction = RIGHT;
	private int relative_x;
	private boolean isDead = false;
	private List<Observer> observers = new ArrayList<Observer>();	
	private Thread thread;
	private boolean hasTeleported = false;
	
	public Lemming(double x, double y, Layer layer)
	{
		this.x = x;
		this.y = y;
		job = Faller.getInstance();
		
		node = createView();
		getChildren().add(node);
		layer.getChildren().add(this);
	}
	
	public void setJob(Jobs job)
	{
		this.job = job;
		Rectangle tmp = (Rectangle)node;
		getChildren().remove(node);
		node = job.changeView(tmp, x, y);
		getChildren().add(node);
	}
	
	public Jobs getJob()
	{
		return job;
	}
	
	private Node createView()
	{
		return job.createView(x, y);
	}

	public void move()
	{
		notifyAllObservers();
		if(direction == RIGHT && job.equals(Walker.getInstance()))
			node.relocate(x+=Settings.LEMMINGS_WALKING_SPEED, y);
		if(direction == LEFT && job.equals(Walker.getInstance()))
			node.relocate(x-=Settings.LEMMINGS_WALKING_SPEED, y);
		if(job.equals(Faller.getInstance()))
			node.relocate(x, y+=Settings.LEMMINGS_FALLING_SPEED);
		if(job.equals(Parachute.getInstance()))
			node.relocate(x, y+=Settings.LEMMINGS_FALLING_SPEED/2);
	}
	
	public Node getBounds()
	{
		return node;
	}
	
   public void notifyAllObservers()
   {
      for (Observer observer : observers) 
      {
         observer.update();
      }
	}

	public double getX()
	{
		return x;
	}

        	public void setDirection(int direction)
	{
		this.direction = direction;
	} 		
	
	public double getY()
	{
		return y;
	}

	public void setY(int y)
	{
		this.y = y;
	}
	
	public void attach(Observer observer)
	{
	    observers.add(observer);		
	}
	
	public void setInGoal()
	{
		hasTeleported = true;
	}

	@Override
	public void run()
	{
		move();
	}

	public boolean getIfHasTeleported()
	{
		return hasTeleported;
	}

	public void setIsDead()
	{
		isDead = true;
	}
	
	public boolean isDead()
	{
		return isDead;
	}
	
	@Override
	public String toString()
	{
		return	"this Lemming";
	}
	
}
