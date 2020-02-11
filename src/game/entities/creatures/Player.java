package game.entities.creatures;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import game.Handler;
import game.entities.Entity;
import game.entities.projectiles.RegularBullet;
import game.gfx.Animation;
import game.gfx.Assets;
import game.inventory.Inventory;

public class Player extends Creature {

	//Animations
	private Animation animDown, animUp, animLeft, animRight, animIdle;
	private int animSpeed = 500;
	
	private Inventory inventory;
	
	//Attack timers
	private long lastMeleeAttackTimer, meleeAttackCooldown = 100, meleeAttackTimer = meleeAttackCooldown; 

	private long lastGunAttackTimer, gunAttackCooldown = 200, gunAttackTimer = gunAttackCooldown; 
	
	public Player(Handler handler, float x, float y) {
		super(handler, x, y, Creature.DEFAULT_CEATURE_WIDTH, Creature.DEFAULT_CEATURE_HEIGHT);
		
		//the player bounds
		bounds.x = 22;
		bounds.y = 30;
		bounds.width = 19;
		bounds.height = 33;
		
		//animations
		animDown = new Animation(animSpeed, Assets.player_down);
		animUp = new Animation(animSpeed, Assets.player_up);
		animLeft = new Animation(animSpeed, Assets.player_left);
		animRight = new Animation(animSpeed, Assets.player_right);
		animIdle = new Animation(animSpeed, Assets.player_idle);
		
		inventory = new Inventory(handler);
	}
	
	@Override
	public void render(Graphics g) {
		
		if(inventory.isActive()) {
			g.drawImage(animIdle.getCurrentFrame(), (int) (x - handler.getGameCamera().getxOffset()), 
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
		animIdle.update();
		//Attack
		checkMeleeAttacks();
		checkGunAttacks();
		
		inventory.tick();
	}
	
	private void checkGunAttacks() {
		//mouse X and Y
		float mx = handler.getMouseManager().getMouseX() + handler.getGameCamera().getxOffset();
		float my = handler.getMouseManager().getMouseY() + handler.getGameCamera().getyOffset();
		
		gunAttackTimer += System.currentTimeMillis() - lastGunAttackTimer; //gun cooldown
		lastGunAttackTimer = System.currentTimeMillis();
		if(gunAttackTimer < gunAttackCooldown)
			return;

		if(handler.getMouseManager().isLeftPressed()) //if left button is pressed -> get mouse position and shoot
			handler.getWorld().getEntityManager().addEntity(new RegularBullet(handler, x + bounds.x + bounds.height/2,y + bounds.y + bounds.width/2,mx,my, this));
		else
			return;
		gunAttackTimer = 0; //reset the cooldown
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
		else //the default animation (should be idle)
			return animIdle.getCurrentFrame();
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
	
}
