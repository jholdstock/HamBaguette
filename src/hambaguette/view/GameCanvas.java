package hambaguette.view;

import hambaguette.controller.GameState;
import hambaguette.model.game.Game;
import hambaguette.model.menu.Menu;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class GameCanvas extends Canvas
{
	private static final long serialVersionUID = 1L;
	
	GameDrawer gd;
	public GameState currentState;
	public Game game;
	private Menu menu;	
	
	public GameCanvas(Menu menu, Game game)
	{
		Image offscreen = new BufferedImage(768, 432, 1);
		gd = new GameDrawer(offscreen);
		this.menu = menu;
		this.game = game;
	}
	
	public void update(Graphics g)
	{
		if (currentState == GameState.MENU)
		{
			gd.drawMenu(menu);
		}
		else
		{
			gd.drawGame(game);
		}
		
		if (currentState == GameState.GAMEOVER)
		{
			gd.drawGameOver();
		}
		
		Graphics2D g2 = (Graphics2D) g;
		gd.drawImage(g2);
	}
}
