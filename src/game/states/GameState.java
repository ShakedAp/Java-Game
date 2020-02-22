package game.states;

import java.awt.Graphics;

import game.Handler;
import game.gfx.Assets;
import game.ui.UIImage;
import game.ui.UIManager;
import game.worlds.World;

public class GameState extends State{
	
	private World world;
	
	private UIImage equipedWeaponUI = new UIImage(null, 832, 412, 128, 128);
	
	public GameState(Handler handler) {
		super(handler, new UIManager(handler));
		//Importing the world
		world = new World(handler, "res/worlds/world1.txt");
		handler.setWorld(world);
		//UI
		handler.getMouseManager().setUiManager(uiManager);
		uiManager.addObject(equipedWeaponUI);
	}
	
	@Override
	public void tick() {
		world.tick();
		uiManager.tick();
		
		equipedWeaponUI.setTexture(handler.getWorld().getEntityManager().getPlayer().getInventory().getEquipedWeapon().getTexture());
	}

	@Override
	public void render(Graphics g) {
		world.render(g);
		uiManager.render(g);
	}

}
