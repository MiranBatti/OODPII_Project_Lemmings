package model;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import view.Settings;

public class Faller implements Jobs
{
	private static Faller instance = null;
	
	protected Faller()
	{
		
	}
	
	public static Faller getInstance()
	{
		if(instance == null)
			instance = new Faller();
		return instance;
	}
	
	public Node createView(int x, int y)
	{
		Rectangle rectangle = new Rectangle(Settings.LEMMINGS_WIDTH, Settings.LEMMINGS_HEIGHT);
		
		rectangle.setX(x);
		rectangle.setY(y);
		
		rectangle.setStroke(Color.BLUE);
		rectangle.setFill(Color.BLUEVIOLET);

		return rectangle;
	}

	@Override
	public Node changeView(Node node, int x, int y)
	{
//		node.relocate(node.getLayoutX(), node.getLayoutY());
		Rectangle rectangle = (Rectangle)node;
		rectangle.relocate(x, y);
		rectangle.setStroke(Color.BLUE);
		rectangle.setFill(Color.BLUEVIOLET);
		
		return node;
	}
}
