package model;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import view.Settings;

public class Stopper implements Jobs, Selectable
{

	private static Stopper instance = null;
	
	protected Stopper()
	{
		
	}
	
	public static Stopper getInstance()
	{
		if(instance == null)
			instance = new Stopper();
		return instance;
	}
	
	@Override
	public Node createView(double x, double y)
	{
		Rectangle rectangle = new Rectangle(Settings.LEMMINGS_WIDTH, Settings.LEMMINGS_HEIGHT);
		
		rectangle.setX(x);
		rectangle.setY(y);
		
		rectangle.setStroke(Color.DEEPPINK);
		rectangle.setFill(Color.DIMGRAY);

		return rectangle;
	}
	
	@Override
	public Node changeView(Rectangle node, double x, double y)
	{
		node.setStroke(Color.DEEPPINK);
		node.setFill(Color.DIMGRAY);
		
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
