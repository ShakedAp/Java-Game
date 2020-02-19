package game.items.Weapons;

import game.Handler;
import game.entities.projectiles.LightBullet;
import game.entities.statics.Tree;
import game.gfx.Assets;
import game.items.Weapon;

public class WaterGun extends Weapon{

	public WaterGun() {
		super(Assets.water_gun, "water gun", 3 , "it squirts", "water at your", "opponents. if you", "can’t make girls", "wet in real", "life, you can in", "game.", 5);
	}
	
	@Override
	public void shoot(Handler handler, float xOrigin, float yOrigin, double dir) {
		handler.getWorld().getEntityManager().addEntity(new LightBullet(handler, xOrigin, yOrigin, dir));
	}

}
