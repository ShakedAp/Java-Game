package game.entities.projectiles;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import game.Handler;
import game.entities.Entity;
import game.entities.creatures.Player;
import game.gfx.Animation;
import game.gfx.Assets;
import game.sounds.SoundEffect;
import game.tiles.Tile;

public class Rocket extends Projectile {
	
	private Animation anim, explosion;
	private Boolean exploded = false;
	private SoundEffect explosionSound;
	
	public Rocket(Handler handler, float x, float y, double dir) {
		super(handler, x, y, dir, 128, 128);
		
		BufferedImage[] animation = new BufferedImage[2];
		animation[0] = rotateImage(angle, Assets.rocket[0]);
		animation[1] = rotateImage(angle, Assets.rocket[1]);

		anim = new Animation(100, animation, true);
		explosion = new Animation(50, Assets.explosion, false);
		
		explosionSound = new SoundEffect("explosion", handler);
		
		damage = 10;
		bounds.x = 34;
		bounds.y = 55;
		bounds.width = 84;
		bounds.height = 20;
	}
	
	@Override
	public void tick() {
		if(!exploded) {
			move(); 
			anim.update();
		}
		else
			explosion.update();
		
		if(exploded && explosion.isFinished())
			kill();
		

	}

	@Override
	public void render(Graphics g) {		
		g.drawImage(getCurrentFrame() ,(int) (x + bounds.x - bounds.width/2 - handler.getGameCamera().getxOffset()),
				(int) (y - bounds.y - bounds.height/2 - handler.getGameCamera().getyOffset()), width, height, null);
	}
	
	private BufferedImage getCurrentFrame() {
		if(!exploded)
			return anim.getCurrentFrame();
		else {
			return explosion.getCurrentFrame();
		}
	}
	
	@Override
	protected void checkCollisons(){
		if(collisionWithTile((int) x/Tile.TILE_WIDTH, (int) y/Tile.TILE_HEIGHT)) {
			exploded = true;
			explosionSound.play();
		}
		if(handler.getWorld().getTile((int) x/Tile.TILE_WIDTH, (int) y/Tile.TILE_HEIGHT) == Tile.wallTile) {
			exploded = true;				
			explosionSound.play();
		}
		
		for(Entity e : handler.getWorld().getEntityManager().getEntities()) { 
			if(e.equals(this) || e instanceof Player) continue;
			if(e.getCollisonBounds(0f,0f).intersects(getCollisonBounds(0f, 0f)) && e.isSolid() && !exploded) {
				e.hurt(damage);
				explosionSound.play();
				exploded = true;
			}
		}
	}
	
	
	
	
 	private BufferedImage rotateImage(double angle, BufferedImage bimg) {
	    int w = bimg.getWidth();    
	    int h = bimg.getHeight();

	    BufferedImage rotated = new BufferedImage(w, h, bimg.getType());  
	    Graphics2D graphic = rotated.createGraphics();
	    graphic.rotate(angle, w/2, h/2);
	    graphic.drawImage(bimg, null, 0, 0);
	    graphic.dispose();
	    return rotated;
	}
	
	
	@Override
	public Rectangle getCollisonBounds(float xOffset, float yOffset) {
		return new Rectangle((int) (x + bounds.x/2 + xOffset), (int) (y + yOffset), bounds.width, bounds.height);
	}

	@Override
	public void die() {
		
	}

	
}
