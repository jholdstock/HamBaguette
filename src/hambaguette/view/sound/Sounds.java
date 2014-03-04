package hambaguette.view.sound;

import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

public class Sounds
{
	public static Sound EATEN;
	public static Sound DEATH;

	public static void loadSounds() throws SoundLoadingException
	{
		EATEN = loadSound("pickup.wav");
		DEATH = loadSound("death.wav");
	}

	public static Sound loadSound(String fileName) throws SoundLoadingException
	{
		Sound retSound;
		try
		{
			AudioInputStream ais = AudioSystem.getAudioInputStream(Sounds.class.getResource("/snd/" + fileName));
			Clip clip = AudioSystem.getClip();
			clip.open(ais);
			retSound = new Sound(clip);
		}
		catch (Exception e)
		{
			throw new SoundLoadingException("Sound " + fileName + " not found");
		}
		return retSound;
	}
}