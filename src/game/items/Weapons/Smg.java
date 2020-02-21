package game.items.Weapons;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import game.Handler;
import game.entities.projectiles.LightBullet;
import game.gfx.Assets;
import game.items.Weapon;

public class Smg extends Weapon{
	
	Random random = new Random();
	
	public Smg() {
		super(Assets.smg, "smg", 10, "The bad pistol", "after he grew", "up. he likes", "to say \"im", " speed\", before", "he instantly run", "out of ammo.", 3);
	}

	@Override
	public void shoot(Handler handler, float xOrigin, float yOrigin, double dir) {
		double spread = ThreadLocalRandom.current().nextDouble(-0.05, 0.05);
		handler.getWorld().getEntityManager().addEntity(new LightBullet(handler, xOrigin, yOrigin, dir + spread));
	}
	

}
