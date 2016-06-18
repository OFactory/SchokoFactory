package de.OFactory.SchokoFactory.inventory.info;

import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import de.OFactory.SchokoFactory.main.Drawable;
import de.OFactory.SchokoFactory.main.Updateable;

public class InfoPanel implements Drawable, Updateable{
	
	public static final Color BG_COLOR = new Color(200, 200, 200); //#FiftyShadesOfGrey
	
	//Position
	private int x;
	private int y;
	private int width;
	private int height;
	
	//PatternState : BUILDING_INFO
	//private Pattern curpattern; //nur wichtig bei: InfoState = Building-Information
	
	//PatternSTATE : BUILD
	private int page;
	
	private List<Tab> tabs;
	public Tab activetab;

	//standard Konstruktor of live
	public InfoPanel(int x, int y, int width, int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		
		
	}
	
	
	//update Tab position
	public void update(GameContainer gc) {
		
		int tabsize = this.getWidth()/tabs.size();
		int i = 0;

		for( Tab t : tabs ){
			t.update(gc);
			t.setX(this.getX() + i * tabsize);
			t.setY(gc.getHeight() - tabsize);
			t.setSize(tabsize);
			i++;
		}
		
		
	}

	public void draw(Graphics g) {
		
		
		
		
		//Hintergrund
		g.setColor(InfoPanel.BG_COLOR);
		g.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
		g.setColor(Color.black);
		g.drawRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
	
		//Tabs
		for(Tab t : tabs)
			t.draw(g);
		
		
		
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


	public List<Tab> getTabs() {
		return tabs;
	}

	public void setTabs(List<Tab> tabs) {
		this.tabs = tabs;
	}
	
	public void switchTab(int n) {
		this.activetab = tabs.get(n);
	}
	
	public void addTab(Tab t){
		this.tabs.add(t);
	}
	
	public void removeTab(Tab t){
		this.tabs.remove(t);
	}


	public int getPage() {
		return page;
	}


	public void setPage(int page) {
		this.page = page;
	}





	
	
	
	
}
