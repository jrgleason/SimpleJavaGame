package com.gleason.game.characters;

import java.awt.Rectangle;
import java.util.ArrayList;

import com.gleason.game.StartingClass;
import com.gleason.game.environment.Background;
import com.gleason.game.visuals.Tile;
import com.gleason.game.weapon.Projectile;

public class Robot {
	
	static final int JUMPSPEED = -15;
	static final int MOVESPEED = 5;
	public static final int HEIGHT = 126;
	public static final int WIDTH = 122;
	static final int COLLISION_BOX_WIDTH = WIDTH;
	
	public static final int GROUND = Background.BG_HEIGHT-Tile.HEIGHT-(HEIGHT/2);
	
	private int centerX = 100;
	private int centerY = GROUND;
	private boolean jumped = false;
	private boolean movingLeft = false;
	private boolean movingRight = false;
	private boolean ducked = false;
	
	private static Background bg1 = StartingClass.getBg1();                 
    private static Background bg2 = StartingClass.getBg2();
   

	private int speedX = 0;
	private int speedY = 1;
	
	private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();

	
	public double getLeftEdge(){
		return centerX - (.5*COLLISION_BOX_WIDTH);
				
	}
	public double getRightEdge(){
		return centerX + (.5*COLLISION_BOX_WIDTH);
				
	}
	
	public void update() {
		setSpeed();
		centerY += speedY;
		if (centerY + speedY >= GROUND) {
			centerY = GROUND;
		}
		doJump();
		// Prevents going beyond X coordinate of 0
		if (centerX + speedX <= 60) {
			centerX = 61;
		}
	}
	
	private static int BOX_OFFSET = 5;
	private static int SQUEEZE_OFFSET=10;
	
	public Rectangle getLeftBounds() {
		
		int test1 = HEIGHT-(BOX_OFFSET*2);
	    return new Rectangle(getLeftBound(), getTopBound()-BOX_OFFSET, BOX_OFFSET, HEIGHT-SQUEEZE_OFFSET);
	}
	public Rectangle getRightBounds() {
		
		int test1 = HEIGHT-(BOX_OFFSET*2);
	    return new Rectangle(getRightBound()-BOX_OFFSET, getTopBound()-SQUEEZE_OFFSET, BOX_OFFSET, HEIGHT-(2*SQUEEZE_OFFSET));
	}
	
	public Rectangle getBottomBounds() {
		int test1 = HEIGHT-(BOX_OFFSET*2);
	    return new Rectangle(getLeftBound()+SQUEEZE_OFFSET, getBottomBound()-BOX_OFFSET, WIDTH-(SQUEEZE_OFFSET*2), BOX_OFFSET);
	}
	
	
	private void setSpeed(){
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
		if (speedX > 0 && centerX > 200){
			bg1.setSpeedX(-MOVESPEED/5);
			bg2.setSpeedX(-MOVESPEED/5);
		}
	}

	public void onCollisionX(){
		speedX = 0;
		centerX--;
	}
	
	public void onCollisionY(){
		speedY = 0;
		centerY--;
		jumped = false;
	}
	
	private void doJump() {
		// Handles Jumping
		if (jumped == true) {
			speedY += 1;
			if (centerY + speedY >= GROUND) {
				centerY = GROUND;
				speedY = 0;
				jumped = false;
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
		if (jumped == false) {
			speedY = -15;
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
	public int getLeftBound(){
		int test = getCenterX()-(WIDTH/2);
		return test;
	}
	public int getRightBound(){
		return getLeftBound()+WIDTH;
	}
	public int getBottomBound(){
		int test = getCenterY()+(HEIGHT/2);
		return test;
	}
	public int getTopBound(){
		int test = getBottomBound()-HEIGHT;
		return test;
	}
}
