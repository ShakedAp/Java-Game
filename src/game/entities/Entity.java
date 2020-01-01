package game.entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import game.Handler;

public abstract class Entity {
	
	protected Handler handler;
	protected float x, y; //the x and y of the entity
	protected int width, height; //the width and height of the entity
	protected Rectangle bounds; //collisions bounds
	
	public Entity(Handler handler, float x, float y, int width, int height) {
		this.handler = handler;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		bounds = new Rectangle(0, 0, width, height); //creating a full image bounding by default
	}
	
	public abstract void tick();
	
	public abstract void render(Graphics g);
	
	
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
	

}



/*

Entity -> Creature -> player
				   -> Enemy
Entity -> item
Entity -> etc


*/