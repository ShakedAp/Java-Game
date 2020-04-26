package game.items.Weapons;

import game.Handler;
import game.entities.projectiles.ShotgunBullet;
import game.gfx.Assets;
import game.items.Weapon;
import game.sounds.SoundEffect;

public class Shotgun extends Weapon{
	
	private SoundEffect shoot =  new SoundEffect("shootSounds/shotgunShoot", handler);
	
	public Shotgun() {
		super(Assets.shotgun, "Shotgun", 2);
		
		bps = 1;
		spread = 20;
		manaCost = 2;
		
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
		if(shoot.getHandler() != handler)
			shoot.setHandler(handler);
		
		shoot.play();
		for(int i=0; i < 5; i++)
			handler.getWorld().getEntityManager().addEntity(new ShotgunBullet(handler, xOrigin, yOrigin, dir + calculateSpread()));
		
	}
}
