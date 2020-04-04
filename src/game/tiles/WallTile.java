package game.tiles;

import game.gfx.Assets;

public class WallTile extends Tile {

	private boolean triggered = false;
	
	public WallTile(int id) {
		super(Assets.wallTile, id);
	}

	@Override
	public boolean isSolid(){
		if(triggered) 
			return true;
		else
			return false;
	}

	
	//GETTERS & SETTERS
	public boolean isTriggered() {
		return triggered;
	}

	public void setTriggered(boolean triggered) {
		this.triggered = triggered;
	}
	
}
