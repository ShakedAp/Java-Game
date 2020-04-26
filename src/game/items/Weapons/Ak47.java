package game.items.Weapons;

import game.Handler;
import game.entities.projectiles.MediumBullet;
import game.gfx.Assets;
import game.items.Weapon;
import game.sounds.SoundEffect;

public class Ak47 extends Weapon{

	private SoundEffect shoot =  new SoundEffect("shootSounds/ak47Shoot", handler);

	
	public Ak47() {
		super(Assets.ak47, "Ak47", 4);
		
		bps = 5;
		
		descLine1 = "mother russia’s";
		descLine2 = "pride concentrated";
		descLine3 = "into one gun";
		descLine4 = "of pure soviet";
		descLine5 = "badass.";
	}
	
	@Override
	public void shoot(Handler handler, float xOrigin, float yOrigin, double dir) {
		if(shoot.getHandler() != handler)
			shoot.setHandler(handler);
		
		shoot.play();
		handler.getWorld().getEntityManager().addEntity(new MediumBullet(handler, xOrigin, yOrigin, dir + calculateSpread()));
	}

}
