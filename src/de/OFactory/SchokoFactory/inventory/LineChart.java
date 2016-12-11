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
	
	private final Color[] SLICE_COLORS = { Color.blue, Color.yellow, Color.green, Color.red, Color.magenta, Color.orange };
	
	private ArrayList<Integer> points = new ArrayList<Integer>();
	
	
	public LineChart(int x, int y, int width, int height){
		
		
		this.x = x;
		this.y = y;
		
		this.width = width;
		this.height = height;
		
		this.shape = new Rectangle(x, y, width, height);
	}
	
	public void draw(Graphics g) {

		g.draw(this.shape);
		
		int point_width = 20;
		int space = ( width - point_width*points.size() ) / ( points.size()+1 );
		int curx = x + space;
		
		int max_height = 0;
		for(int value: points){
			if(value > max_height)
				max_height = value;
		}
		
		if(max_height != 0){
			//int px = 0;
			//int apx = 0;
			//int apy = points.get(0);
			//int dx = this.width/points.size();
			
			System.out.println("PUNKTE - LINECHART");
			
			for (int value:points) {
				
				System.out.println("\t " + value);
				
				
				double hoehe = (value/max_height) *height;
				System.out.println(hoehe);
				
				
				g.setColor(Color.green);
				g.fillRect(curx, y + height - (int) hoehe, point_width, (int) hoehe);
				
				curx += point_width + space;
				
				/*
				px = px + dx;
				py = py/this.height*10;
				g.drawLine(this.x + apx, this.y + this.height - apy, this.x + px, this.y + this.height - py);
				apx = px;
				apy = py;
				*/
				
				
				
				
				
			}
		}
	}

	public void update(GameContainer gc) {

		this.shape = new Rectangle(this.x, this.y, this.width, this.height);
		
	}
	
	public void addPoint(int value) {
		if(points.size() > 10){
			points.remove(0);
		}
		
		System.out.println(points.size());
		
		points.add(value);

	}
	
	public ArrayList<Integer> getPoints() {
		return points;
	}

	public void setPoints(ArrayList<Integer> points) {
		this.points = points;
	}

}
