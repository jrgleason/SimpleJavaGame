package com.gleason.game.characters;

import java.awt.Rectangle;
import java.util.ArrayList;

import com.gleason.game.StartingClass;
import com.gleason.game.environment.Background;
import com.gleason.game.util.Collidable;
import com.gleason.game.visuals.Tile;
import com.gleason.game.weapon.Projectile;
import com.sun.media.sound.EmergencySoundbank;

public class Robot implements Collidable{

	static final int JUMPSPEED = -15;
	static final int MOVESPEED = 5;
	public static final int HEIGHT = 126;
	public static final int WIDTH = 120;
	static final int COLLISION_BOX_WIDTH = WIDTH;

	public static final int GROUND = Background.BG_HEIGHT - Tile.HEIGHT
			- (HEIGHT / 2);
	// public static final int GROUND = 382;

	private int centerX = 100;
	private int centerY = GROUND;
	private boolean jumped = false;
	private boolean movingLeft = false;
	private boolean movingRight = false;
	private boolean ducked = false;
	private boolean alive = true;

	private static Background bg1 = StartingClass.getBg1();
	private static Background bg2 = StartingClass.getBg2();

	private int speedX = 0;
	private int speedY = 1;

	private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();

	public double getLeftEdge() {
		return centerX - (.5 * COLLISION_BOX_WIDTH);

	}

	public double getRightEdge() {
		return centerX + (.5 * COLLISION_BOX_WIDTH);

	}

	public void update() {
		setSpeed();
		centerY += speedY;
		// }
		doJump();
		// Prevents going beyond X coordinate of 0
		if (centerX + speedX <= 60) {
			centerX = 61;
		}
		if(getTopBound() > 480){
			this.killPlayer();
		}
	}

	private static int BOX_OFFSET = 5;
	private static int TOP_TO_ARM = 33;
	private static int ARM_HEIGHT = 20;
	private static int DISTANCE_TO_FEET = 25;
	private static int DISTANCE_TO_HEAD = 40;

	private boolean falling = false;

	public void killPlayer(){
		setAlive(false);
	}
	
	public Rectangle getLeftBounds() {
		return new Rectangle(getLeftBound(), getTopBound() + TOP_TO_ARM,
				BOX_OFFSET, ARM_HEIGHT);
	}

	public Rectangle getRightBounds() {
		return new Rectangle(getRightBound() - BOX_OFFSET, getTopBound()
				+ TOP_TO_ARM, BOX_OFFSET, ARM_HEIGHT);
	}

	public Rectangle getInnerRightBounds() {
		return new Rectangle(getRightBound() - (DISTANCE_TO_FEET),
				getTopBound() + BOX_OFFSET, BOX_OFFSET, HEIGHT
						- (2 * BOX_OFFSET));
	}

	public Rectangle getBottomBounds() {
		return new Rectangle(getLeftBound() + DISTANCE_TO_FEET,
				getBottomBound() - BOX_OFFSET, WIDTH - (DISTANCE_TO_FEET * 2),
				BOX_OFFSET);
	}

	public Rectangle getTopBounds() {
		return new Rectangle(getLeftBound(), getTopBound(), WIDTH, BOX_OFFSET);
	}

	public Rectangle getFullBounds() {
		// return new Rectangle(getLeftBound(),getTopBound(),HEIGHT,WIDTH);
		return new Rectangle(getLeftBound(), getTopBound(), WIDTH, HEIGHT);
	}
	
	public Rectangle getBounds(){
		return getFullBounds();
	}

	private void setSpeed() {
		if (speedX < 0) {
			centerX += speedX;
		}
		if (speedX <= 0) {
			bg1.setSpeedX(0);
			bg2.setSpeedX(0);
		}
		if (centerX <= 200 && speedX > 0) {
			centerX += speedX;
		}
		if (speedX > 0 && centerX > 200) {
			bg1.setSpeedX(-MOVESPEED / 5);
			bg2.setSpeedX(-MOVESPEED / 5);
		}
	}

	public void onCollisionX(Collidable aggressor) {
		if(aggressor instanceof Tile){
			speedX = 0;
			centerX = centerX - MOVESPEED;
			String test = "";
		}
		else if(aggressor instanceof Enemy){
			this.setAlive(false);
		}
	}

	public void onCollisionY(Collidable aggressor) {
		speedY = 0;
		centerY--;
		jumped = false;
	}

	private void doJump() {
		// Handles Jumping
		if (jumped == true) {
			speedY += 1;
			if (!isFalling()) {
				if (centerY >= GROUND) {
					centerY = GROUND;
					speedY = 0;
					jumped = false;
				}
			}
		}
	}

	public void moveRight() {
		if (ducked == false) {
			speedX = MOVESPEED;
		}
	}

	public void moveLeft() {
		if (ducked == false) {
			speedX = -MOVESPEED;
		}
	}

	public void stopRight() {
		setMovingRight(false);
		stop();
	}

	public void stopLeft() {
		setMovingLeft(false);
		stop();
	}

	public void stop() {
		if (isMovingRight() == false && isMovingLeft() == false) {
			speedX = 0;
		}

		if (isMovingRight() == false && isMovingLeft() == true) {
			moveLeft();
		}

		if (isMovingRight() == true && isMovingLeft() == false) {
			moveRight();
		}
	}

	public void jump() {
		jump(-15);

	}

	public void jump(int speed) {
		if (jumped == false) {
			speedY = speed;
			jumped = true;
		}

	}

	public void shoot() {
		Projectile p = new Projectile(centerX + 50, centerY - 25);
		projectiles.add(p);
	}

	public int getCenterX() {
		return centerX;
	}

	public ArrayList<Projectile> getProjectiles() {
		return projectiles;
	}

	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}

	public int getCenterY() {
		return centerY;
	}

	public void setCenterY(int centerY) {
		this.centerY = centerY;
	}

	public boolean isJumped() {
		return jumped;
	}

	public void setJumped(boolean jumped) {
		this.jumped = jumped;
	}

	public int getSpeedX() {
		return speedX;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public int getSpeedY() {
		return speedY;
	}

	public void setSpeedY(int speedY) {
		this.speedY = speedY;
	}

	public boolean isMovingLeft() {
		return movingLeft;
	}

	public void setMovingLeft(boolean movingLeft) {
		this.movingLeft = movingLeft;
	}

	public boolean isMovingRight() {
		return movingRight;
	}

	public void setMovingRight(boolean movingRight) {
		this.movingRight = movingRight;
	}

	public boolean isDucked() {
		return ducked;
	}

	public void setDucked(boolean ducked) {
		this.ducked = ducked;
	}

	public int getLeftBound() {
		int test = getCenterX() - (WIDTH / 2);
		return test;
	}

	public int getRightBound() {
		return getLeftBound() + WIDTH;
	}

	public int getBottomBound() {
		int test = getCenterY() + (HEIGHT / 2);
		return test;
	}

	public int getTopBound() {
		int test = getBottomBound() - HEIGHT;
		return test;
	}

	public boolean isFalling() {
		return falling;
	}

	public void setFalling(boolean falling) {
		this.falling = falling;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}
}
