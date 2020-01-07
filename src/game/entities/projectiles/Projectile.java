package game.entities.projectiles;

import game.Handler;
import game.entities.Entity;

public abstract class Projectile extends Entity{

	protected double velX, velY;
	
	
	public Projectile(Handler handler, float fromX, float fromY, float toX, float toY, int width, int height) {
		super(handler, fromX, fromY, width, height);
		velX = (toX - fromX) / 15;
		velY = (toY - fromY) / 15;
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
