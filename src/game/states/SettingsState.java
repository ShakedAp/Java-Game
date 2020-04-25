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
		
		uiManager.addObject(new UIImage(Assets.settings_title, 960/2 -Assets.settings_title.getWidth()/2, 0, Assets.settings_title.getWidth(), Assets.settings_title.getHeight()));

		
		uiManager.addObject(new UIImageButton(25, 25 , 84, 68, Assets.btn_home, new ClickListener(){
			@Override
			public void onClick() {
				handler.goToState(handler.getGame().menuState);
			}
		}));

		uiManager.addObject(new UIImageToggleButton(600, 150 , 64, 64, Assets.btn_toggle, new ClickListener(){ 
			@Override
			public void onClick() {
				handler.getGame().setSfxOn(!handler.getGame().isSfxOn());
			}
		}));

		uiManager.addObject(new UIImageToggleButton(600, 250 , 64, 64, Assets.btn_toggle, new ClickListener(){ 
		@Override
		public void onClick() {
			handler.getGame().setMusicOn(!handler.getGame().isMusicOn());
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
