package game.gfx;

import java.awt.Font;
import java.awt.image.BufferedImage;

public class Assets {

	private static final int width = 32, height = 32;
	private static final int  newWidth = 128, newHeight = 128;
	
	public static Font font28, font24; 
	
	public static BufferedImage dirt, grass, stone, tree, rock, wood, player_idle, bullet; 
	public static BufferedImage bad_pistol, RPG;
	public static BufferedImage inventoryScreen;
	
	public static BufferedImage[] player_down, player_up, player_left, player_right, btn_start;
	
	
	public static void init() {
		font28 = FontLoader.loadFont("res/fonts/slkscr.ttf", 28);
		font24 = FontLoader.loadFont("res/fonts/tiny.ttf", 24);
		
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/sheet.png"));
		SpriteSheet robotSheet = new SpriteSheet(ImageLoader.loadImage("/textures/robotSheet.png"));
		SpriteSheet gunSheet = new SpriteSheet(ImageLoader.loadImage("/textures/gunSheet.png"));
		
		inventoryScreen = ImageLoader.loadImage("/textures/inventoryScreen.png");
		
		player_down = new BufferedImage[13];
		player_up = new BufferedImage[13];
		player_left = new BufferedImage[9];
		player_right= new BufferedImage[9];
		
		btn_start = new BufferedImage[2];
		
		bullet = ImageLoader.loadImage("/textures/bullet.png");
		
		bad_pistol = gunSheet.crop(0, 0, newWidth, newHeight);
		RPG = gunSheet.crop(0, newHeight * 3, newWidth, newHeight);
		
		//buttons
		btn_start[0] = sheet.crop(width * 6, height * 4, width * 2, height);
		btn_start[1] = sheet.crop(width * 6, height * 5, width * 2, height);
		
		//items
		wood = sheet.crop(width, height, width, height); 
		
		//player animation
		player_down[0] = robotSheet.crop(0, 0, newWidth, newHeight);
		player_down[1] = robotSheet.crop(newWidth, 0, newWidth, newHeight);
		player_down[2] = robotSheet.crop(newWidth * 2, 0, newWidth, newHeight);
		player_down[3] = robotSheet.crop(newWidth * 3, 0, newWidth, newHeight);
		player_down[4] = robotSheet.crop(newWidth * 4, 0, newWidth, newHeight);
		player_down[5] = robotSheet.crop(newWidth * 5, 0, newWidth, newHeight);
		player_down[6] = robotSheet.crop(newWidth * 6, 0, newWidth, newHeight);
		player_down[7] = robotSheet.crop(newWidth * 7, 0, newWidth, newHeight);
		player_down[8] = robotSheet.crop(0, newHeight, newWidth, newHeight);
		player_down[9] = robotSheet.crop(newWidth, newHeight, newWidth, newHeight);
		player_down[10] = robotSheet.crop(newWidth * 2, newHeight, newWidth, newHeight);
		player_down[11] = robotSheet.crop(newWidth * 3, newHeight, newWidth, newHeight);
		player_down[12] = robotSheet.crop(newWidth * 4, newHeight, newWidth, newHeight);
		
		
		player_up[0] = robotSheet.crop(0, newHeight * 2, newWidth, newHeight);
		player_up[1] = robotSheet.crop(newWidth, newHeight * 2, newWidth, newHeight);
		player_up[2] = robotSheet.crop(newWidth * 2, newHeight * 2, newWidth, newHeight);
		player_up[3] = robotSheet.crop(newWidth * 3, newHeight * 2, newWidth, newHeight);
		player_up[4] = robotSheet.crop(newWidth * 4, newHeight * 2, newWidth, newHeight);
		player_up[5] = robotSheet.crop(newWidth * 5, newHeight * 2, newWidth, newHeight);
		player_up[6] = robotSheet.crop(newWidth * 6, newHeight * 2, newWidth, newHeight);
		player_up[7] = robotSheet.crop(newWidth * 7, newHeight * 2, newWidth, newHeight);
		player_up[8] = robotSheet.crop(0, newHeight * 3, newWidth, newHeight);
		player_up[9] = robotSheet.crop(newWidth, newHeight * 3, newWidth, newHeight);
		player_up[10] = robotSheet.crop(newWidth * 2, newHeight * 3, newWidth, newHeight);
		player_up[11] = robotSheet.crop(newWidth * 3, newHeight * 3, newWidth, newHeight);
		player_up[12] = robotSheet.crop(newWidth * 4, newHeight * 3, newWidth, newHeight);
		
		player_right[0] = robotSheet.crop(0, newHeight * 4, newWidth, newHeight);
		player_right[1] = robotSheet.crop(newWidth, newHeight * 4, newWidth, newHeight);
		player_right[2] = robotSheet.crop(newWidth * 2, newHeight * 4, newWidth, newHeight);
		player_right[3] = robotSheet.crop(newWidth * 3, newHeight * 4, newWidth, newHeight);
		player_right[4] = robotSheet.crop(newWidth * 4, newHeight * 4, newWidth, newHeight);
		player_right[5] = robotSheet.crop(newWidth * 5, newHeight * 4, newWidth, newHeight);
		player_right[6] = robotSheet.crop(newWidth * 6, newHeight * 4, newWidth, newHeight);
		player_right[7] = robotSheet.crop(newWidth * 7, newHeight * 4, newWidth, newHeight);
		player_right[8] = robotSheet.crop(0, newHeight * 5, newWidth, newHeight);
		
		player_left[0] = robotSheet.crop(0, newHeight * 6, newWidth, newHeight);
		player_left[1] = robotSheet.crop(newWidth, newHeight * 6, newWidth, newHeight);
		player_left[2] = robotSheet.crop(newWidth * 2, newHeight * 6, newWidth, newHeight);
		player_left[3] = robotSheet.crop(newWidth * 3, newHeight * 6, newWidth, newHeight);
		player_left[4] = robotSheet.crop(newWidth * 4, newHeight * 6, newWidth, newHeight);
		player_left[5] = robotSheet.crop(newWidth * 5, newHeight * 6, newWidth, newHeight);
		player_left[6] = robotSheet.crop(newWidth * 6, newHeight * 6, newWidth, newHeight);
		player_left[7] = robotSheet.crop(newWidth * 7, newHeight * 6, newWidth, newHeight);
		player_left[8] = robotSheet.crop(0, newHeight * 7, newWidth, newHeight);
		
		player_idle = robotSheet.crop(newWidth * 7 , newHeight * 7, newWidth, newHeight);
		
		
		
		
		
		//other
		dirt = sheet.crop(width, 0, width, height);
		grass = sheet.crop(width * 2, 0, width, height);
		//grass = img.crop(0, 0, 128, 128);
		stone = sheet.crop(width * 3, 0, width, height);
		tree = sheet.crop(0, 0, width, height * 2);
		rock = sheet.crop(0, height * 2, width, height);
	}
}
