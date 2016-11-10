package de.OFactory.SchokoFactory.inventory;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;

import de.OFactory.SchokoFactory.main.Drawable;

public class CakeChart implements Drawable{
	
	protected int ID;
	
	protected int x;
	protected int y;
	protected int radius;
	
	protected Shape shape;

	protected String content;
	
	private int[] slices;
	
	protected boolean hovered = false;
	protected boolean clicked = false;
	
	private final Color[] SLICE_COLORS = { Color.blue, Color.yellow, Color.green, Color.red, Color.magenta, Color.orange };
	
	
	
	public CakeChart(int ID, int x, int y, int radius, String content){
		
		this.ID = ID;
		
		this.x = x;
		this.y = y;
		this.radius = radius;

		
		this.content = content;
		
		

		this.shape = new Circle(x+radius, y+radius, radius);
		
	}
	//-------------------------------------------------------------------------
	
	public void update(Input in){
		
		
		if(this.shape.contains(in.getMouseX(), in.getMouseY())) {
			this.hovered = true;
		} else {
			this.hovered = false;
		}
		
		if(this.hovered){
			if(in.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)){
				this.clicked = true;
			}
		}
		
		
		
		
	}
	
	public void updateShape(){

		this.shape = new Circle(this.getX(), this.getY(), this.getRadius());

	}
	


	public void draw(Graphics g){
		
		
		double total = 0.0D;
	    for (int i = 0; i < slices.length; i++) {
	      total += slices[i];
	    }

	    double curValue = 0.0D;
	    int startAngle = 0;
	    for (int i = 0; i < slices.length; i++) {
	    	startAngle = (int) (curValue  *360/ total);
	    	int arcAngle = (int) (slices[i]  *360/ total);
	    	g.setColor(SLICE_COLORS[i]);
	    	g.fillArc(x, y, radius*2, radius*2, startAngle, startAngle+arcAngle);
	    	curValue += slices[i];
	    	
	    }
		
		//g.fill(this.shape);
		
		g.setColor(Color.black);
		g.draw(this.shape);
		
		
		/*
		if(this.content != "") {
			GameFonts.SUB.drawString( this.x + 2, this.y + this.radius, content, new Color(256 - g.getColor().getRed(), 256 - g.getColor().getGreen(), 256 - g.getColor().getBlue()));
		} */
	}
	
	
	
	
	//-------------------------------------------------------------------------
	
	//Setters & Getters
	
	public void setX(int x) {
		this.x = x;
		updateShape();
	}
	
	public int getX() {
		return this.x;
	}
	
	public void setY(int y) {
		this.y = y;
		updateShape();
	}
	
	public int getY() {
		return this.y;
	}
	
	private float getRadius() {
		return this.radius;
	}
	
	public void setRadius(int r) {
		this.radius = r;
		updateShape();
	}
	
	public int getID() {
		return this.ID;
	}

	/**
	 * @return the slices
	 */
	public int[] getSlices() {
		return slices;
	}

	/**
	 * @param slices the slices to set
	 */
	public void setSlices(int[] slices) {
		this.slices = slices;
	}
	
	
}
