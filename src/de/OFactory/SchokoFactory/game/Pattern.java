package de.OFactory.SchokoFactory.game;


import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;

import de.OFactory.SchokoFactory.game.patterns.Chemiefabrik;
import de.OFactory.SchokoFactory.game.patterns.Farm;
import de.OFactory.SchokoFactory.game.patterns.Gießer;
import de.OFactory.SchokoFactory.game.patterns.Kakaoplantage;
import de.OFactory.SchokoFactory.game.patterns.Lagerhalle;
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
	
	private HashMap<String, Object> pattern_info = new HashMap<String, Object>();
	
	private int xcoor;
	private int ycoor;
	
	public boolean hovered = false;
	public boolean selected = false;
	
	private Map m;

	public Pattern(Map map, int x, int y, PatternState ps, int id, int xcoor, int ycoor) {
		super(x, y, MainState.patternimg);
		this.m = map;
		this.ps = ps;
		this.setId(id);
		this.xcoor = xcoor;
		this.ycoor = ycoor;
		
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
	 * @return null : Wenn es keine ad�quate Klasse f�r den PatternState ps gibt
	 */
	public static Pattern getInstance(Map map,int x, int y, PatternState ps, int id, int xcoor, int ycoor){
		
		Pattern p = null;
		
		switch(ps){ //SWITCH-CASE FOR THE WIN
		
			case WIESE: p = new Wiese(map, x, y, id, xcoor, ycoor);
				break;
			case CHEMIEFABRIK: p = new Chemiefabrik(map, x, y, id, xcoor, ycoor);
				break;
			case RÜHRER: p = new Rührer(map, x, y, id, xcoor, ycoor);
				break;
			case LAGERHALLE: p = new Lagerhalle(map, x, y, id, xcoor, ycoor);
				break;
			case GIEßER: p = new Gießer(map, x, y, id, xcoor, ycoor);
				break;
			case KAKAOPLANTAGE: p = new Kakaoplantage(map, x, y, id, xcoor, ycoor);
				break;
			case MOLKEREI: p = new Molkerei(map, x, y, id, xcoor, ycoor);
				break;
			case WEIZENFELD: p = new Weizenfeld(map, x, y, id, xcoor, ycoor);
				break;
			case TANK: p = new Tank(map, x, y, id, xcoor, ycoor);
				break;
			case ZUCKERPLANTAGE: p = new Zuckerplantage(map, x, y, id, xcoor, ycoor);
				break;
			case FARM: p = new Farm(map, x, y, id, xcoor, ycoor);
				break;
			default: System.err.print("ERR <005>: Kann dem PatternState \"" + ps + "\" keine Klasse zuweisen! return null;");
				break;
			
		}

		return p;
		
	}
	
	/**
	 *  Updated die Texturen auf die Aktuelle Skalierung (MainState.curscale);
	 */
	public void updateTexture(){
		this.setImage(MainState.patternimg);
	}
	

	@Override
	public void update(GameContainer gc) {
		
		Polygon p = new Polygon();
		int off_x = this.getCurrentImage().getScaledCopy(MainState.curpatternscale).getWidth();
		int off_y = this.getCurrentImage().getScaledCopy(MainState.curpatternscale).getHeight();
		p.addPoint(getX() + off_x*0.1F,   getY() + off_y*0.82F);
		p.addPoint(getX() + off_x/2,      getY() + off_y*0.68F);
		p.addPoint(getX() + off_x*0.9F,   getY() + off_y*0.82F);		
		p.addPoint(getX() + off_x/2,      getY() + off_y*0.95F);
		
		
		this.setClickBox(p);
		this.selected = MainState.selected_pattern == this;
		
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
			MainState.curpatterninfo = this.getXcoor()+" "+this.getYcoor() + " (" + this.getPatternState() + ") ";
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
		if(this.getX() > gc.getWidth() || this.getX() + this.getCurrentImage().getWidth()*MainState.curpatternscale < 0
				|| this.getY() > gc.getHeight() || this.getY() + this.getCurrentImage().getHeight()*MainState.curpatternscale < 0)
			this.rendered = false; // --> Nicht rendern
		else
			this.rendered = true;
		
		
		
		//Spezifischer Update Kontext jeder Pattern-Art
		updateContext();
		//Spezifischer Update Kontext f�r die Info eines Pattern jeder Pattern-Art
		updatePatternInfo();
		//System.out.println("Lots Of Love"); LOL starring
	}

	/**
    * 
    * Diese Methode gibt die direkt angrenzenden Nachbarn eines Feldes zur�ck.
    * Die Felder werden nach Himmelsrichtungen
    * 
    *  no: Nordosten,
    *  so: Suedosten,
    *  sw: Suedwesten,
    *  nw: Nordwesten
    * 
    * geordnet.   
    * 					
    * 				   no
    * 				nw	P so
    * 				   sw
    * 
    * Gibt null zur�ck, wenn es einen der Pattern nicht gibt!
    * Bitte bei Verwendung null checken!
    * 
    * @return ArrayList<Pattern> direkt angrenzende Nachbarn
    */
	public ArrayList<Pattern> getPatternNeighbours(){
	   
		ArrayList<Pattern> ps = new ArrayList<Pattern>();
		ArrayList<Point> dirs = new ArrayList<Point>();
		dirs.add(new Point(-1,  0)); //NO
		dirs.add(new Point( 0,  1)); //SO
		dirs.add(new Point( 1,  0)); //SW
		dirs.add(new Point( 0, -1)); //NW
    
	    for(Point dir : dirs){
	    	
	    	boolean success = false;
	    	
	    	for(Pattern pt : this.getMap()) {
	    		
	    		if(success == false){ //Suche noch nicht erfolgreich
		    		if(pt.getXCoordinate() == this.getXCoordinate() + dir.getX() &&
		    				pt.getYCoordinate() == this.getYCoordinate() + dir.getY()) {
		    			ps.add(pt);
		    			success = true;
		    		}
	    		}
		    }
	    	
	    	if(success == false)
	    		ps.add(null);
	    }
	    
	    
	    return ps;
	}
	
	/**
    * 
    * Diese Methode gibt die indirekt angrenzenden Nachbarn eines Feldes zur�ck.
    * Die Felder sind demnach �ber die Punkte des Feldes verkn�pft und teilen sich nicht eine Kante.
    * Die Felder werden nach Himmelsrichtungen
    * 
    *  n: Norden,
    *  no: Nordosten,
    *  o: Osten,
    *  so:S�dosten,
    *  s: S�den,
    *  sw:S�dwesten,
    *  w: Westen,
    *  nw:Nordwesten
    *  
    * geordnet.   
    * 
    * 		 N  no  O
    * 		nw	P  so
    * 		 W  sw  S
    * 
    * Gibt null zur�ck, wenn es einen der Pattern nicht gibt!
    * Bitte bei Verwendung null checken!
    * 
    * @return ArrayList<Pattern> anliegende Pattern
    */
	public ArrayList<Pattern> getPatternNears(){
	   
		
		ArrayList<Pattern> ps = new ArrayList<Pattern>();
		ArrayList<Point> dirs = new ArrayList<Point>();
		dirs.add(new Point(-1, -1)); //N
		dirs.add(new Point(-1,  0)); //NO
		dirs.add(new Point(-1,  1)); //O
		dirs.add(new Point( 0,  1)); //SO
		dirs.add(new Point( 1,  1)); //S
		dirs.add(new Point( 1,  0)); //SW
		dirs.add(new Point( 1, -1)); //W
		dirs.add(new Point( 0, -1)); //NW
    
		 for(Point dir : dirs){
		    	
		    	boolean success = false;
		    	
		    	for(Pattern pt : this.getMap()) {
		    		
		    		if(success == false){ //Suche noch nicht erfolgreich
			    		if(pt.getXCoordinate() == this.getXCoordinate() + dir.getX() &&
			    				pt.getYCoordinate() == this.getYCoordinate() + dir.getY()) {
			    			ps.add(pt);
			    			success = true;
			    		}
		    		}
			    }
		    	
		    	if(success == false)
		    		ps.add(null);
		    }
	    
	    
	    return ps;
	}
	
	public abstract void updateContext();
	
	

	@Override
	public void draw(Graphics g) {
		if(this.isRendered()) {
			
			Color filter = Color.white;
			
			if(this.hovered){ //GEHOVERED
				if(MainState.clicked == this) { //GEKLICKT
					filter = new Color(100, 255, 255); // GEKLICKT
				} else { // GEHOVERED
					if(this instanceof Wiese && MainState.curpatternstate != null && MainState.curpatternstate != PatternState.WIESE)
						filter = new Color(255, 100, 100); //GEHOVERED + WIESE
					else
						filter = new Color(200, 200, 150); // nur GEHOVERED
				}

			} else if(selected){ //AUSGEW�HLT
				filter = new Color(150, 255, 150);
			} //NORMAL => WEI�
			
			this.getCurrentImage().getScaledCopy(MainState.curpatternscale).draw(this.getX(), this.getY(), filter);
			
			
		}
		
		/* DEBUG CLICKBOX
		g.setColor(Color.pink);
		g.setLineWidth(2F);
		
		if(this.getClickBox() != null)
			g.draw(this.getClickBox());
		*/
		
	}
	
	public String toString(){
		return "Pattern(" + this.getId() + ", " + this.getPatternState() + ")";
	}
	
	/**
	 * Updated Pattern-Info (HashMap) der Werte eines Objektes
	 * @return void //TODO �berall implementieren
	 */
	public abstract void updatePatternInfo();
	
	public void putPatternInfo(String s, Object o){
		pattern_info.putIfAbsent(s, o);
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

	public Map getMap() {
		return m;
	}

	public void setMap(Map m) {
		this.m = m;
	}

	/**
	 * @return die X-Koordinate des Patterns auf der Map m
	 */
	public int getXCoordinate() {
		return xcoor;
	}

	/**
	 * @param xcoor Die X-Koordinate des Patterns auf der Map m
	 */
	public void setXCoordinate(int xcoor) {
		this.xcoor = xcoor;
	}
	
	/**
	 * @return die Y-Koordinate des Patterns auf der Map m
	 */
	public int getYCoordinate() {
		return ycoor;
	}

	/**
	 * @param xcoor Die Y-Koordinate des Patterns auf der Map m
	 */
	public void setYCoordinate(int ycoor) {
		this.ycoor = ycoor;
	}

	public int getXcoor() {
		return xcoor;
	}

	public void setXcoor(int xcoor) {
		this.xcoor = xcoor;
	}

	public int getYcoor() {
		return ycoor;
	}

	public void setYcoor(int ycoor) {
		this.ycoor = ycoor;
	}

	/**
	 * @return the pattern_info
	 */
	public HashMap<String, Object> getPatternInfo() {
		return pattern_info;
	}

	/**
	 * @param pattern_info the pattern_info to set
	 */
	public void setPatternInfo(HashMap<String, Object> pattern_info) {
		this.pattern_info = pattern_info;
	}



}
