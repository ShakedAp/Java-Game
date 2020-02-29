package game.items.Weapons;

import java.util.concurrent.ThreadLocalRandom;

import game.Handler;
import game.entities.projectiles.LightBullet;
import game.entities.projectiles.ShotgunBullet;
import game.gfx.Assets;
import game.items.Weapon;

public class Shotgun extends Weapon{

	public Shotgun() {
		super(Assets.shotgun, "shotgun", 2);
		
		bps = 1;
		spread = 20;
		
		descLine1 = "look cool while";
		descLine2 = "blasting your";
		descLine3 = "enemies!";
		descLine4 = "";
		descLine5 = "(shoots five";
		descLine6 = "bullets at a";
		descLine7 = "time)";
	}
	
	@Override
	public void shoot(Handler handler, float xOrigin, float yOrigin, double dir) {
		for(int i=0; i < 5; i++)
			handler.getWorld().getEntityManager().addEntity(new ShotgunBullet(handler, xOrigin, yOrigin, dir + calculateSpread()));
		
	}
}
