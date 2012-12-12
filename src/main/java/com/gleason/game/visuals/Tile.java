package com.gleason.game.visuals;

import java.awt.Image;
import java.awt.Rectangle;

import com.gleason.game.StartingClass;
import com.gleason.game.characters.Robot;
import com.gleason.game.environment.Background;

public class Tile {
	private int tileX, tileY, speedX;
	private TileType type;
	public Image tileImage;

	public static final int WIDTH = 40;
	public static final int HEIGHT = 40;

	private Background bg = StartingClass.getBg1();

	public enum TileType {
		OCEAN(StartingClass.tileocean, 1), GROUND(StartingClass.tiledirt, 5), GRASS_RIGHT(
				StartingClass.tilegrassRight, 6), GRASS_LEFT(
				StartingClass.tilegrassLeft, 4), GRASS_TOP(
				StartingClass.tilegrassTop, 8), GRASS_BOTTOM(
				StartingClass.tilegrassBot, 2);
		private Image image;
		private int ordinal;

		private TileType(Image image, int ordinal) {
			this.image = image;
			this.ordinal = ordinal;
		}

		public Image getImage() {
			return image;
		}

		public int getOrdinal() {
			return ordinal;
		}

		public static TileType getByOrdinal(int ordinal) {
			for (TileType t : TileType.values()) {
				if (t.getOrdinal() == ordinal) {
					return t;
				}
			}
			// Return default
			return null;
		}
	}

	// public double getLeftBound(){
	//
	// }

	public Tile(int x, int y, TileType type) {
		tileX = x * WIDTH;
		tileY = y * HEIGHT;

		this.type = type;
		if (type != null) {
			tileImage = type.getImage();
		}
	}

	boolean up = false;

	private void handleCollision(Robot myBot) {
		Rectangle r1 = this.getBounds();
		Rectangle r2 = myBot.getRightBounds();
		Rectangle r3 = myBot.getBottomBounds();
		Rectangle r4 = myBot.getInnerRightBounds();
		Rectangle head = myBot.getTopBounds();
		if (r1.y > 0) {
			if(r1.intersects(head)){
				//TODO: How do we handle hitting your head?
			}
			else if (r1.intersects(r2) ) {
				myBot.onCollisionX();
			} 
			else if( r1.intersects(r4)){
				myBot.onCollisionX();
			}
			else if (r1.intersects(r3)) {
				myBot.onCollisionY();
			}
		}
	}
	
	public boolean isWithinRangeX(Robot mybot){
		if(getTileX()<mybot.getRightBound() && getTileX()+WIDTH > mybot.getLeftBound()){
			return true;
		}
		return false;
	}

	public void update(Robot myBot) {
		speedX = bg.getSpeedX() * 5;

		handleCollision(myBot);
		tileX += speedX;
	}

	public int getTileX() {
		return tileX;
	}

	public void setTileX(int tileX) {
		this.tileX = tileX;
	}

	public int getTileY() {
		return tileY;
	}

	public void setTileY(int tileY) {
		this.tileY = tileY;
	}

	public int getSpeedX() {
		return speedX;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public TileType getType() {
		return type;
	}

	public void setType(TileType type) {
		this.type = type;
	}

	public Image getTileImage() {
		return tileImage;
	}

	public void setTileImage(Image tileImage) {
		this.tileImage = tileImage;
	}

	public Background getBg() {
		return bg;
	}

	public void setBg(Background bg) {
		this.bg = bg;
	}

	public int getLeftBound() {
		return getTileX();
	}

	public int getRightBound() {
		return getLeftBound() + WIDTH;
	}

	public int getBottomBound() {
		int test = getTopBound() + HEIGHT;
		return test;
	}

	public int getTopBound() {
		int test = getTileY();
		return getTileY();

	}

	public Rectangle getBounds() {
		return new Rectangle(getLeftBound(), getTopBound(), WIDTH, HEIGHT);
	}
}
