package game.items.Weapons;

import game.Handler;
import game.entities.projectiles.LightBullet;
import game.gfx.Assets;
import game.items.Weapon;

public class Shotgun extends Weapon{

	public Shotgun() {
		super(Assets.shotgun, "shotgun", 1, "look cool while", "blasting your", " enemies!", "", "(shoots three", "bullets at a", "time)", 3);
	}
	
	@Override
	public void shoot(Handler handler, float xOrigin, float yOrigin, double dir) {
		handler.getWorld().getEntityManager().addEntity(new LightBullet(handler, xOrigin, yOrigin, dir));
	}
}
