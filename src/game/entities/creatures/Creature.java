package game.entities.creatures;

import game.Handler;
import game.entities.Entity;
import game.tiles.Tile;

public abstract class Creature extends Entity{

	public static final int DEFAULT_HEALTH = 100; //setting our default health and speed, width, height
	public static final float DEFAULT_SPEED = 3.0f;
	public static final int DEFAULT_CEATURE_WIDTH = 64,
							DEFAULT_CEATURE_HEIGHT = 64;

	
	protected int health;
	protected float speed, xMove, yMove; //setting our movement variables
	
	public Creature(Handler handler ,float x, float y, int width, int height) {
		super(handler, x, y, width, height); //passing these variables to the extended class
		health = DEFAULT_HEALTH;
		speed = DEFAULT_SPEED;
		xMove = 0;
		yMove = 0;
	}

	
	public void move() { 
		if(!checkEntityCollsions(xMove, 0f))
			moveX();
		if(!checkEntityCollsions(0f, yMove))
			moveY();
		
	}
	
	//with collisions!
	public void moveX() {
		if (xMove > 0) { //moving right
				//the position of the tile you are trying to get to
				int tempX = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILE_WIDTH; 
				
				if (! collisionWithTile(tempX, (int) (y + bounds.y) / Tile.TILE_HEIGHT) && !collisionWithTile(tempX, (int) (y + bounds.y + bounds.height) / Tile.TILE_HEIGHT)){ 
					//if the player is not in a collision with tile
								x += xMove;			
				} else { //perfect collisions (no gap)
					x = tempX * Tile.TILE_WIDTH - bounds.x - bounds.width - 1;
					
				}
		}else if(xMove < 0) { //moving left
			int tempX = (int) (x + xMove + bounds.x) / Tile.TILE_WIDTH; 
			
			if (! collisionWithTile(tempX, (int) (y + bounds.y) / Tile.TILE_HEIGHT) && !collisionWithTile(tempX, (int) (y + bounds.y + bounds.height) / Tile.TILE_HEIGHT)){ 
							x += xMove;	
			}else {
				x = tempX * Tile.TILE_WIDTH + Tile.TILE_WIDTH - bounds.x;
			}
		}
	}
	
	
	public void moveY(){
		if(yMove < 0){//Up
			int tempY = (int) (y + yMove + bounds.y) / Tile.TILE_HEIGHT; //to get the higher points
			
			if(!collisionWithTile((int) (x + bounds.x) / Tile.TILE_WIDTH, tempY) &&
					!collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILE_WIDTH, tempY)){
				y += yMove;
			}else {
				y =  tempY * Tile.TILE_WIDTH + Tile.TILE_HEIGHT - bounds.y;
			}
		}else if(yMove > 0){//Down
			int tempY = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILE_HEIGHT; //to get the lower points
			
			if(!collisionWithTile((int) (x + bounds.x) / Tile.TILE_WIDTH, tempY) &&
					!collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILE_WIDTH, tempY)){
				y += yMove;
			}else {
				y = tempY * Tile.TILE_WIDTH - bounds.height - bounds.y - 1;
			}
		}
	}
	
	
	protected boolean collisionWithTile(int x, int y) { //checks if the creature is in a collision (in a solid tile)
		return handler.getWorld().getTile(x, y).isSolid();
	}

	
	//getters and setters
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
