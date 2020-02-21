package game.gfx;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

	public class ImageLoader {

		public static BufferedImage loadImage(String path) {
			//Try to load an image
			try {
				return ImageIO.read(ImageLoader.class.getResource(path));  //loading the image by using the given path
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(1);
			}
			return null;
		}
	}


