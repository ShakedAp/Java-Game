package game.items.Weapons;

import game.Handler;
import game.entities.projectiles.LightBullet;
import game.gfx.Assets;
import game.gfx.sounds.SoundEffect;
import game.items.Weapon;

public class BadPistol extends Weapon{


	
	public BadPistol() {
		super(Assets.bad_pistol, "Bad Pistol", 0);
		
		manaCost = 0;
		
		descLine1 = "just the plain";
		descLine2 = "good old pistol.";
		descLine3 = "";
		descLine4 = "(its quite bad,";
		descLine5 = "though)";
	}
	
	@Override
	public void shoot(Handler handler, float xOrigin, float yOrigin, double dir) {
		SoundEffect shoot = new SoundEffect(".//res//sounds//shoot.wav", handler);
		handler.getWorld().getEntityManager().addEntity(new LightBullet(handler, xOrigin, yOrigin, dir + calculateSpread()));
		shoot.play();
	}

}
