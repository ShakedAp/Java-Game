package game.gfx;

import java.awt.image.BufferedImage;

public class Animation {

	private int speed, index; //The speed per frame (till we switch a frame)
	private long lastTime, deltaTime;
	private boolean loop, finished = false;
	private BufferedImage[] frames; 
	
	public Animation(int speed, BufferedImage[] frames, boolean loop) {
		this.speed = speed;
		this.frames = frames;
		this.loop = loop;
		index = 0;
		
		//Timer
		lastTime = System.currentTimeMillis(); //Current time in milliseconds
		deltaTime = 0;
	}
	
	public void update() {
		if(index >= frames.length-1 && !loop) {
			return;
		}
		deltaTime += System.currentTimeMillis() - lastTime; //Calculating the elapsed time
		lastTime = System.currentTimeMillis(); //Updating the current time
		
		//if we have reached the time that we need to switch a frame
		if(deltaTime > speed && frames.length > 1) {
			deltaTime = 0;
			index ++;
		} 

		if(index >= frames.length && loop) index = 0;
		if(index == frames.length-1 && !loop) finished=true;
	}
	
	
	public BufferedImage getCurrentFrame() {
		return frames[index];
	}
	
	//GETTERS & SETTERS
	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}
}
