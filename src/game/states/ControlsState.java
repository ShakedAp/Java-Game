package game.states;

import java.awt.Graphics;

import game.Handler;
import game.gfx.Assets;
import game.ui.ClickListener;
import game.ui.UIImage;
import game.ui.UIImageButton;
import game.ui.UIImageToggleButton;
import game.ui.UIManager;

public class ControlsState extends State{

	public ControlsState(Handler handler) {
		super(handler, new UIManager(handler));		
	
		
		// UI objects
		uiManager.addObject(new UIImage(Assets.start_menu_background, 0, 0, 960, 540));
		// Back to menu
		uiManager.addObject(new UIImageButton(25, 25 , 161, 66, Assets.btn_menu, new ClickListener(){
			@Override
			public void onClick() {
				handler.getMouseManager().setUiManager(handler.getGame().settingsState.getUiManager());
				State.setState(handler.getGame().settingsState);	
			}
		}));
	}

	@Override
	public void tick() {
		handler.getMouseManager().setUiManager(uiManager);
		uiManager.tick();
	}

	@Override
	public void render(Graphics g) {
		uiManager.render(g);
	}

}