package game.states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

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
	private UIImage choose = new UIImage(Assets.choosen, 832, 412, 128, 128);
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
		
		// If the inventory isn't active and the escape is pressed then toggle pause
		if(!world.getEntityManager().getPlayer().getInventory().isActive() && handler.getKeyManager().keyJustPressed(KeyEvent.VK_ESCAPE)) 
			paused = !paused;
		
		if(!paused) 
			world.tick();
		
		uiManager.tick();
		equipedWeaponUI.setTexture(world.getEntityManager().getPlayer().getInventory().getEquippedWeapon().getTexture());
	}

	@Override
	public void render(Graphics g) {
		world.render(g);
		uiManager.render(g);

		
		//TEST PLAYER HEALTH, SHIELD, MANA BAR
		int barWidth = 150, barHeight = 32;
		
		g.setColor(Color.RED); // Health
		g.fillRect(12, 422, barWidth / world.getEntityManager().getPlayer().getMaxHealth() * world.getEntityManager().getPlayer().getHealth() + 2, barHeight);
		
		Text.drawString(g, 
				world.getEntityManager().getPlayer().getHealth() + "/" + world.getEntityManager().getPlayer().getMaxHealth()
				, 87, 437, true, Color.WHITE, Assets.font28);
		g.drawImage(Assets.bar, 10, 420, null);
		
		g.setColor(Color.GRAY); //Shield
		g.fillRect(12, 462, barWidth / world.getEntityManager().getPlayer().getMaxShield() * world.getEntityManager().getPlayer().getShield() +2, barHeight);
		Text.drawString(g, 
				world.getEntityManager().getPlayer().getShield() + "/" + world.getEntityManager().getPlayer().getMaxShield()
				, 87, 479, true, Color.WHITE, Assets.font28);
		g.drawImage(Assets.bar, 10, 460, null);
		
		g.setColor(Color.CYAN); // Mana
		g.fillRect(12, 502, barWidth * world.getEntityManager().getPlayer().getMana() / world.getEntityManager().getPlayer().getMaxMana() + 2 , barHeight);
		Text.drawString(g, 
				world.getEntityManager().getPlayer().getMana() + "/" + world.getEntityManager().getPlayer().getMaxMana()
				, 87, 519, true, Color.WHITE, Assets.font28);
		g.drawImage(Assets.bar, 10, 500, null);
	}

}
