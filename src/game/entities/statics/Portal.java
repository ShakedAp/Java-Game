package game.entities.statics;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import game.Handler;
import game.gfx.Animation;
import game.gfx.Assets;
import game.states.State;

public class Portal extends StaticEntity {

	private Animation spin, open, close;
	private boolean closed = false;
	private State destination;

	public Portal(Handler handler, float x, float y, State destination) {
		super(handler, x, y, 128, 128);
		this.destination = destination;
		health = 999;
		
		bounds.x = 60;
		bounds.y = 35;
		bounds.width = 7;
		bounds.height = 71;
		
		spin = new Animation(100, Assets.portal_spin, true);
		open = new Animation(100, Assets.portal_open, false);
		close = new Animation(100, Assets.portal_close, false);
	}

	@Override
	public void tick() {
		// Animations
		if(!open.isFinished()) open.update();
		else if(!closed) spin.update();
		else close.update();
		if(closed && close.isFinished()) kill();
		
		if(handler.getWorld().getEntityManager().getPlayer().getCollisonBounds(0, 0).intersects(getCollisonBounds(0,0))) {
			closed = true;
			handler.getWorld().getEntityManager().getPlayer().setVisible(false);
		}
		
	}
	
	
	@Override
	public void render(Graphics g) {
		g.drawImage(getCurrentAnimationFrame() , (int) (x - handler.getGameCamera().getxOffset()),
				(int) (y - handler.getGameCamera().getyOffset()), width, height, null);
	}

	@Override
	public void die() {
		try {Thread.sleep(500);} catch (InterruptedException e) {}
		handler.goToState(destination);
	}
	
	@Override
	public boolean isSolid() {
		return false;
	}
	
	
	private BufferedImage getCurrentAnimationFrame() {
		if(!open.isFinished())  return open.getCurrentFrame();
		if(closed) return close.getCurrentFrame();
		return spin.getCurrentFrame();
	}
	
	//GETTERS & SETTERS
	public State getDestination() {
		return destination;
	}

	public void setDestination(State destination) {
		this.destination = destination;
	}
	
	
}
