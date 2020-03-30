package game.ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import game.gfx.SpriteSheet;

public class UIComicFrame extends UIObject {

	private BufferedImage texture;
	
	public UIComicFrame(SpriteSheet comic, float x, float y, int width, int height) {
		super(x, y, width, height);
		texture = comic.crop((int) x,(int) y, width, height);
	}

	@Override
	public void tick() {}

	@Override
	public void render(Graphics g) {
		g.drawImage(texture, (int) x, (int) y, width, height, null);
	}

	@Override
	public void onClick() { 
		
	}

	//GETTERS & SETTERS
	public BufferedImage getTexture() {
		return texture;
	}

	public void setTexture(BufferedImage texture) {
		this.texture = texture;
	}

}
