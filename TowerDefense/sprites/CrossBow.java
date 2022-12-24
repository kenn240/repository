import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class CrossBow extends Tower {


	private static Image image;
	private static Image[] rotatedImages = new Image[360];
	private double width;
	private double height;
	
	

	public CrossBow(double centerX, double centerY) {
		super(centerX, centerY, .5, 250, 1,  "res/crossbow.png", "res/bullet.png", 50, 50); // each increment of 50 is 1 tile
		

		try {
			image = ImageIO.read(new File("res/crossbow.png"));
		} catch (IOException e) {
			System.out.println(e.toString());
		}
		if (image != null) {
			for (int i = 0; i < 360; i++) {
				rotatedImages[i] = ImageRotator.rotate(image, i);
			}
			this.height = image.getHeight(null);
			this.width = image.getWidth(null);
		}
		
	}



}
