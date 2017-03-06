package model;

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
}
