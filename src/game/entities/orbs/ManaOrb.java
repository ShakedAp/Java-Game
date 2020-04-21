package game.entities.orbs;

import java.awt.Graphics;

import game.Handler;
import game.gfx.Assets;

public class ManaOrb extends Orb {

	private int manaGain = 8;
	private float range = 13f;

	public ManaOrb(Handler handler, float x, float y) {
		super(handler, x, y, 8, 8);
		speed = 7.5f;
	}

	@Override
	public void tick() {

		if (handler.getWorld().getEntityManager().getPlayer().getCollisonBounds(0, 0)
				.intersects(getCollisonBounds(0, 0))) {
			
			if(handler.getWorld().getEntityManager().getPlayer().getMana()  < handler.getWorld().getEntityManager().getPlayer().getMaxMana()) {
				if(handler.getWorld().getEntityManager().getPlayer().getMaxMana() - handler.getWorld().getEntityManager().getPlayer().getMana() < manaGain)
					handler.getWorld().getEntityManager().getPlayer().setMana(handler.getWorld().getEntityManager().getPlayer().getMaxMana());
				else
				handler.getWorld().getEntityManager().getPlayer().setMana(handler.getWorld().getEntityManager().getPlayer().getMana() + manaGain);
			}
			kill();
		}
		
		followPlayer();

		move();
	}
	
	
	private void followPlayer() {
		xMove = 0;
		yMove = 0;
		float playerX = (float) handler.getWorld().getEntityManager().getPlayer().getCollisonBounds(0, 0).getX();
		float playerY = (float) handler.getWorld().getEntityManager().getPlayer().getCollisonBounds(0, 0).getY();		
		
		float xMoveSpeed = range - Math.abs((playerX - x) / 20);
		float yMoveSpeed = range - Math.abs((playerY - y) / 20);

		if (xMoveSpeed > speed) xMoveSpeed = speed;
		if (yMoveSpeed > speed) yMoveSpeed = speed;
				
		if(xMoveSpeed > 0) {
			if (x > playerX + speed) xMove -= xMoveSpeed;
			else if (x < playerX - speed) xMove += xMoveSpeed;
		}

		if(yMoveSpeed > 0) {
			if (y < playerY - speed) yMove += yMoveSpeed;
			else if (y > playerY + speed) yMove -= yMoveSpeed;
		}
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.mana_orb, (int) (x - handler.getGameCamera().getxOffset()),
				(int) (y - handler.getGameCamera().getyOffset()), width, height, null);
	}

	@Override
	public boolean isSolid() {
		return false;
	}

	@Override
	public void die() {

	}

}
