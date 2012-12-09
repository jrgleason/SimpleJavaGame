package com.gleason.game.characters;

import java.util.ArrayList;

import com.gleason.game.StartingClass;
import com.gleason.game.environment.Background;
import com.gleason.game.visuals.Tile;
import com.gleason.game.weapon.Projectile;

public class Robot {
	
	static final int JUMPSPEED = -15;
	static final int MOVESPEED = 5;
	static final int GROUND = 382;
	static final int COLLISION_BOX_WIDTH = 40;
	
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
		return centerX - (.5*COLLISION_BOX_WIDTH);
				
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
}
