import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Trebuchet extends Tower{
	
	private Image image;
	private Image[] rotatedImages = new Image[360];
	private double width;
	private double height;
	private double currentAngle = 0;
	private double centerX;
	private double centerY;
	private int range = 100;

	public Trebuchet(double centerX, double centerY) {
		super(centerX, centerY, 15, 400, 5, "res/trebuchet.png", "res/bullet.png", 100, 100);
		// TODO Auto-generated constructor stub
		
		try {
			image = ImageIO.read(new File("res/trebuchet.png"));
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
