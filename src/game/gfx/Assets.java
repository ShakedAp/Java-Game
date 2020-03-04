package game.gfx;

import java.awt.Font;
import java.awt.image.BufferedImage;

public class Assets {

	private static final int width = 128, height = 128;

	public static Font font28, font24;

	public static BufferedImage stone, rock, wood, untextured, regularTile, voidTile, wallTile;
	public static BufferedImage bullet, choosen, bar, shield_icon, heart_icon;
	public static BufferedImage bad_pistol, RPG, shotgun, smg, ak47, water_gun;
	public static BufferedImage inventoryScreen, popupInv;

	public static BufferedImage[] rocket;
	public static BufferedImage[] btn_start, btn_pause;

	public static BufferedImage player_idle;
	public static BufferedImage[] player_down, player_up, player_left, player_right;

	public static void init() {
		
		// Fonts
		font28 = FontLoader.loadFont("res/fonts/slkscr.ttf", 28);
		font24 = FontLoader.loadFont("res/fonts/tiny.ttf", 24);

		// Sprite-Sheets
		SpriteSheet startSheet = new SpriteSheet(ImageLoader.loadImage("/textures/startSheet.png"));
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
		btn_start = new BufferedImage[2];
		btn_start[0] = startSheet.crop(0, 0, width * 2, height);
		btn_start[1] = startSheet.crop(0, height, width * 2, height);

		btn_pause = new BufferedImage[2];
		btn_pause[0] = ImageLoader.loadImage("/textures/pauseButton.png");
		btn_pause[1] = ImageLoader.loadImage("/textures/pauseButton.png");

		choosen = ImageLoader.loadImage("/textures/chosen.png");
		
		bar = ImageLoader.loadImage("/textures/bar.png");
		shield_icon = ImageLoader.loadImage("/textures/shieldIcon.png");
		heart_icon = ImageLoader.loadImage("/textures/heartIcon.png");
		

		// Inventory
		inventoryScreen = ImageLoader.loadImage("/textures/inventoryScreen.png");
		popupInv = ImageLoader.loadImage("/textures/popupInv.png");

		// Tiles
		untextured = tileSheet.crop(0, 0, width, height);
		regularTile = tileSheet.crop(width, 0, width, height);
		stone = tileSheet.crop(width * 2, 0, width, height);
		voidTile = tileSheet.crop(width * 3, 0, width, height);
		wallTile = tileSheet.crop(width * 4, 0, width, height);

		rock = untextured;
		
	
		
		
		
		
		
		
		
		
		
		
		
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
