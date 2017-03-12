package model;

import javafx.scene.Node;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import view.Layer;

public class Obstacle extends Region
{
	private double startX, startY, endX, endY;
	private Node node;
	
	public Obstacle(int startX, double d, double sceneWidth, double e, Layer layer)
	{
		this.startX = startX;
		this.endX = sceneWidth;
		this.startY = d;
		this.endY = e;
		
		node = createView();
		getChildren().add(node);
		layer.getChildren().add(this);
	}

	private Node createView()
	{
		Line line = new Line();
		line.setStroke(Color.WHITE);
		line.setStartX(startX);
		line.setEndX(endX);
		line.setStartY(startY);
		line.setEndY(endY);
		return line;
	}
}
