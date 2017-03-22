package model;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import view.Layer;

public class Goal extends Rectangle
{
	private final double x, y, width, height;
	private Node node;
	
	public Goal(double x, double y, double width, double height, Layer layer)
	{
		this.x = x;
		this.width = width;
		this.y = y;
		this.height = height;
		
		node = createView();
		layer.getChildren().add(node);
	}

	private Node createView()
	{
		setStroke(Color.GREENYELLOW);
		setFill(Color.GREEN);
		setX(x);
		setY(y);
		setWidth(width);
		setHeight(height);
		return this;
	}
}
