package de.OFactory.SchokoFactory.game;


import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;

import de.OFactory.SchokoFactory.game.patterns.Chemiefabrik;
import de.OFactory.SchokoFactory.game.patterns.Kakaoplantage;
import de.OFactory.SchokoFactory.game.patterns.Molkerei;
import de.OFactory.SchokoFactory.game.patterns.Rührer;
import de.OFactory.SchokoFactory.game.patterns.Tank;
import de.OFactory.SchokoFactory.game.patterns.Weizenfeld;
import de.OFactory.SchokoFactory.game.patterns.Wiese;
import de.OFactory.SchokoFactory.game.patterns.Zuckerplantage;
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
	
	/** Liefert ein Pattern eines Patternstates 
	 * unter Parametersierung aller Attribute des Patterns (x, y, id)
	 * Liefer null wenn keine PatternKlasse vorhanden
	 * 
	 * Patternklasse = extends Pattern;
	 * 
	 * @param x : x-Koordinate
	 * @param y : y-Koordinate
	 * @param ps : PatternState, dessen Klasse ermittelt werden soll
	 * @param id : id des Patterns in der Map(Map-spezifische Koordinate)
	 * @return Pattern p der Klasse des PatternStates ps
	 * @return null : Wenn es keine adäquate Klasse für den PatternState ps gibt
	 */
	public static Pattern getInstance(int x, int y, PatternState ps, int id){
		
		Pattern p = null;
		
		if(ps == PatternState.WIESE)
			p = new Wiese(x, y, id);
		else if(ps == PatternState.CHEMIEFABRIK)
			p = new Chemiefabrik(x, y, id);
		else if(ps == PatternState.RÜHRER)
			p = new Rührer(x, y, id);
		else if(ps == PatternState.LAGERHALLE)
			p = new Rührer(x, y, id);
		else if(ps == PatternState.GIEßER)
			p = new Rührer(x, y, id);
		else if(ps == PatternState.KAKAOPLANTAGE)
			p = new Kakaoplantage(x, y, id);
		else if(ps == PatternState.MOLKEREI)
			p = new Molkerei(x, y, id);
		else if(ps == PatternState.WEIZENFELD)
			p = new Weizenfeld(x, y, id);
		else if(ps == PatternState.TANK)
			p = new Tank(x, y, id);
		else if(ps == PatternState.ZUCKERPLANTAGE)
			p = new Zuckerplantage(x, y, id);
		else
			System.err.print("ERR <005>: Kann dem PatternState \"" + ps + "\" keine Klasse zuweisen! return null;");
		
		return p;
		
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
	
	public String toString(){
		return "Pattern(" + this.getId() + ", " + this.getPatternState() + ")";
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
