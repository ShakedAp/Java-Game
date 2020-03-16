package game.gfx.sounds;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundEffect {
	
	private Clip clip;
	
	public SoundEffect (String soundPath){
		try{
			File file = new File(soundPath);
			AudioInputStream sound = AudioSystem.getAudioInputStream(file);	
			clip = AudioSystem.getClip();
			clip.open(sound);
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println(e);
		}

	}
	
	public void play(){
		clip.setFramePosition(0);
		clip.start();
		
	}

}
