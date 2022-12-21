import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class WizardSprite extends Enemy{
	
	private static Image image;

	public WizardSprite(double centerX, double centerY, int health) {
		super(centerX, centerY, health, 100, "res/wizard.png");
		if (image == null) {
			try {

				image = ImageIO.read(new File("res/wizard.png"));
			}

			catch (IOException e) {
				System.err.println(e.toString());

			}
		}
		
	}

}
