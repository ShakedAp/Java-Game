package game.sounds;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import game.Handler;

public class SoundEffect {
	
	private Clip clip;
	private Handler handler;
	
	private long lastPosition;
	
	public SoundEffect (String path, Handler handler){
		this.handler = handler;
		clip = loadClip("./res/sounds/" + path + ".wav");
	}
	
	public void play(){
		if(handler != null && !handler.getGame().isSfxOn())
			return;
		clip.setFramePosition(0);
		clip.start();
	}

	public void pause(){
		lastPosition = clip.getMicrosecondPosition();
		clip.stop();
	}
	
	public void resume(){
		if(handler != null && !handler.getGame().isSfxOn())
			return;
		clip.setMicrosecondPosition(lastPosition);;
		clip.start();
	}
	
	// Helper methods
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

	//GETTERS & SETTERS
	public Clip getClip() {
		return clip;
	}

	public void setClip(Clip clip) {
		this.clip = clip;
	}

	public long getLastPosition() {
		return lastPosition;
	}

	public void setLastPosition(long lastPosition) {
		this.lastPosition = lastPosition;
	}

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}
	
}
