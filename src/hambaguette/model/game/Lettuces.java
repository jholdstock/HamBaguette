package hambaguette.model.game;

import java.util.Random;

public class Lettuces
{
	public Vector2[] positions;

	public Lettuces(int numberOfLettuces)
	{
		positions = new Vector2[numberOfLettuces];
		Random random = new Random();

		do
		{			
			for (int i = 0; i < positions.length; i++)
			{
				int yPos;
				do
				{
					yPos = random.nextInt(7);
				} while (yPos == 3); // Prevent lettuce on starting row - adds false difficulty to game
				
				int xPos = random.nextInt(14);
						
				positions[i] = new Vector2(xPos, yPos);
			}
		} while (lettucesOverlapping() == true);
	}

	private boolean lettucesOverlapping()
	{
		if (positions.length == 1)
		{
			return false;
		}
		
		boolean overlap = false;
		for (int i = 0; i < positions.length; i++)
		{
			for (int j = 0; j < positions.length; j++)
			{
				if (i == j)
				{
					continue;
				}
				else
				{
					if (positions[i].equals(positions[j]))
					{
						overlap = true;
					}
				}
			}			
		}
		return overlap;
	}

}
