package game.states;

import java.awt.Graphics;

import game.Handler;
import game.gfx.Assets;
import game.ui.ClickListener;
import game.ui.UIImageButton;
import game.ui.UIManager;

public class MenuState extends State{

	private UIManager uiManager;
	
	public MenuState(Handler handler) {
		super(handler);
		uiManager = new UIManager(handler);
		handler.getMouseManager().setUiManager(uiManager); //setting the uiManager of this game to this uiManager
		
		uiManager.addObject(new UIImageButton(256, 148 , 128, 64, Assets.btn_start, new ClickListener(){ 
			//creating a new ClickListener with the action we want to do	
			@Override
			public void onClick() {
				handler.getMouseManager().setUiManager(null);
				State.setState(handler.getGame().gameState);
				
			}
		}));
	}
	
	@Override
	public void tick() {
		uiManager.tick();
	}

	@Override
	public void render(Graphics g) {
		uiManager.render(g);
		
		
		
		
		
		//temporary code
		handler.getMouseManager().setUiManager(null);
		State.setState(handler.getGame().gameState);
	}

}
