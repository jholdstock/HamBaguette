package hambaguette.controller;

import hambaguette.model.game.Game;
import hambaguette.model.menu.Menu;
import hambaguette.view.GameCanvas;
import hambaguette.view.sound.SoundPlayingException;

import javax.swing.JOptionPane;

public class GameComponent
{
	private Game game;
	private Menu menu;
	private int refreshRate;
	private GameState currentState;
	private GameCanvas canvas;
	private GameController gc;
	private MenuController mc;
	
	public GameComponent()
	{
		currentState = GameState.MENU;
		refreshRate = 50;
		menu = new Menu();
		mc = new MenuController(menu);
		game = new Game();
		gc = new GameController(game);
		canvas = new GameCanvas(menu, game);
		canvas.addKeyListener(new GameKeyListener());
	}

	public synchronized void start()
	{
		Thread thread = new Thread(){
			public synchronized void run()
			{
				long lastTime = System.currentTimeMillis();

				while (true)
				{
					long time = System.currentTimeMillis();
					if (time - lastTime >= refreshRate)
					{
						try
						{
							gameLoop();
						}
						catch (SoundPlayingException e)
						{
							JOptionPane.showMessageDialog(canvas.getParent(), e.getMessage());
							System.exit(1);
						}
						lastTime = time;
						GameKeyListener.keyPressed = ' ';
						canvas.repaint();
					}
				}
			}
		};
		thread.start();
	}
	
	private void gameLoop() throws SoundPlayingException
	{
		char keyPressed = GameKeyListener.keyPressed;
		switch (currentState)
		{
			case GAMEPLAY:
				gc.processKeys(keyPressed);
				currentState = game.updateGame();
				break;

			case GAMEOVER:
				if (keyPressed == 'S')
				{
					currentState = GameState.MENU;
					refreshRate = 50;
				}
				break;

			case FLASHING:
				currentState = game.advanceFlashing();
				break;

			case MENU:
				mc.processKeys(keyPressed);
				if (GameKeyListener.keyPressed == 'S')
				{
					game.reset(menu.getLettuce(), menu.wallsEnabled(), menu.getSpeed());
					refreshRate = 300 - (25 * menu.getSpeed());
					currentState = GameState.GAMEPLAY;
				}
				break;
		}
		canvas.currentState = currentState;
	}

	public GameCanvas getCanvas()
	{
		return canvas;
	}
}