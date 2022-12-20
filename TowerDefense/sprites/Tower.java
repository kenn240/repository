import java.awt.Image;

import java.io.File; 
import java.io.IOException;
import javax.imageio.ImageIO;

public class Tower implements DisplayableSprite {
	
	private Image image;
	private double centerX;
	private double centerY;
	private double width;
	private double height;
	private boolean dispose = false;
	private Image[] rotatedImages = new Image[360];
	private double currentAngle = 0;
	private double timeSinceLastShot;
	private boolean inProximity;
	private double reloadTime;
	private double range;
	private String projectileImage;
	

		public Tower(double centerX, double centerY, double reloadTime, double range, String imageName) {
			super();
			this.centerX = centerX;
			this.centerY = centerY;
			this.reloadTime = reloadTime;
			this.range = range;
			
			
			try {
				image = ImageIO.read(new File(imageName));
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

		
		
		public Image getImage() {
			/*
			try {
				Tower.image = rotatedImages[Math.abs((int) currentAngle)];
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println(e);
			}
			*/
			return image;
		}

		public Image getImage(int angle) {
			return rotatedImages[angle];
		}

		// DISPLAYABLE

		public boolean getVisible() {
			return true;
		}

		public double getMinX() {
			return centerX - (width / 2);
		}

		public double getMaxX() {
			return centerX + (width / 2);
		}

		public double getMinY() {
			return centerY - (height / 2);
		}

		public double getMaxY() {
			return centerY + (height / 2);
		}

		public double getHeight() {
			return height;
		}

		public double getWidth() {
			return width;
		}

		public double getCenterX() {
			return centerX;
		};

		public double getCenterY() {
			return centerY;
		};

		public boolean getDispose() {
			return dispose;
		}

		public void setDispose(boolean dispose) {
			this.dispose = dispose;
		}

		public void update(Universe universe, KeyboardInput keyboard, long actual_delta_time) {
			
			

			timeSinceLastShot += actual_delta_time * 0.01;

			if (this.checkProximity(universe) == true && timeSinceLastShot >= reloadTime) {
				shoot(universe);
				this.image = getImage(findTheta(universe));
				
				timeSinceLastShot = 0;
			}
			

		}
		
		private boolean checkProximity(Universe universe) {
			for (DisplayableSprite sprite : universe.getSprites()) {
				
				if (sprite instanceof SkeletonSprite &&
				
						Math.sqrt
						(Math.pow(sprite.getCenterX() - this.centerX, 2)
						+ Math.pow(sprite.getCenterY() - this.centerY, 2)) <= range) {
				return true;
				}
			}
			return false;
			
		}

		

		
		

		public void shoot(Universe universe) {
			double x = 0;
			double y = 0;

			for (DisplayableSprite sprite : universe.getSprites()) {
				if (sprite instanceof SkeletonSprite && 
						Math.sqrt
						(Math.pow(sprite.getCenterX() - this.centerX, 2)
						+ Math.pow(sprite.getCenterY() - this.centerY, 2)
						) <= range) {
						x = sprite.getCenterX() - this.centerX;
						y = sprite.getCenterY() - this.centerY;

				} else {

				}
			}
			if (x != 0 && y != 0) {
				CannonBallSprite bullet = new CannonBallSprite(centerX, centerY, x, y);
				// create a new cannon ball given the x direction that it should
				// head and the y direction it should head
				
				universe.getSprites().add(bullet);
				timeSinceLastShot = getReloadTime();
			}
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
	
		public double getReloadTime() {
			return reloadTime;
		}

		public void setReloadTime(int reloadTime) {
			this.reloadTime = reloadTime;
		}


		
		


		
		
	}


