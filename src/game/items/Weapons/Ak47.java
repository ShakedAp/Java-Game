package game.items.Weapons;

import game.Handler;
import game.entities.projectiles.LightBullet;
import game.entities.projectiles.MediumBullet;
import game.gfx.Assets;
import game.items.Weapon;

public class Ak47 extends Weapon{

	public Ak47() {
		super(Assets.ak47, "ak47", 5 ,"mother russia’s", "pride concentrated", "into one gun", "of pure soviet", "badass.", "", "", 4);
	}
	
	@Override
	public void shoot(Handler handler, float xOrigin, float yOrigin, double dir) {
		handler.getWorld().getEntityManager().addEntity(new MediumBullet(handler, xOrigin, yOrigin, dir));
	}

}
