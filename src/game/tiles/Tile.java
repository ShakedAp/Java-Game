package game.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import game.tiles.floorTiles.BottomFloorTile;
import game.tiles.floorTiles.BottomLeftFloorTile;
import game.tiles.floorTiles.BottomRightFloorTile;
import game.tiles.floorTiles.LeftFloorTile;
import game.tiles.floorTiles.MiddleFloorTile;
import game.tiles.floorTiles.RightFloorTile;
import game.tiles.floorTiles.TopFloorTile;
import game.tiles.floorTiles.TopLeftFloorTile;
import game.tiles.floorTiles.TopRightFloorTile;

public class Tile {
	
	
	//STATIC STUFF
	
	public static Tile[] tiles = new Tile[999];
	//The tiles
	public static Tile voidTile = new VoidTile(0);
	public static Tile untexturedTile = new UntexturedTile(1);
	public static Tile rockTile = new RockTile(3);
	public static Tile wallTile = new WallTile(4);
	
	public static Tile middleFloorTile = new MiddleFloorTile(5);
	public static Tile topLeftFloorTile = new TopLeftFloorTile(6);
	public static Tile topRightFloorTile = new TopRightFloorTile(7);
	public static Tile bottomRightFloorTile = new BottomRightFloorTile(8);
	public static Tile bottomLeftFloorTile = new BottomLeftFloorTile(9);
	
	public static Tile TopFloorTile = new TopFloorTile(10);
	public static Tile rightFloorTile = new RightFloorTile(11);
	public static Tile bottomFloorTile = new BottomFloorTile(12);
	public static Tile leftFloorTile = new LeftFloorTile(13);
	
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
