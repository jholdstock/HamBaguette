package hambaguette.controller;

import hambaguette.model.game.Game;
import hambaguette.model.game.Vector2;

public class GameController 
{
	private Game game;
	
	public GameController(Game game)
	{
		this.game = game;
	}
	
	public void processKeys(char keyPressed)
	{
		switch (keyPressed)
		{
			case 'u':
				game.getBaguette().changeDirection(Vector2.UP);
				break;

			case 'd':
				game.getBaguette().changeDirection(Vector2.DOWN);
				break;

			case 'l':
				game.getBaguette().changeDirection(Vector2.LEFT);
				break;

			case 'r':
				game.getBaguette().changeDirection(Vector2.RIGHT);
				break;
				
			default:
				return;
		}
	}
}
