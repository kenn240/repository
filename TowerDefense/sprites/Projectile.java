import java.awt.Image;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Projectile implements DisplayableSprite, MovableSprite, CollidingSprite {

	private Image image1;
	
	private double centerX = 0;
	private double centerY = 0;
	private double width = 10;
	private double height = 10;
	private boolean dispose = false;
	private static long score = 0;
	private boolean isAtExit = false;
	private boolean inProximity = false;
	private boolean achievement = false;
	private double dX, dY, elapsedTime;
	private double velocityX;
	private double velocityY;
	private int damage;

	public Projectile(double centerX, double centerY, double dX, double dY, String imageName, int damage) {
		super();
		this.centerX = centerX;
		this.centerY = centerY;
		this.dX = dX;
		this.dY = dY;
		this.damage = damage;
		
			try {
				image1 = ImageIO.read(new File(imageName));
				System.out.println(imageName);
			} 
			catch (IOException e) {
				System.out.println(e.toString());
			}

		
	}

	public Image getImage() {

		return image1;

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
		elapsedTime += (actual_delta_time * 0.001);
		if (elapsedTime > 1 || checkCollisionWithBarrier(universe) == true) {
			this.dispose = true;
			
			
		}
		
		

		// calculate new position based on velocity and time elapsed
		//double deltaX = actual_delta_time * 0.001 * velocityX;

		this.centerX += dX * 0.5;

		//double deltaY = actual_delta_time * 0.001 * velocityY;

		this.centerY += dY * 0.5;

		checkCollisionWithBarrier(universe);
	}

	public void setCenterX(double centerX) {

		this.centerX = centerX;
	}

	@Override
	public void setCenterY(double centerY) {
		this.centerY = centerY;

	}

	@Override
	public void setVelocityX(double velocityX) {
		this.velocityX = velocityX;

	}

	@Override
	public void setVelocityY(double velocityY) {
		this.velocityY = velocityY;

	}

	private boolean checkCollisionWithBarrier(Universe universe) {
		boolean colliding = false;
		for (int i = 0; i < universe.getSprites().size(); i++) {

			DisplayableSprite sprite = universe.getSprites().get(i);

			if (sprite instanceof Enemy) {
				if (CollisionDetection.pixelBasedOverlaps(this, sprite)) {
					((Enemy) sprite).removeHealth(damage);
					colliding = true;
					
					
				}

			}
		}
		return colliding;
	}

	private void checkCoinPickup(Universe universe) {
		// not implemented
	}

	private void checkExit(Universe universe, double deltaX, double deltaY) {// use inside
		// not implemented
	}

	private void checkProximity(Universe universe) {
		// not implemented
	}

	public long getScore() {
		// not implemented
		return 0;
	}

	@Override
	public String getProximityMessage() {
		// not implemented
		return "";
	}

	@Override
	public boolean getIsAtExit() {
		// not implemented
		return false;
	}

	

}
