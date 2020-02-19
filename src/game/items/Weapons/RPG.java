package game.items.Weapons;

import game.Handler;
import game.entities.projectiles.LightBullet;
import game.gfx.Assets;
import game.items.Weapon;

public class RPG extends Weapon{

	public RPG() {
		super(Assets.RPG, "RPG", 1, "it stands for:", "remote", "party", "(for) gamers", "", "(shoots rockets)", "", 1);
	}
	
	@Override
	public void shoot(Handler handler, float xOrigin, float yOrigin, double dir) {
		handler.getWorld().getEntityManager().addEntity(new LightBullet(handler, xOrigin, yOrigin, dir));
	}
}
