package model;

import java.util.List;

public abstract class Observer
{
	   protected List<Lemming> lemmings;
	   public abstract void update();	
}
