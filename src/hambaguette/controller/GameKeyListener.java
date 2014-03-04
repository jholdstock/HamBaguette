package hambaguette.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameKeyListener implements KeyListener
{
	public static char keyPressed;
	
	public void keyPressed(KeyEvent e)
	{
		switch (e.getKeyCode())
		{
			case 38:
				keyPressed = 'u';
				break;
			case 40:
				keyPressed = 'd';
				break;
			case 37:
				keyPressed = 'l';
				break;
			case 39:
				keyPressed = 'r';
				break;
			case 32:
				keyPressed = 'S';
				break;
			case 87:
				keyPressed = 'W';
				break;
			case 27:
				System.exit(0);
				break;
			default:
				keyPressed = ' ';
				break;
		}
	}

	public void keyReleased(KeyEvent e)
	{}

	public void keyTyped(KeyEvent e)
	{}
}