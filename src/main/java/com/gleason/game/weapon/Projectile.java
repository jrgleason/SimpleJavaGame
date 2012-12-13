package com.gleason.game.weapon;

import java.awt.Rectangle;

import com.gleason.game.characters.Enemy;
import com.gleason.game.characters.Robot;
import com.gleason.game.util.Collidable;

public class Projectile implements Collidable{

	private int x, y, speedX;
	private boolean visible;
	public static int HEIGHT = 5;
	public static int WIDTH=10;
	private boolean used = false;
	private final Robot mybot;
	
	public Projectile(int startX, int startY, Robot mybot){
		this.mybot = mybot;
		x = startX;
		y = startY;
		speedX = 7;
		visible = true;
	}
	
	public void update(){
		x += speedX;
		if (x > 800){
			visible = false;
		}
		
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getSpeedX() {
		return speedX;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public void onCollisionX(Collidable aggressor) {
		// TODO Auto-generated method stub
	}

	public void onCollisionY(Collidable aggressor) {
	}

	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle(getX(),getY(),WIDTH, HEIGHT);
	}

	public boolean isUsed() {
		return used;
	}

	public void setUsed(boolean used) {
		this.used = used;
	}

	public Robot getMybot() {
		return mybot;
	}
	
}
