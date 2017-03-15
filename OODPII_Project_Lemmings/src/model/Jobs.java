package model;

import javafx.scene.Node;
import javafx.scene.shape.Rectangle;

public interface Jobs
{
	public Node createView(int x, int y);
	public Node changeView(Rectangle node, int x, int y);
}
