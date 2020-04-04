package game.entities.statics;

import java.awt.Graphics;

import game.Handler;
import game.gfx.Assets;
import game.tiles.Tile;

public class Sign extends StaticEntity{
	
	public Sign(Handler handler, float x, float y) {
		super(handler, x, y, 48, 48);
		
		bounds.x = 21;
		bounds.y = 22;
		bounds.width = 4;
		bounds.height = 24;
	}

	@Override
	public void tick() {
		
		
	}

	@Override
	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		int w = width;
		int h = height;
		// Translate used to make sure scale is centered
		g2.translate(w/2, h/2);
		g2.scale(1.6, 1.6);
		g2.translate(-w/2, -h/2);
		
		g.drawImage(Assets.sign,(int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);

	}
	
	@Override
	public void die() {
		
	}
}
