package hambaguette.model.game;

import java.util.Random;

public class Ham
{
	private Vector2 pos;
	
	public Ham(Baguette baguette, Lettuces lettuces)
	{
		Random random = new Random();
		boolean overlap;
		do
		{
			overlap = false;
			pos = new Vector2(random.nextInt(14), random.nextInt(7));
			for (BaguetteSegment bs : baguette.getSegments())
			{
				Vector2 segmentPos = bs.getPosition();
				if (segmentPos.equals(pos))
				{
					overlap = true;
				}
			}
			
			for (Vector2 lPos : lettuces.positions)
			{
				if (lPos.equals(pos))
				{
					overlap = true;
				}
			}
		} while (overlap == true);
	}

	public Vector2 getPosition()
	{
		return pos;
	}
}
