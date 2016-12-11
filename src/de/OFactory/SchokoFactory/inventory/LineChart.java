package de.OFactory.SchokoFactory.inventory;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

import de.OFactory.SchokoFactory.main.Drawable;
import de.OFactory.SchokoFactory.main.Updateable;

public class LineChart implements Updateable, Drawable{

	int x;
	int y;
	int width;
	protected int height;
	
	protected Shape shape;
	
	ArrayList<Integer> points = new ArrayList<Integer>();
	
	
	public LineChart(int x, int y, int width, int height){
		
		
		this.x = x;
		this.y = y;
		
		this.width = width;
		this.height = height;
		
		this.shape = new Rectangle(x, y, width, height);
	}
	
	public void draw(Graphics g) {

		g.draw(this.shape);
		int px = 0;
		int apx = 0;
		int apy = points.get(0);
		int dx = this.width/points.size();
		
		for (int py:points) {
			
			px = px + dx;
			py = py/this.height*10;
			g.drawLine(this.x + apx, this.y + this.height - apy, this.x + px, this.y + this.height - py);
			apx = px;
			apy = py;
			
		}
	}

	public void update(GameContainer gc) {

		this.shape = new Rectangle(this.x, this.y, this.width, this.height);
		
	}
	
	public void addPoint(int value) {
		points.add(value);

	}

}
