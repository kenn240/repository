
import java.util.ArrayList;



import java.awt.Image;
import javax.imageio.ImageIO;

import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

public class CastleBackground implements Background {
	
	protected static int TILE_WIDTH = 50;
	protected static int TILE_HEIGHT = 50;
	
	private Image path;
	private Image stoneTile;
	private Image greenBarrier;
	private Image woodBorder;
	private Image cannonBorder;
	private Image trebuchetBorder;
	private int maxCols = 0;
	private int maxRows = 0; 
	private double shiftX = 0;
	private double shiftY = 0;
	
	public int map[][] = new int[][] {
		{5, 5, 5, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
		{5, 6, 5, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
		{5, 5, 5, 3, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 7}, 
		{5, 5, 5, 3, 1, 3, 3, 3, 3, 2, 2, 1, 1, 2, 2, 2, 1, 2, 2, 2, 2, 1, 2, 2}, 
		{5, 5, 5, 3, 1, 1, 1, 1, 3, 2, 2, 2, 1, 1, 2, 2, 1, 1, 1, 1, 1, 1, 2, 2}, 
		{5, 8, 5, 3, 3, 3, 3, 1, 3, 2, 2, 2, 2, 1, 1, 2, 1, 2, 2, 2, 2, 2, 2, 2},
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
	
	public CastleBackground() {
		try {
			this.path = ImageIO.read(new File("res/stoneTile.png"));
			this.stoneTile = ImageIO.read(new File("res/stoneTile.png"));
			this.greenBarrier = ImageIO.read(new File("res/waterTile.png"));
			this.woodBorder = ImageIO.read(new File("res/border.png"));
			this.cannonBorder = ImageIO.read(new File("res/cannonBorder.png"));
			this.trebuchetBorder = ImageIO.read(new File("res/trebuchetBorder.png"));
		}
		catch (IOException e) {
    		System.out.println(e.toString());
    	}
    	maxRows = map.length - 1;
    	maxCols = map[0].length - 1;
	}
	
	public Tile getTile(int col, int row) {
		Image image = null; 
		
		if (row < 0 || row > maxRows || col < 0 || col > maxCols) {
			image = null;
		}
		else if (map[row][col] == 0) {
			image = null;
		}
		else if (map[row][col] == 1 || map[row][col] == 7) {
			image = path;
		}
		else if (map[row][col] == 2) {
			image = stoneTile;
		}
		else if (map[row][col] == 3) {
			image = greenBarrier;
		}
		else if (map[row][col] == 5) {
			image = woodBorder;
		}
		else if (map[row][col] == 6) {
			image = cannonBorder;
		}
		else if (map[row][col] == 8) {
			image = trebuchetBorder;
		}
		
		int x = (col * TILE_WIDTH);
		int y = (row * TILE_HEIGHT);
		
		Tile newTile = new Tile(image, x, y, TILE_WIDTH, TILE_HEIGHT, false);
		
		return newTile;
	}

	@Override
	public int getCol(double x) {
		
				int col = 0;
				if (TILE_WIDTH != 0) {
					col = (int) (x / TILE_WIDTH);
					if (x < 0) {
						return col - 1;
					}
					else {
						return col;
					}
				}
				else {
					return 0;
				}
			
	}

	@Override
	public int getRow(double y) {
		
				int row = 0;
				
				if (TILE_HEIGHT != 0) {
					row = (int) (y / TILE_HEIGHT);
					if (y < 0) {
						return row - 1;
					}
					else {
						return row;
					}
				}
				else {
					return 0;
				}
	}

	@Override
	public double getShiftX() {
		// TODO Auto-generated method stub
		return shiftX;
	}

	@Override
	public double getShiftY() {
		// TODO Auto-generated method stub
		return shiftY;
	}

	@Override
	public void setShiftX(double shiftX) {
		this.shiftX = shiftX;
		
	}

	@Override
	public void setShiftY(double shiftY) {
		this.shiftY = shiftY;
		
	}

	public ArrayList<DisplayableSprite> getBarriers() {
		ArrayList<DisplayableSprite> barriers = new ArrayList<DisplayableSprite>();
		for (int col = 0; col < map[0].length; col++) {
			for (int row = 0; row < map.length; row++) {
				if (map[row][col] == 1) {
					barriers.add(new BarrierSprite(col * TILE_WIDTH, row * TILE_HEIGHT, (col + 1) * TILE_WIDTH, (row + 1) * TILE_HEIGHT, true));
				}
				
			}
		}
		return barriers;
	}

}
