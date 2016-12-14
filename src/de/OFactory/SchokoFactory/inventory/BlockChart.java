package de.OFactory.SchokoFactory.inventory;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

import de.OFactory.SchokoFactory.main.Drawable;
import de.OFactory.SchokoFactory.main.Updateable;

public class BlockChart implements Updateable, Drawable{

	int x;
	int y;
	int width;
	protected int height;
	
	protected Shape shape;
	
	ArrayList<Integer> points = new ArrayList<Integer>();
	
	
	public BlockChart(int x, int y, int width, int height){
		
		
		this.x = x;
		this.y = y;
		
		this.width = width;
		this.height = height;
		
		this.shape = new Rectangle(x, y, width, height);
	}
	
	public void draw(Graphics g) {

		g.draw(this.shape);
		g.setColor(Color.blue);
		g.setLineWidth(2);
		if (points.size() > 0) {
			int max = getMax(points);
			int px = 0;

			int dx = (int)((float)this.width/points.size());
			if (dx == 0) {
				dx = 1;
			} else if (dx > this.width/5)
				dx = this.width/5;
			
			for (int py:points) {
				
				
				int thickness =  dx/3*2;
				if (thickness == 0)
					thickness = 1;
				py = (int)((float)py/max * this.height * 0.9);
				g.fillRect(this.x + px, this.y + this.height - py, thickness, py);
				
				
				px = px + dx;

			}
		}
		g.setLineWidth(1);
	}

	public void update(GameContainer gc) {

		this.shape = new Rectangle(this.x, this.y, this.width, this.height);
		
	}
	
	public void addPoint(int value) {
		points.add(value);

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
