package game.entities.projectiles;

import java.awt.Graphics;

import game.Handler;
import game.entities.Entity;

public abstract class Projectile extends Entity {

	protected final int xOrigin, yOrigin; //start point
	protected double angle;
	protected double moveX, moveY;
	protected double speed, range, damage;
	
	public static final int DEFAULT_SPEED = 10, DEFAULT_RANGE = 400, DEFAULT_DAMAGE = 1;
	
	public Projectile(Handler handler, float x, float y, double dir, int width, int height , int speed , int range, int damage) {
		super(handler, x, y, width, height);
		
		this.speed = speed;
		this.range = range;
		this.damage = damage;
		this.xOrigin = (int) x;
		this.yOrigin = (int) y;
		angle = dir;
		
		moveX = speed * Math.cos(angle);
		moveY = speed * Math.sin(angle);
	}
	
	protected void move(){
		if(distancePassed() > range) kill();
		
		x += moveX;
		y += moveY;
		//range checking
		
	}
	
	protected double distancePassed() {
		double dist = 0;
		dist = Math.sqrt((xOrigin - x)*(xOrigin - x) + (yOrigin - y)*(yOrigin -y));
		return dist;
	}
	
	
	
	
	@Override
	public boolean isSolid() {
		return false;
	}
	
	
}
