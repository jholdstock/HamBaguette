package hambaguette.model.game;

public class Vector2
{
	private int x;
	private int y;

	public static final Vector2 ZERO = new Vector2(0, 0);
	public static final Vector2 UP = new Vector2(0, -1);
	public static final Vector2 DOWN = new Vector2(0, 1);
	public static final Vector2 LEFT = new Vector2(-1, 0);
	public static final Vector2 RIGHT = new Vector2(1, 0);

	public Vector2(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public void setX(int x)
	{
		this.x = x;
	}
	
	public void setY(int y)
	{
		this.y = y;
	}

	@Override
	public boolean equals(Object param)
	{
		if (param == null)
		{
			return false;
		}
		
		Vector2 other;
		try
		{
			other = (Vector2) param;
		}
		catch (ClassCastException e)
		{
			return false;
		}
		
		if (x == other.x && y == other.y)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public static Vector2 add(Vector2 param1, Vector2 param2)
	{
		int newX = param1.x + param2.x;
		int newY = param1.y + param2.y;
		return new Vector2(newX, newY);
	}
}