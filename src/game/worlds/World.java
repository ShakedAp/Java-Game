package game.worlds;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import game.Handler;
import game.entities.Entity;
import game.entities.EntityManager;
import game.entities.creatures.Player;
import game.gfx.Assets;
import game.items.ItemManager;
import game.tiles.Tile;
import game.ui.ClickListener;
import game.ui.UIImageButton;
import game.ui.UIImageToggleButton;
import game.utils.Utils;

public class World {
	
	private Handler handler;
	private int width, height; //width and height of the world (by tiles)
	private int spawnX, spawnY; //spawning cords
	private int [][] tiles; //the tile array of the tiles in the map
	private boolean paused = false;
	
	// UI
	private UIImageToggleButton pauseButton = new UIImageToggleButton(10, 10, 64, 64, Assets.btn_pause, new ClickListener() {
		@Override
		public void onClick() {
			if(!paused) {
				handler.getCurrentState().getUiManager().addObject(resumeButton);
				handler.getCurrentState().getUiManager().addObject(exitButton);
				handler.getCurrentState().getUiManager().addObject(sfxToggle);
				handler.getCurrentState().getUiManager().addObject(musicToggle);
			}
			else{
				handler.getCurrentState().getUiManager().removeObject(resumeButton);
				handler.getCurrentState().getUiManager().removeObject(exitButton);
				handler.getCurrentState().getUiManager().removeObject(sfxToggle);
				handler.getCurrentState().getUiManager().removeObject(musicToggle);
			}
			paused = !paused;	
		}});
	
	private UIImageButton resumeButton = new UIImageButton(100, 100, 150, 64, Assets.btn_start, new ClickListener() {
		@Override
		public void onClick() {
			handler.getCurrentState().getUiManager().removeObject(resumeButton);
			handler.getCurrentState().getUiManager().removeObject(exitButton);
			handler.getCurrentState().getUiManager().removeObject(sfxToggle);
			handler.getCurrentState().getUiManager().removeObject(musicToggle);
			paused = !paused;
			pauseButton.setToggled(true);
		}});
	
	private UIImageToggleButton sfxToggle = new UIImageToggleButton(600, 150 , 64, 64, Assets.btn_toggle, new ClickListener(){ 
		@Override
		public void onClick() {
			handler.getGame().setSfxOn(!handler.getGame().isSfxOn());
		}
	});
		
	private UIImageToggleButton musicToggle = new UIImageToggleButton(600, 250 , 64, 64, Assets.btn_toggle, new ClickListener(){ 
		@Override
		public void onClick() {
			handler.getGame().setMusicOn(!handler.getGame().isMusicOn());
		}
	});
	
	private UIImageButton exitButton = new UIImageButton(100, 200, 150, 64, Assets.btn_start, new ClickListener() {
		@Override
		public void onClick() {
			handler.getGame().close();
		}});
	
	// Entities
	private EntityManager entityManager;
	
	// Items
	private ItemManager itemManager;
	
	// Sections
	private SectionManager sectionManager;
	private MiniMap miniMap;
	
	public World(Handler handler, String path, int sectionX, int sectionY) {
		this.handler = handler;
		
		entityManager = new EntityManager(handler, new Player(handler, 0, 0));
		itemManager = new ItemManager(handler);
		sectionManager = new SectionManager(handler, sectionX, sectionY);
		
		loadWorld(path);
		
		// Spawning the player
		entityManager.getPlayer().setX(spawnX);
		entityManager.getPlayer().setY(spawnY);
		
		miniMap = new MiniMap(handler, sectionManager);
	}
	
	public void tick(){
		if(!entityManager.getPlayer().getInventory().isActive() && handler.getKeyManager().keyJustPressed(KeyEvent.VK_ESCAPE)) {
			if(!paused) {
				handler.getCurrentState().getUiManager().addObject(resumeButton);
				handler.getCurrentState().getUiManager().addObject(exitButton);
				handler.getCurrentState().getUiManager().addObject(sfxToggle);
				handler.getCurrentState().getUiManager().addObject(musicToggle);
			}
			else{
				handler.getCurrentState().getUiManager().removeObject(resumeButton);
				handler.getCurrentState().getUiManager().removeObject(exitButton);
				handler.getCurrentState().getUiManager().removeObject(sfxToggle);
				handler.getCurrentState().getUiManager().removeObject(musicToggle);
			}	
			pauseButton.setToggled(paused);
			paused = !paused;
		}
		
		sfxToggle.setToggled(handler.getGame().isSfxOn());
		musicToggle.setToggled(handler.getGame().isMusicOn());
		
		if(paused) return;
		
		itemManager.tick();
		entityManager.tick();
		sectionManager.tick();
	}
	
	public void render(Graphics g) {
		// Making the rendering more efficient by rendering only what we can see
		int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILE_WIDTH); //if we get a negative tile it will return 0.
		int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILE_WIDTH + 1);
		int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILE_HEIGHT);
		int yEnd = (int) Math.min(height, (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILE_HEIGHT + 1);;
		
		for(int y = yStart; y < yEnd; y++) {
			for(int x = xStart; x < xEnd; x++) {
				getTile(x, y).render(g, (int) (x * Tile.TILE_WIDTH - handler.getGameCamera().getxOffset()),
						(int) (y * Tile.TILE_HEIGHT - handler.getGameCamera().getyOffset()));
			}
		}
		
		
		
		entityManager.render(g);
		itemManager.render(g);
		entityManager.getPlayer().postRender(g);
		sectionManager.render(g);
		miniMap.render(g);
	}
	
	
	
	public Tile getTile(int x, int y) {
		if(x < 0 || y < 0 || x >= width || y>= height) // If we are out of the map size
			return Tile.untexturedTile;
		
		
		Tile t = Tile.tiles[tiles[x][y]];
		if (t == null)  return Tile.untexturedTile;
		return t;
	}
	
	private void loadWorld(String path) {
		try {
			String file = Utils.loadFileAsString(path);
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
		catch (Exception e) {

		}
	}
	
	
	public void updateCurrentPlayer(Player player) {
		player.setX(spawnX);
		player.setY(spawnY);
		handler.getWorld().getEntityManager().getPlayer().setVisible(true);
		entityManager.removeEntity(entityManager.getPlayer());
		entityManager.addEntity(player);
		entityManager.setPlayer(player);
		
		for(Entity e : entityManager.getToAdd())
			if(e instanceof Player) {
				entityManager.removeEntityToAdd(e);
				break;
			}
			
	}
	
	
	
	//GETTERS & SETTERS
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

	public SectionManager getSectionManager() {
		return sectionManager;
	}

	public void setSectionManager(SectionManager sectionManager) {
		this.sectionManager = sectionManager;
	}

	public boolean isPaused() {
		return paused;
	}

	public void setPaused(boolean paused) {
		this.paused = paused;
	}

	public UIImageToggleButton getPauseButton() {
		return pauseButton;
	}

	public void setPauseButton(UIImageToggleButton pauseButton) {
		this.pauseButton = pauseButton;
	}

	
}
