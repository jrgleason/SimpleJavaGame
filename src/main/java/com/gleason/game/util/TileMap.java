package com.gleason.game.util;

import java.util.Random;

public class TileMap {
	public static void main(String args[]) {
		@SuppressWarnings("unused")
		TileMap tp = new TileMap();
	}

	public TileMap() {
		int[][] TileMap = new int[30][50];

		System.out.println("New TileMap created.");
		Random r = new Random();

		int rows = TileMap.length;
		int columns = TileMap[1].length;
		
		printTiles(rows, columns, TileMap, r);

	}

	private void printTiles(int rows, int columns, int[][] TileMap, Random r) {

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				TileMap[i][j] = r.nextInt(5);
				System.out.print(" " + TileMap[i][j]);
			}

			System.out.println("");

		}
	}
}
