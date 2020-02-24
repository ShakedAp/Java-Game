package game.items.Weapons;

import game.Handler;
import game.entities.projectiles.LightBullet;
import game.gfx.Assets;
import game.items.Weapon;

public class BadPistol extends Weapon{

	public BadPistol() {
		super(Assets.bad_pistol, "bad pistol", 0);
		
		descLine1 = "just the plain";
		descLine2 = "good old pistol.";
		descLine3 = "";
		descLine4 = "(its quite bad,";
		descLine5 = "though)";
	}
	
	@Override
	public void shoot(Handler handler, float xOrigin, float yOrigin, double dir) {
		handler.getWorld().getEntityManager().addEntity(new LightBullet(handler, xOrigin, yOrigin, dir));
	}

}
