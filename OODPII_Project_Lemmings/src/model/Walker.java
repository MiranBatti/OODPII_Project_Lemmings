package model;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import view.Settings;

public class Walker implements Jobs
{
	private static Walker instance = null;
	
	protected Walker()
	{
		
	}
	
	public static Walker getInstance()
	{
		if(instance == null)
			instance = new Walker();
		return instance;
	}
	
	@Override
	public Node createView(int x, int y)
	{
		Rectangle rectangle = new Rectangle(Settings.LEMMINGS_WIDTH, Settings.LEMMINGS_HEIGHT);
		
		rectangle.setX(x);
		rectangle.setY(y);
		
		rectangle.setStroke(Color.RED);
		rectangle.setFill(Color.INDIANRED);

		return rectangle;
	}
	
	@Override
	public Node changeView(Node node, int x, int y)
	{
		Rectangle rectangle = (Rectangle)node;
		rectangle.relocate(node.getLayoutX(), node.getLayoutY());
		rectangle.setStroke(Color.RED);
		rectangle.setFill(Color.MEDIUMVIOLETRED);
		
		return rectangle;
	}
	
}
