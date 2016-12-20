package de.OFactory.SchokoFactory.inventory;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

import de.OFactory.SchokoFactory.main.Drawable;
import de.OFactory.SchokoFactory.main.Updateable;

public class LinesChart extends LineChart implements Updateable, Drawable{





	int x;
	int y;
	int width;
	protected int height;
	
	int dx; //Abstand x-Achse zwischen Punkten
	
	protected Shape shape;
	
	private ArrayList<Integer>[] lines;
	ArrayList<Integer> points = new ArrayList<Integer>();
	
	private final Color[] LINE_COLORS = { Color.red, Color.blue, Color.yellow, Color.green, Color.magenta, Color.orange };
	
	
	
	
	public LinesChart(int x, int y, int width, int height) {
		super(x, y, width, height);

		this.x = x;
		this.y = y;
		
		this.width = width;
		this.height = height;
		
		this.shape = new Rectangle(x, y, width, height);
		
	}


	
	public void draw(Graphics g) {

		g.draw(shape);
		
		
		
		int max = getMax(lines[0]);
		/*for (ArrayList<Integer> l:lines)
			if (getMax(l) > max)
				max = getMax(points);*/
		
		//int charheight = GameFonts.SUB.getHeight("b");
		//g.drawString(" "+max, (int)this.x+this.width, (int)(this.y + this.height*0.1 - charheight/2));
		
		
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
		
		

		
		int i = 0;
		for (ArrayList<Integer> points: lines) {
			

			int px = 0;
			int apx = 0;
			int apy = (int)((float)points.get(0)/max*this.height * 0.9);
			dx = (int)((float)this.width/points.size());
			if (dx < 1) {
				dx = 1;
			} else if (dx > this.width/5)
				dx = this.width/5;	
			
			for (int py:points) {
				
				px = px + dx;

				py = (int)((float)py/max * this.height * 0.9);
				g.setColor(LINE_COLORS[i]);
				g.drawLine(this.x + apx, this.y + this.height - apy, this.x + px, this.y + this.height - py);

				
				apx = px;
				apy = py;
				
			}
		i++;
		}
		
		g.setLineWidth(1);
		g.setColor(Color.darkGray);
		
		for (int f = 0; f <= lines[0].size(); f++) {
			
			g.drawLine(this.x + f*dx, this.y + this.height, this.x + f*dx, this.y + this.height + 5);
					
		}
		

		
		
	}

	public void addPoints(int[] points) {
		
		for (int i = 0; i <= points.length-1; i++) {
			this.lines[i].add(points[i]);
			if (this.lines[i].size() > this.width)
				this.lines[i].remove(0);
		}
			
		

	}
	
	/**
	 * @return the lines
	 */
	public ArrayList<Integer>[] getLines() {
		return lines;
	}
	
	/**
	 * @param slices the slices to set
	 */
	public void setLines(ArrayList<Integer>[] lines) {
		this.lines = lines;
	}
}
