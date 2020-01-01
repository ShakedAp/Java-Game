package game.gfx;

import java.awt.image.BufferedImage;

import game.utils.Utils;

public class Animation {

	private int speed, index; //the speed per frame
	private long lastTime, deltaTime;
	private BufferedImage[] frames; //all of the frames (in our animation)
	
	public Animation(int speed, BufferedImage[] frames) {
		this.speed = speed;
		this.frames = frames;
		index = 0;
		
		//timer
		lastTime = System.currentTimeMillis(); //getting the current time in milliseconds
		deltaTime = 0;
	}
	
	public void update() {
		deltaTime += System.currentTimeMillis() - lastTime; //calculating the time that have been past
		lastTime = System.currentTimeMillis(); //updating the time to the current time
		
		
		if(deltaTime > speed && frames.length > 1) {
			deltaTime = 0;
			index ++;
		} 
		if(index >= frames.length)
			index = 0; 
	
	}
	
	
	public BufferedImage getCurrentFrame() {
		return frames[index];
	}
	
	
}
