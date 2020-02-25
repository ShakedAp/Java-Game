package game.entities.creatures;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import game.Handler;
import game.gfx.Animation;
import game.gfx.Assets;
import game.inventory.Inventory;
import game.items.Item;
import game.items.Weapon;
import game.ui.UIImageButton;
import game.ui.UIObject;

public class Player extends Creature {

	// Player animations
	private Animation animDown, animUp, animLeft, animRight;
	private int animSpeed = 100;

	// Health
	private int maxHealth = 6, maxShield = 5 , maxMana = 200;
	private int shield = 5, mana = 200;

	private Inventory inventory;

	public Player(Handler handler, float x, float y) {
		super(handler, x, y, 128, 128);

		health = 6;
		
		// Bounds
		bounds.x = 43;
		bounds.y = 43;
		bounds.width = 42;
		bounds.height = 50;

		// Animations
		animDown = new Animation(animSpeed, Assets.player_down);
		animUp = new Animation(animSpeed, Assets.player_up);
		animLeft = new Animation(animSpeed, Assets.player_left);
		animRight = new Animation(animSpeed, Assets.player_right);

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
		if (!inventory.isActive()) { // If the inventory is active we dont want to move
			getInput();
			move();
		}
		handler.getGameCamera().centerOnEntity(this);

		// Animations
		animDown.update();
		animUp.update();
		animLeft.update();
		animRight.update();
		// Attack
		checkShooting();

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

	private long lastRangedAttackTimer, rangedAttackCooldown = 100, rangedAttackTimer = rangedAttackCooldown;

	private void checkShooting() {
		Item a = (Weapon) inventory.getEquipedWeapon();
		Weapon b = null;
		b = (Weapon) a;
		rangedAttackCooldown = (long) (1000 / b.getBps());

		// Attack timer
		rangedAttackTimer += System.currentTimeMillis() - lastRangedAttackTimer;
		lastRangedAttackTimer = System.currentTimeMillis(); // = The current time (in miliseconds)

		// Allow us to shoot only when the cooldown has reached and the inventory is not open
		if (rangedAttackTimer < rangedAttackCooldown || inventory.isActive())
			return;
		
		for(UIObject o: handler.getGame().gameState.getUiManager().getObjects())
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

		if (handler.getMouseManager().isLeftPressed()) {
			b.shoot(handler, x + bounds.x + 10, y + bounds.y + 10, dir);
		} else
			return;

		// Restarting the timer
		rangedAttackTimer = 0;
	}

	@Override
	public void die() {
		System.out.println("GAME OVER");
	}	
	
	private BufferedImage getCurrentAnimationFrame() {
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

	@Override
	public void hurt(int amount) {
		if (shield > 0) shield -= amount;
		else health -= amount;

		if (shield < 0) {
			health += shield;
			shield = 0;
		}

		if (health <= 0) {
			active = false;
			die();
		}
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

}
