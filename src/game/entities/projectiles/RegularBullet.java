package game.entities.projectiles;

import java.awt.Color;
import java.awt.Graphics;

import game.Handler;
import game.entities.Entity;

public class RegularBullet extends Projectile {
	
	private Entity shooter;

	public RegularBullet(Handler handler, float fromX, float fromY, float toX, float toY, Entity shooter) {
		super(handler, fromX, fromY, toX, toY, 8, 8, 30);
		this.shooter = shooter;
	}

	@Override
	public void tick() {
		move();
		tickLifeCounter();
		checkDamaging(shooter, 1);
		tileCollsionsCheck();
		}
	
	private void checkDamaging(Entity shooter, int amt) {
		for(Entity e : handler.getWorld().getEntityManager().getEntities()) { //loops threw each entity
			if(e instanceof Projectile || e.equals(shooter)) //if the entity is a bullet, or the shooter
				continue;
			if(e.getCollisonBounds(0,0).intersects(this.getCollisonBounds(0,0))) {
				this.kill();
				e.hurt(amt);
			}
		}
	}
	
	
	@Override
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int) (x - handler.getGameCamera().getxOffset()),(int) (y- handler.getGameCamera().getyOffset()), width, height);
	}

	@Override
	public void die() {
		
	}

}
