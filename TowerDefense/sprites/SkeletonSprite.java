import java.awt.Image;


import java.io.File;
import java.io.IOException;
import java.awt.Image;
import javax.imageio.ImageIO;

public class SkeletonSprite extends Enemy {

	private static final double VELOCITY = 200;
	
	private final int WIDTH = 50;
	private final int HEIGHT = 50;

	private static Image image;
	
	public SkeletonSprite(double centerX, double centerY, int health) {
		super(centerX, centerY, health, VELOCITY, "res/skeleton.png", 10);
		

		if (image == null) {
			try {

				image = ImageIO.read(new File("res/skeleton.png"));
			}

			catch (IOException e) {
				System.err.println(e.toString());

			}
		}
	}

	
}
