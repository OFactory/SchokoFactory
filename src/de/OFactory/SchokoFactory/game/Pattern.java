package de.OFactory.SchokoFactory.game;


import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

import de.OFactory.SchokoFactory.game.patterns.Tank;
import de.OFactory.SchokoFactory.game.patterns.Wiese;
import de.OFactory.SchokoFactory.main.MainState;

public abstract class Pattern extends GameObject{
	
	private PatternState ps;
	private boolean rendered = true;
	private Shape clickbox;
	
	private int id;
	
	public boolean hovered = false;
	

	public Pattern(int x, int y, PatternState ps, int id) {
		super(x, y, MainState.patternimg);
		this.ps = ps;
		this.setId(id);
		
	}

	@Override
	public void update(GameContainer gc) {
		
		Polygon p = new Polygon();
		p.addPoint(this.getX() + this.getCurrentImage().getWidth()*0.1F,   this.getY() + this.getCurrentImage().getHeight()*0.82F);
		p.addPoint(this.getX() + this.getCurrentImage().getWidth()/2,      this.getY() + this.getCurrentImage().getHeight()*0.68F);
		p.addPoint(this.getX() + this.getCurrentImage().getWidth()*0.9F,   this.getY() + this.getCurrentImage().getHeight()*0.82F);		
		p.addPoint(this.getX() + this.getCurrentImage().getWidth()/2,      this.getY() + this.getCurrentImage().getHeight()*0.95F);
		
		
		this.setClickBox(p);

		
		//Animation tick
		
		if(!(img_e == img_b)){ // Animation vorhanden
			this.i++;
		
		
			if(this.i == getDelay()){
				if(this.getCurrentImagePosition() >= img_e)
					this.setCurrentImagePosition(img_b);
				else
					this.setCurrentImagePosition(this.getCurrentImagePosition()+1);
				
				this.i = 0;
			}
			
			
			
		}
		
		Input in = gc.getInput();

		this.setY(this.getY() + MainState.allv_y);
		this.setX(this.getX() + MainState.allv_x);
		
		
		if(this.getClickBox().contains(in.getMouseX(), in.getMouseY())){
			this.hovered = true;
			MainState.curpatterninfo = "P" + this.getId() + " (" + this.getPatternState() + ") ";
		} else {
			this.hovered = false;
		}
		
		
		if(this.hovered && in.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)){
			MainState.clicked = this;
			
		} else {
			if(MainState.clicked == this)
				MainState.clicked = null;
		}
		
		//Pattern aus Bildschirmrand?
		if(this.getX() > gc.getWidth() || this.getX() + this.getCurrentImage().getWidth() < 0
				|| this.getY() > gc.getHeight() || this.getY() + this.getCurrentImage().getHeight() < 0)
			this.rendered = false; // --> Nicht rendern
		else
			this.rendered = true;
		
		
		
		//Spezifischer Update Kontext jeder Pattern art
		
		updateContext();
		
	}

	public abstract void updateContext();

	@Override
	public void draw(Graphics g) {
		if(this.isRendered()) {
			if(this.hovered){
				this.getCurrentImage().draw(this.getX(), this.getY(), new Color(150, 150, 200));
				
				if(MainState.clicked == this) {
					this.getCurrentImage().draw(this.getX(), this.getY(), new Color(200, 150, 150));
				}
				
			} else {	
				this.getCurrentImage().draw(this.getX(), this.getY());
			}
			
			
		}
		
	}
	
	////////////////////////////////////////// Setter & Getter ///////////

	public PatternState getPatternState() {
		return ps;
	}

	public void setPatternState(PatternState ps) {
		this.ps = ps;
	}

	public boolean isRendered() {
		return rendered;
	}

	public void setRendered(boolean rendered) {
		this.rendered = rendered;
	}
	
	public Shape getClickBox(){
		return this.clickbox;
	}
	
	public void setClickBox(Shape s){
		this.clickbox = s;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
