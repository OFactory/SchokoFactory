package de.OFactory.SchokoFactory.game;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Shape;

import de.OFactory.SchokoFactory.main.Drawable;
import de.OFactory.SchokoFactory.main.Updateable;

public abstract class GameObject implements Updateable, Drawable{
	
	private int x;
	private int y;

	

	
	private Shape clickbox;
	
	
	public GameObject( int x, int y){
		this.x = x;
		this.y = y;

		
	}
	
	public void update(GameContainer gc){
		
		//Animation tick is in every single animated class
		

		
		
		
	}

	public void draw(Graphics g){
		
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
	


	
	/*public boolean checkOpaqueColor(int x, int y){
		
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
		
	}*/

	public Shape getClickbox() {
		return clickbox;
	}

	public void setClickbox(Shape clickbox) {
		this.clickbox = clickbox;
	}
	
	
	

}
