package game.entities.orbs;

import java.awt.Graphics;
import java.awt.geom.Ellipse2D;

import game.Handler;
import game.entities.creatures.Player;
import game.gfx.Assets;

public class ManaOrb extends Orb {

	private int manaGain = 8;
	private float range = 13f;
	
	public ManaOrb(Handler handler, float x, float y) {
		super(handler, x, y, 16, 16);
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
	
	
	private void followPlayer() { //TODO: make both axis of movement work with each other
		Player p = handler.getWorld().getEntityManager().getPlayer();
		Ellipse2D e = new Ellipse2D.Float(p.getX() - 170 , p.getY() - 170, 155 * 3, 155 * 3);
		
		xMove = 0;
		yMove = 0;
		
		if(!e.intersects(getCollisonBounds(0,0)))
			return;
		
		float playerX = (float) handler.getWorld().getEntityManager().getPlayer().getCollisonBounds(0, 0).getX();
		float playerY = (float) handler.getWorld().getEntityManager().getPlayer().getCollisonBounds(0, 0).getY();		
		
		float xMoveSpeed = range - Math.abs((playerX - x) / 20);
		float yMoveSpeed = range - Math.abs((playerY - y) / 20);

		if (xMoveSpeed > speed) xMoveSpeed = speed;
		if (yMoveSpeed > speed) yMoveSpeed = speed;
				
		if(xMoveSpeed > 0) {
			if (x > playerX + speed/2) xMove -= xMoveSpeed;
			else if (x < playerX - speed/2) xMove += xMoveSpeed;
		}

		if(yMoveSpeed > 0) {
			if (y < playerY - speed/2) yMove += yMoveSpeed;
			else if (y > playerY + speed/2) yMove -= yMoveSpeed;
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
