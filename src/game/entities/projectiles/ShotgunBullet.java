package game.entities.projectiles;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.concurrent.ThreadLocalRandom;

import game.Handler;
import game.entities.Entity;
import game.entities.creatures.Player;
import game.gfx.Assets;

public class ShotgunBullet extends Projectile {
	
	private int minSpeed = 8, maxSpeed = 12;
	
	public ShotgunBullet(Handler handler, float x, float y, double dir) {
		super(handler, x, y, dir, 12, 12);
		
		damage = 2;
		
		calculateSpeed(minSpeed, maxSpeed);
	}
	
	@Override
	public void tick() {
		move(); 
		
		for(Entity e : handler.getWorld().getEntityManager().getEntities()) { 
			if(e.equals(this) || e instanceof Player) continue;
			if(e.getCollisonBounds(0f,0f).intersects(this.getCollisonBounds(0f, 0f)) && e.isSolid()) {
				e.hurt(damage);
				kill();
			}
		}
		
		speed -= 0.1;
		updateMoveX();
		updateMoveY();
		
		if(speed <= 0) kill();

	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.bullet,(int) (x - handler.getGameCamera().getxOffset()),
				(int) (y- handler.getGameCamera().getyOffset()), width, height, null);
	}

	@Override
	public void die() {
		
	}
	
	private void calculateSpeed(double minSpeed, double maxSpeed) {
		speed = (float) ThreadLocalRandom.current().nextDouble(minSpeed, maxSpeed);
		updateMoveX();
		updateMoveY();
	}
	
	
	
}
