package game.entities.projectiles;

import java.awt.Color;
import java.awt.Graphics;

import game.Handler;

public class RegularBullet extends Projectile {

	
	public RegularBullet(Handler handler, float x, float y, double dir) {
		super(handler, x, y, dir, 8, 8, 10);
	}

	@Override
	public void die() {
		
	}

	@Override
	public void tick() {
		move();
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.drawRect((int) (x - handler.getGameCamera().getxOffset()),(int) (y - handler.getGameCamera().getyOffset()), width, height);
		
	}

	
	
}
