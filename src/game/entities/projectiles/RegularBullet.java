package game.entities.projectiles;

import java.awt.Color;
import java.awt.Graphics;

import game.Handler;

public class RegularBullet extends Projectile {


	public RegularBullet(Handler handler, float fromX, float fromY, float toX, float toY, int tickLife) {
		super(handler, fromX, fromY, toX, toY, 8, 8, tickLife);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick() {
		move();
		tickLifeCounter();
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
