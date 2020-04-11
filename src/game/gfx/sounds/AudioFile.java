package game.gfx.sounds;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

import game.Handler;

public class AudioFile implements LineListener {
	
	private Clip clip;
	private Handler handler;
	private long lastPosition;
	private boolean playing;
	
	public AudioFile(String path){
		clip = loadClip(path);
	}
	
	public void play(){
		clip.setFramePosition(0);
		clip.start();
		playing = true;
	}

	public void pause(){
		lastPosition = clip.getMicrosecondPosition();
		clip.stop();
		playing = false;
	}
	
	public void resume(){
		clip.setMicrosecondPosition(lastPosition);;
		clip.start();
		playing = true;	
	}
	
	public void stop(){
		clip.stop();
		playing = false;
	}
	
	@Override
	public void update(LineEvent event) {
		if(event.getType() == LineEvent.Type.START)
			playing = true;
		if(event.getType() == LineEvent.Type.STOP)
			playing = false;	
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

	public boolean isPlaying() {
		return playing;
	}

	public void setPlaying(boolean playing) {
		this.playing = playing;
	}
}
