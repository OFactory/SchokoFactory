package de.OFactory.SchokoFactory.game;

import java.awt.Point;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;
import java.awt.image.BufferedImage;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Shape;

import de.OFactory.SchokoFactory.main.Drawable;
import de.OFactory.SchokoFactory.main.Updateable;

public abstract class GameObject implements Updateable, Drawable{
	
	private int x;
	private int y;
	private Image[] img;
	private int curimg = 0;
	protected int img_b = 0;
	protected int img_e = 0;
	
	protected int i = 0;
	private int delay = 10;
	
	private Shape clickbox;
	
	
	public GameObject( int x, int y, Image[] img){
		this.x = x;
		this.y = y;
		this.img = img;
		
	}
	
	public void update(GameContainer gc){
		
		//Animation tick
		
		if(!(img_e == img_b)){ // Animation vorhanden
			this.i++;
		
		
			if(this.i == getDelay()){
				if(this.curimg > img_e)
					this.curimg = img_b;
				else
					this.curimg++;
				
				this.i = 0;
			}
			
			
			
		}
		
		
		
	}

	
	
	/////////////////////////////////// Setter und Getter ///////////////////
	
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getX() {
		return this.x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getY() {
		return this.y;
	}
	
	public void setImage(Image[] img) {
		this.img = img;
	}
	
	public Image[] getImage() {
		return this.img;
	}
	
	public void setCurrentImagePosition(int f){
		this.curimg = f;
	}
	
	public int getCurrentImagePosition(){
		return this.curimg;
	}
	
	public void setPatternFrame(int f) {
		this.img_b = f;
		this.img_e = f;
		this.curimg = f;
	}
	
	public void setFrameLoop(int f1, int f2){
		this.img_b = f1;
		this.img_e = f2;
		this.curimg = f1 + 1;
	}
	

	public Image getCurrentImage() {
		return this.img[curimg];
	}
	
	public void setCurrentImage(Image img) {
		this.img[curimg] = img;
	}

	public int getDelay() {
		return delay;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}
	
	public boolean checkOpaqueColor(int x, int y){
		
		//Punkte in Bezug auf die jeweiligen Images
		int xpos = x - this.x;
		int ypos = y - this.y;
				
		Color c = this.getCurrentImage().getColor(xpos, ypos);
		//System.out.println(c.getAlpha());
		
		System.out.println(xpos + " | " + ypos + ";  " + c.getRed() + "-" + c.getGreen() + "-" + c.getBlue() + "->-" + c.getAlpha());	
		
		if(c.getGreen() == 255 && c.getBlue() == 255 && c.getRed() == 255){
			return false;
		}
		
		return true;
		
	}

	public Shape getClickbox() {
		return clickbox;
	}

	public void setClickbox(Shape clickbox) {
		this.clickbox = clickbox;
	}
	
	
	

}
