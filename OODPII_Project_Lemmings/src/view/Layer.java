package view;

import javafx.scene.layout.Pane;

public class Layer extends Pane
{
	public Layer(double width, double height)
	{
		setPrefSize(width, height);
		setStyle("-fx-background-color: #000000");
	}
	
	public void setBackground(String value)
	{
		setStyle("-fx-background-color: #" + value);
	}
}
