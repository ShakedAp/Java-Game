package game.states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import game.Handler;
import game.gfx.Assets;
import game.gfx.Text;
import game.items.Weapon;
import game.ui.ClickListener;
import game.ui.UIImage;
import game.ui.UIImageButton;
import game.ui.UIManager;
import game.worlds.Section;
import game.worlds.World;

public class TutorialState extends State {

	private World world;
	
	public TutorialState(Handler handler) {
		super(handler, new UIManager(handler));
		world = new World(handler, "res/worlds/tutorialWorld", 0, 0);
		handler.setWorld(world);		

		// UI
		uiManager.addObject(world.getPauseButton());
	}

	@Override
	public void tick() {
		uiManager.tick();
		world.tick();
		
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_SPACE)) {
			handler.getMouseManager().setUiManager(handler.getGame().gameState.getUiManager());
			State.setState(handler.getGame().gameState);
		}
	}

	@Override
	public void render(Graphics g) {
		world.render(g);
		uiManager.render(g);
		// Draw player UI
		world.getEntityManager().getPlayer().renderUI(g);
		
	}

}
