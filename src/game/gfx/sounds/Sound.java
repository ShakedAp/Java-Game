package game.gfx.sounds;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import game.Handler;

public class Sound {
	
	private Clip clip;
	private Handler handler;
	
	private boolean loop;
	private long lastPosition;
	
	public Sound (String path, boolean loop, Handler handler){
		this.handler = handler;
		this.loop = loop;
		clip = loadClip(".//res//sounds//" + path);
	}
	
	public void play(){
		if(handler != null && !handler.getGame().isSfxOn())
			return;
		clip.setFramePosition(0);
		clip.start();
		if(loop) clip.loop(Clip.LOOP_CONTINUOUSLY);
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
		if(loop) clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	// Helper method
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

	public boolean isLoop() {
		return loop;
	}

	public void setLoop(boolean loop) {
		this.loop = loop;
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
