package game.entities.projectiles;

import java.awt.Graphics;

import game.Handler;
import game.entities.Entity;
import game.entities.creatures.Player;
import game.gfx.Assets;

public class MediumBullet extends Projectile{

	public MediumBullet(Handler handler, float x, float y, double dir) {
		super(handler, x, y, dir, 8, 8);
		speed = 11;
		range = 400;
		damage = 2;
	}

	@Override
	public void tick() {
		move();
		
		//	TODO: Implement in all of the bullets 
			for(Entity e : handler.getWorld().getEntityManager().getEntities()) { 
				if(e.equals(this) || e instanceof Player)
					continue;
				if(e.getCollisonBounds(0f,0f).intersects(this.getCollisonBounds(0f, 0f)) && e.isSolid()) {
					e.hurt(damage);
					kill();
				}
			}
			
			
			
			
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.bullet,(int) (x - handler.getGameCamera().getxOffset()),
				(int) (y- handler.getGameCamera().getyOffset()), width, height, null);
	}

	@Override
	public void die() {
		
	}

}
