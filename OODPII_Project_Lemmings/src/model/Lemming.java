package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;

public class Lemming extends Region
{
	private int x, y;
	private Jobs job;
	private GraphicsContext gc;
	
	public Lemming(int x, int y)
	{
		this.x = x;
		this.y = y;
		setJob(Walker.getInstance());
	}
	
	public void setJob(Jobs job)
	{
		this.job = job;
	}
	
	public void move()
	{
		gc.moveTo(x++, y++);
	}
	
	public void draw(GraphicsContext gc)
	{
		this.gc = gc;
		gc.setFill(Color.WHITE);
		gc.fillRect(x, y, 20, 20);
	}
}
