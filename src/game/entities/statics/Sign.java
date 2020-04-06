package game.entities.statics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import game.Handler;
import game.gfx.Assets;

public class Sign extends StaticEntity{
	
	protected Rectangle zoomArea;
	
	public Sign(Handler handler, float x, float y) {
		super(handler, x, y, 48, 48);
		
		
		health = 999;
		
		bounds.x = 21;
		bounds.y = 22;
		bounds.width = 4;
		bounds.height = 24;
		
		zoomArea = new Rectangle((int) (x - 50), (int) (y - 50), 150, 150);
	}

	@Override
	public void tick() {
		if(zoomArea.intersects(handler.getWorld().getEntityManager().getPlayer().getCollisonBounds(0, 0))) {
			handler.getGame().setZoomScale(1.2);
			handler.getWorld().getEntityManager().getPlayer().setConversation(true);
		}
		else {
			handler.getGame().setZoomScale(1);
			handler.getWorld().getEntityManager().getPlayer().setConversation(false);
		}
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.sign,(int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
		
		g.setColor(Color.black);
		g.drawRect((int) (zoomArea.x - handler.getGameCamera().getxOffset()), (int) (zoomArea.y - handler.getGameCamera().getyOffset()), zoomArea.width, zoomArea.height);
	}
	
	@Override
	public void die() {
		
	}
}
