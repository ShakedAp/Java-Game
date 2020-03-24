package game.states;

import java.awt.Graphics;

import game.Handler;
import game.gfx.Assets;
import game.ui.ClickListener;
import game.ui.UIImage;
import game.ui.UIImageButton;
import game.ui.UIImageToggleButton;
import game.ui.UIManager;

public class SettingsState extends State{

	public SettingsState(Handler handler) {
		super(handler, new UIManager(handler));		
	
		
		// UI objects
		uiManager.addObject(new UIImage(Assets.start_menu_background, 0, 0, 960, 540));
		
		uiManager.addObject(new UIImageButton(25, 25 , 161, 66, Assets.btn_menu, new ClickListener(){ 
			@Override
			public void onClick() {
				handler.getMouseManager().setUiManager(handler.getGame().menuState.getUiManager());
				State.setState(handler.getGame().menuState);	
			}
		}));
		
		uiManager.addObject(new UIImageToggleButton(100, 100 , 115, 52, Assets.btn_toggle, new ClickListener(){ 
			@Override
			public void onClick() {
				
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
