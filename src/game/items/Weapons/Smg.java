package game.items.Weapons;

import game.Handler;
import game.entities.projectiles.LightBullet;
import game.gfx.Assets;
import game.items.Weapon;

public class Smg extends Weapon{
	
	public Smg() {
		super(Assets.smg, "Smg", 3);
		
		bps = 10;
		spread = 6;
		
		descLine1 = "the bad pistol";
		descLine2 = "after he grew";
		descLine3 = "up. he likes";
		descLine4 = "to say \"im";
		descLine5 = "speed\", before";
		descLine6 = "he instantly run";
		descLine7 = "out of ammo.";
	}

	@Override
	public void shoot(Handler handler, float xOrigin, float yOrigin, double dir) {
		
		handler.getWorld().getEntityManager().addEntity(new LightBullet(handler, xOrigin, yOrigin, dir + calculateSpread()));
	}
	

}
