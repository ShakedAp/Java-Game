package game.items.Weapons;

import game.Handler;
import game.entities.projectiles.LightBullet;
import game.entities.statics.Tree;
import game.gfx.Assets;
import game.items.Weapon;

public class BadPistol extends Weapon{

	public BadPistol() {
		super(Assets.bad_pistol, "bad pistol", 3 ,"just the plain", "good old pistol.", "", "(its quite bad,", "though)", "", "",  0);
	}
	
	@Override
	public void shoot(Handler handler, float xOrigin, float yOrigin, double dir) {
		handler.getWorld().getEntityManager().addEntity(new LightBullet(handler, xOrigin, yOrigin, dir));
	}

}
