package de.OFactory.SchokoFactory.inventory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.RoundedRectangle;
import org.newdawn.slick.geom.Shape;

import de.OFactory.SchokoFactory.game.GameFonts;
import de.OFactory.SchokoFactory.main.Drawable;
import de.OFactory.SchokoFactory.main.ResourceManager;

public class Button implements Drawable{
	
	protected int ID;
	
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	
	protected Shape shape;
	
	protected Color hovered_color = new Color(150, 150, 255);
	protected Color normal_color  = new Color(150, 150, 150);
	protected Color clicked_color = new Color(100, 100, 100);

	protected String content;
	
	protected boolean hovered = false;
	protected boolean clicked = false;
	
	protected boolean clickable = true;
	protected int     cooldown_counter = 0;
	
	protected Font font = GameFonts.MAIN;
	
	
	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
	}

	protected Object source;
	
	protected ActionListener al;

	private int cornerradius;
	
	public Button(int ID, int x, int y, int width, int height, String content){
		
		this.ID = ID;
		
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		this.content = content;

		
	}
	
	public Button(int ID, int x, int y, int width, int height, String content, int cornerradius){
		
		this.ID = ID;
		
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		this.cornerradius = cornerradius;
		
		this.content = content;
		
		if(cornerradius != 0){
			this.shape = new RoundedRectangle(x, y, width, height, cornerradius);
		} else {
			this.shape = new Rectangle(x, y, width, height);
		}
		
		
	}
	
	public Button(int ID, int x, int y, int width, int height, String content, Color normal, Color hovered, Color clicked, int cornerradius){
		
		this.ID = ID;
		
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		this.content = content;
		
		this.cornerradius = cornerradius;
		
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
	
	public void update(Input in){
		
		
		if(this.shape.contains(in.getMouseX(), in.getMouseY())) {
			this.hovered = true;
		} else {
			this.hovered = false;
		}
		
		if(!clickable)
			cooldown_counter++;
		
		if(cooldown_counter == 10){
			cooldown_counter = 0;
			clickable = true;
			clicked = false;
		}
		
		if(hovered && clickable){
			if(in.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)){
				clickable = false;
				cooldown_counter = 0;
				clicked = true;
				al.actionPerformed(new ActionEvent(this, 0, "click"));
				ResourceManager.snd_click.play();
			}
		}
		
		
		
		
		
	}
	
	public void addActionListener(ActionListener al){
		this.al = al;
	}
	
	public void updateShape(){
		if(cornerradius != 0){
			this.shape = new RoundedRectangle(this.getX(), this.getY(), this.getWidth(), this.getHeight(), cornerradius);
		} else {
			this.shape = new Rectangle(this.getX(), this.getY(), this.getWidth(), this.getHeight());
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
			getFont().drawString( x + width/2 - ( getFont().getWidth(content)/2 ), y + height/2 - ( getFont().getHeight(content)/2 ) , content, new Color(256 - g.getColor().getRed(), 256 - g.getColor().getGreen(), 256 - g.getColor().getBlue()));
		}
		
	}
	
	
	
	
	//-------------------------------------------------------------------------
	
	//Setters & Getters
	
	public void setSource(Object o){
		this.source = o;
	}
	
	public String getContent(){
		return this.content;
	}
	
	public Object getSource(){
		return this.source;
	}
	
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
	
	public void setWidth(int width){
		this.width = width;
		updateShape();
	}
	
	public int getWidth() {
		return width;
	}
	
	public void setHeight(int height){
		this.height = height;
		updateShape();
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getID() {
		return this.ID;
	}
	
	
}
