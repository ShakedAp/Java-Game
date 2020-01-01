package game.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile {
	
	
	//STATIC STUFF (sorry Anna)
	
	public static Tile[] tiles = new Tile[256]; // all of the tiles
	//The tiles
	public static Tile grassTile = new GrassTile(0);
	public static Tile dirtTile = new DirtTile(1);
	public static Tile rockTile = new RockTile(2);
	
	
	
	
	
	
	
	//CLASS
	protected BufferedImage texture; //the texture for the tile
	protected final int id; //every tile have a unique id
	
	public static final int TILE_WIDTH = 64, //default width and height
							TILE_HEIGHT = 64;

	public Tile(BufferedImage texture, int id) {
		this.texture = texture;
		this.id = id;	
		
		tiles[id] = this; //adding the created tile into the array
	}
	
	
	public void tick() {
		
	}
	
	public void render(Graphics g, int x, int y) {
		g.drawImage(texture, x, y, TILE_WIDTH, TILE_HEIGHT, null);
	}
	
	public boolean isSolid(){
		return false;
	}
	
	//getters and setters
	public int getId() {
		return id;
	}
	
	
}
