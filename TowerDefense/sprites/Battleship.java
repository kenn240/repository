import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Battleship extends Tower{
	private Image image;
	private Image[] rotatedImages = new Image[360];
	private double width = 100;
	private double height = 100;
	private double centerX;
	private double centerY;
	
	public Battleship(double centerX, double centerY) {
		super(centerX, centerY, 4, 300, 1, "res/battleship.png", "res/bullet.png", 100, 100);
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
	// overriding the shoot method since this one shoots multiple proejectiles
	public void shoot(Universe universe) {
		double x = 0;
		double y = 0;
		
		for (DisplayableSprite sprite : universe.getSprites()) {
			if ((sprite instanceof Enemy) && checkProximity(sprite)) {
					x = sprite.getCenterX() - this.centerX;
					y = sprite.getCenterY() - this.centerY;
					break;
					} 
			
		}
		if (x != 0 && y != 0) {
			// goes straight to the enemy
			Projectile bullet = new Projectile(centerX, centerY, x, y, "res/bullet.png", 1);
			// goes 100 pixels left of the enemy
			Projectile bullet2 = new Projectile(centerX, centerY, x-100, y-100, "res/bullet.png", 1);
			// goes 100 pixels right of the enemy
			Projectile bullet3 = new Projectile(centerX, centerY, (x + 100), y+100, "res/bullet.png", 1);
			universe.getSprites().add(bullet);
			universe.getSprites().add(bullet2);
			universe.getSprites().add(bullet3);
			
		}
	}

}
