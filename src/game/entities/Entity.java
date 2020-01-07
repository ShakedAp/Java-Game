package game.entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import game.Handler;

public abstract class Entity {
	
	public static final int DEFAULT_HEALTH = 10;
	
	protected Handler handler;
	protected float x, y; //the x and y of the entity
	protected int width, height; //the width and height of the entity
	protected int health;
	protected Rectangle bounds; //collisions bounds
	protected boolean active = true; //checking if the entity is "alive"
	
	public Entity(Handler handler, float x, float y, int width, int height) {
		this.handler = handler;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		health = DEFAULT_HEALTH;
		
		bounds = new Rectangle(0, 0, width, height); //creating a full image bounding by default
	}
	
	public abstract void tick();
	
	public abstract void render(Graphics g);
	
	public abstract void die();
	
	public void hurt(int amount){
		health -= amount;
		if(health <= 0) {
			active = false;
			die();
		}
	}
	
	public void kill() {
		hurt(health);
	}
	
	public boolean checkEntityCollsions(float xOffset, float yOffset) { //x and y offset are for moving (tempX, tempY)
		for(Entity e : handler.getWorld().getEntityManager().getEntities()) { //loops each of the entities
			if(e.equals(this))
				continue;
			if(e.getCollisonBounds(0f,0f).intersects(this.getCollisonBounds(xOffset, yOffset))) //the the 2 bounds are intersects
				return true;
			
		}
		return false; //if the loop has exited = no collision
	}
	
	
	
	
	public Rectangle getCollisonBounds(float xOffset, float yOffset) { //returning the position of the bounding box of an entity
		return new Rectangle((int) (x + bounds.x + xOffset), (int) (y + bounds.y + yOffset), bounds.width, bounds.height);
	}
	
	
	
	//getters and setters

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	

}



/*

Entity -> Creature -> player
				   -> Enemy
Entity -> item
Entity -> etc


*/