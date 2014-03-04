package hambaguette.model.game;

import hambaguette.controller.GameState;
import hambaguette.view.sound.SoundPlayingException;
import hambaguette.view.sound.Sounds;

public class Game
{
	private int deadTimer;
	private boolean drawBaguette;
	
	private Baguette baguette;
	private Ham ham;
	private Lettuces lettuces;

	private int points;

	private boolean walls;
	private int speed;

	public void reset(int noLettuces, boolean wallsIn, int speedIn)
	{
		speed = speedIn;
		deadTimer = 0;
		walls = wallsIn;
		
		baguette = new Baguette();
		points = 0;
		lettuces = new Lettuces(noLettuces);

		ham = new Ham(baguette, lettuces);
		
		drawBaguette = true;
	}
	
	public Ham getHam()
	{
		return ham;
	}

	public Lettuces getLettuces()
	{
		return lettuces;
	}

	public int getPoints()
	{
		return points;
	}
	
	public Baguette getBaguette()
	{
		return baguette;
	}
	
	public boolean getDrawBaguette()
	{
		return drawBaguette;
	}
	
	public GameState advanceFlashing()
	{
		if (deadTimer < 8)
		{
			drawBaguette = !drawBaguette;
			deadTimer++;
			return GameState.FLASHING;
		}
		else
		{
			return GameState.GAMEOVER;
		}
	}


	public GameState updateGame() throws SoundPlayingException
	{
		baguette.checkForCollisions(walls, lettuces);

		if (baguette.isDead())
		{
			Sounds.DEATH.play();
			return GameState.FLASHING;
		}
		else
		{
			boolean hamEaten = false;
			if (baguette.getNextHeadPos(walls).equals(ham.getPosition()))
			{
				hamEaten = true;
				points = points + speed;
				Sounds.EATEN.play();
			}
			
			baguette.move(hamEaten, walls);
			
			if (hamEaten)
			{
				ham = new Ham(baguette, lettuces);
			}
			return GameState.GAMEPLAY;
		}
	}
}