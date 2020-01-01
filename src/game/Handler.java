package game;

import game.gfx.GameCamera;
import game.input.KeyManager;
import game.worlds.World;

public class Handler {
	
	private Game game;
	private World world;
	
	public Handler(Game game) {
		this.game = game;
	}
	
	
	
	
	//GETTERS SETTERS
	public KeyManager getKeyManager() {
		return game.getKeyManager();
	}
	
	public GameCamera getGameCamera() {
		return game.getGameCamera();
	}
	
	public int getWidth() {
		return game.getWidth();
	}
	
	public int getHeight() {
		return game.getHeight();
	}

	public Game getGame() {
		return game;
	}


	public void setGame(Game game) {
		this.game = game;
	}


	public World getWorld() {
		return world;
	}


	public void setWorld(World world) {
		this.world = world;
	}
	
}
