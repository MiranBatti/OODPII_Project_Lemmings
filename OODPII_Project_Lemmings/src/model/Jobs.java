package model;

import javafx.scene.Node;

public interface Jobs
{
	public Node createView(int x, int y);
	public Node changeView(Node node, int x, int y);
}
