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

public class GameState extends State {

	private World world;
	private boolean paused = false;

	private UIImage equipedWeaponUI = new UIImage(null, 832, 412, 128, 128);
	private UIImageButton pauseButton = new UIImageButton(10, 10, 80, 64, Assets.btn_pause, new ClickListener() {
		@Override
		public void onClick() {
			paused = !paused;
		}});

	public GameState(Handler handler) {
		super(handler, new UIManager(handler));
		// Importing the world
		world = new World(handler, "res/worlds/world1.txt", 4, 4);
		handler.setWorld(world);		
		
		// Sections
		world.getSectionManager().setObject(new Section(handler, 448, 1088, 512, 512), 0, 1);
		world.getSectionManager().setObject(new Section(handler, 1664, 1024, 576, 576), 1, 1);
		world.getSectionManager().setObject(new Section(handler, 1792, 256, 320, 256), 1, 0);
		world.getSectionManager().setObject(new Section(handler, 1536, 2112, 832, 832), 1, 2);
		world.getSectionManager().setObject(new Section(handler, 2944, 896, 704, 704), 2, 1);
		world.getSectionManager().setObject(new Section(handler, 4224, 832, 1024, 1024), 3, 1);
		world.getSectionManager().setObject(new Section(handler, 3072, 2304, 448, 448), 2, 2);
		world.getSectionManager().setObject(new Section(handler, 2880, 3392, 832, 768), 2, 3);
		
		// UI
		uiManager.addObject(equipedWeaponUI);
		uiManager.addObject(new UIImage(Assets.chosen, 832, 412, 128, 128));
		uiManager.addObject(new UIImage(Assets.mana_display, 832 + 48, 412 + 48 + 49, 30, 30));
		uiManager.addObject(pauseButton);
		
		uiManager.addObject(new UIImage(Assets.shield_icon, 7, 465, 24, 30));
		uiManager.addObject(new UIImage(Assets.heart_icon, 5, 425, 30, 30));
	}

	@Override
	public void tick() {
		// If the inventory isn't active and the escape key is pressed then toggle pause
		if(!world.getEntityManager().getPlayer().getInventory().isActive() && handler.getKeyManager().keyJustPressed(KeyEvent.VK_ESCAPE)) 
			paused = !paused;
		
		if(!paused) world.tick();
		
		uiManager.tick();
		equipedWeaponUI.setTexture(world.getEntityManager().getPlayer().getInventory().getEquippedWeapon().getTexture());
	}

	@Override
	public void render(Graphics g) {
		world.render(g);
		uiManager.render(g);
		
		// Draw mana cost
		Weapon wpn = (Weapon) world.getEntityManager().getPlayer().getInventory().getEquippedWeapon();
		Text.drawString(g, Integer.toString(wpn.getManaCost()), 895, 521, true, Color.white, Assets.font24);
		// Draw player helath bars
		world.getEntityManager().getPlayer().renderHealthBars(g);
		
		
		
		if(paused) g.drawImage(Assets.inventoryScreen, 100, 100, null);
		
		
		
	}

}
