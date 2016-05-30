package de.OFactory.SchokoFactory.inventory;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.RoundedRectangle;
import org.newdawn.slick.geom.Shape;

import de.OFactory.SchokoFactory.main.Drawable;

public class Button implements Drawable{
	
	protected int ID;
	
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	
	protected Shape shape;
	
	protected Color hovered_color;
	protected Color normal_color;
	protected Color clicked_color;
	
	protected String content;
	
	boolean hovered = false;
	boolean clicked = false;
	
	
	
	public Button(int ID, int x, int y, int width, int height, String content, int cornerradius){
		
		this.ID = ID;
		
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		this.content = content;
		
		if(cornerradius != 0){
			this.shape = new RoundedRectangle(x, y, width, height, cornerradius);
		} else {
			this.shape = new Rectangle(x, y, width, height);
		}
		
		this.clicked_color = new Color(100, 100, 100);
		this.hovered_color = new Color(150, 150, 255);
		this.normal_color  = new Color(150, 150, 150);
		
	}
	
	public Button(int ID, int x, int y, int width, int height, String content, Color normal, Color hovered, Color clicked, int cornerradius){
		
		this.ID = ID;
		
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		this.content = content;
		
		if(cornerradius != 0){
			this.shape = new RoundedRectangle(x, y, width, height, cornerradius);
		} else {
			this.shape = new Rectangle(x, y, width, height);
		}
		
		this.clicked_color = clicked;
		this.normal_color  = normal;
		this.hovered_color = hovered;
		
	}
	
	//-------------------------------------------------------------------------
	
	@SuppressWarnings("static-access")
	public void update(Input in){

		if(this.shape.contains(in.getMouseX(), in.getMouseY())) {
			this.hovered = true;
		} else {
			this.hovered = false;
		}
		
		if(this.hovered){
			if(in.isMouseButtonDown(in.MOUSE_LEFT_BUTTON)){
				this.clicked = true;
			}
		}
		
		
		
		
	}
	
	public void draw(Graphics g){
		if(this.clicked) {
			g.setColor(clicked_color);
			
			if(this.hovered){ // Durschnittsfarbe
				g.setColor(new Color((clicked_color.getRed()+hovered_color.getRed())/2, (clicked_color.getGreen()+hovered_color.getGreen())/2, (clicked_color.getBlue()+hovered_color.getBlue())/2));
			}	
		} else if(this.hovered)
			g.setColor(hovered_color);
		else
			g.setColor(normal_color);
		
		
		g.fill(this.shape);
		
		g.setColor(Color.black);
		g.draw(this.shape);
		
		if(this.content != "") {
			g.drawString(content, this.x + this.width/2, this.y + this.height/2);
		}
		
	}
	
	
	
	
	//-------------------------------------------------------------------------
	
	//Setters & Getters
	
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
	
	public void setWidth(int width){
		this.width = width;
	}
	
	public int getWidth() {
		return width;
	}
	
	public void setHeight(int height){
		this.height = height;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getID() {
		return this.ID;
	}
	
	
}
