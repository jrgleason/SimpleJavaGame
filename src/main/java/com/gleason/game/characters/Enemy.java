package com.gleason.game.characters;

import java.awt.Rectangle;

import com.gleason.game.StartingClass;
import com.gleason.game.environment.Background;
import com.gleason.game.util.Collidable;
import com.gleason.game.weapon.Projectile;

public class Enemy implements Collidable {
	protected int maxHealth, currentHealth, weaponPower, speedX, xLocation,
			yLocation;
	private static final int WIDTH = 96;
	private static final int HEIGHT = 96;
	private boolean alive = true;
	private Background bg = StartingClass.getBg1();

	public void update() {
		xLocation += speedX;
		speedX = bg.getSpeedX() * 5;
	}

	public void die() {
		setAlive(false);
	}

	public void attack() {
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	public int getCurrentHealth() {
		return currentHealth;
	}

	public void setCurrentHealth(int currentHealth) {
		this.currentHealth = currentHealth;
	}

	public int getWeaponPower() {
		return weaponPower;
	}

	public void setWeaponPower(int weaponPower) {
		this.weaponPower = weaponPower;
	}

	public int getSpeedX() {
		return speedX;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public int getxLocation() {
		return xLocation;
	}

	public void setxLocation(int xLocation) {
		this.xLocation = xLocation;
	}

	public int getyLocation() {
		return yLocation;
	}

	public void setyLocation(int yLocation) {
		this.yLocation = yLocation;
	}

	public Background getBg() {
		return bg;
	}

	public void setBg(Background bg) {
		this.bg = bg;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public void onCollisionX(Collidable aggressor) {
		// TODO Auto-generated method stub
		if (isAlive()) {
			if (aggressor instanceof Projectile) {
				Projectile p = (Projectile) aggressor;
				if (!p.isUsed()) {
					this.setAlive(false);
					p.setVisible(false);
					p.setUsed(true);
				}
			}
		}
	}

	public void onCollisionY(Collidable aggressor) {
		// TODO Auto-generated method stub

	}

	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle(getxLocation() - (WIDTH / 2), getyLocation()
				- (HEIGHT / 2), WIDTH, HEIGHT);
	}
}
