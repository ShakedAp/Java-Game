package game.entities.orbs;

import game.Handler;
import game.entities.Entity;

public abstract class Orb extends Entity{

	public static final float DEFAULT_SPEED = 3.0f;
	
	protected float speed, xMove, yMove;
	
	public Orb(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
		speed = DEFAULT_SPEED;
	}

	public void move() {
		x += xMove;
		y += yMove;
	}
	
}
