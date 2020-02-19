package game.entities.creatures;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import game.Handler;
import game.entities.Entity;
import game.entities.projectiles.LightBullet;
import game.entities.projectiles.Projectile;
import game.gfx.Animation;
import game.gfx.Assets;
import game.inventory.Inventory;
import game.items.Item;
import game.items.Weapon;

public class Player extends Creature {

	//Animations
	private Animation animDown, animUp, animLeft, animRight;
	private int animSpeed = 100;
	
	private Inventory inventory;
	
	//Attack timers
	private long lastMeleeAttackTimer, meleeAttackCooldown = 100, meleeAttackTimer = meleeAttackCooldown; 
	private long lastRangedAttackTimer, rangedAttackCooldown = 100, rangedAttackTimer = rangedAttackCooldown; 

	public Player(Handler handler, float x, float y) {
		super(handler, x, y, 128, 128);
		
		//the player bounds
		bounds.x = 43;
		bounds.y = 43;
		bounds.width = 42;
		bounds.height = 50;
		
		//animations
		animDown = new Animation(animSpeed, Assets.player_down);
		animUp = new Animation(animSpeed, Assets.player_up);
		animLeft = new Animation(animSpeed, Assets.player_left);
		animRight = new Animation(animSpeed, Assets.player_right);
		
		inventory = new Inventory(handler);
	}
	
	@Override
	public void render(Graphics g) {
		if(inventory.isActive()) {
			g.drawImage(Assets.player_idle, (int) (x - handler.getGameCamera().getxOffset()), 
					(int) (y - handler.getGameCamera().getyOffset()), width, height, null); 
			return;
		}
		
		g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()), 
				(int) (y - handler.getGameCamera().getyOffset()), width, height, null); 
	}
	
	public void postRender(Graphics g) {
		inventory.render(g);
	}
	
	
	@Override
	public void tick() {
		getInput();
		
		if(!inventory.isActive())
			move();
		
		handler.getGameCamera().centerOnEntity(this); //making the camera center on the player
		//Animations
		animDown.update(); 
		animUp.update();
		animLeft.update();
		animRight.update();
		//Attack
		checkMeleeAttacks();
		checkShooting();
		
		inventory.tick();
		
	}
	
	private void checkShooting() {
		Item a = (Weapon) inventory.getEquipedWeapon();
		Weapon b = null;
		b = (Weapon) a;
		rangedAttackCooldown = 1000 / b.getBps();
	
		
		rangedAttackTimer += System.currentTimeMillis() - lastRangedAttackTimer;
		lastRangedAttackTimer = System.currentTimeMillis();
		
		if(rangedAttackTimer < rangedAttackCooldown) return;
		
		if(inventory.isActive()) return;
			
		double dx = handler.getMouseManager().getMouseX() - handler.getWidth() / 2;
		double dy =  handler.getMouseManager().getMouseY() - handler.getHeight() /2 ;
		double dir = Math.atan2(dy, dx); //getting the angle


		if(handler.getMouseManager().isLeftPressed()) {
			b.shoot(handler,x + bounds.x + 10, y + bounds.y + 10, dir);
		 } else
			 return;
		
		rangedAttackTimer = 0;
	}

	private void checkMeleeAttacks() {
		//Attack cooldown
		meleeAttackTimer += System.currentTimeMillis() - lastMeleeAttackTimer;
		lastMeleeAttackTimer = System.currentTimeMillis();
		if(meleeAttackTimer < meleeAttackCooldown) //if we are still in the cooldown -> exit
			return;
		if(inventory.isActive())
			return;
		
		
		Rectangle cb = getCollisonBounds(0,0);
		Rectangle attackRect = new Rectangle();
		int attackSize = 20; //the size of the attack ("near the player bounds)
		attackRect.width = attackSize;
		attackRect.height = attackSize;
		
		if(handler.getKeyManager().aUp) { //the direction the player is attacking (or facing in order to attack)
			attackRect.x = cb.x + cb.width/2 - attackSize/2;
			attackRect.y = cb.y - attackSize;
		} else if(handler.getKeyManager().aDown) {
			attackRect.x = cb.x + cb.width/2 - attackSize/2;
			attackRect.y = cb.y + cb.height;
		} else if(handler.getKeyManager().aLeft) {
			attackRect.x = cb.x - attackSize;
			attackRect.y = cb.y + cb.height/2 - attackSize/2;	
		}else if(handler.getKeyManager().aRight) {
			attackRect.x = cb.x + cb.width;
			attackRect.y = cb.y + cb.height/2 - attackSize/2;	
		} else 
			return; //if the player is not attacking -> exit
		
		meleeAttackTimer = 0; //restarting the cooldown
		
		for (Entity e : handler.getWorld().getEntityManager().getEntities()){
			if(e.equals(this)) //to make sure we are not hurting ourselves
				continue;
			if(e.getCollisonBounds(0, 0).intersects(attackRect)) { 
				e.hurt(1);
				return;
				}
		}
	}
	
	
	
	@Override
	public void die() {
		System.out.println("GAME OVER");
	}
	
	private void getInput() {
		xMove = 0;
		yMove = 0;
		
		//moving using keyboard
		if (handler.getKeyManager().up == true) 
			yMove -= speed; //move up
		if (handler.getKeyManager().down == true) 
			yMove += speed; //move down 
		if (handler.getKeyManager().left == true) 
			xMove -= speed; //move left
		if (handler.getKeyManager().right == true) 
			xMove += speed; //move right
		
	}
	
	
	
	
	private BufferedImage getCurrentAnimationFrame() {
		if(yMove < 0) //moving up
			return animUp.getCurrentFrame();
		else if(yMove > 0)//moving down
				return animDown.getCurrentFrame();
		else if(xMove < 0) //moving left
			return animLeft.getCurrentFrame();
		else if(xMove > 0) //moving right
			return animRight.getCurrentFrame(); 
		else
			return Assets.player_idle;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
	
}
