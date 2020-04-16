package game.entities.orbs;

import java.awt.Graphics;

import game.Handler;
import game.gfx.Assets;

public class ManaOrb extends Orb {

	private int manaGain = 8;
	
	public ManaOrb(Handler handler, float x, float y) {
		super(handler, x, y, 8, 8);

	}

	@Override
	public void tick() {
		
		if(handler.getWorld().getEntityManager().getPlayer().getCollisonBounds(0, 0).intersects(getCollisonBounds(0,0))) {
				handler.getWorld().getEntityManager().getPlayer().setMana(handler.getWorld().getEntityManager().getPlayer().getMana() + manaGain);
				kill();
			}
				
		move();
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
