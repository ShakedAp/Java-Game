package game.entities.projectiles;

import java.awt.Graphics;

import game.Handler;
import game.gfx.Assets;

public class MediumBullet extends Projectile{

	public MediumBullet(Handler handler, float x, float y, double dir) {
		super(handler, x, y, dir, 8, 8, 11, 400, 2);
	}

	@Override
	public void tick() {
		move();
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