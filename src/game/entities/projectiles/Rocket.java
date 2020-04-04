package game.entities.projectiles;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

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
		BufferedImage img = rocketAnim.getCurrentFrame();
		
		
		// Rotating sprite
		AffineTransform transform = new AffineTransform();
	    transform.rotate(angle, img.getWidth()/2 , img.getHeight()/2);
	    AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
	    img = op.filter(img, null);
		
		g.drawImage(img ,(int) (x + bounds.x - bounds.width/2 - handler.getGameCamera().getxOffset()),
				(int) (y - bounds.y - bounds.height/2 - handler.getGameCamera().getyOffset()), width, height, null);
	}
	

	@Override
	public void die() {
		
	}

	
}
