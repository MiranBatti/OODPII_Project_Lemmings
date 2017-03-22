package model;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import view.Settings;

public class Parachute implements Jobs, Selectable
{
	private static Parachute instance = null;
	
	protected Parachute()
	{
		
	}
	
	public static Parachute getInstance()
	{
		if(instance == null)
			instance = new Parachute();
		return instance;
	}
	
	@Override
	public Node createView(double x, double y)
	{
		Rectangle rectangle = new Rectangle(Settings.LEMMINGS_WIDTH, Settings.LEMMINGS_HEIGHT*2);
		
		rectangle.setX(x);
		rectangle.setY(y);
		
		rectangle.setStroke(Color.BLUE);
		rectangle.setFill(Color.ORANGERED);

		return rectangle;
	}
	
	@Override
	public Node changeView(Rectangle node, double x, double y)
	{
		node.setStroke(Color.BLUE);
		node.setFill(Color.ORANGERED);
		
		return node;
	}

	@Override
	public Node getImage()
	{
		Rectangle r = new Rectangle(Settings.SCENE_WIDTH/8, Settings.SCENE_HEIGHT/8);
		r.setFill(Color.DARKRED);
		return r;
	}
}
