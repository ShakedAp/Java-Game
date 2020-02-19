package game.items;

import java.awt.image.BufferedImage;

import game.Handler;

public abstract class Weapon extends Item {

	protected int bps; //how many bullets per second
	
	public Weapon(BufferedImage texture, String name, int bps, String desc1, String desc2, String desc3, String desc4, String desc5, String desc6, String desc7, int id) {
		super(texture, name, desc1, desc2, desc3, desc4, desc5, desc6, desc7, id);
		this.bps = bps;
	}

	//ABSTRACT
	public abstract void shoot(Handler handler, float x, float y, double dir);

	//HELPER
	
	public int getBps() {
		return bps;
	}

	public void setRateOfFire(int rateOfFire) {
		this.bps = rateOfFire;
	}

	
	
}
