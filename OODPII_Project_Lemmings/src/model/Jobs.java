package model;

import javafx.scene.Node;
import javafx.scene.shape.Rectangle;

public interface Jobs
{
	public Node createView(double x, double y);
	public Node changeView(Rectangle node, double x, double y);
}
