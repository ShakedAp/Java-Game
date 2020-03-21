package game.states;

import java.awt.Graphics;

import game.Handler;
import game.ui.UIManager;

public class StoryState extends State{

	public StoryState(Handler handler) {
		super(handler, new UIManager(handler));

	}

	@Override
	public void tick() {
		uiManager.tick();
		
	}

	@Override
	public void render(Graphics g) {
		uiManager.render(g);
		
	}

}
