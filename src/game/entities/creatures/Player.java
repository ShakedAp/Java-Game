package game.entities.creatures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import game.Handler;
import game.entities.Entity;
import game.entities.projectiles.RegularBullet;
import game.gfx.Animation;
import game.gfx.Assets;

public class Player extends Creature {

	//Animations
	private Animation animDown;
	private Animation animUp;
	private Animation animLeft;
	private Animation animRight;
	private Animation animIdle;
	private int animSpeed = 500;
	//Attack timers
	private long lastMeleeAttackTimer, meleeAttackCooldown = 400, meleeAttackTimer = meleeAttackCooldown; 
	private long lastGunAttackTimer, gunAttackCooldown = 400, gunAttackTimer = gunAttackCooldown; 
	
	
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
		
	}
	
	@Override
	public void render(Graphics g) {
		g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()), 
				(int) (y - handler.getGameCamera().getyOffset()), width, height, null); 
		//rendering the player with the offset (camera)
		
		
		//Collision box
//		g.setColor(Color.blue);
//		g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()),
//				(int) (y + bounds.y - handler.getGameCamera().getyOffset()),
//				bounds.width, bounds.height);
	}

	@Override
	public void tick() {
		getInput();
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
	}
	
	private void checkGunAttacks() {
		//mouse X and Y
		float mx = handler.getMouseManager().getMouseX() - handler.getGameCamera().getxOffset();
		float my = handler.getMouseManager().getMouseY() - handler.getGameCamera().getyOffset();
			
		gunAttackTimer += System.currentTimeMillis() - lastGunAttackTimer; //gun cooldown
		lastGunAttackTimer = System.currentTimeMillis();
		if(gunAttackTimer < gunAttackCooldown)
			return;
		
		
		if(handler.getMouseManager().isLeftPressed()) //if left button is pressed -> get mouse position and shoot
			handler.getWorld().getEntityManager().addEntity(new RegularBullet(handler, x,y,mx,my, 30));
		else
			return;
		gunAttackTimer = 0;
		
		
		
	}
	
	
	private void checkMeleeAttacks() {
		//Attack cooldown
		meleeAttackTimer += System.currentTimeMillis() - lastMeleeAttackTimer;
		lastMeleeAttackTimer = System.currentTimeMillis();
		if(meleeAttackTimer < meleeAttackCooldown) //if we are still in the cooldown -> exit
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
			return; //if the player is not attacking exit
		
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
	
}
