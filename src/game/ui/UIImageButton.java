package game.ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class UIImageButton extends UIObject {

	private BufferedImage[] images;
	private ClickListener clicker;

	public UIImageButton(float x, float y, int width, int height, BufferedImage[] images, ClickListener clicker) {
		super(x, y, width, height);

		this.images = images;
		this.clicker = clicker;
	}

	@Override
	public void tick() {
	}

	@Override
	public void render(Graphics g) {
		if (hovering)
			g.drawImage(images[1], (int) x, (int) y, width, height, null);
		else
			g.drawImage(images[0], (int) x, (int) y, width, height, null);
	}

	@Override
	public void onClick() {
		clicker.onClick(); // For each new clicker we are going pass there will be new action when clicking
	}

	
	// GETTERS & SETTERS
	public BufferedImage[] getImages() {
		return images;
	}

	public void setImages(BufferedImage[] images) {
		this.images = images;
	}

	public ClickListener getClicker() {
		return clicker;
	}

	public void setClicker(ClickListener clicker) {
		this.clicker = clicker;
	}

}
