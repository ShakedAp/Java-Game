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
		world = new World(handler, "res/worlds/world1.txt");
		handler.setWorld(world);		
		
		// UI
		
		uiManager.addObject(equipedWeaponUI);
		uiManager.addObject(new UIImage(Assets.choosen, 832, 412, 128, 128));
		uiManager.addObject(new UIImage(Assets.mana_display, 832 + 48, 412 + 48 + 49, 30, 30));
		uiManager.addObject(pauseButton);
		
		uiManager.addObject(new UIImage(Assets.shield_icon, 7, 465, 24, 30));
		uiManager.addObject(new UIImage(Assets.heart_icon, 5, 425, 30, 30));
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
		
		Weapon wpn = (Weapon) world.getEntityManager().getPlayer().getInventory().getEquippedWeapon();
		Text.drawString(g, Integer.toString(wpn.getManaCost()), 895, 521, true, Color.white, Assets.font24);
		
		
		//TEST PLAYER HEALTH, SHIELD, MANA BAR
		int barWidth = 150, barHeight = 32;
		int barX = 40;
		
		g.setColor(Color.RED); // Health
		g.fillRect(barX+2, 422, barWidth / world.getEntityManager().getPlayer().getMaxHealth() * world.getEntityManager().getPlayer().getHealth() + 2, barHeight);
		Text.drawString(g, world.getEntityManager().getPlayer().getHealth() + "/" + world.getEntityManager().getPlayer().getMaxHealth(), barX+77, 437, true, Color.WHITE, Assets.font28);
		g.drawImage(Assets.bar, barX, 420, null);
		
		g.setColor(Color.GRAY); //Shield
		g.fillRect(barX+2, 462, barWidth / world.getEntityManager().getPlayer().getMaxShield() * world.getEntityManager().getPlayer().getShield() +2, barHeight);
		Text.drawString(g, world.getEntityManager().getPlayer().getShield() + "/" + world.getEntityManager().getPlayer().getMaxShield(), barX+77, 479, true, Color.WHITE, Assets.font28);
		g.drawImage(Assets.bar, barX, 460, null);
		
		g.setColor(Color.CYAN); // Mana
		g.fillRect(barX+2, 502, barWidth * world.getEntityManager().getPlayer().getMana() / world.getEntityManager().getPlayer().getMaxMana() + 2 , barHeight);
		Text.drawString(g, world.getEntityManager().getPlayer().getMana() + "/" + world.getEntityManager().getPlayer().getMaxMana(), barX+77, 519, true, Color.WHITE, Assets.font28);
		g.drawImage(Assets.bar, barX, 500, null);
	}

}
