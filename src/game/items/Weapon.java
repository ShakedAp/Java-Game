package game.items;

import java.awt.image.BufferedImage;

import game.Handler;

public abstract class Weapon extends Item {

	protected double bps; // How many bullets per second
	public static final double DEFAULT_BPS = 3;
	
	public Weapon(BufferedImage texture, String name, int id) {
		super(texture, name, id);
		bps = DEFAULT_BPS;
	}

	
	public abstract void shoot(Handler handler, float x, float y, double dir);

	
	//GETTERS & SETTERS
	public double getBps() {
		return bps;
	}

	public void setRateOfFire(int rateOfFire) {
		this.bps = rateOfFire;
	}

	
	
}
