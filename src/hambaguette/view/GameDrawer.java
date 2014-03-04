package hambaguette.view;

import hambaguette.model.game.Baguette;
import hambaguette.model.game.BaguetteSegment;
import hambaguette.model.game.Game;
import hambaguette.model.game.Ham;
import hambaguette.model.game.Lettuces;
import hambaguette.model.game.Vector2;
import hambaguette.model.menu.Menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;

public class GameDrawer
{
	private final int TILESIZE = 16;
	private Graphics2D graphics;
	private AffineTransform screenTransform;
	private Image offScreen;
	
	public GameDrawer(Image offScreen)
	{	
		this.offScreen = offScreen;
		screenTransform = new AffineTransform();
		screenTransform.setToScale(3, 3);
		
		graphics = (Graphics2D) offScreen.getGraphics();
		graphics.setFont(new Font("Arial", Font.PLAIN, 10));
		graphics.setColor(Color.WHITE);
	}
	
	public void drawImage(Graphics2D graphics)
	{
		graphics.drawImage(offScreen, screenTransform, null);
	}
	
	public void drawGame(Game game)
	{
		drawBackground();
		drawHam(game.getHam());
		drawLettuces(game.getLettuces());
		drawPoints(game.getPoints());
		if (game.getDrawBaguette())
		{
			drawBaguette(game.getBaguette());
		}

	}
	
	public void drawMenu(Menu menu)
	{
		drawBackground();
		graphics.drawImage(Sprites.TITLE, null, 0, 0);
		graphics.drawString("Up/down to set difficulty: " + menu.getSpeed(), 70, 82);
		graphics.drawString("Left/right to set lettuce: " + menu.getLettuce(), 75, 94);
		graphics.drawString("W to toggle walls: " + (menu.wallsEnabled() ? "on" : "off"), 84, 106);
		graphics.drawString("SPACE to begin", 90, 125);
	}
	
	private void drawPoints(int points)
	{
		graphics.drawString("POINTS: " + points, 15, 12);
	}
	
	private void drawBaguette(Baguette baguette)
	{
		int baguetteLength = baguette.getSegments().size();
		for (int i = 0; i < baguetteLength; i++)
		{
			BaguetteSegment bagseg = baguette.getSegments().get(i);
			Vector2 bagsegPos = bagseg.getPosition();
			int posX = bagsegPos.getX();
			int posY = bagsegPos.getY();
			graphics.drawImage(Sprites.getSpriteForSegment(i, baguette.getSegments()), null, TILESIZE + (TILESIZE * posX),
					TILESIZE + (TILESIZE * posY));
		}
	}
	
	private void drawLettuces(Lettuces lettuces)
	{
		for (Vector2 lettucePos : lettuces.positions)
		{
			graphics.drawImage(Sprites.LETTUCE, null, TILESIZE + (lettucePos.getX() * TILESIZE), TILESIZE + (lettucePos.getY() * TILESIZE));
		}
	}

	private void drawHam(Ham ham)
	{
		graphics.drawImage(Sprites.FOOD, null, TILESIZE + (ham.getPosition().getX() * TILESIZE), TILESIZE + (ham.getPosition().getY() * TILESIZE));
	}

	public void drawGameOver()
	{
		graphics.drawImage(Sprites.GAMEOVER, null, 0, 0);
	}

	private void drawBackground()
	{
		graphics.drawImage(Sprites.BACKGROUND, null, 0, 0);
	}
}
