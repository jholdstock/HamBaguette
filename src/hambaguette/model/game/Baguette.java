package hambaguette.model.game;

import java.util.ArrayList;

public class Baguette
{
	private ArrayList<BaguetteSegment> segments;
	private Vector2 currentDirection;
	private boolean dead;

	public Baguette()
	{
		segments = new ArrayList<BaguetteSegment>();
		segments.add(new BaguetteSegment(new Vector2(4, 3), Vector2.RIGHT));
		segments.add(new BaguetteSegment(new Vector2(3, 3), Vector2.RIGHT));
		segments.add(new BaguetteSegment(new Vector2(2, 3), Vector2.RIGHT));
		currentDirection = Vector2.RIGHT;
		dead = false;
	}

	public boolean isDead()
	{
		return dead;
	}

	public ArrayList<BaguetteSegment> getSegments()
	{
		return segments;
	}

	public Vector2 getNextHeadPos(boolean walls)
	{
		Vector2 currentHeadPos = segments.get(0).getPosition();
		Vector2 newHeadPos = Vector2.add(currentHeadPos, currentDirection);
		if (walls == false)
		{
			int posX = newHeadPos.getX();
			int posY = newHeadPos.getY();
			if (posX >= 14)
			{
				newHeadPos.setX(0);
			}
			else if (posX < 0)
			{
				newHeadPos.setX(13);
			}
			else if (posY >= 7)
			{
				newHeadPos.setY(0);
			}
			else if (posY < 0)
			{
				newHeadPos.setY(6);
			}
		}
		return newHeadPos;
	}

	public void checkForCollisions(boolean walls, Lettuces lettuces)
	{
		Vector2 newHeadPos = getNextHeadPos(walls);
		
		checkForCollisionWithSelf(newHeadPos);
		checkForCollisionWithLettuces(newHeadPos, lettuces);
		
		if (walls == true)
		{
			checkForCollisionWithWalls(newHeadPos);
		}		
	}

	private void checkForCollisionWithWalls(Vector2 newHeadPos)
	{
		int posX = newHeadPos.getX();
		int posY = newHeadPos.getY();
		if (posX < 0 | posY < 0 | posX >= 14 | posY >= 7)
		{
			dead = true;
		}
	}

	private void checkForCollisionWithLettuces(Vector2 newHeadPos, Lettuces lettuces)
	{
		for (Vector2 lPos : lettuces.positions)
		{
			if (newHeadPos.equals(lPos))
			{
				dead = true;
			}
		}
	}

	private void checkForCollisionWithSelf(Vector2 newHeadPos)
	{
		for (int i = 0; i < segments.size() - 1; i++)
		{
			BaguetteSegment bagseg = segments.get(i);
			if (newHeadPos.equals(bagseg.getPosition()))
			{
				dead = true;
			}
		}
	}

	public void changeDirection(Vector2 newDirection)
	{
		if (Vector2.add(currentDirection, newDirection).equals(Vector2.ZERO))
		{
			return;
		}
		else
		{
			currentDirection = newDirection;
		}
	}

	public void move(boolean foodEaten, boolean walls)
	{
		addNewHead(walls);

		if (!foodEaten)
		{
			removeTail();
		}
	}

	private void addNewHead(boolean walls)
	{
		Vector2 newHeadPos = getNextHeadPos(walls);
		BaguetteSegment newHead = new BaguetteSegment(newHeadPos, currentDirection);
		segments.add(0, newHead);
	}

	private void removeTail()
	{
		segments.remove(segments.size() - 1);
	}
}