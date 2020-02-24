package game.gfx;
import game.Handler;
import game.entities.Entity;
import game.tiles.Tile;

public class GameCamera {
	
	private Handler handler;
	private float xOffset, yOffset; // The offset of each tile rendering (example: 0,0 -> -10,-10)
	
	
	public GameCamera(Handler handler, float xOffset, float yOffset) {
		this.handler = handler;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
	
	
	public void removeBlankSpace() {
		// The x axis
		if (xOffset < 0) xOffset = 0;
		else if (xOffset > handler.getWorld().getWidth() * Tile.TILE_WIDTH - handler.getWidth())
			xOffset = handler.getWorld().getWidth() * Tile.TILE_WIDTH - handler.getWidth() ;
		
		// The y axis
		if (yOffset < 0) yOffset = 0;
		else if (yOffset > handler.getWorld().getHeight() * Tile.TILE_HEIGHT - handler.getHeight())
			yOffset = handler.getWorld().getHeight() * Tile.TILE_HEIGHT - handler.getHeight() ;
	}
	
	
	
	public void move(float xAmount, float yAmount) {
		//Move the camera
		xOffset += xAmount;
		yOffset += yAmount;
		removeBlankSpace();
	}
	
	
	public void centerOnEntity(Entity e){
		// Making the entity centered on the screen (not on the edge)
		xOffset = e.getX() - handler.getWidth() / 2 +  e.getWidth() / 2;  
		yOffset = e.getY() - handler.getHeight() / 2 + e.getHeight() / 2;
		removeBlankSpace();		
	}
	
	
	
	
	//GETTERS & SETTERS
	public float getxOffset() {
		return xOffset;
	}


	public void setxOffset(float xOffset) {
		this.xOffset = xOffset;
	}


	public float getyOffset() {
		return yOffset;
	}


	public void setyOffset(float yOffset) {
		this.yOffset = yOffset;
	}

}
