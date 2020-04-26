package game.items.Weapons;

import game.Handler;
import game.entities.projectiles.LightBullet;
import game.gfx.Assets;
import game.items.Weapon;
import game.sounds.SoundEffect;

public class WaterGun extends Weapon{

	private SoundEffect shoot =  new SoundEffect("shootSounds/waterShoot", handler);
	
	public WaterGun() {
		super(Assets.water_gun, "Water gun", 5);
		
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
		if(shoot.getHandler() != handler)
			shoot.setHandler(handler);
		
		shoot.play();
		handler.getWorld().getEntityManager().addEntity(new LightBullet(handler, xOrigin, yOrigin, dir + calculateSpread()));
	}

}
