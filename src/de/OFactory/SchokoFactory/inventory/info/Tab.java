package de.OFactory.SchokoFactory.inventory.info;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import de.OFactory.SchokoFactory.main.Drawable;
import de.OFactory.SchokoFactory.main.Updateable;

public class Tab implements Drawable, Updateable{
	
	public static Color BG_COLOR_DISABLED = new Color(100, 100, 100);
	public static Color BG_COLOR_ACTIVE   = new Color(150, 150, 200);
	public static Color BG_COLOR_NORMAL   = new Color(180, 180, 180);
	
	private int x;
	private int y;
	private int width;
	
	private boolean active;
	private boolean disabled;
	private Image  display;
	private String name;
	
	public Tab(Image img, String name){
		this.display = img;
		this.setName(name);
	}
	
	public void update(GameContainer gc) {
		// TODO Auto-generated method stub
		
	}

	public void draw(Graphics g) {
		if(this.active)
			g.setColor(Tab.BG_COLOR_ACTIVE);
		else if(this.disabled)
			g.setColor(Tab.BG_COLOR_DISABLED);
		else
			g.setColor(Tab.BG_COLOR_NORMAL);
		
		g.fillRect(this.getX(), this.getY(), this.getWidth(), this.getWidth());
		g.setColor(Color.black);
		g.drawRect(this.getX(), this.getY(), this.getWidth(), this.getWidth());
		
	}
	
	
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public Image getDisplay() {
		return display;
	}
	public void setDisplay(Image display) {
		this.display = display;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}




	
		
}
