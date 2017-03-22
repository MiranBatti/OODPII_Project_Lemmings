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
	private int x, y;
	private Jobs job;
	private Node node;
	private final int RIGHT = 0;
	private final int LEFT = 1;
	private int direction = RIGHT;
	private int relative_x;
	private boolean isDead = false;
	private List<Observer> observers = new ArrayList<Observer>();	
	private Thread thread;
	
	public Lemming(int x, int y, Layer layer)
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
		//TODO: cannot change jobs while falling
		this.job = job;
		Rectangle tmp = (Rectangle)node;
		getChildren().remove(node);
		node = job.changeView(tmp, x, y);
		getChildren().add(node);
	}
	
	private Node createView()
	{
		return job.createView(x, y);
	}

	public void move()
	{
		notifyAllObservers();
		if(direction == RIGHT && !job.equals(Faller.getInstance()))
			node.relocate(x++, y);
		if(direction == LEFT && !job.equals(Faller.getInstance()))
			node.relocate(x--, y);
		if(job.equals(Faller.getInstance()))
		{
			node.relocate(x, y++);
		}
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

	public int getX()
	{
		return x;
	}

        	public void setDirection(int direction)
	{
		this.direction = direction;
	} 		
	
	public int getY()
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

	@Override
	public void run()
	{
		move();
	}
	
	
}
