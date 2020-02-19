package game.items.Weapons;

import game.Handler;
import game.entities.projectiles.LightBullet;
import game.gfx.Assets;
import game.items.Weapon;

public class Smg extends Weapon{
	
	public Smg() {
		super(Assets.smg, "smg", 1, "The bad pistol", "after he grew", "up. he likes", "to say \"im", " speed\", before", "he instantly run", "out of ammo.", 4);
	}

	@Override
	public void shoot(Handler handler, float xOrigin, float yOrigin, double dir) {
		handler.getWorld().getEntityManager().addEntity(new LightBullet(handler, xOrigin, yOrigin, dir));
	}
	

}
