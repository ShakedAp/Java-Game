package game.entities.creatures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import game.Handler;
import game.gfx.Animation;
import game.gfx.Assets;
import game.gfx.Text;
import game.inventory.Inventory;
import game.items.Weapon;
import game.ui.UIImageButton;
import game.ui.UIObject;

public class Player extends Creature {

	// Player animations
	private Animation animDown, animUp, animLeft, animRight;
	private int animSpeed = 100;
	private boolean visible = true;

	// Health
	private int maxHealth = 6, maxShield = 5 , maxMana = 200;
	private int shield = 5, mana = 200;

	private Inventory inventory;

	public Player(Handler handler, float x, float y) {
		super(handler, x, y, 128, 128);

		health = 6;
		speed  = 20;
		speed = 10;
		
		// Bounds
		bounds.x = 43;
		bounds.y = 43;
		bounds.width = 42;
		bounds.height = 50;

		// Animations
		animDown = new Animation(animSpeed, Assets.player_down, true);
		animUp = new Animation(animSpeed, Assets.player_up, true);
		animLeft = new Animation(animSpeed, Assets.player_left, true);
		animRight = new Animation(animSpeed, Assets.player_right, true);

		inventory = new Inventory(handler);
	}

	@Override
	public void render(Graphics g) {
		if (inventory.isActive()) // If the inventory is open, display the player as idle
			g.drawImage(Assets.player_idle, (int) (x - handler.getGameCamera().getxOffset()),
					(int) (y - handler.getGameCamera().getyOffset()), width, height, null);
		else
			g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()),
					(int) (y - handler.getGameCamera().getyOffset()), width, height, null);
	}

	public void postRender(Graphics g) {
		// Anything that we want to render after the player)
		inventory.render(g);
	}

	@Override
	public void tick() {
		if (!inventory.isActive()) {
			getInput();
			move();
		}
		
		if(visible) handler.getGameCamera().centerOnEntity(this);

		// Animations
		animDown.update();
		animUp.update();
		animLeft.update();
		animRight.update();
	
		checkAttacks();
		
		checkShieldRegeneration();
		
		inventory.tick();
	}

	private void getInput() {
		// Each time resting the movement variables so we won't overllap
		xMove = 0;
		yMove = 0;

		// Moving the player using keyboard
		if (handler.getKeyManager().up == true)
			yMove -= speed; // Move up
		if (handler.getKeyManager().down == true)
			yMove += speed; // Move down
		if (handler.getKeyManager().left == true)
			xMove -= speed; // Move left
		if (handler.getKeyManager().right == true)
			xMove += speed; // Move right
	}

	private long lastRegenTimer, regenCooldown = 4000, regenTimer;
	private void checkShieldRegeneration(){	
		if(shield == maxShield) {
			regenTimer = 0;
			lastRegenTimer = System.currentTimeMillis();
			return;
		}
		
		regenTimer += System.currentTimeMillis() - lastRegenTimer;
		lastRegenTimer = System.currentTimeMillis();
		
		if (regenTimer < regenCooldown)
			return;

		if(regenTimer > regenCooldown + 1000) {
			shield++;
			regenTimer = regenCooldown;
		}
		
		
	}
	
	
	private long lastAttackTimer, attackCooldown = 100, attackTimer = attackCooldown;
	private void checkAttacks() {
		Weapon currentEquipedWeapon = (Weapon) inventory.getEquippedWeapon();
		attackCooldown = (long) (1000 / currentEquipedWeapon.getBps());

		// Attack timer
		attackTimer += System.currentTimeMillis() - lastAttackTimer;
		lastAttackTimer = System.currentTimeMillis();

		// Allow us to shoot only when the cooldown has reached and the inventory is not open
		if (attackTimer < attackCooldown || inventory.isActive())
			return;
		
		for(UIObject o: handler.getGame().tutorialState.getUiManager().getObjects())
			if(o.isHovering() && o instanceof UIImageButton)
				return;
		

		// Calculating the angel of the bullet (player to mouse)
		float playerX = x + bounds.x + 10;
		float mouseX = handler.getMouseManager().getMouseX() + handler.getGameCamera().getxOffset();

		float playerY = y + bounds.y + 10;
		float mouseY = handler.getMouseManager().getMouseY() + handler.getGameCamera().getyOffset();

		double dx = mouseX - playerX;
		double dy = mouseY - playerY;
		double dir = Math.atan2(dy, dx);

		if (handler.getMouseManager().isLeftPressed() && mana > 0 && mana-currentEquipedWeapon.getManaCost() >= 0) {
			currentEquipedWeapon.shoot(handler, x + bounds.x + 10, y + bounds.y + 10, dir);
			mana -= currentEquipedWeapon.getManaCost();
		} else
			return;

		// Restarting the timer
		attackTimer = 0;
	}

	@Override
	public void die() {
		System.out.println("GAME OVER");
	}	
	
	@Override
	public void hurt(int amount) {
		if (shield > 0) shield -= amount;
		else health -= amount;

		if (shield < 0) {
			health += shield;
			shield = 0;
		}

		regenTimer = 0;
		
		if (health <= 0) {
			active = false;
			die();
		}
	}
	
	int xOffset = 0;	
	public void renderUI(Graphics g) {
		
		// Zoom move out effect
		if(handler.getGame().getCurrentZoomScale() > 1 && xOffset > -150) 
			xOffset -= 5;
		
		if(handler.getGame().getCurrentZoomScale() == 1 && xOffset < 0) 
			xOffset += 5;
		
		renderHealthBars(g, xOffset);
		renderEquipedUI(g, xOffset);
	}
	
	private void renderHealthBars(Graphics g, int xOffset) {
		int barWidth = 150, barHeight = 32;
		int barX = 40 + xOffset;
		
		g.setColor(Color.RED); // Health
		g.fillRect(barX+2, 422, barWidth / maxHealth * health + 2, barHeight);
		Text.drawString(g, health + "/" + maxHealth, barX+77, 437, true, Color.WHITE, Assets.font28);
		g.drawImage(Assets.bar, barX, 420, null);
		g.drawImage(Assets.heart_icon, 5 + xOffset, 425, 30, 30, null);

		g.setColor(Color.GRAY); //Shield
		g.fillRect(barX+2, 462, barWidth / maxShield * shield +2, barHeight);
		Text.drawString(g, shield + "/" + maxShield, barX+77, 479, true, Color.WHITE, Assets.font28);
		g.drawImage(Assets.bar, barX, 460, null);
		g.drawImage(Assets.shield_icon, 7 + xOffset, 465, 24, 30, null);
		
		g.setColor(Color.CYAN); // Mana
		g.fillRect(barX+2, 502, barWidth * mana / maxMana + 2 , barHeight);
		Text.drawString(g, mana + "/" + maxMana, barX+77, 519, true, Color.WHITE, Assets.font28);
		g.drawImage(Assets.bar, barX, 500, null);
	}
	
	private void renderEquipedUI(Graphics g, int xOffset) {
		g.drawImage(Assets.chosen, 832 + -xOffset, 412, 128, 128, null);
		g.drawImage(inventory.getEquippedWeapon().getTexture(), 832 + -xOffset, 412, 128, 128, null);
		
		// mana cost
		g.drawImage(Assets.mana_display, 880 + -xOffset, 509, 30, 30, null);
		Weapon wpn = (Weapon) inventory.getEquippedWeapon();
		Text.drawString(g, Integer.toString(wpn.getManaCost()), 895 + -xOffset, 521, true, Color.white, Assets.font24);
	}
	
	private BufferedImage getCurrentAnimationFrame() {
		if(!visible)
			return null;
		
		if (yMove < 0) // Moving up
			return animUp.getCurrentFrame();
		else if (yMove > 0)// Moving down
			return animDown.getCurrentFrame();
		else if (xMove < 0) // Moving left
			return animLeft.getCurrentFrame();
		else if (xMove > 0) // Moving right
			return animRight.getCurrentFrame();
		else
			return Assets.player_idle;
	}


	// GETTERS & SETTERS
	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	public int getMaxShield() {
		return maxShield;
	}

	public void setMaxShield(int maxShield) {
		this.maxShield = maxShield;
	}

	public int getShield() {
		return shield;
	}

	public void setShield(int shield) {
		this.shield = shield;
	}

	public int getMaxMana() {
		return maxMana;
	}

	public void setMaxMana(int maxMana) {
		this.maxMana = maxMana;
	}

	public int getMana() {
		return mana;
	}

	public void setMana(int mana) {
		this.mana = mana;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

}
