package com.gleason.game;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.gleason.game.characters.Enemy;
import com.gleason.game.characters.HellBoy;
import com.gleason.game.characters.Robot;
import com.gleason.game.environment.Background;
import com.gleason.game.visuals.Animation;
import com.gleason.game.visuals.Tile;
import com.gleason.game.visuals.Tile.TileType;
import com.gleason.game.weapon.Projectile;

public class StartingClass extends Applet implements Runnable, KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Robot myBot;
	private Image image, currentSprite, character, character2, character3,
			characterDown, characterJumped, background, hellboy, hellboy2,
			hellboy3, hellboy4, hellboy5;
	public static Image tilegrassTop, tilegrassBot, tilegrassLeft,
			tilegrassRight, tiledirt, tileocean;
	private ArrayList<Tile> tilearray = new ArrayList<Tile>();
	private List<Enemy> enemies = new ArrayList<Enemy>();
	private URL base;
	private Graphics second;
	private static Background bg1, bg2;
	private Animation anim, hanim;

	@Override
	public void init() {
		setSize(800, 480);
		setBackground(Color.BLUE);
		setFocusable(true);
		addKeyListener(this);
		Frame frame = (Frame) this.getParent().getParent();
		frame.setTitle("Q-Bot Alpha");
		try {
			base = getDocumentBase();
		} catch (Exception e) {
			// TODO: handle exception
		}

		setupImages();

	}

	private void setupImages() {
		character = getImage(base, "data/character.png");
		character2 = getImage(base, "data/character2.png");
		character3 = getImage(base, "data/character3.png");

		characterDown = getImage(base, "data/down.png");
		characterJumped = getImage(base, "data/jumped.png");

		hellboy = getImage(base, "data/heliboy.png");
		hellboy2 = getImage(base, "data/heliboy2.png");
		hellboy3 = getImage(base, "data/heliboy3.png");
		hellboy4 = getImage(base, "data/heliboy4.png");
		hellboy5 = getImage(base, "data/heliboy5.png");

		background = getImage(base, "data/background.png");

		tiledirt = getImage(base, "data/tiledirt.png");
		tileocean = getImage(base, "data/tileocean.png");
		tilegrassTop = getImage(base, "data/tilegrasstop.png");
		tilegrassBot = getImage(base, "data/tilegrassbot.png");
		tilegrassLeft = getImage(base, "data/tilegrassleft.png");
		tilegrassRight = getImage(base, "data/tilegrassright.png");

		anim = new Animation();
		anim.addFrame(character, 1250);
		anim.addFrame(character2, 50);
		anim.addFrame(character3, 50);
		anim.addFrame(character2, 50);

		hanim = new Animation();
		hanim.addFrame(hellboy, 100);
		hanim.addFrame(hellboy2, 100);
		hanim.addFrame(hellboy3, 100);
		hanim.addFrame(hellboy4, 100);
		hanim.addFrame(hellboy5, 100);
		hanim.addFrame(hellboy4, 100);
		hanim.addFrame(hellboy3, 100);
		hanim.addFrame(hellboy2, 100);

		currentSprite = anim.getImage();
	}

	@Override
	public void start() {
		bg1 = new Background(0, 0);
		bg2 = new Background(2160, 0);

		// Initialize Tiles

		try {
			loadMap("data/map1.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		HellBoy firstEnemy = new HellBoy(340, 360);
		HellBoy secondEnemy = new HellBoy(700, 360);
		enemies.add(firstEnemy);
		enemies.add(secondEnemy);
		myBot = new Robot();
		Thread thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			myBot.update();
			if (myBot.isJumped()) {
				currentSprite = characterJumped;
			} else if (myBot.isJumped() == false && myBot.isDucked() == false) {
				currentSprite = anim.getImage();
			}
			for (Projectile p : myBot.getProjectiles()) {
				if (p.isVisible() == true) {
					p.update();
				} else {
					if (myBot.getProjectiles().indexOf(p) != -1) {
						myBot.getProjectiles().remove(p);
						break;
					}
				}
			}
			updateTiles();
			for (Enemy e : enemies) {
				e.update();
			}
			bg1.update();
			bg2.update();

			animate();
			repaint();
			try {
				Thread.sleep(17);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private boolean checkTiles(){
		boolean returnValue = false;
		for(Tile t : tilearray){
			if(t.getTileX() <= myBot.getRightEdge() && (t.getTileX()+t.WIDTH) >= myBot.getRightEdge()){
				return true;
			}
					
		}
		return returnValue;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {

		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			System.out.println("Move up");
			break;

		case KeyEvent.VK_DOWN:
			currentSprite = characterDown;
			if (myBot.isJumped() == false) {
				myBot.setDucked(true);
				myBot.setSpeedX(0);
			}
			break;

		case KeyEvent.VK_LEFT:
			if(!checkTiles()){
				myBot.moveLeft();
				myBot.setMovingLeft(true);
			}
			break;

		case KeyEvent.VK_RIGHT:
			if(!checkTiles()){
				myBot.moveRight();
				myBot.setMovingRight(true);
			}
			break;

		case KeyEvent.VK_SPACE:
			myBot.jump();
			break;

		case KeyEvent.VK_CONTROL:
			if (myBot.isDucked() == false && myBot.isJumped() == false) {
				myBot.shoot();
			}
			break;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			System.out.println("Stop moving up");
			break;

		case KeyEvent.VK_DOWN:
			currentSprite = anim.getImage();
			myBot.setDucked(false);
			break;

		case KeyEvent.VK_LEFT:
			myBot.stopLeft();
			break;

		case KeyEvent.VK_RIGHT:
			myBot.stopRight();
			break;

		case KeyEvent.VK_SPACE:
			break;
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Graphics g) {
		if (image == null) {
			image = createImage(this.getWidth(), this.getHeight());
			second = image.getGraphics();
		}
		second.setColor(getBackground());
		second.fillRect(0, 0, getWidth(), getHeight());
		second.setColor(getForeground());
		paint(second);
		g.drawImage(image, 0, 0, this);
	}

	public void animate() {
		anim.update(10);
		hanim.update(50);
	}

	private void updateTiles() {
		for (Tile t : tilearray) {
			t.update();
		}
	}

	private void paintTiles(Graphics g) {
		for (Tile t : tilearray) {
			g.drawImage(t.getTileImage(), t.getTileX(), t.getTileY(), this);
		}
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(background, bg1.getBgX(), bg1.getBgY(), this);
		g.drawImage(background, bg2.getBgX(), bg2.getBgY(), this);

		paintTiles(g);

		for (Projectile p : myBot.getProjectiles()) {
			g.setColor(Color.YELLOW);
			g.fillRect(p.getX(), p.getY(), 10, 5);
		}

		g.drawImage(currentSprite, myBot.getCenterX() - 61,
				myBot.getCenterY() - 63, this);
		for (Enemy e : enemies) {
			g.drawImage(hanim.getImage(), e.getxLocation() - 48,
					e.getyLocation() - 48, this);
		}

	}

	private void loadMap(String filename) throws IOException {
		ArrayList lines = new ArrayList();
		int width = 0;
		int height = 0;

		BufferedReader reader = new BufferedReader(new FileReader(filename));
		while (true) {
			String line = reader.readLine();
			// no more lines to read
			if (line == null) {
				reader.close();
				break;
			}

			if (!line.startsWith("!")) {
				lines.add(line);
				width = Math.max(width, line.length());

			}
		}
		height = lines.size();

		for (int j = 0; j < 12; j++) {
			String line = (String) lines.get(j);
			for (int i = 0; i < width; i++) {
				System.out.println(i + "is i ");

				if (i < line.length()) {
					char ch = line.charAt(i);
					TileType type = TileType.getByOrdinal(Character.getNumericValue(ch));
					if(type != null){
						Tile t = new Tile(i, j, type);
						tilearray.add(t);
					}
				}

			}
		}

	}

	public static Background getBg1() {
		return bg1;
	}

	public static void setBg1(Background bg1) {
		StartingClass.bg1 = bg1;
	}

	public static Background getBg2() {
		return bg2;
	}

	public static void setBg2(Background bg2) {
		StartingClass.bg2 = bg1;
	}
}
