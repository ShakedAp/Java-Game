package game.entities.projectiles;

import java.awt.Graphics;

import game.Handler;
import game.entities.Entity;

public abstract class Projectile extends Entity {

	protected final int xOrigin, yOrigin; //start point
	protected double angle;
	protected double moveX, moveY;
	protected double speed, range, damage;
	
	public Projectile(Handler handler, float x, float y, double dir, int width, int height , int speed) {
		super(handler, x, y, width, height);
		
		this.speed = speed;
		this.xOrigin = (int) x;
		this.yOrigin = (int) y;
		angle = dir;
		
		moveX = speed * Math.cos(angle);
		moveY = speed * Math.sin(angle);
	}
	
	protected void move(){
		x += moveX;
		y += moveY;
	}
	
	@Override
	public boolean isSolid() {
		return false;
	}
	
	
}
