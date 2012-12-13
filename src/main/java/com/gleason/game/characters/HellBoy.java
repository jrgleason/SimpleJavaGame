package com.gleason.game.characters;

public class HellBoy extends Enemy {
	public HellBoy(int centerX, int centerY){
		setxLocation(centerX);
		setyLocation(centerY);
	}

	@Override
	int getWidth() {
		// TODO Auto-generated method stub
		return 70;
	}

	@Override
	int getHeight() {
		// TODO Auto-generated method stub
		return 70;
	}

	@Override
	int getPoints() {
		// TODO Auto-generated method stub
		return 1;
	}
}
