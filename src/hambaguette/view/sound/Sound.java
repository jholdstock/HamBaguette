package hambaguette.view.sound;

import javax.sound.sampled.Clip;

public class Sound
{
	private Clip clip;
	
	public Sound(Clip clip)
	{
		this.clip = clip;
	}
	
	public void play() throws SoundPlayingException
	{
		try
		{
			if (clip != null)
			{
				Thread soundThread = new Thread()
				{
					public void run()
					{
						synchronized (clip)
						{
							clip.stop();
							clip.setFramePosition(0);
							clip.start();
						}
					}
				};
				soundThread.start();
			}
		}
		catch (Exception e)
		{
			throw new SoundPlayingException("Could not play sound");
		}
	}
}
