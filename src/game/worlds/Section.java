package game.worlds;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import game.Handler;
import game.entities.Entity;
import game.entities.creatures.Enemy;

public class Section {
	
	private Handler handler;
	private int x, y, width, height;
	private Rectangle bounds;
	private boolean active = true, playerCollsion, enemyCollision;
	
	public Section(Handler handler, int x, int y, int width, int height){
		this.handler = handler;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		bounds = new Rectangle(x, y, width, height);
	}
	
	public void tick() {
		if(bounds.contains(handler.getWorld().getEntityManager().getPlayer().getCollisonBounds(0f, 0f))) {
			playerCollsion = true;
			active = false;
		}
		else
			playerCollsion = false;
		
		for(Entity e : handler.getWorld().getEntityManager().getEntities())
			if( bounds.contains(e.getCollisonBounds(0, 0)))
				if(e instanceof Enemy) {
					if(active) ((Enemy) e).setFrozen(true);
					if(!active) ((Enemy) e).setFrozen(false);
				}
		
		
		for(Entity e : handler.getWorld().getEntityManager().getEntities())
			if( bounds.contains(e.getCollisonBounds(0, 0)))
				if(e instanceof Enemy) {
					enemyCollision = true;
					return;
				}
		enemyCollision = false;
	}
	
	public void render(Graphics g) {
		if(!active) return;
		g.setColor(Color.green);
		g.drawRect((int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height);
	}

	
	
	//GETTERS & SETTERS
	public Rectangle getBounds() {
		return bounds;
	}

	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isPlayerCollsion() {
		return playerCollsion;
	}

	public void setPlayerCollsion(boolean playerCollsion) {
		this.playerCollsion = playerCollsion;
	}

	public boolean isEnemyCollision() {
		return enemyCollision;
	}

	public void setEnemyCollision(boolean enemyCollision) {
		this.enemyCollision = enemyCollision;
	}
	
	
	
	
}
