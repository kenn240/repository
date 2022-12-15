import java.util.ArrayList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.*;
import java.awt.event.*;

import java.util.Random;

//2020-10-27
public class SkeletonDefenseUniverse implements Universe {
	
	private Background a = null;
	private boolean complete = false;
	private boolean teleport = false;
	private DisplayableSprite player1;
	private static ArrayList<DisplayableSprite> sprites = new ArrayList<DisplayableSprite>();
	private String status = "";
	private ArrayList<Background> backgrounds = null;
	private double xCenter = 600;
	private double yCenter = 350;
	private int level = 10;
	private double elapsedTime;
	private final double RATE = 1;
	private static long score = 1000;
	private static long health = 100;
	private static long skeletonsKilled = 0;
	private static int wave = 1;
	
	
	private TowerType towerType;
	private enum TowerType {
		CANNON(0), TREBUCHET(1), ARCHER(2);
		private int value = 0;

		private TowerType(int value) {
			this.value = value;
		}
	};
	
	public int map[][] = new int[][] {
		{5, 5, 5, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
		{5, 6, 5, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
		{5, 5, 5, 3, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1}, 
		{5, 5, 5, 3, 1, 3, 3, 3, 3, 2, 2, 1, 1, 1, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2}, 
		{5, 5, 5, 3, 1, 1, 1, 1, 3, 2, 2, 2, 2, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 2}, 
		{5, 5, 5, 3, 3, 3, 3, 1, 3, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2},
		{5, 5, 5, 3, 3, 3, 3, 1, 3, 2, 2, 2, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2},
		{5, 5, 5, 3, 3, 3, 3, 1, 3, 2, 2, 2, 1, 2, 2, 1, 1, 1, 1, 2, 2, 2, 1, 2},
		{5, 5, 5, 3, 3, 3, 3, 1, 3, 2, 2, 2, 1, 1, 2, 1, 2, 2, 1, 2, 2, 2, 1, 2},
		{5, 5, 5, 3, 3, 1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 1, 2, 2, 1, 2, 2, 2, 1, 2},
		{5, 5, 5, 3, 3, 1, 3, 3, 3, 2, 2, 2, 2, 1, 2, 1, 2, 2, 1, 2, 2, 2, 1, 3},
		{5, 5, 5, 3, 3, 1, 3, 3, 3, 2, 2, 2, 2, 1, 1, 1, 2, 2, 1, 1, 1, 1, 1, 3},
		{5, 5, 5, 3, 3, 1, 3, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3},
		{5, 5, 5, 3, 3, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3},
		
		
	};
	
	private double timeSinceLastSpawn;

	private final double VELOCITY = 200;

	// //require a separate list for sprites to be removed to avoid a concurence
	// exception
	private ArrayList<DisplayableSprite> disposalList = new ArrayList<DisplayableSprite>();

	public SkeletonDefenseUniverse() {
		
		
		a = new CastleBackground();
		backgrounds = new ArrayList<Background>();
		ArrayList<DisplayableSprite> barriers = ((CastleBackground) a).getBarriers();
		backgrounds.add(a);
		sprites.addAll(barriers);

	}
	
	public double getScale() {
		return 1;
	}

	public double getXCenter() {
		return xCenter;
	}

	public double getYCenter() {
		return yCenter;
	}

	public void setXCenter(double xCenter) {
		this.xCenter = xCenter;
	}

	public void setYCenter(double yCenter) {
		this.yCenter = yCenter;
	}

	public boolean isComplete() {
		return complete;
	}

	public void setComplete(boolean complete) {
		this.complete = complete;
	}

	public ArrayList<Background> getBackgrounds() {
		return backgrounds;
	}

	public DisplayableSprite getPlayer1() {
		return player1;
	}

	public ArrayList<DisplayableSprite> getSprites() {
		return sprites;
	}

	public boolean centerOnPlayer() {
		return false;
	}

	public void update(KeyboardInput keyboard, long actual_delta_time) {
		
		disposeSprites();
		elapsedTime += (actual_delta_time * 0.001);
		timeSinceLastSpawn += (actual_delta_time * 0.001);
		double velocityX = 0;
		double velocityY = 0;
		
		if (keyboard.keyDownOnce(67)) { // c key
			towerType = TowerType.CANNON;
		}
		if (keyboard.keyDown(84)) { // t key
			towerType = TowerType.TREBUCHET;
		}
		//System.out.println(towerType);

		if (health <= 0) {
			complete = true;
		}

		if (skeletonsKilled >= 5 * wave && skeletonsKilled % 5 != 0) {
			nextWave();
			addScore(500);
		}
		
		timeSinceLastSpawn = spawnSkeletons(timeSinceLastSpawn);
		
		

		for (int i = 0; i < sprites.size(); i++) {
			DisplayableSprite sprite = sprites.get(i);

			sprite.update(this, null, actual_delta_time);
		}
		double xPos = MouseInput.screenX / CastleBackground.TILE_WIDTH;;
		double yPos = MouseInput.screenY / CastleBackground.TILE_HEIGHT; // find position on array;
		if (keyboard.keyDown(32)) {
			placeTower(xPos, yPos, towerType);
		}
		
	}
	public double spawnSkeletons(double timeSinceLastSpawn) {
		if (timeSinceLastSpawn >= RATE) {
			SkeletonSprite e1 = (new SkeletonSprite(530, 750, wave));
			sprites.add(e1);
			return 0;
		}
		else {
			return timeSinceLastSpawn;
		}
	}
	
	
	public void placeTower(double xPos, double yPos, TowerType towerType) {
		if (towerType == TowerType.CANNON && map[(int) yPos][(int) xPos] == 2) {
			sprites.add(new CannonSprite(MouseInput.screenX, MouseInput.screenY));
			addScore(-700);
			map[(int) yPos][(int) xPos] = 4;
			
		}
		if (towerType == TowerType.TREBUCHET && map[(int) yPos][(int) xPos] == 2) {
			sprites.add(new Trebuchet(MouseInput.screenX, MouseInput.screenY));
			addScore(-2000);
			map[(int) yPos][(int) xPos] = 4;
			
		}
	}

	public String toString() {
		return this.status;
	}

	protected void disposeSprites() {

		// collect a list of sprites to dispose
		// this is done in a temporary list to avoid a concurrent modification exception
		for (int i = 0; i < sprites.size(); i++) {
			DisplayableSprite sprite = sprites.get(i);
			if (sprite.getDispose() == true) {
				disposalList.add(sprite);
			}
		}

		// go through the list of sprites to dispose
		// note that the sprites are being removed from the original list
		for (int i = 0; i < disposalList.size(); i++) {
			DisplayableSprite sprite = disposalList.get(i);
			sprites.remove(sprite);
			System.out.println("Remove: " + sprite.toString());
		}

		// clear disposal list if necessary
		if (disposalList.size() > 0) {
			disposalList.clear();
		}
	}

	public static long getScore() {
		return score;
	}

	public static void addScore(int a) {
		score += a;
	}

	public static long getHealth() {
		return health;
	}

	public static void addHealth(int m) {
		health += m;
	}

	public static long getSkeletonsKilled() {
		return skeletonsKilled;
	}

	public static void skeletonKilled() {
		skeletonsKilled++;
	}

	public static int getWave() {
		return wave;
	}

	public static void nextWave() {
		wave++;
	}

}
