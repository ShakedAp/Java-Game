package game.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile {
	
	
	//STATIC STUFF (sorry Anna)
	
	public static Tile[] tiles = new Tile[256];
	//The tiles
	public static Tile voidTile = new VoidTile(0);
	public static Tile untexturedTile = new UntexturedTile(1);
	public static Tile regularTile = new RegularTile(2);
	public static Tile rockTile = new RockTile(3);
	public static Tile wallTile = new WallTile(4);
	
	
	//CLASS
	protected BufferedImage texture;
	protected final int id;
	
	public static final int TILE_WIDTH = 64,
							TILE_HEIGHT = 64;

	public Tile(BufferedImage texture, int id) {
		this.texture = texture;
		this.id = id;	
		
		tiles[id] = this;
	}
	
	
	public void tick() {
		
	}
	
	public void render(Graphics g, int x, int y) {
		g.drawImage(texture, x, y, TILE_WIDTH, TILE_HEIGHT, null);
	}
	
	public boolean isSolid(){
		return false;
	}
	
	//GETTERS & SETTERS
	public int getId() {
		return id;
	}
	
	
}
