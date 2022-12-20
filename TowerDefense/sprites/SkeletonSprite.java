import java.awt.Image;


import java.io.File;
import java.io.IOException;
import java.awt.Image;
import javax.imageio.ImageIO;

public class SkeletonSprite extends Enemy {

	private static final double VELOCITY = 200;
	private static final int WIDTH = 50;
	private static final int HEIGHT = 50;

	private static Image image;
	
	private long elapsedTime = 0;
	private boolean directionFound = false;
	private double centerX = 0;
	private double centerY = 0;
	private double width = 50;
	private double height = 50;
	private boolean dispose = false;
	private int skeletonHealth;
	public int map[][] = new int[][] {
		{5, 5, 5, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
		{5, 6, 5, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
		{5, 5, 5, 3, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 7}, 
		{5, 5, 5, 3, 1, 3, 3, 3, 3, 2, 2, 1, 1, 2, 2, 2, 1, 2, 2, 2, 2, 1, 2, 2}, 
		{5, 5, 5, 3, 1, 1, 1, 1, 3, 2, 2, 2, 1, 1, 2, 2, 1, 1, 1, 1, 1, 1, 2, 2}, 
		{5, 5, 5, 3, 3, 3, 3, 1, 3, 2, 2, 2, 2, 1, 1, 2, 1, 2, 2, 2, 2, 2, 2, 2},
		{5, 5, 5, 3, 3, 3, 3, 1, 3, 2, 2, 2, 2, 2, 1, 2, 1, 2, 2, 2, 2, 2, 2, 2},
		{5, 5, 5, 3, 3, 3, 3, 1, 3, 2, 2, 2, 2, 1, 1, 2, 1, 1, 1, 2, 2, 2, 2, 2},
		{5, 5, 5, 3, 3, 3, 3, 1, 3, 2, 2, 2, 2, 1, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2},
		{5, 5, 5, 3, 3, 1, 1, 1, 2, 2, 2, 2, 2, 1, 2, 2, 1, 1, 1, 2, 2, 2, 2, 2},
		{5, 5, 5, 3, 3, 1, 3, 3, 3, 2, 2, 2, 2, 1, 2, 1, 1, 2, 2, 2, 2, 2, 2, 3},
		{5, 5, 5, 3, 3, 1, 3, 3, 3, 2, 2, 2, 2, 1, 1, 1, 2, 2, 2, 1, 1, 1, 2, 3},
		{5, 5, 5, 3, 3, 1, 3, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3},
		{5, 5, 5, 3, 3, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3},
		{5, 5, 5, 3, 3, 3, 3, 3, 3, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3},
		{5, 5, 5, 3, 3, 3, 3, 3, 3, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3},
		{5, 5, 5, 3, 3, 3, 3, 3, 3, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3},
};

	// an example of an enumeration, which is a series of constants in a list. this
	// restricts the potential values of a variable
	// declared with that type to only those values within the set, thereby
	// promoting both code safety and readability
	private Direction direction = Direction.UP;

	private enum Direction {
		DOWN(0), LEFT(1), UP(2), RIGHT(3);

		private int value = 0;

		private Direction(int value) {
			this.value = value;
		}
	};

	public SkeletonSprite(double centerX, double centerY) {
		super(centerX, centerY, 1);
		

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
