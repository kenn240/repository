import java.awt.Image;


import java.io.File;
import java.io.IOException;
import java.awt.Image;
import javax.imageio.ImageIO;

public class SkeletonSprite extends Enemy {

	private final double VELOCITY = 200;
	private final int WIDTH = 50;
	private final int HEIGHT = 50;

	private static Image image;
	
	
	
	
	
	

	// an example of an enumeration, which is a series of constants in a list. this
	// restricts the potential values of a variable
	// declared with that type to only those values within the set, thereby
	// promoting both code safety and readability
	/*
	private Direction direction = Direction.UP;

	private enum Direction {
		DOWN(0), LEFT(1), UP(2), RIGHT(3);

		private int value = 0;

		private Direction(int value) {
			this.value = value;
		}
	};
	*/
	public SkeletonSprite(double centerX, double centerY, int health) {
		super(centerX, centerY, health, 200);
		

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
