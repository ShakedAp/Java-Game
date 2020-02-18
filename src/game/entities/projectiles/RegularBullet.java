package game.entities.projectiles;

import java.awt.Color;
import java.awt.Graphics;

import game.Handler;
import game.gfx.Assets;
import game.tiles.Tile;

public class RegularBullet extends Projectile {

	
	public RegularBullet(Handler handler, float x, float y, double dir) {
		super(handler, x, y, dir, 8, 8, DEFAULT_SPEED, DEFAULT_RANGE, DEFAULT_DAMAGE, 0);
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
		g.drawImage(Assets.bullet,(int) (x - handler.getGameCamera().getxOffset()),
				(int) (y- handler.getGameCamera().getyOffset()), width, height, null);
		
	}

	
	
}
