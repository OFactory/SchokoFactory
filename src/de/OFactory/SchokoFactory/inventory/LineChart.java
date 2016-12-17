package de.OFactory.SchokoFactory.inventory;

import java.util.ArrayList;

import org.newdawn.slick.Color;
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
		
		if (points.size() > 0) {
			
				
			int max = getMax(points);
			
			
			int z = 0;
			int d = 100;
			while ((int)max/d > 6)
				d *= 10;
				d /= 4;
				
				
			for (int i = 0; i < (int)max/d+1; i ++) {
				int y = (int)((float)i*d/max * this.height * 0.9);
				if (i % 2 == 0) {
					g.setColor(Color.darkGray);
					g.drawString(z+"", (int)this.x+this.width+10, (int)this.y + this.height - y - 10);
					g.drawLine(this.x,this.y + this.height - y, this.x+this.width+5, this.y + this.height - y);
				} else {
					g.setColor(Color.lightGray);
					g.drawLine(this.x,this.y + this.height - y, this.x+this.width, this.y + this.height - y);
				}
				
				z += d;
			}
			g.setColor(Color.blue);
			g.setLineWidth(2);
			
			int px = 0;
			int apx = 0;
			int apy = (int)((float)points.get(0)/max*this.height * 0.9);
			int dx = (int)((float)this.width/points.size());
			if (dx < 1) {
				dx = 1;
			} else if (dx > this.width/5)
				dx = this.width/5;	
			
			for (int py:points) {
				
				px = px + dx;

				py = (int)((float)py/max * this.height * 0.9);
				g.drawLine(this.x + apx, this.y + this.height - apy, this.x + px, this.y + this.height - py);
				
				apx = px;
				apy = py;
				
			}
			
			g.setLineWidth(1);
		}
		
	}

	public void update(GameContainer gc) {

		this.shape = new Rectangle(this.x, this.y, this.width, this.height);
		
	}
	
	public void addPoint(int value) {
		points.add(value);
		if (points.size() > this.width)
			points.remove(0);

	}
	
	public int getMax(ArrayList<Integer> list){
	    int max = Integer.MIN_VALUE;
	    for(int i=0; i<list.size(); i++){
	        if(list.get(i) > max){
	            max = list.get(i);
	        }
	    }
	    return max;
	}
}
