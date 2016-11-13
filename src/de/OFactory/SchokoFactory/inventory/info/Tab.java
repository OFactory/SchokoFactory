package de.OFactory.SchokoFactory.inventory.info;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

import de.OFactory.SchokoFactory.game.GameFonts;
import de.OFactory.SchokoFactory.main.Drawable;
import de.OFactory.SchokoFactory.main.Updateable;

public abstract class Tab implements Drawable, Updateable{
	
	public static Color BG_COLOR_DISABLED = new Color(100, 100, 100);
	public static Color BG_COLOR_ACTIVE   = new Color(50, 100, 200);
	public static Color BG_COLOR_NORMAL   = new Color(180, 180, 180);
	
	private int x;
	private int y;
	private int size;
	private boolean disabled;
	private Image  display;
	private String name;
	
	private InfoPanel ip;
	
	public Tab(InfoPanel ip, Image img, String name){
		this.ip = ip;
		this.display = img;
		this.setName(name);
		
	}
	
	public void update(GameContainer gc) {
		Input in = gc.getInput();
		
		if((in.getMouseX() > x && in.getMouseX() < x + size) 
				&& (in.getMouseY() > y && in.getMouseY() < y + size)){
			
			if(in.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)){
				ip.activetab = this;
			}
			
			
		}
		
		updateContent(gc);
		
	}

	public void draw(Graphics g) {
		if(this.isActive()){ // Aktiv			
			this.drawContent(g);
			g.setColor(Tab.BG_COLOR_ACTIVE);
		} else if(this.isDisabled()) // Deaktiviert
			g.setColor(Tab.BG_COLOR_DISABLED);
		else //Normal
			g.setColor(Tab.BG_COLOR_NORMAL);
		
		g.fillRect(x, y, size, size);
		g.setColor(Color.black);
		g.drawRect(x, y, size, size);
		
		//g.drawImage(display.getScaledCopy(size/display.getWidth()), -30);
		//display.draw(x, y-500);
		int charwidth = GameFonts.MAIN.getWidth("" + name.charAt(0));
		int charheight = GameFonts.MAIN.getHeight("" + name.charAt(0));
		GameFonts.MAIN.drawString(x + (size-charwidth)/2, y + (size-charheight)/2 + 2, "" + name.charAt(0), Color.black);
	}
	
	public abstract void drawContent(Graphics g);
	public abstract void updateContent(GameContainer gc);
	
	public boolean isActive() {
		return ip.activetab == this;
	}
	public void setActive(boolean active) {
		ip.activetab = this;
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

	public void setInfoPanel(InfoPanel ip){
		this.ip = ip;
	}
	
	public InfoPanel getInfoPanel(){
		return ip;
	}
	
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
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
