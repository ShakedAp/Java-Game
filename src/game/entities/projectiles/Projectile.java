package game.entities.projectiles;

import game.Handler;
import game.entities.Entity;

public abstract class Projectile extends Entity{

	protected double velX, velY;
	
	
	public Projectile(Handler handler, float x, float y, int width, int height, double velX, double velY) {
		super(handler, x, y, width, height);
		this.velX = velX;
		this.velY = velY;
	}
	
	public void move(){
		x+= velX;
		y+= velY;
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
