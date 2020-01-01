package game.entities.creatures;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import game.Handler;
import game.gfx.Animation;
import game.gfx.Assets;
import game.utils.Utils;

public class Player extends Creature {

	//Animations
	
	private Animation animDown;
	private Animation animUp;
	private Animation animLeft;
	private Animation animRight;
	private Animation animIdle;
	private int animSpeed = 500;
	
	
	
	
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
	public void tick() {
		getInput();
		move();
		handler.getGameCamera().centerOnEntity(this); //making the camera center on the player
		
		animDown.update();
		animUp.update();
		animLeft.update();
		animRight.update();
		animIdle.update();
		
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
