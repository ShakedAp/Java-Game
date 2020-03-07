package game.items.Weapons;

import game.Handler;
import game.entities.projectiles.LightBullet;
import game.gfx.Assets;
import game.items.Weapon;

public class WaterGun extends Weapon{

	public WaterGun() {
		super(Assets.water_gun, "water gun", 5);
		
		manaCost = 0;
		
		descLine1 = "it squirts";
		descLine2 = "water at your";
		descLine3 = "opponents. if you";
		descLine4 = "can't make girls";
		descLine5 = "wet in real";
		descLine6 = "life, you can in";
		descLine7 = "game.";
	}
	
	@Override
	public void shoot(Handler handler, float xOrigin, float yOrigin, double dir) {
		handler.getWorld().getEntityManager().addEntity(new LightBullet(handler, xOrigin, yOrigin, dir + calculateSpread()));
	}

}
