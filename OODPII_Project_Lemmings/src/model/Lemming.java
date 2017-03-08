package model;

import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import view.Layer;

public class Lemming extends Region
{
	private int x, y;
	private Jobs job;
	private Node node;
	
	public Lemming(int x, int y, Layer layer)
	{
		this.x = x;
		this.y = y;
		setJob(Faller.getInstance());
		
		node = createView();
		getChildren().add(node);
		layer.getChildren().add(this);
	}
	
	public void setJob(Jobs job)
	{
		this.job = job;
	}
	
	public Node createView()
	{
		return job.createView(x, y);
	}
	
	public void move()
	{
		relocate(200, 100);
	}
	
}
