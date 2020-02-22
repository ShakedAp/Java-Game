package game.ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class UIImage extends UIObject {

	private BufferedImage texture;
	
	public UIImage( BufferedImage texture, float x, float y, int width, int height) {
		super(x, y, width, height);
		this.texture = texture;
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
