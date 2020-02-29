package game.entities.projectiles;

import java.awt.Graphics;
import java.awt.Graphics2D;

import game.Handler;
import game.entities.Entity;
import game.entities.creatures.Player;
import game.gfx.Animation;
import game.gfx.Assets;

public class Rocket extends Projectile {
	
	private Animation rocketAnim;
	private int animSpeed = 100;
	
	public Rocket(Handler handler, float x, float y, double dir) { //TODO: fix bugs!
		super(handler, x, y, dir, 128, 128);
		
		rocketAnim = new Animation(animSpeed, Assets.rocket);
		
		damage = 10;
		range = 999999999;
		
		bounds.x = 34;
		bounds.y = 55;
		bounds.width = 84;
		bounds.height = 20;
	}
	
	@Override
	public void tick() {
		move(); 
		rocketAnim.update();
		
		for(Entity e : handler.getWorld().getEntityManager().getEntities()) { 
			if(e.equals(this) || e instanceof Player) continue;
			if(e.getCollisonBounds(0f,0f).intersects(this.getCollisonBounds(0f, 0f)) && e.isSolid()) {
				e.hurt(damage);
				kill();
			}
		}
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(rocketAnim.getCurrentFrame() ,(int) (x + bounds.x - bounds.width/2 - handler.getGameCamera().getxOffset()),
				(int) (y - bounds.y - bounds.height/2 - handler.getGameCamera().getyOffset()), width, height, null);
	}
	
	public void render(Graphics2D g2d) {
		g2d.drawImage(rocketAnim.getCurrentFrame() ,(int) (x + bounds.x - bounds.width/2 - handler.getGameCamera().getxOffset()),
				(int) (y - bounds.y - bounds.height/2 - handler.getGameCamera().getyOffset()), width, height, null);
	}
	

	@Override
	public void die() {
		
	}

	
}
