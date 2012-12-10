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
    
    public enum TileType{
    	OCEAN(StartingClass.tileocean,1),
    	GROUND(StartingClass.tiledirt,5),
    	GRASS_RIGHT(StartingClass.tilegrassRight,6),
    	GRASS_LEFT(StartingClass.tilegrassLeft,4),
    	GRASS_TOP(StartingClass.tilegrassTop,8),
    	GRASS_BOTTOM(StartingClass.tilegrassBot,2);
    	private Image image;
    	private int ordinal;
    	private TileType(Image image,int ordinal){
    		this.image = image;
    		this.ordinal = ordinal;
    	}
    	public Image getImage(){
    		return image;
    	}
    	public int getOrdinal(){
    		return ordinal;
    	}
    	public static TileType getByOrdinal(int ordinal){
    		for(TileType t : TileType.values()){
    			if(t.getOrdinal() == ordinal){
    				return t;
    			}
    		}
    		//Return default
    		return null;
    	}
    }
    
//    public double getLeftBound(){
//    	
//    }
    
    public Tile(int x, int y, TileType type) {
        tileX = x * WIDTH;
        tileY = y * HEIGHT;

        this.type = type;
        if(type != null){
        	tileImage = type.getImage();
        }
    }
    
    
    boolean up = false;
    public void update() {
        // TODO Auto-generated method stub
    	switch(this.type){
    	case OCEAN:
    		if (bg.getSpeedX() == 0){
                speedX = 0;
            }else{
                speedX = -2;
            }
    		break;
    	case GROUND:
    		speedX = bg.getSpeedX()*5;
    	}
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
	
	public int getLeftBound(){
		return getTileX();
	}
	public int getRightBound(){
		return getLeftBound()+WIDTH;
	}
	public int getBottomBound(){
		int test = getTopBound()+HEIGHT;
		return test;
	}
	public int getTopBound(){
		int test = getTileY();
		return getTileY();
		
	}
	public Rectangle getBounds() {
	    return new Rectangle(getLeftBound(), getTopBound(), WIDTH, HEIGHT);
	}
}
