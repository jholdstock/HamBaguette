package hambaguette.model.menu;

public class Menu
{
	private int lettuce = 2;
	private boolean walls = true;
	private int speed = 7;
	
	public void toggleWalls()
	{
		walls  = !walls ;
	}
	
	public boolean wallsEnabled()
	{
		return walls ;
	}
	
	public void increaseLettuce()
	{
		if (lettuce < 9)
		{
			lettuce++;
		}
	}
	
	public void decreaseLettuce()
	{
		if (lettuce > 1)
		{
			lettuce--;
		}
	}
	
	public int getLettuce()
	{
		return lettuce;
	}
	
	public void increaseSpeed()
	{
		if (speed < 9)
		{
			speed++;
		}
	}
	
	public void decreaseSpeed()
	{
		if (speed > 1)
		{
			speed--;
		}
	}
	
	public int getSpeed()
	{
		return speed;
	}
}
