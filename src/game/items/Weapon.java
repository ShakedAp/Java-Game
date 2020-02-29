package game.items;

import java.awt.image.BufferedImage;
import java.util.concurrent.ThreadLocalRandom;

import game.Handler;

public abstract class Weapon extends Item {

	protected double bps, spread;
	protected int manaCost;
	public static final int DEFAULT_MANACOST = 1;
	public static final double DEFAULT_BPS = 3, DEFAULT_SPREAD = 4;
	
	public Weapon(BufferedImage texture, String name, int id) {
		super(texture, name, id);
		bps = DEFAULT_BPS;
		spread = DEFAULT_SPREAD;
		manaCost = DEFAULT_MANACOST;
	}
	
	public abstract void shoot(Handler handler, float x, float y, double dir);

	
	protected double calculateSpread(){
		return ThreadLocalRandom.current().nextDouble(-0.01 * this.spread, 0.01 * this.spread);
	}
	
	//GETTERS & SETTERS
	public double getBps() {
		return bps;
	}

	public void setRateOfFire(int rateOfFire) {
		this.bps = rateOfFire;
	}

	public int getManaCost() {
		return manaCost;
	}

	
	
}
