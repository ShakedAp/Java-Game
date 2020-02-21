package game.entities.projectiles;

import java.awt.Graphics;

import game.Handler;
import game.gfx.Animation;
import game.gfx.Assets;

public class Rocket extends Projectile {
	
	private Animation rocket;
	private int animSpeed = 100;
	
	public Rocket(Handler handler, float x, float y, double dir) {
		super(handler, x, y, dir, 128, 128, DEFAULT_SPEED, DEFAULT_RANGE, DEFAULT_DAMAGE);
		
		rocket = new Animation(animSpeed, Assets.rocket);
		
		bounds.x = 34;
		bounds.y = 55;
		bounds.width = 84;
		bounds.height = 20;
	}
	
	@Override
	public void tick() {
		move(); 
		rocket.update();
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(rocket.getCurrentFrame() ,(int) (x + bounds.x - bounds.width/2 - handler.getGameCamera().getxOffset()),
				(int) (y - bounds.y - bounds.height/2 - handler.getGameCamera().getyOffset()), width, height, null);
	}
	

	@Override
	public void die() {
		
	}

	
}
