package hambaguette;

import hambaguette.controller.GameComponent;
import hambaguette.view.SpriteLoadingException;
import hambaguette.view.Sprites;
import hambaguette.view.sound.SoundLoadingException;
import hambaguette.view.sound.Sounds;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Main
{
	public static void main(String[] args)
	{	
		JFrame frame = new JFrame("Ham Baguette - By Jamie Holdstock");
		try
		{
			Sprites.loadSprites();
			Sounds.loadSounds();

		}
		catch (SpriteLoadingException|SoundLoadingException e)
		{
			JOptionPane.showMessageDialog(frame, e.getMessage());
			System.exit(1);
		}
		GameComponent gameComponent = new GameComponent();
		frame.setSize(774, 460);
		frame.setLayout(new BorderLayout());
		frame.add(gameComponent.getCanvas(), BorderLayout.CENTER);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		gameComponent.start();
	}

}
