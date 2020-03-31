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
import game.tiles.wallTiles.BottomLeftWallTile;
import game.tiles.wallTiles.BottomRightWallTile;
import game.tiles.wallTiles.BottomWallTile;
import game.tiles.wallTiles.LeftWallTile;
import game.tiles.wallTiles.RightWallTile;
import game.tiles.wallTiles.TopLeftWallTile;
import game.tiles.wallTiles.TopRightWallTile;
import game.tiles.wallTiles.TopWallTile;

public class Tile {
	
	
	//STATIC STUFF
	
	public static Tile[] tiles = new Tile[256];
	//The tiles
	public static Tile voidTile = new VoidTile(0);
	public static Tile untexturedTile = new UntexturedTile(1);
	public static Tile rockTile = new RockTile(3);
	public static Tile wallTile = new WallTile(4);
	//floor
	public static Tile middleFloorTile = new MiddleFloorTile(5);
	public static Tile topLeftFloorTile = new TopLeftFloorTile(6);
	public static Tile topRightFloorTile = new TopRightFloorTile(7);
	public static Tile bottomRightFloorTile = new BottomRightFloorTile(8);
	public static Tile bottomLeftFloorTile = new BottomLeftFloorTile(9);
	
	public static Tile topFloorTile = new TopFloorTile(10);
	public static Tile rightFloorTile = new RightFloorTile(11);
	public static Tile bottomFloorTile = new BottomFloorTile(12);
	public static Tile leftFloorTile = new LeftFloorTile(13);
	//wall
	public static Tile topLeftWallTile = new TopLeftWallTile(14);
	public static Tile topRightWallTile = new TopRightWallTile(15);
	public static Tile bottomRightWallTile = new BottomRightWallTile(16);
	public static Tile bottomLeftWallTile = new BottomLeftWallTile(17);
	
	public static Tile topWallTile = new TopWallTile(18);
	public static Tile rightWallTile = new RightWallTile(19);
	public static Tile bottomWallTile = new BottomWallTile(20);
	public static Tile leftWallTile = new LeftWallTile(21);
	
	
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
