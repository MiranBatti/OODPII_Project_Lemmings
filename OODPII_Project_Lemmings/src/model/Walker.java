package model;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import view.Settings;

public class Walker implements Jobs, Selectable
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
	public Node createView(double x, double y)
	{
		Rectangle rectangle = new Rectangle(Settings.LEMMINGS_WIDTH, Settings.LEMMINGS_HEIGHT);
		
		rectangle.setX(x);
		rectangle.setY(y);
		
		rectangle.setStroke(Color.RED);
		rectangle.setFill(Color.INDIANRED);

		return rectangle;
	}
	
	@Override
	public Node changeView(Rectangle node, double x, double y)
	{
		node.setStroke(Color.RED);
		node.setFill(Color.MEDIUMVIOLETRED);
		
		return node;
	}
	
	public Node getImage()
	{
		Rectangle r = new Rectangle(Settings.SCENE_WIDTH/8, Settings.SCENE_HEIGHT/8);
		r.setFill(Color.AQUA);
		return r;
	}
	
	@Override
	public String toString()
	{
		return "this object";
	}
	
}
