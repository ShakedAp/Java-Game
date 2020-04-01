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
import game.ui.UIObject;
import game.worlds.Section;
import game.worlds.World;

public class GameState extends State {

	private World world;
	public GameState(Handler handler) {
		super(handler, handler.getGame().tutorialState.getUiManager());
		world = new World(handler, "res/worlds/world1.txt", 4, 4);
		
		// Sections
		world.getSectionManager().setObject(new Section(handler, 448, 1088, 512, 512), 0, 1);
		world.getSectionManager().setObject(new Section(handler, 1664, 1024, 576, 576), 1, 1);
		world.getSectionManager().setObject(new Section(handler, 1792, 256, 320, 256), 1, 0);
		world.getSectionManager().setObject(new Section(handler, 1536, 2112, 832, 832), 1, 2);
		world.getSectionManager().setObject(new Section(handler, 2944, 896, 704, 704), 2, 1);
		world.getSectionManager().setObject(new Section(handler, 4224, 832, 1024, 1024), 3, 1);
		world.getSectionManager().setObject(new Section(handler, 3072, 2304, 448, 448), 2, 2);
		world.getSectionManager().setObject(new Section(handler, 2880, 3392, 832, 768), 2, 3);
		
		// UI Manager
		uiManager.addObject(world.getPauseButton());
	}
	
	
	@Override
	public void tick() {
		if(!handler.getWorld().equals(world)) handler.setWorld(world);
		
		world.tick();
		uiManager.tick();
	}

	@Override
	public void render(Graphics g) {
		world.render(g);
		uiManager.render(g);
		
		// Draw mana cost
		Weapon wpn = (Weapon) world.getEntityManager().getPlayer().getInventory().getEquippedWeapon();
		Text.drawString(g, Integer.toString(wpn.getManaCost()), 895, 521, true, Color.white, Assets.font24);
		// Draw player helath bars
		world.getEntityManager().getPlayer().renderUI(g);		
		
		
	}

}
