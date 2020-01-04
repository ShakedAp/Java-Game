package game.ui;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

public abstract class UIObject{

	protected float x, y;
	protected int width, height;
	protected Rectangle bounds;
	protected boolean hovering = false;
	
	public  UIObject(float x, float y, int width, int height) {
		this.x= x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		bounds = new Rectangle((int) x, (int) y, width, height); //as default setting the bounds to the entire image
	}

	public abstract void tick();
	
	public abstract void render(Graphics g);
	
	public abstract void onClick();
	
	public void onMouseMove(MouseEvent e) { //every time the mouse moved and hovering the object set "hovering" to true
		if (bounds.contains(e.getX(), e.getY())) //if the mouse is hovering the object
			hovering = true;
		else
			hovering = false;
	}
	
	public void onMouseRelease(MouseEvent e) { //whenever the user is releasing the mouse when hovering the object he is clicking it
		if(hovering)
			onClick();
		
	}
	
	
	
	//Getters and setters
	
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

	public boolean isHovering() {
		return hovering;
	}

	public void setHovering(boolean hovering) {
		this.hovering = hovering;
	}
	

}
