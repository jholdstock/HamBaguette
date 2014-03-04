package hambaguette.model.game;

public class BaguetteSegment
{
	private Vector2 pos;
	private Vector2 direction;

	public BaguetteSegment(Vector2 pos, Vector2 direction)
	{
		this.pos = pos;
		this.direction = direction;
	}

	public Vector2 getPosition()
	{
		return pos;
	}

	public Vector2 getDirection()
	{
		return direction;
	}
}