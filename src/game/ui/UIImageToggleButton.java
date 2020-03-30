package game.ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import game.states.State;

public class UIImageToggleButton extends UIObject {

	private BufferedImage[] images;
	private ClickListener clicker;
	private Boolean toggled = true;

	public UIImageToggleButton(float x, float y, int width, int height, BufferedImage[] images, ClickListener clicker) {
		super(x, y, width, height);

		this.images = images;
		this.clicker = clicker;
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		if(toggled)
		g.drawImage(images[1], (int) x, (int) y, width, height, null);
			else 
		g.drawImage(images[0], (int) x, (int) y, width, height, null);
	}

	@Override
	public void onClick() {
		clicker.onClick();
		toggled = !toggled;
	}

	// GETTERS & SETTERS
	public Boolean isToggled() {
		return toggled;
	}

	public void setToggled(Boolean toggled) {
		this.toggled = toggled;
	}

}
