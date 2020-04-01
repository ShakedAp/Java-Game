package game.states;

import java.awt.Graphics;

import game.Handler;
import game.gfx.Assets;
import game.ui.ClickListener;
import game.ui.UIImage;
import game.ui.UIImageButton;
import game.ui.UIManager;

public class MenuState extends State{

	
	public MenuState(Handler handler) {
		super(handler, new UIManager(handler));
		handler.getMouseManager().setUiManager(uiManager);

		// UI objects
		uiManager.addObject(new UIImage(Assets.start_menu_background, 0, 0, 960, 540));
		
		uiManager.addObject(new UIImageButton(256, 128 , 152, 66, Assets.btn_start, new ClickListener(){ 
			// Creating a new ClickListener with the action we want to do	
			@Override
			public void onClick() {
				handler.goToState(handler.getGame().storyState);	
			}
		}));	
		uiManager.addObject(new UIImageButton(512, 256 , 191, 66, Assets.btn_settings, new ClickListener(){ 
			@Override
			public void onClick() {
				handler.goToState(handler.getGame().settingsState);	
			}
		}));
	}
	
	@Override
	public void tick() {
		uiManager.tick();
		
		// TEMPORARY CODE
		handler.goToState(handler.getGame().tutorialState);
	}

	@Override
	public void render(Graphics g) {
		uiManager.render(g);
	}

}
