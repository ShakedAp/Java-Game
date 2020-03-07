package game.items.Weapons;

import game.Handler;
import game.entities.projectiles.Rocket;
import game.gfx.Assets;
import game.items.Weapon;

public class RPG extends Weapon{

	public RPG() {
		super(Assets.RPG, "RPG", 1);
		
		bps = 0.7;
		manaCost = 6;
		
		descLine1 = "it stands for:";
		descLine2 = "remote";
		descLine3 = "party";
		descLine4 = "(for) gamers";
		descLine5 = "";
		descLine6 = "(shoots rockets)";
		
		
	}
	
	@Override
	public void shoot(Handler handler, float xOrigin, float yOrigin, double dir) {
		handler.getWorld().getEntityManager().addEntity(new Rocket(handler, xOrigin, yOrigin, dir));
	}
}
