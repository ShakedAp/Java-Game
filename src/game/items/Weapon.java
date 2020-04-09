package game.items;

import java.awt.image.BufferedImage;
import java.util.concurrent.ThreadLocalRandom;

import game.Handler;
import game.items.Weapons.Ak47;
import game.items.Weapons.BadPistol;
import game.items.Weapons.RPG;
import game.items.Weapons.Shotgun;
import game.items.Weapons.Smg;
import game.items.Weapons.WaterGun;

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

	protected double calculateSpread() {
		return ThreadLocalRandom.current().nextDouble(-0.01 * this.spread, 0.01 * this.spread);
	}

	@Override
	public Weapon createNew(int x, int y) {
		for (int i = 0; i < Item.items.length; i++) {
			if (Item.items[i].equals(this)) {
				switch(i){
					case 0:
						Weapon w1 = new BadPistol();
						w1.setPosition(x, y);
						return w1;
					case 1:
						Weapon w2 = new RPG();
						w2.setPosition(x, y);
						return w2;
					case 2:
						Weapon w3 = new Shotgun();
						w3.setPosition(x, y);
						return w3;
					case 3:
						Weapon w4 = new Smg();
						w4.setPosition(x, y);
						return w4;
					case 4:
						Weapon w5 = new Ak47();
						w5.setPosition(x, y);
						return w5;
					case 5:
						Weapon w6 = new WaterGun();
						w6.setPosition(x, y);
						return w6;
				}
			}
		}
		return null;		
	}

	// GETTERS & SETTERS
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
