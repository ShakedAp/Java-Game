package game.gfx.sounds;

import javax.sound.sampled.Clip;

public class SoundObject {
	
	private Clip clip;
	
	private boolean loop;
	private long lastPosition;
	
	public SoundObject (Clip clip){
		this.clip = clip;
	}
	
	public void play(){
		clip.setFramePosition(0);
		clip.start();
		if(loop) clip.loop(Clip.LOOP_CONTINUOUSLY);
	}

	public void pause(){
		lastPosition = clip.getMicrosecondPosition();
		clip.stop();
	}
	
	public void resume(){
		clip.setMicrosecondPosition(lastPosition);;
		clip.start();
		if(loop) clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
}
