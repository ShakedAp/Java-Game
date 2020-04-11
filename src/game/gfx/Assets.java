package game.gfx;

import java.awt.Font;
import java.awt.image.BufferedImage;

public class Assets {

	private static final int width = 128, height = 128;

	public static Font font28, font24, font21;

	public static BufferedImage sign, stone, untextured, voidTile, wallTile;
	public static BufferedImage bullet, chosen, bar, shield_icon, heart_icon, mana_display;
	public static BufferedImage bad_pistol, RPG, shotgun, smg, ak47, water_gun;
	public static BufferedImage inventoryScreen, popupInv;
	public static BufferedImage start_menu_background, comic, convBox;

	public static BufferedImage[] rocket;
	public static BufferedImage[] btn_start, btn_settings, btn_menu, btn_pause, btn_toggle;
	public static BufferedImage[] floor_tiles, wall_tiles;
	public static BufferedImage[] chest, portal_spin, portal_open, portal_close;

	public static BufferedImage player_idle;
	public static BufferedImage[] player_down, player_up, player_left, player_right;

	public static void init() {
		
		// Fonts
		font28 = FontLoader.loadFont("res/fonts/slkscr.ttf", 28);
		font24 = FontLoader.loadFont("res/fonts/tiny.ttf", 24);
		font21 = FontLoader.loadFont("res/fonts/smol.ttf", 21);

		// Sprite-Sheets	
		SpriteSheet robotSheet = new SpriteSheet(ImageLoader.loadImage("/textures/robotSheet.png"));
		SpriteSheet gunSheet = new SpriteSheet(ImageLoader.loadImage("/textures/gunSheet.png"));
		SpriteSheet tileSheet = new SpriteSheet(ImageLoader.loadImage("/textures/tileSheet.png"));
		SpriteSheet rocketSheet = new SpriteSheet(ImageLoader.loadImage("/textures/rocket.png"));

		// Ammo
		rocket = new BufferedImage[2];
		rocket[0] = rocketSheet.crop(0, 0, width, height);
		rocket[1] = rocketSheet.crop(0, height, width, height);

		bullet = ImageLoader.loadImage("/textures/bullet.png");

		// Guns
		bad_pistol = gunSheet.crop(0, 0, width, height);
		smg = gunSheet.crop(0, height, width, height);
		shotgun = gunSheet.crop(0, height * 2, width, height);
		RPG = gunSheet.crop(0, height * 3, width, height);
		ak47 = gunSheet.crop(0, height * 4, width, height);
		water_gun = gunSheet.crop(0, height * 5, width, height);

		// UI
		SpriteSheet startSheet = new SpriteSheet(ImageLoader.loadImage("/textures/startMenu/startSheet.png"));
		btn_start = new BufferedImage[2];
		btn_start[0] = startSheet.crop(0, 0, 152, 66);
		btn_start[1] = startSheet.crop(0, 66, 152, 66);

		SpriteSheet settingsSheet = new SpriteSheet(ImageLoader.loadImage("/textures/startMenu/settingsSheet.png"));
		btn_settings = new BufferedImage[2];
		btn_settings[0] = settingsSheet.crop(0, 0, 191, 66);
		btn_settings[1] = settingsSheet.crop(0, 66, 191, 66);
		
		SpriteSheet menuSheet = new SpriteSheet(ImageLoader.loadImage("/textures/startMenu/menuSheet.png"));
		btn_menu = new BufferedImage[2];
		btn_menu[0] = menuSheet.crop(0, 0, 161, 66);
		btn_menu[1] = menuSheet.crop(0, 66, 161, 66);
		
		SpriteSheet toggleSheet = new SpriteSheet(ImageLoader.loadImage("/textures/startMenu/toggleButton.png"));
		btn_toggle = new BufferedImage[2];
		btn_toggle[0] = toggleSheet.crop(0, 0, 115, 52);
		btn_toggle[1] = toggleSheet.crop(0, 52, 115, 52);
		
		comic = ImageLoader.loadImage("/textures/startMenu/comic.png");
		convBox =  ImageLoader.loadImage("/textures/convBox.png");
		
		btn_pause = new BufferedImage[2];
		btn_pause[0] = ImageLoader.loadImage("/textures/pauseButton.png");
		btn_pause[1] = ImageLoader.loadImage("/textures/pauseButton.png");
		
		start_menu_background = ImageLoader.loadImage("/textures/startMenu/startMenuBack.png");

		chosen = ImageLoader.loadImage("/textures/chosen.png");
		mana_display = ImageLoader.loadImage("/textures/manaDisplay.png");
		
		bar = ImageLoader.loadImage("/textures/bar.png");
		shield_icon = ImageLoader.loadImage("/textures/shieldIcon.png");
		heart_icon = ImageLoader.loadImage("/textures/heartIcon.png");
		

		// Inventory
		inventoryScreen = ImageLoader.loadImage("/textures/inventoryScreen.png");
		popupInv = ImageLoader.loadImage("/textures/popupInv.png");

		
		// Static entities
		sign = ImageLoader.loadImage("/textures/sign.png");
		
		SpriteSheet chestSheet = new SpriteSheet(ImageLoader.loadImage("/textures/chestSheet.png"));
		chest = new BufferedImage[3];
		chest[0] = chestSheet.crop(0, 0, width, height);
		chest[1] = chestSheet.crop(width, 0, width, height);
		chest[2] = chestSheet.crop(0, height, width, height);
		
		int PW = 64, PH = 64;
		SpriteSheet portalSheet = new SpriteSheet(ImageLoader.loadImage("/textures/portalSheet.png"));
		portal_spin = new BufferedImage[8];
		portal_spin[0] = portalSheet.crop(0, 0, PW, PH);
		portal_spin[1] = portalSheet.crop(PW, 0, PW, PH);
		portal_spin[2] = portalSheet.crop(PW*2, 0, PW, PH);
		portal_spin[3] = portalSheet.crop(PW*3, 0, PW, PH);
		portal_spin[4] = portalSheet.crop(PW*4, 0, PW, PH);
		portal_spin[5] = portalSheet.crop(PW*5, 0, PW, PH);
		portal_spin[6] = portalSheet.crop(PW*6, 0, PW, PH);
		portal_spin[7] = portalSheet.crop(PW*7, 0, PW, PH);
		
		portal_open = new BufferedImage[8];
		portal_open[0] = portalSheet.crop(0, PH, PW, PH);
		portal_open[1] = portalSheet.crop(PW, PH, PW, PH);
		portal_open[2] = portalSheet.crop(PW*2, PH, PW, PH);
		portal_open[3] = portalSheet.crop(PW*3, PH, PW, PH);
		portal_open[4] = portalSheet.crop(PW*4, PH, PW, PH);
		portal_open[5] = portalSheet.crop(PW*5, PH, PW, PH);
		portal_open[6] = portalSheet.crop(PW*6, PH, PW, PH);
		portal_open[7] = portalSheet.crop(PW*7, PH, PW, PH);
		
		portal_close = new BufferedImage[6];
		portal_close[0] = portalSheet.crop(0, PH*2, PW, PH);
		portal_close[1] = portalSheet.crop(PW, PH*2, PW, PH);
		portal_close[2] = portalSheet.crop(PW*2, PH*2, PW, PH);
		portal_close[3] = portalSheet.crop(PW*3, PH*2, PW, PH);
		portal_close[4] = portalSheet.crop(PW*4, PH*2, PW, PH);
		portal_close[5] = portalSheet.crop(PW*5, PH*2, PW, PH);
		
		
		
		
		
		
		
		
		
		// Tiles
		untextured = tileSheet.crop(0, 0, width, height);
		stone = tileSheet.crop(width, 0, width, height);
		voidTile = tileSheet.crop(width * 2, 0, width, height);
		wallTile = tileSheet.crop(width * 3, 0, width, height);
		
		//floor
		floor_tiles = new BufferedImage[9];
		floor_tiles[0] = tileSheet.crop(width, height * 2, width, height); // Middle
		floor_tiles[1] = tileSheet.crop(0, height, width, height); // Top-left corner
		floor_tiles[2] = tileSheet.crop(width * 2, height, width, height); // Top-right corner
		floor_tiles[3] = tileSheet.crop(width * 2, height * 3, width, height); // Bottom-left corner
		floor_tiles[4] = tileSheet.crop(0, height * 3, width, height); // Bottom-right corner
		floor_tiles[5] = tileSheet.crop(width, height, width, height); // Top tile
		floor_tiles[6] = tileSheet.crop(width * 2, height * 2, width, height); // Right tile
		floor_tiles[7] = tileSheet.crop(width, height * 3, width, height); // Bottom-left corner
		floor_tiles[8] = tileSheet.crop(0, height * 2 , width, height); // Left tile
		//walls
		wall_tiles = new BufferedImage[8];
		wall_tiles[0] = tileSheet.crop(0, height * 4, width, height); // Top-left corner
		wall_tiles[1] = tileSheet.crop(width * 2, height * 4, width, height); // Top-right corner
		wall_tiles[2] = tileSheet.crop(width * 2, height * 6, width, height); // Bottom-left corner
		wall_tiles[3] = tileSheet.crop(0, height * 6, width, height); // Bottom-right corner
		
		wall_tiles[4] = tileSheet.crop(width, height * 4, width, height); // Top tile
		wall_tiles[5] = tileSheet.crop(width * 2, height * 5, width, height); // Right tile
		wall_tiles[6] = tileSheet.crop(width, height * 6, width, height); // Bottom tile
		wall_tiles[7] = tileSheet.crop(0, height * 5, width, height); // Left Tile
		
		
		// Player animation
		player_down = new BufferedImage[13];
		player_up = new BufferedImage[13];
		player_left = new BufferedImage[9];
		player_right = new BufferedImage[9];

		player_down[0] = robotSheet.crop(0, 0, width, height);
		player_down[1] = robotSheet.crop(width, 0, width, height);
		player_down[2] = robotSheet.crop(width * 2, 0, width, height);
		player_down[3] = robotSheet.crop(width * 3, 0, width, height);
		player_down[4] = robotSheet.crop(width * 4, 0, width, height);
		player_down[5] = robotSheet.crop(width * 5, 0, width, height);
		player_down[6] = robotSheet.crop(width * 6, 0, width, height);
		player_down[7] = robotSheet.crop(width * 7, 0, width, height);
		player_down[8] = robotSheet.crop(0, height, width, height);
		player_down[9] = robotSheet.crop(width, height, width, height);
		player_down[10] = robotSheet.crop(width * 2, height, width, height);
		player_down[11] = robotSheet.crop(width * 3, height, width, height);
		player_down[12] = robotSheet.crop(width * 4, height, width, height);

		player_up[0] = robotSheet.crop(0, height * 2, width, height);
		player_up[1] = robotSheet.crop(width, height * 2, width, height);
		player_up[2] = robotSheet.crop(width * 2, height * 2, width, height);
		player_up[3] = robotSheet.crop(width * 3, height * 2, width, height);
		player_up[4] = robotSheet.crop(width * 4, height * 2, width, height);
		player_up[5] = robotSheet.crop(width * 5, height * 2, width, height);
		player_up[6] = robotSheet.crop(width * 6, height * 2, width, height);
		player_up[7] = robotSheet.crop(width * 7, height * 2, width, height);
		player_up[8] = robotSheet.crop(0, height * 3, width, height);
		player_up[9] = robotSheet.crop(width, height * 3, width, height);
		player_up[10] = robotSheet.crop(width * 2, height * 3, width, height);
		player_up[11] = robotSheet.crop(width * 3, height * 3, width, height);
		player_up[12] = robotSheet.crop(width * 4, height * 3, width, height);

		player_right[0] = robotSheet.crop(0, height * 4, width, height);
		player_right[1] = robotSheet.crop(width, height * 4, width, height);
		player_right[2] = robotSheet.crop(width * 2, height * 4, width, height);
		player_right[3] = robotSheet.crop(width * 3, height * 4, width, height);
		player_right[4] = robotSheet.crop(width * 4, height * 4, width, height);
		player_right[5] = robotSheet.crop(width * 5, height * 4, width, height);
		player_right[6] = robotSheet.crop(width * 6, height * 4, width, height);
		player_right[7] = robotSheet.crop(width * 7, height * 4, width, height);
		player_right[8] = robotSheet.crop(0, height * 5, width, height);

		player_left[0] = robotSheet.crop(0, height * 6, width, height);
		player_left[1] = robotSheet.crop(width, height * 6, width, height);
		player_left[2] = robotSheet.crop(width * 2, height * 6, width, height);
		player_left[3] = robotSheet.crop(width * 3, height * 6, width, height);
		player_left[4] = robotSheet.crop(width * 4, height * 6, width, height);
		player_left[5] = robotSheet.crop(width * 5, height * 6, width, height);
		player_left[6] = robotSheet.crop(width * 6, height * 6, width, height);
		player_left[7] = robotSheet.crop(width * 7, height * 6, width, height);
		player_left[8] = robotSheet.crop(0, height * 7, width, height);

		player_idle = robotSheet.crop(width * 7, height * 7, width, height);
	}
}
