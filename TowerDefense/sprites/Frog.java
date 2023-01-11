import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Frog extends Enemy{

	private static Image image;
	
	public Frog(double centerX, double centerY, int health) {
		super(centerX, centerY, health, 50, "res/frogPirate.png", 5000);
		if (image == null) {
			try {

				image = ImageIO.read(new File("res/frogPirate.png"));
			}

			catch (IOException e) {
				System.err.println(e.toString());

			}
		}
	}

}
