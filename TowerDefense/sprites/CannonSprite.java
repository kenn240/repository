import java.awt.Image;


import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class CannonSprite extends Tower {
	private Image image;
	private Image[] rotatedImages = new Image[360];
	private double width;
	private double height;
	
	public CannonSprite(double centerX, double centerY) {

		super(centerX, centerY, 3, 100, 1, "res/cannonImage.png", "res/bullet.png");
		
		try {
			image = ImageIO.read(new File("res/cannonImage.png"));
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
	/*
	public Image getImage() {
	
		try {
			image = rotatedImages[Math.abs((int) currentAngle)];
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println(e);
		}
		return image;
	}
	
	public Image getImage(int angle) {
		return rotatedImages[angle];
	}
	
	public int findTheta(Universe universe) {
		double x = 0;
		double y = 0;
		for (DisplayableSprite sprite : universe.getSprites()) {

			if (sprite instanceof SkeletonSprite && Math.sqrt(Math.pow(sprite.getCenterX() - this.centerX, 2)
					+ Math.pow(sprite.getCenterY() - this.centerY, 2)) <= range) {
				x = sprite.getCenterX() - this.centerX;
				y = sprite.getCenterY() - this.centerY;

				currentAngle = ((Math.toDegrees(Math.atan(y / x))));
				// find angle by taking arctangent of difference in y
				// divided by difference in x (tan^-1(opposite / adjacent))
				// and then converting it to degrees
				if (Math.abs(currentAngle) > 360) {
					currentAngle = currentAngle % 360;
				}
				// if its over 360 degrees, take the remainder
				if (x > 0) {
					currentAngle += 180;
				}
				// if the sprite is behind the cannon add 180 to the degree
				if (currentAngle < 0) {
					currentAngle += 360;
				}
				// if its a negative degree add 360 tp it

			}
		}

		return (int) (currentAngle);
	}

	*/
}
