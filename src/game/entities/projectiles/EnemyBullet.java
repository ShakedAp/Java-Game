package game.entities.projectiles;

import java.awt.Graphics;

import game.Handler;
import game.gfx.Assets;

public class EnemyBullet extends Projectile {
	
	public EnemyBullet(Handler handler, float x, float y, double dir) {
		super(handler, x, y, dir, 8, 8);
	}
	
	@Override
	public void tick() {
		move(); 
		
		if(getCollisonBounds(0,0).intersects(handler.getWorld().getEntityManager().getPlayer().getCollisonBounds(0, 0))) {
			handler.getWorld().getEntityManager().getPlayer().hurt(1);
			kill();
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
