package de.OFactory.SchokoFactory.inventory.info;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import de.OFactory.SchokoFactory.main.Drawable;
import de.OFactory.SchokoFactory.main.Updateable;

public class EcoScreen  implements Drawable, Updateable{

	//Position
	private int x;
	private int y;
	private int width;
	private int height;
	
	private boolean show;
	
	public EcoScreen(int x, int y, int width, int height) {
		
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
	}

	@Override
	public void update(GameContainer gc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics g) {
		if (this.show) {
			//Hintergrund
			g.setColor(InfoPanel.BG_COLOR);
			g.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
			g.setColor(Color.black);
			g.drawRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
		}
	}

	
	public void toggle() {
		if (this.show) 
			this.show = false;
		else
			this.show = true;
	}
	
	public boolean isShow() {
		return show;
	}

	public void setShow(boolean show) {
		this.show = show;
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

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

}
