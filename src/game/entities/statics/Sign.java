package game.entities.statics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import game.Handler;
import game.gfx.Assets;
import game.gfx.Text;

public class Sign extends StaticEntity{
	
	private Rectangle zoomArea;
	private String text;
	private boolean textActive;
	
	public Sign(Handler handler, float x, float y, String text) {
		super(handler, x, y, 48, 48);
		health = 999;
		
		this.text = text;
		
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
			textActive = true;
			System.out.println(handler.getGame().getCurrentZoomScale());
		}	
		else {
			System.out.println(handler.getGame().getCurrentZoomScale());
			handler.getGame().setZoomScale(1);
			textActive = false;
		}
		
	}

	@Override
	public void render(Graphics g) {		
		g.drawImage(Assets.sign,(int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
		
		if(textActive)
		convRender(g);
		
		
		g.setColor(Color.black);
		g.drawRect((int) (zoomArea.x - handler.getGameCamera().getxOffset()), (int) (zoomArea.y - handler.getGameCamera().getyOffset()), zoomArea.width, zoomArea.height);
	}
	
		int convIndex = 0;
		private void convRender(Graphics g) {
				g.drawImage(Assets.convBox, 166, 350, null);
				
				int digitsPerLine = 62;
				
				if(convIndex < text.length())
					convIndex ++;
				
				if(convIndex < digitsPerLine)
				Text.drawString(g, text.substring(0,convIndex), 180, 380, false, Color.black ,Assets.font21);
				
				if(text.length() >= digitsPerLine && convIndex >= digitsPerLine && convIndex < 121) {
					Text.drawString(g, text.substring(0, digitsPerLine), 180, 380, false, Color.black ,Assets.font21);
					Text.drawString(g, text.substring(digitsPerLine, convIndex), 180, 400, false, Color.black ,Assets.font21);
				}
				
				if(text.length() >= digitsPerLine*2 && convIndex >= digitsPerLine*2) {
					Text.drawString(g, text.substring(0, digitsPerLine), 180, 380, false, Color.black ,Assets.font21);
					Text.drawString(g, text.substring(digitsPerLine, digitsPerLine*2), 180, 400, false, Color.black ,Assets.font21);
					Text.drawString(g, text.substring(digitsPerLine*2, convIndex), 180, 420, false, Color.black ,Assets.font21);
				}
	}
	
	
	
	@Override
	public void die() {
		
	}
}
