package hambaguette.controller;

import hambaguette.model.menu.Menu;

public class MenuController 
{
	private Menu menu;
	
	public MenuController(Menu menu)
	{
		this.menu = menu;
	}
	
	public void processKeys(char keyPressed)
	{
		switch (keyPressed)
		{
			case 'u':
				menu.increaseSpeed();
				break;
				
			case 'd':
				menu.decreaseSpeed();
				break;
			
			case 'W':
				menu.toggleWalls();
				break;
			
			case 'l':
				menu.decreaseLettuce();
				break;
			
			case 'r':
				menu.increaseLettuce();
				break;
				
			default:
				return;
		}
	}
}
