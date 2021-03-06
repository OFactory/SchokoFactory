package de.OFactory.SchokoFactory.inventory;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
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
	
	double dx; //Abstand x-Achse zwischen Punkten
	
	protected Shape shape;
	
	private ArrayList<Integer>[] lines;
	ArrayList<Integer> points = new ArrayList<Integer>();
	
	private final Color[] LINE_COLORS = { Color.red, Color.blue, Color.yellow, Color.green, /*Color.magenta, Color.orange*/ new Color(0, 0, 255, 0.3F), new Color(255, 255, 0, 0.3F), new Color(0, 255, 0, 0.5F)};
	
	
	
	
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
		
		g.setLineWidth(1);
		
		int max = getMax(lines[0]);
		for (ArrayList<Integer> l:lines)
			if (getMax(l) > max)
				max = getMax(points);
		
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

		
		
		

		if (lines[0].size() != 0) {
			g.setLineWidth(2);
			int i = 0;
			for (ArrayList<Integer> points: lines) {
				
	
				int px = 0;
				int apx = 0;
				int apy = (int)((float)points.get(0)/max*this.height * 0.9);
				dx = (double)this.width/points.size();
				if (dx < 1) {
					dx = 1;
				} else if (dx > this.width/5)
					dx = this.width/5;	
				
				//for (int py:points) {
				for (int n = 0; n < points.size();n++) {
					
	
					px = (int)(n*dx);
					int py = points.get(n);
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
			/*
			int df = 30;
			while (this.width/(int)lines[0].size()*df < 20)
				df *= 2;
		
			for (int f = 0; f <= lines[0].size(); f+=df) {
				
				g.drawLine((float)(this.x + f*dx), (float)this.y + this.height, (float)(this.x + f*dx), (float)this.y + this.height + 5);
				g.drawString(f+"", (float)(this.x + f*dx - 3), (float)this.y + this.height + 5);
			}*/
		
		
		//Monate
			/*
			for (long f = MainState.m.getTime()-lines[0].size()+1 +30; f <= MainState.m.getTime(); f+=30) {
				int shift = 0;
				if (MainState.m.getTime()-lines[0].size() +1 != 0) {
					shift = (int)(MainState.m.getTime() % 30);		// irgendwie wackelt hier ein Frame ... fixme
	
				} 
	
				g.drawLine(this.x + (int)((f-(MainState.m.getTime()-lines[0].size()+1))*dx - shift*dx), this.y, this.x + (int)((f-(MainState.m.getTime()-lines[0].size()+1))*dx - shift*dx), this.y + this.height);
				g.drawString((f+1)/30+"", this.x + (int)((f-(MainState.m.getTime()-lines[0].size()+1))*dx - dx*15 - shift*dx) - 5, this.y + this.height + 5);
			}*/
		}
		
	}

	public void update(GameContainer gc) {

		this.shape = new Rectangle(this.x, this.y, this.width, this.height);
		
	}
	
	public void addPoints(int[] points) {
		
		for (int i = 0; i <= points.length-1; i++) {
			this.lines[i].add(points[i]);
			if (lines[i].size() > this.width)
				lines[i].remove(0);
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
	
	public int getX() {
		return this.x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
}
