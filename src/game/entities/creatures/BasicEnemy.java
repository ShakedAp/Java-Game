package game.entities.creatures;

import java.awt.Graphics;
import java.awt.Rectangle;

import game.Handler;
import game.entities.orbs.ManaOrb;
import game.entities.projectiles.EnemyBullet;
import game.gfx.Assets;

public class BasicEnemy extends Enemy {

	public BasicEnemy(Handler handler, float x, float y) {
		super(handler, x, y, 128, 128);

		// Bounds
		bounds.x = 43;
		bounds.y = 43;
		bounds.width = 42;
		bounds.height = 50;

	}

	@Override
	public void tick() {
		
		if(!frozen) {
			move();
		shoot();
		}

	}
	

	
	long lastTimer, cooldown = 1000, timer = cooldown;
	private void shoot() {
		if(!handler.getWorld().getEntityManager().getPlayer().isActive())
			return;
		
		timer += System.currentTimeMillis() -  lastTimer;
		lastTimer = System.currentTimeMillis();
		if(timer <= cooldown)
			return;
		
		
		
		
		Rectangle playerBounds = handler.getWorld().getEntityManager().getPlayer().getCollisonBounds(0, 0);
		float enemyX = x + bounds.x + 10;
		float playerX = (float) (playerBounds.x + playerBounds.width/2);

		float enemyY = y + bounds.y + 10;
		float playerY = (float) (playerBounds.y + playerBounds.height/2);

		double dx = playerX - enemyX;
		double dy = playerY - enemyY;
		double dir = Math.atan2(dy, dx);
		
		
		handler.getWorld().getEntityManager().addEntity(new EnemyBullet(handler, enemyX, enemyY, dir));
		timer = 0;
	}
	
	
	
	private void chase() {
		xMove = 0;
		yMove = 0;
		float playerY = handler.getWorld().getEntityManager().getPlayer().getY();
		float playerX = handler.getWorld().getEntityManager().getPlayer().getX();
		
		if (x > playerX + speed) 
			xMove -= speed;
		else if (x < playerX - speed) 
			xMove += speed;
		

		if (y < playerY - speed) 
			yMove += speed;
		else if (y > playerY + speed) 
			yMove -= speed;
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.player_idle, (int) (x - handler.getGameCamera().getxOffset()),
				(int) (y - handler.getGameCamera().getyOffset()), null);
	}

	@Override
	public void die() {
		handler.getWorld().getEntityManager().addEntity(new ManaOrb(handler, getCollisonBounds(0,0).x +  bounds.width/2, getCollisonBounds(0,0).y + bounds.height/2));	
	}

}
