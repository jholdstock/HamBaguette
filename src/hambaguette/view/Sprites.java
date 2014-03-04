package hambaguette.view;

import hambaguette.model.game.BaguetteSegment;
import hambaguette.model.game.Vector2;

import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Sprites
{
	public static BufferedImage BACKGROUND;
	public static BufferedImage GAMEOVER;
	public static BufferedImage TITLE;

	private static BufferedImage LEFTEND;
	private static BufferedImage HORIZONTAL;
	private static BufferedImage RIGHTEND;
	private static BufferedImage TOPEND;
	private static BufferedImage VERTICAL;
	private static BufferedImage BOTTOMEND;
	private static BufferedImage BOTTOMRIGHT;
	private static BufferedImage BOTTOMLEFT;
	private static BufferedImage TOPRIGHT;
	private static BufferedImage TOPLEFT;

	public static BufferedImage FOOD;
	public static BufferedImage LETTUCE;
	
	public static void loadSprites() throws SpriteLoadingException
	{
		BACKGROUND = loadImage("background.png");
		GAMEOVER = loadImage("gameover.png");
		TITLE = loadImage("title.png");

		BufferedImage spriteImage = loadImage("sprites.png");
		LEFTEND = spriteImage.getSubimage(0, 0, 16, 16);
		HORIZONTAL = spriteImage.getSubimage(16, 0, 16, 16);
		RIGHTEND = spriteImage.getSubimage(32, 0, 16, 16);
		TOPEND = spriteImage.getSubimage(48, 0, 16, 16);
		VERTICAL = spriteImage.getSubimage(48, 16, 16, 16);
		BOTTOMEND = spriteImage.getSubimage(48, 32, 16, 16);
		BOTTOMRIGHT = spriteImage.getSubimage(0, 16, 16, 16);
		BOTTOMLEFT = spriteImage.getSubimage(16, 16, 16, 16);
		TOPRIGHT = spriteImage.getSubimage(0, 32, 16, 16);
		TOPLEFT = spriteImage.getSubimage(16, 32, 16, 16);
		FOOD = spriteImage.getSubimage(32, 32, 16, 16);
		LETTUCE = spriteImage.getSubimage(32, 16, 16, 16);
	}

	private static BufferedImage loadImage(String fileName) throws SpriteLoadingException
	{
		BufferedImage retImage = null;
		try
		{
			URL url = Sprites.class.getResource("/img/" + fileName);
			retImage = ImageIO.read(url);
		}
		catch (Exception e)
		{
			throw new SpriteLoadingException("Image " + fileName + " not found: \n" + e.getLocalizedMessage());
		}
		return retImage;
	}
	
	public static BufferedImage getSpriteForSegment(int posInBaguette, ArrayList<BaguetteSegment> segments)
	{
		// I am not proud of this method :(
		Vector2 direction = segments.get(posInBaguette).getDirection();
		if (posInBaguette == 0) // Head
		{
			if (direction == Vector2.UP)
			{
				return TOPEND;
			}
			else if (direction == Vector2.DOWN)
			{
				return BOTTOMEND;
			}
			else if (direction == Vector2.LEFT)
			{
				return LEFTEND;
			}
			else
			{
				return RIGHTEND;
			}
		}
		else if (posInBaguette == segments.size() - 1) // Tail
		{
			Vector2 previousDirection = segments.get(posInBaguette - 1).getDirection();
			
			if (previousDirection == Vector2.UP)
			{
				return BOTTOMEND;
			}
			else if (previousDirection == Vector2.DOWN)
			{
				return TOPEND;
			}
			else if (previousDirection == Vector2.LEFT)
			{
				return RIGHTEND;
			}
			else
			{
				return LEFTEND;
			}
		} 
		else // Any other segment
		{
			Vector2 firstDirection = segments.get(posInBaguette - 1).getDirection();
			if ((firstDirection == Vector2.UP && direction == Vector2.UP) 
					|| (firstDirection == Vector2.DOWN && direction == Vector2.DOWN))
			{
				return VERTICAL;
			}
			else if ((firstDirection == Vector2.LEFT && direction == Vector2.LEFT)
					|| (firstDirection == Vector2.RIGHT && direction == Vector2.RIGHT))
			{
				return HORIZONTAL;
			}
			else if ((firstDirection == Vector2.RIGHT && direction == Vector2.UP)
					|| (firstDirection == Vector2.DOWN && direction == Vector2.LEFT))
			{
				return BOTTOMRIGHT;
			}
			else if ((firstDirection == Vector2.LEFT && direction == Vector2.UP)
					|| (firstDirection == Vector2.DOWN && direction == Vector2.RIGHT))
			{
				return BOTTOMLEFT;
			}
			else if ((firstDirection == Vector2.RIGHT && direction == Vector2.DOWN)
					|| (firstDirection == Vector2.UP && direction == Vector2.LEFT))
			{
				return TOPRIGHT;
			}
			else 
			{
				return TOPLEFT;
			}
		}
	}
}