package model;

import javafx.scene.Node;

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
		// TODO Auto-generated method stub
		return null;
	}
	
}
