package game.entities.creatures;

import java.awt.Graphics;

import game.Handler;
import game.gfx.Assets;

public class BasicEnemy extends Enemy{

	public BasicEnemy(Handler handler, float x, float y) {
		super(handler, x, y, 128, 128);
		
		// Bounds
		bounds.x = 43;
		bounds.y = 43;
		bounds.width = 42;
		bounds.height = 50;
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.player_idle, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()),null);
	}

	@Override
	public void die() {
		
	}

}
