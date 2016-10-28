package de.OFactory.SchokoFactory.inventory;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.RoundedRectangle;
import org.newdawn.slick.geom.Shape;

import de.OFactory.SchokoFactory.game.GameFonts;
import de.OFactory.SchokoFactory.main.Drawable;

public class CakeChart implements Drawable{
	
	protected int ID;
	
	protected int x;
	protected int y;
	protected int radius;
	
	protected Shape shape;
	
	protected Color hovered_color;
	protected Color normal_color;
	protected Color clicked_color;
	
	
	protected String content;
	
	protected boolean hovered = false;
	protected boolean clicked = false;
	
	
	
	public CakeChart(int ID, int x, int y, int radius, String content){
		
		this.ID = ID;
		
		this.x = x;
		this.y = y;
		this.radius = radius;

		
		this.content = content;
		

		this.shape = new Circle(x+radius, y+radius, radius);

		
		this.clicked_color = new Color(100, 100, 100);
		this.hovered_color = new Color(150, 150, 255);
		this.normal_color  = new Color(150, 150, 150);
		
	}
	
	public CakeChart(int ID, int x, int y, int width, int height, String content, Color normal, Color hovered, Color clicked, int cornerradius){
		
		this.ID = ID;
		
		this.x = x;
		this.y = y;

		
		this.content = content;
		

		

		this.shape = new Circle(x+radius, y+radius, radius);

		
		this.clicked_color = clicked;
		this.normal_color  = normal;
		this.hovered_color = hovered;
		
	}
	
	//-------------------------------------------------------------------------
	
	public void update(Input in){
		
		
		if(this.shape.contains(in.getMouseX(), in.getMouseY())) {
			this.hovered = true;
		} else {
			this.hovered = false;
		}
		
		if(this.hovered){
			if(in.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)){
				this.clicked = true;
			}
		}
		
		
		
		
	}
	
	public void updateShape(){

		this.shape = new Circle(this.getX(), this.getY(), this.getRadius());

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
			GameFonts.SUB.drawString( this.x + 2, this.y + this.radius, content, new Color(256 - g.getColor().getRed(), 256 - g.getColor().getGreen(), 256 - g.getColor().getBlue()));
		}
		
	}
	
	
	
	
	//-------------------------------------------------------------------------
	
	//Setters & Getters
	
	public void setX(int x) {
		this.x = x;
		updateShape();
	}
	
	public int getX() {
		return this.x;
	}
	
	public void setY(int y) {
		this.y = y;
		updateShape();
	}
	
	public int getY() {
		return this.y;
	}
	
	private float getRadius() {
		return this.radius;
	}
	
	public void setRadius(int r) {
		this.radius = r;
		updateShape();
	}
	
	public int getID() {
		return this.ID;
	}
	
	
}
