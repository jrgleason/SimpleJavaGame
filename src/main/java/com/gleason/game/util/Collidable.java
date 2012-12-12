package com.gleason.game.util;

import java.awt.Rectangle;

public interface Collidable {
	void onCollisionX(Collidable aggressor);
	void onCollisionY(Collidable aggressor);
	Rectangle getBounds();
}
