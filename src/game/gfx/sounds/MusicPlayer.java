package game.gfx.sounds;

import java.io.File;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class MusicPlayer {

	ArrayList<SoundObject> sounds = new ArrayList<SoundObject>();
	
	public MusicPlayer(String... paths) {
		addSounds(paths);
		
		sounds.get(0).play();
	}
	
	public void addSounds(String... paths){
		for(String path : paths) {
			SoundObject s = new SoundObject(loadClip(".//res//sounds//" + path));
			sounds.add(s);
		}
	}
	
	private Clip loadClip(String path) {
		try{	
			AudioInputStream sound = AudioSystem.getAudioInputStream(loadFile(path));	
			Clip clip = AudioSystem.getClip();
			clip.open(sound);
			return clip;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	private File loadFile(String path) {
		File file = new File(path);
		if(file.exists())
			return file;
		else {
			System.out.println("File: " + path +  " could not be found.");
			return null;
		}
	}

}
