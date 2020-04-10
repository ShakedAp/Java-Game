package game.entities.statics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import game.Handler;
import game.gfx.Assets;
import game.gfx.Text;

public class Sign extends StaticEntity {

	private Rectangle zoomBounds;
	private String text;
	private boolean playerNear;

	public Sign(Handler handler, float x, float y, String text) {
		super(handler, x, y, 48, 64);
		health = 999;

		this.text = text;

		bounds.x = 21;
		bounds.y = 22;
		bounds.width = 4;
		bounds.height = 24;

		zoomBounds = new Rectangle((int) (x - 50), (int) (y - 50), 150, 150);
	}

	@Override
	public void tick() {
		if (zoomBounds.intersects(handler.getWorld().getEntityManager().getPlayer().getCollisonBounds(0, 0))) {
			handler.getGame().setZoomScale(1.2);
			playerNear = true;
		} else
			playerNear = false;
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.sign, (int) (x - handler.getGameCamera().getxOffset()),
				(int) (y - handler.getGameCamera().getyOffset()), width, height, null);
		if (playerNear)
			convRender(g);

//		g.setColor(Color.black);
//		g.drawRect((int) (zoomBounds.x - handler.getGameCamera().getxOffset()),
//				(int) (zoomBounds.y - handler.getGameCamera().getyOffset()), zoomBounds.width, zoomBounds.height);
	}

	int convIndex = 0;

	private void convRender(Graphics g) {
		g.drawImage(Assets.convBox, 166, 350, null);

		int digitsPerLine = 62;

		if (convIndex < text.length())
			convIndex++;

		if (convIndex < digitsPerLine)
			Text.drawString(g, text.substring(0, convIndex), 180, 380, false, Color.black, Assets.font21);

		if (text.length() >= digitsPerLine && convIndex >= digitsPerLine && convIndex <= digitsPerLine * 2) {
			Text.drawString(g, text.substring(0, digitsPerLine), 180, 380, false, Color.black, Assets.font21);
			Text.drawString(g, text.substring(digitsPerLine, convIndex), 180, 400, false, Color.black, Assets.font21);
		}

		if (text.length() > digitsPerLine * 2 && convIndex > digitsPerLine * 2) {
			Text.drawString(g, text.substring(0, digitsPerLine), 180, 380, false, Color.black, Assets.font21);
			Text.drawString(g, text.substring(digitsPerLine, digitsPerLine * 2), 180, 400, false, Color.black,
					Assets.font21);
			Text.drawString(g, text.substring(digitsPerLine * 2, convIndex), 180, 420, false, Color.black,
					Assets.font21);
		}
	}

	@Override
	public void die() {

	}

	// GETTERS & SETTERS
	public Rectangle getZoomBounds() {
		return zoomBounds;
	}

	public void setZoomBounds(Rectangle zoomBounds) {
		this.zoomBounds = zoomBounds;
	}

	public boolean isPlayerNear() {
		return playerNear;
	}
}
