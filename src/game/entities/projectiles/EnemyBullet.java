package game.entities.projectiles;

import java.awt.Graphics;

import game.Handler;
import game.entities.Entity;
import game.entities.creatures.Player;
import game.gfx.Assets;
import game.tiles.Tile;

public class EnemyBullet extends Projectile {
	
	public EnemyBullet(Handler handler, float x, float y, double dir) {
		super(handler, x, y, dir, 8, 8);
	}
	
	@Override
	public void tick() {
		move(); 
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.bullet,(int) (x - handler.getGameCamera().getxOffset()),
				(int) (y- handler.getGameCamera().getyOffset()), width, height, null);
	}

	
	protected void checkCollisons(){
		if(collisionWithTile((int) x/Tile.TILE_WIDTH, (int) y/Tile.TILE_HEIGHT))
			kill();
		if(handler.getWorld().getTile((int) x/Tile.TILE_WIDTH, (int) y/Tile.TILE_HEIGHT) == Tile.wallTile)
			kill();
		
		
		if(getCollisonBounds(0,0).intersects(handler.getWorld().getEntityManager().getPlayer().getCollisonBounds(0, 0))) {
			handler.getWorld().getEntityManager().getPlayer().hurt(1);
			kill();
		}
	}
	
	@Override
	public void die() {
		
	}

	
}
