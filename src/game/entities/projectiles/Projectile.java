package game.entities.projectiles;

import game.Handler;
import game.entities.Entity;
import game.entities.creatures.Player;
import game.tiles.Tile;

public abstract class Projectile extends Entity {

	protected final int xOrigin, yOrigin; //start point
	protected double angle;
	protected double moveX, moveY;
	protected float speed;
	protected int damage;

	public static final float DEFAULT_SPEED = 10;
	public static final int DEFAULT_DAMAGE = 1;
	
	public Projectile(Handler handler, float x, float y, double dir, int width, int height) {
		super(handler, x, y, width, height);
		
		speed = DEFAULT_SPEED;
		damage = DEFAULT_DAMAGE;
		this.xOrigin = (int) x;
		this.yOrigin = (int) y;
		angle = dir;
		
		updateMoveX();
		updateMoveY();
	}
	
	protected void move(){
		if(distancePassed() > 1280)
			kill();
		
		checkCollisons();
		
		x += moveX;
		y += moveY;
	}
	
	protected void checkCollisons(){
		if(collisionWithTile((int) x/Tile.TILE_WIDTH, (int) y/Tile.TILE_HEIGHT))
			kill();
		if(handler.getWorld().getTile((int) x/Tile.TILE_WIDTH, (int) y/Tile.TILE_HEIGHT) == Tile.wallTile)
			kill();
		
		for(Entity e : handler.getWorld().getEntityManager().getEntities()) { 
			if(e.equals(this) || e instanceof Player) continue;
			if(e.getCollisonBounds(0f,0f).intersects(this.getCollisonBounds(0f, 0f)) && e.isSolid()) {
				e.hurt(damage);
				kill();
			}
		}
	}
	
	
	protected double distancePassed() {
		double dist = 0;
		dist = Math.sqrt((xOrigin - x)*(xOrigin - x) + (yOrigin - y)*(yOrigin -y));
		return dist;
	}
	
	protected void updateMoveX() {
		moveX = speed * Math.cos(angle);
	}
	
	
	protected void updateMoveY() {
		moveY = speed * Math.sin(angle);
	}
	
	@Override
	public boolean isSolid() {
		return false;
	}
	
	
}
