import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Battleship extends Tower{
	private Image image;
	private Image[] rotatedImages = new Image[360];
	private double width;
	private double height;
	private double centerX;
	private double centerY;
	
	public Battleship(double centerX, double centerY) {
		super(centerX, centerY, 4, 300, 1, "res/battleship.png", "res/bullet.png");
		this.centerX = centerX;
		this.centerY = centerY;
		try {
			image = ImageIO.read(new File("res/battleship.png"));
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
	public void shoot(Universe universe) {
		double x = 0;
		double y = 0;
		
		double x1 = 0;
		double y1 = 0;

		for (DisplayableSprite sprite : universe.getSprites()) {
			if ((sprite instanceof Enemy) && checkProximity(sprite)) {
					x = sprite.getCenterX() - this.centerX;
					y = sprite.getCenterY() - this.centerY;
					
					 x1 = Math.toDegrees(Math.acos(x/(x+100)));
					 y1 = Math.toDegrees(Math.acos(y/(y+100)));
					 

			} 
			
		}
		if (x != 0 && y != 0) {
			Projectile bullet = new Projectile(centerX, centerY, x, y, "res/bullet.png", 1);
			Projectile bullet2 = new Projectile(centerX, centerY, x1 * 20, y1 * 20, "res/bullet.png", 1);
			Projectile bullet3 = new Projectile(centerX, centerY, -(x1 + 90) * 20, -(y1 + 90) * 20, "res/bullet.png", 1);
			// create a new cannon ball given the x direction that it should
			// head and the y direction it should head
			
			universe.getSprites().add(bullet);
			universe.getSprites().add(bullet2);
			universe.getSprites().add(bullet3);
		}
	}

}
