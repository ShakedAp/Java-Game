package game.states;

import java.awt.Color;
import java.awt.Graphics;

import game.Handler;
import game.gfx.Assets;
import game.ui.UIImage;
import game.ui.UIManager;
import game.worlds.World;

public class GameState extends State {

	private World world;

	private UIImage equipedWeaponUI = new UIImage(null, 832, 412, 128, 128);
	private UIImage choose = new UIImage(Assets.choose, 832, 412, 128, 128);

	public GameState(Handler handler) {
		super(handler, new UIManager(handler));
		// Importing the world
		world = new World(handler, "res/worlds/world1.txt");
		handler.setWorld(world);
		// UI
		handler.getMouseManager().setUiManager(uiManager);
		uiManager.addObject(equipedWeaponUI);
		uiManager.addObject(choose);
	}

	@Override
	public void tick() {
		world.tick();
		uiManager.tick();

		equipedWeaponUI.setTexture(world.getEntityManager().getPlayer().getInventory().getEquipedWeapon().getTexture());
	}

	@Override
	public void render(Graphics g) {
		world.render(g);
		uiManager.render(g);

		
		//TEST PLAYER HEALTH, SHIELD, MANA BAR
		g.setColor(Color.black);
		g.fillRect(10, 20, 100, 30); // Shield
		g.fillRect(10, 60, 100, 30); // Health
		g.fillRect(10, 100, 100, 30); // Mana

		g.setColor(Color.GRAY); //Shield
		g.fillRect(12, 22, 100 / world.getEntityManager().getPlayer().getMaxShield() * world.getEntityManager().getPlayer().getShield(), 26);

		g.setColor(Color.RED); // Health
		g.fillRect(12, 62, 100 / world.getEntityManager().getPlayer().getMaxHealth() * world.getEntityManager().getPlayer().getHealth() - 4, 26);
		
		g.setColor(Color.CYAN); // Mana
		g.fillRect(12, 102, 100 * world.getEntityManager().getPlayer().getMana() / world.getEntityManager().getPlayer().getMaxMana()- 4, 26);
	}

}
