package game.entities.projectiles;

import java.awt.Graphics;

import game.Handler;
import game.entities.Entity;
import game.tiles.Tile;

public abstract class Projectile extends Entity {

	protected final int xOrigin, yOrigin; //start point
	protected double angle;
	protected double moveX, moveY;
	protected float speed, range;
	protected int damage;
	
	public static final float DEFAULT_SPEED = 10 ,DEFAULT_RANGE = 300.0f;
	public static final int  DEFAULT_DAMAGE = 1;
	
	public Projectile(Handler handler, float x, float y, double dir, int width, int height) {
		super(handler, x, y, width, height);
		
		speed = DEFAULT_SPEED;
		range = DEFAULT_RANGE;
		damage = DEFAULT_DAMAGE;
		this.xOrigin = (int) x;
		this.yOrigin = (int) y;
		angle = dir;
		
		moveX = speed * Math.cos(angle);
		moveY = speed * Math.sin(angle);
	}
	
	protected void move(){
		if(distancePassed() > range) kill();
		if(collisionWithTile((int) x/Tile.TILE_WIDTH, (int) y/Tile.TILE_HEIGHT)) kill();
		
		
		x += moveX;
		y += moveY;		
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
