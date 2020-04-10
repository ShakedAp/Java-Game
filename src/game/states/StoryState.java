package game.states;

import java.awt.Graphics;

import game.Handler;
import game.gfx.Assets;
import game.gfx.SpriteSheet;
import game.ui.UIComicFrame;
import game.ui.UIManager;

public class StoryState extends State{

	private UIComicFrame[] comicFrames;
	
	public StoryState(Handler handler) {
		super(handler, new UIManager(handler));
		
		// Comic
		SpriteSheet comic = new SpriteSheet(Assets.comic);
		comicFrames = new UIComicFrame[9];
		
		for(int y = 0; y < 3; y++) {
			for(int x = 0; x < 3; x++) {
				comicFrames[3*y+x] = new UIComicFrame(comic, 320*x, 180*y, 320, 180);
			}
		}
	}

	
	int i = 0;
	@Override
	public void tick() {
		uiManager.tick();
		// If any key is pressed add a new comicFramem, if we have finished all the frames go to the tutorial
		if(handler.getKeyManager().anyKeyPressed) {
			if(i < 9) uiManager.addObject(comicFrames[i]);
			else {
				handler.getMouseManager().setUiManager(handler.getGame().tutorialState.getUiManager());
				State.setState(handler.getGame().tutorialState);
			}
			i++;
		}
	}

	@Override
	public void render(Graphics g) {
		uiManager.render(g);		
		
	}

}
