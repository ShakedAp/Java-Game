package game.entities.projectiles;

import java.awt.Color;
import java.awt.Graphics;

import game.Handler;

public class RegularBullet extends Projectile {


	public RegularBullet(Handler handler, float fromX, float fromY, float toX, float toY, int width, int height) {
		super(handler, fromX, fromY, toX, toY, width, height);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick() {
		move();
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int) (x - handler.getGameCamera().getxOffset()),(int) (y- handler.getGameCamera().getyOffset()), width, height);
	}

	@Override
	public void die() {
		
	}

}
