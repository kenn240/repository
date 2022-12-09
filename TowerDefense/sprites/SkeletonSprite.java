import java.awt.Image;


import java.io.File;
import java.io.IOException;
import java.awt.Image;
import javax.imageio.ImageIO;

public class SkeletonSprite implements DisplayableSprite, CollidingSprite {

	private static final double VELOCITY = 200;
	private static final int WIDTH = 50;
	private static final int HEIGHT = 50;

	private static Image image;
	private boolean directionFound = false;
	private long elapsedTime = 0;

	private double centerX = 0;
	private double centerY = 0;
	private double width = 50;
	private double height = 50;
	private boolean dispose = false;
	private int skeletonHealth;
	public int map[][] = new int[][] {
		{5, 5, 5, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
		{5, 6, 5, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
		{5, 5, 5, 3, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1}, 
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

	public SkeletonSprite(double centerX, double centerY, int health) {

		this.centerX = centerX;
		this.centerY = centerY;
		this.skeletonHealth = health;
		this.width = WIDTH;
		this.height = HEIGHT;

		if (image == null) {
			try {

				image = ImageIO.read(new File("res/skeleton.png"));
			}

			catch (IOException e) {
				System.err.println(e.toString());

			}
		}
	}

	public Image getImage() {

		return image;

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

		elapsedTime += actual_delta_time;
		
		
		if (checkCollisionWithCannonBall(universe)) {

			skeletonHealth--;
			if (skeletonHealth <= 0) {
				this.dispose = true;

				SkeletonDefenseUniverse.addScore(100);
				SkeletonDefenseUniverse.skeletonKilled();
			}
		}

	
		

		
		int xPos = (int) Math.round((this.centerX -30)/ CastleBackground.TILE_WIDTH) ;
		int yPos = (int) Math.round(this.centerY / CastleBackground.TILE_HEIGHT);
		
		
		Coordonite corner = (findCorner(xPos, yPos, direction));
		int cornerY = corner.getyDirection();
		int cornerX = corner.getxDirection();
		
		// this is not the most elegant solution to making an enemy AI but it's definitly better than hard coding it if the path is very complex 
		// basically, depending on the direction the enemy is going, it checks the tiles next to it to determine where to go
		// ex. if were going up, it checks the tile to the left of the corner and the right of the corner to figure out which way to go 
		switch (direction) {
			case UP:
				directionFound = true;
				cornerY ++;
				if (this.centerY <= (cornerY*50) + 20) {
					if (map[cornerY][cornerX+1] == 1) { 
						direction = Direction.RIGHT;
						}
					else {direction = Direction.LEFT;}
				}
				break;
			
		
		case RIGHT:
			directionFound = true;
			cornerX--;
			if (this.centerX >= (cornerX*50) + 20) {
				if (map[cornerY+1][cornerX] == 1) {
					direction = SkeletonSprite.Direction.DOWN;
				}
				else {
					direction = SkeletonSprite.Direction.UP;
				}
			}
			break;
		
		case DOWN:
			directionFound = true;
			cornerY--;
			if (this.centerY >= (cornerY * 50) + 20) {
				
				System.out.println(this.centerX + ", " + this.centerY);
				if (map[cornerY][cornerX+1] == 1) {
					direction = SkeletonSprite.Direction.RIGHT;    
				}
				else {
					direction = SkeletonSprite.Direction.LEFT;
				}
			}
			break;
		
		case LEFT:
			directionFound = true;
			cornerX++;
			if (this.centerX <= (cornerX * 50) + 20) {
				if (map[cornerY+1][cornerX] == 1) {
					direction = SkeletonSprite.Direction.DOWN;
				}
				else {
					direction = SkeletonSprite.Direction.UP;
				}
			}
			break;
			
		}
		if (directionFound == false) {
			direction = SkeletonSprite.Direction.UP;
		}
		
		
		
		switch (direction) {
		case UP:
			this.centerY -= actual_delta_time * 0.001 * VELOCITY;
			break;
		case DOWN:
			this.centerY += actual_delta_time * 0.001 * VELOCITY;
			break;
		case LEFT:
			this.centerX -= actual_delta_time * 0.001 * VELOCITY;
			break;
		case RIGHT:
			this.centerX += actual_delta_time * 0.001 * VELOCITY;
			break;

		}

	}
	public Coordonite findCorner(int xCord, int yCord, Direction direction) {
		// depending on which direction we go, check all the tiles in front of where the skeleton is going until the tile is no longer type path
		if (xCord < 24 && yCord < 16) {
			if (map[yCord][xCord] != 1) {
		
			Coordonite c = new Coordonite(xCord, yCord);
			return c;
		}
		else {
			if (direction == Direction.DOWN) {
				return findCorner(xCord, yCord +1, direction);
			}
			if (direction == Direction.UP) {
				return findCorner(xCord, yCord -1, direction);
			}
			if (direction == Direction.RIGHT) {
				return findCorner(xCord+1, yCord, direction);
			}
			else {
				return findCorner(xCord-1, yCord, direction);
			}
		}
		}
		else {
			Coordonite c = new Coordonite(xCord, yCord);
			return c;
		}
		
	}

	@Override
	public long getScore() {
		
		return 0;
	}

	private boolean checkCollisionWithCannonBall(Universe universe) {

		boolean colliding = false;
		for (int i = 0; i < universe.getSprites().size(); i++) {

			DisplayableSprite sprite = universe.getSprites().get(i);

			if (sprite instanceof CannonBallSprite) {
				if (CollisionDetection.pixelBasedOverlaps(this, sprite)) {
					sprite.setDispose(true);
					// remove the cannon ball if its colliding
					colliding = true;
					
				}

			}
		}
		return colliding;
	}

	@Override
	public String getProximityMessage() {
		
		return null;
	}

	@Override
	public boolean getIsAtExit() {
		
		return false;
	}
}
