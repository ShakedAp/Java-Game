package game.entities.projectiles;

import java.awt.Color;
import java.awt.Graphics;

import game.Handler;
import game.gfx.Assets;

public class RegularBullet extends Projectile {

	
	public RegularBullet(Handler handler, float x, float y, double dir) {
		super(handler, x, y, dir, 8, 8, 10, DEFAULT_RANGE, DEFAULT_DAMAGE);
	}

	@Override
	public void die() {
		
	}

	@Override
	public void tick() {
		move();
		
		if(collisionWithTile((int) x, (int) y))
				System.out.println("true");
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.bullet,(int) (x - handler.getGameCamera().getxOffset()),
				(int) (y- handler.getGameCamera().getyOffset()), width, height, null);
		
	}

	
	
}
