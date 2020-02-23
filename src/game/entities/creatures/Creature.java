package game.entities.creatures;

import game.Handler;
import game.entities.Entity;
import game.tiles.Tile;

public abstract class Creature extends Entity{
	
	public static final float DEFAULT_SPEED = 3.0f;
	public static final int DEFAULT_CEATURE_WIDTH = 64, DEFAULT_CEATURE_HEIGHT = 64;

	protected float speed, xMove, yMove;
	
	public Creature(Handler handler ,float x, float y, int width, int height) {
		super(handler, x, y, width, height);
		speed = DEFAULT_SPEED;
		xMove = 0;
		yMove = 0;
	}
	
	public void move() {
		//If the entity isn't going to collide with any other entity allow him to move
		if(!checkEntitySolidCollsions(xMove, 0f))
			moveX();
		if(!checkEntitySolidCollsions(0f, yMove))
			moveY();
	}
	
	public void moveX() {
		//Moving right
		if (xMove > 0) {
				//The position of the tile you are trying to get to (after movement)
				int tempX = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILE_WIDTH;
				
				if (!collisionWithTile(tempX, (int) (y + bounds.y) / Tile.TILE_HEIGHT)
					&& !collisionWithTile(tempX, (int) (y + bounds.y + bounds.height) / Tile.TILE_HEIGHT))
					x += xMove; //if no collision ahead with a solid tile, move player
				 else 
					x = tempX * Tile.TILE_WIDTH - bounds.x - bounds.width - 1;
		}
		
		//Moving left (same as the right movement)
		else if(xMove < 0) {
			int tempX = (int) (x + xMove + bounds.x) / Tile.TILE_WIDTH; 
			
			if (!collisionWithTile(tempX, (int) (y + bounds.y) / Tile.TILE_HEIGHT) 
				&& !collisionWithTile(tempX, (int) (y + bounds.y + bounds.height) / Tile.TILE_HEIGHT))
				x += xMove;	
			else 
				x = tempX * Tile.TILE_WIDTH + Tile.TILE_WIDTH - bounds.x;
		}
	}
	
	
	public void moveY(){
		//Moving up
		if(yMove < 0){
			int tempY = (int) (y + yMove + bounds.y) / Tile.TILE_HEIGHT;
			
			if(!collisionWithTile((int) (x + bounds.x) / Tile.TILE_WIDTH, tempY) 
				&& !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILE_WIDTH, tempY))
				y += yMove;
			else 
				y =  tempY * Tile.TILE_WIDTH + Tile.TILE_HEIGHT - bounds.y;
			}
		
			//Moving down
			else if(yMove > 0){
			int tempY = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILE_HEIGHT;
			
			if(!collisionWithTile((int) (x + bounds.x) / Tile.TILE_WIDTH, tempY) &&
					!collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILE_WIDTH, tempY))
				y += yMove;
			else 
				y = tempY * Tile.TILE_WIDTH - bounds.height - bounds.y - 1;
		}
	}
	
	
	
	//GETTERS & SETTERS
	public float getxMove() {
		return xMove;
	}


	public void setxMove(float xMove) {
		this.xMove = xMove;
	}


	public float getyMove() {
		return yMove;
	}

	
	
	
	public void setyMove(float yMove) {
		this.yMove = yMove;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}
	
	

}
