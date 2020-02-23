package game.states;

import java.awt.Color;
import java.awt.Graphics;

import game.Handler;
import game.gfx.Assets;
import game.gfx.Text;
import game.ui.ClickListener;
import game.ui.UIImage;
import game.ui.UIImageButton;
import game.ui.UIManager;
import game.worlds.World;

public class GameState extends State {

	private World world;
	private boolean paused = false;

	private UIImage equipedWeaponUI = new UIImage(null, 832, 412, 128, 128);
	private UIImage choose = new UIImage(Assets.choose, 832, 412, 128, 128);
	private UIImageButton pauseButton = new UIImageButton(10, 10, 80, 64, Assets.btn_pause, new ClickListener() {
		@Override
		public void onClick() {
			paused = !paused;
		}});

	public GameState(Handler handler) {
		super(handler, new UIManager(handler));
		// Importing the world
		world = new World(handler, "res/worlds/world1.txt");
		handler.setWorld(world);
		// UI
		
		uiManager.addObject(equipedWeaponUI);
		uiManager.addObject(choose);
		uiManager.addObject(pauseButton);
	}

	@Override
	public void tick() {
		handler.getMouseManager().setUiManager(uiManager);
		if(!paused)
			world.tick();
		
		uiManager.tick();

		equipedWeaponUI.setTexture(world.getEntityManager().getPlayer().getInventory().getEquipedWeapon().getTexture());
	}

	@Override
	public void render(Graphics g) {
		world.render(g);
		uiManager.render(g);

		
		//TEST PLAYER HEALTH, SHIELD, MANA BAR
		int barWidth = 150, barHeight = 30;
		
		g.setColor(Color.black);
		g.fillRect(10, 420, barWidth, barHeight); // Health
		g.fillRect(10, 460, barWidth, barHeight); // Shield 
		g.fillRect(10, 500, barWidth, barHeight); // Mana

		
		g.setColor(Color.RED); // Health
		g.fillRect(12, 422, barWidth / world.getEntityManager().getPlayer().getMaxHealth() * world.getEntityManager().getPlayer().getHealth() - 4, 26);
		Text.drawString(g, 
				world.getEntityManager().getPlayer().getHealth() + "/" + world.getEntityManager().getPlayer().getMaxHealth()
				, 85, 435, true, Color.WHITE, Assets.font28);
		
		
		g.setColor(Color.GRAY); //Shield
		g.fillRect(12, 462, barWidth / world.getEntityManager().getPlayer().getMaxShield() * world.getEntityManager().getPlayer().getShield() - 4, 26);
		Text.drawString(g, 
				world.getEntityManager().getPlayer().getShield() + "/" + world.getEntityManager().getPlayer().getMaxShield()
				, 85, 475, true, Color.WHITE, Assets.font28);
		
		
		g.setColor(Color.CYAN); // Mana
		g.fillRect(12, 502, barWidth * world.getEntityManager().getPlayer().getMana() / world.getEntityManager().getPlayer().getMaxMana() - 4, 26);
		Text.drawString(g, 
				world.getEntityManager().getPlayer().getMana() + "/" + world.getEntityManager().getPlayer().getMaxMana()
				, 85, 515, true, Color.WHITE, Assets.font28);
	}

}
