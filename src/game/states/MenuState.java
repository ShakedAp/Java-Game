package game.states;

import java.awt.Graphics;
import java.awt.Image;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;

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
		
		uiManager.addObject(new UIImage(Assets.menu_title, 960/2 -Assets.menu_title.getWidth()/2, 0, Assets.menu_title.getWidth(), Assets.menu_title.getHeight()));

		
		uiManager.addObject(new UIImageButton(960/2 - 172/2, 128 , 172, 72, Assets.btn_start, new ClickListener(){ 
			@Override
			public void onClick() {
				handler.goToState(handler.getGame().storyState);	
			}
		}));	
		uiManager.addObject(new UIImageButton(960/2 - 172/2, 256 , 172, 72, Assets.btn_settings, new ClickListener(){ 
			@Override
			public void onClick() {
				handler.goToState(handler.getGame().settingsState);	
			}
		}));
		
		uiManager.addObject(new UIImageButton(960/2 - 172/2, 256+128, 172, 72, Assets.btn_exit, new ClickListener() {
			@Override
			public void onClick() {
				handler.getGame().close();
			}}));
		
	}
	
	@Override
	public void tick() {
		uiManager.tick();
		
		// TEMPORARY CODE
//		handler.goToState(handler.getGame().tutorialState);
	}

	@Override
	public void render(Graphics g) {
		uiManager.render(g);
		


	}

}
