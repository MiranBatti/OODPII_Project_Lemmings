package model;

import javafx.scene.Node;
import javafx.scene.layout.Region;
import view.Layer;
import view.Settings;

public class Lemming extends Region
{
	private int x, y;
	private Jobs job;
	private Node node;
	private final int RIGHT = 0;
	private final int LEFT = 1;
	private int direction = RIGHT;
	private int scene_x;
	
	public Lemming(int x, int y, Layer layer)
	{
		this.x = x;
		this.y = y;
		job = Faller.getInstance();
		
		scene_x = (int)Settings.SCENE_WIDTH - x;
		
		node = createView();
		getChildren().add(node);
		layer.getChildren().add(this);
	}
	
	public void setJob(Jobs job, Layer layer)
	{
		this.job = job;
		Node tmp = node;
		relocate(node.getLayoutX(), node.getLayoutY());
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
		collisionDetection();
		if(direction == RIGHT && !job.equals(Faller.getInstance()))
			relocate(x++, y);
		if(direction == LEFT && !job.equals(Faller.getInstance()))
			relocate(x--, y);
		if(job.equals(Faller.getInstance()))
			relocate(x, y++);
	}
	
	public Node getBounds()
	{
		return node;
	}
	
	private void collisionDetection()
	{
		if(x == scene_x)
			direction = LEFT;
		if(x == scene_x - Settings.SCENE_WIDTH)
			direction = RIGHT;
	}
	
}
