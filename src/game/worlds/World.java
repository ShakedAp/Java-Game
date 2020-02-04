package game.worlds;

import java.awt.Graphics;

import game.Handler;
import game.entities.EntityManager;
import game.entities.creatures.Player;
import game.entities.statics.Rock;
import game.entities.statics.Tree;
import game.items.ItemManager;
import game.tiles.Tile;
import game.utils.Utils;

public class World {
	
	private Handler handler;
	private int width, height; //width and height of the world (by tiles)
	private int spawnX, spawnY; //spawning cords
	private int [][] tiles; //the tile array of the tiles in the map
	
	//entities
	private EntityManager entityManager;
	
	//items
	private ItemManager itemManager;
	
	public World(Handler handler, String path) {
		this.handler = handler;
		
		entityManager = new EntityManager(handler, new Player(handler, 100, 100));
		itemManager = new ItemManager(handler);
		
		//Temporary entity code!
		entityManager.addEntity(new Tree(handler, 100, 100));
		entityManager.addEntity(new Rock(handler, 100, 300));
		
		loadWorld(path);
		
		//spawning the player
		entityManager.getPlayer().setX(spawnX);
		entityManager.getPlayer().setY(spawnY);
	}
	
	
	
	public void tick(){
		itemManager.tick();
		entityManager.tick();
	}
	
	public void render(Graphics g) {
		//making the rendering more efficient by rendering only what we can see
		int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILE_WIDTH); //if we get a negative tile it will return 0.
		int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILE_WIDTH + 1);
		int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILE_HEIGHT);
		int yEnd = (int) Math.min(height, (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILE_HEIGHT + 1);;
		
		for(int y = yStart; y < yEnd; y++) {
			for(int x = xStart; x < xEnd; x++) {
				getTile(x, y).render(g, (int) (x * Tile.TILE_WIDTH - handler.getGameCamera().getxOffset()),
						(int) (y * Tile.TILE_HEIGHT - handler.getGameCamera().getyOffset()));
				//rendering the tiles with the camera offset
			}
		}
		
		
		itemManager.render(g);
		entityManager.render(g);
		
	}
	
	
	
	public Tile getTile(int x, int y) {
		if(x < 0 || y < 0 || x >= width || y>= height) //for safety (collisions)
			return Tile.grassTile;
		
		
		
		
		Tile t = Tile.tiles[tiles[x][y]];
		
		if (t == null) { //for safety (default tile)
			return Tile.dirtTile;
		}
		return t; //if not null return the current tile
	}
	
	
	
	
	private void loadWorld(String path) { //loading the world
		String file = Utils.loadFileAsString(path); //loading our world as string
		String[] tokens = file.split("\\s+"); //Splitting each number to his own "space", but without spaces
		
		width = Utils.parseInt(tokens[0]); //setting the width to the first number
		height = Utils.parseInt(tokens[1]);//setting the height to the second number
		spawnX = Utils.parseInt(tokens[2]);//setting the spawnX to the third number
		spawnY = Utils.parseInt(tokens[3]);//setting the spawnY to the forth number
		
		tiles = new int[width][height];
		
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				tiles[x][y] = Utils.parseInt(tokens[(x + y * width)  + 4]); 
			}
		}
	}

	
	
	
	//GETTERS SETTERS
	public EntityManager getEntityManager() {
		return entityManager;
	}


	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}



	public Handler getHandler() {
		return handler;
	}



	public void setHandler(Handler handler) {
		this.handler = handler;
	}



	public ItemManager getItemManager() {
		return itemManager;
	}



	public void setItemManager(ItemManager itemManager) {
		this.itemManager = itemManager;
	}

	
}
