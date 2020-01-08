package game.entities.projectiles;

import game.Handler;
import game.entities.Entity;
import game.tiles.Tile;

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
	
	
	private void setVelocity(float fromX, float fromY, float toX, float toY) {	
		velX = (toX - fromX) / 15;
		velY = (toY - fromY) / 15;
		//TODO: better bullet shooting speed
	}
	
	protected void tileCollsionsCheck(){
		int tempX = (int) (x / Tile.TILE_WIDTH);
		int tempY = (int) (y / Tile.TILE_HEIGHT);
		if(collisionWithTile(tempX, tempY))
			this.kill();
	}
	
	protected void tickLifeCounter() {
		if(tickCount >= tickLife) { //if it reached the time life limit
			kill();
			return;
			}
		tickCount++; //for each time this method is called (at the tick method)
	}

	@Override
	public boolean isSolid() {
		return false;
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
