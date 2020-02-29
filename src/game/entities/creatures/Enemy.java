package game.entities.creatures;

import game.Handler;

public abstract class Enemy extends Creature{

	private boolean frozen = false;
	
	public Enemy(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
	}

	
	//GETTERS & SETTERS
	public boolean isFrozen() {
		return frozen;
	}

	public void setFrozen(boolean frozen) {
		this.frozen = frozen;
	}
	
	@Override
	public void hurt(int amount){
		if(frozen) return;
		
		health -= amount;
		if(health <= 0) {
			active = false;
			die();
		}
	}

}
