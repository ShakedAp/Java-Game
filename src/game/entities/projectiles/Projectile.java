package game.entities.projectiles;

import game.Handler;
import game.entities.Entity;

public abstract class Projectile extends Entity{

	protected double velX, velY;
	protected int tickLife;
	private int tickCount= 0;
	
	public Projectile(Handler handler, float fromX, float fromY, float toX, float toY, int width, int height,int tickLife) {
		super(handler, fromX, fromY, width, height);
		setVelocity(fromX, fromY, toX, toY);
		this.tickLife = tickLife;
	}
	
	public void move(){
		x+= velX;
		y+= velY;
	}
	
	public void tickLifeCounter() {
		if(tickCount >= tickLife) {
			kill();
			return;
			}
		tickCount++;
	}

	private void setVelocity(float fromX, float fromY, float toX, float toY) {	
		velX = (toX - fromX) / 15;
		velY = (toY - fromY) / 15;
	}
	
	
	
	//Getter Setters
	public double getVelX() {
		return velX;
	}

	public void setVelX(double velX) {
		this.velX = velX;
	}

	public double getVelY() {
		return velY;
	}

	public void setVelY(double velY) {
		this.velY = velY;
	}
	
	
	
	
	

}
