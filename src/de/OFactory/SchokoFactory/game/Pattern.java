package de.OFactory.SchokoFactory.game;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;

import de.OFactory.SchokoFactory.game.patterns.Chemiefabrik;
import de.OFactory.SchokoFactory.game.patterns.Farm;
import de.OFactory.SchokoFactory.game.patterns.Gew�chshaus;
import de.OFactory.SchokoFactory.game.patterns.Gie�er;
import de.OFactory.SchokoFactory.game.patterns.Hof;
import de.OFactory.SchokoFactory.game.patterns.Lagerhalle;
import de.OFactory.SchokoFactory.game.patterns.Molkerei;
import de.OFactory.SchokoFactory.game.patterns.PRB�ro;
import de.OFactory.SchokoFactory.game.patterns.R�hrer;
import de.OFactory.SchokoFactory.game.patterns.Tank;
import de.OFactory.SchokoFactory.game.patterns.Feld;
import de.OFactory.SchokoFactory.game.patterns.Wiese;
import de.OFactory.SchokoFactory.game.patterns.Labor;
import de.OFactory.SchokoFactory.main.MainState;
import de.OFactory.SchokoFactory.main.ResourceManager;

public abstract class Pattern extends GameObject implements Serializable {

	private static final long serialVersionUID = -1672562077428577840L;

	private PatternState ps;

	private boolean rendered = true;
	private Shape clickbox;

	private int id;

	private HashMap<String, Object> pattern_info = new HashMap<String, Object>();

	private int xcoor;
	private int ycoor;

	private int hovery;

	public boolean hovered = false;
	public boolean selected = false;
	
	//private Image img;
	private int frame = 0;
	private int frame_start = 0;	//frame_start = frame_end -> keine Animation
	private int frame_end = 0;
	private int frame_total = 1;

	private int i = 0;

	private int delay = 10;
	
	public Pattern(int x, int y, PatternState ps, int id, int xcoor,
			int ycoor) {
		super(x, y);
		this.ps = ps;
		this.setId(id);
		this.xcoor = xcoor;
		this.ycoor = ycoor;
		

	}

	/**
	 * Liefert ein Pattern eines Patternstates unter Parametersierung aller
	 * Attribute des Patterns (x, y, id) Liefer null wenn keine PatternKlasse
	 * vorhanden
	 * 
	 * Patternklasse = extends Pattern;
	 * 
	 * @param x
	 *            : x-Koordinate
	 * @param y
	 *            : y-Koordinate
	 * @param ps
	 *            : PatternState, dessen Klasse ermittelt werden soll
	 * @param id
	 *            : id des Patterns in der Map(Map-spezifische Koordinate)
	 * @return Pattern p der Klasse des PatternStates ps
	 * @return null : Wenn es keine ad�quate Klasse f�r den PatternState ps gibt
	 */
	public static Pattern getInstance(Map map, int x, int y, PatternState ps,
			int id, int xcoor, int ycoor) {

		Pattern p = null;

		switch (ps) { // SWITCH-CASE FOR THE WIN

		case WIESE:
			p = new Wiese(x, y, id, xcoor, ycoor);
			break;
		case CHEMIEFABRIK:
			p = new Chemiefabrik(x, y, id, xcoor, ycoor);
			break;
		case R�HRER:
			p = new R�hrer(x, y, id, xcoor, ycoor);
			break;
		case LAGER:
			p = new Lagerhalle( x, y, id, xcoor, ycoor);
			break;
		case GIE�ER:
			p = new Gie�er(x, y, id, xcoor, ycoor);
			break;
		case FELD:
			p = new Feld(x, y, id, xcoor, ycoor);
			break;
		case HOF:
			p = new Hof(x, y, id, xcoor, ycoor);
			break;
		case MOLKEREI:
			p = new Molkerei(x, y, id, xcoor, ycoor);
			break;
		case LABOR:
			p = new Labor(x, y, id, xcoor, ycoor);
			break;
		case TANK:
			p = new Tank(x, y, id, xcoor, ycoor);
			break;
		case GEW�CHSHAUS:
			p = new Gew�chshaus(x, y, id, xcoor, ycoor);
			break;
		case FARM:
			p = new Farm(x, y, id, xcoor, ycoor);
			break;
		case PRB�RO:
			p = new PRB�ro(x, y, id, xcoor, ycoor);
			break;
		default:
			System.err.print("ERR <005>: Kann dem PatternState \"" + ps
					+ "\" keine Klasse zuweisen! return null;");
			break;

		}

		return p;

	}

	/**
	 * Updated die Texturen auf die Aktuelle Skalierung (MainState.curscale);
	 */
	public void updateTexture() {
		
	}
	
	public void setAnimation(int start, int end, int total, int delay){
		this.frame_start = start;
		this.frame_end = end;
		this.frame_total = total;
		this.delay = delay;
		
	}

	@Override
	public void update(GameContainer gc) {
		
		if (this.frame_start < this.frame_end){		// falls eine Animation eingestellt ist
			updateAnimation();
			
		} else{
			
		}
		
		Polygon p = new Polygon();
		Image source = this.ps.getSource();
		Image img = source.getSubImage(frame*ResourceManager.pattern_width, 0, ResourceManager.pattern_width, ResourceManager.pattern_height);
		
		int off_x = img.getScaledCopy((float) MainState.curpatternscale).getWidth();
		int off_y = img.getScaledCopy((float) MainState.curpatternscale).getHeight();
		p.addPoint(getX() + off_x * 0.05F, getY() + off_y * 0.75F);
		p.addPoint(getX() + off_x * 0.5F,  getY() + off_y * 0.55F);
		p.addPoint(getX() + off_x * 0.95F, getY() + off_y * 0.75F);
		p.addPoint(getX() + off_x * 0.5F,  getY() + off_y * 0.95F);

		this.setClickBox(p);
		this.selected = MainState.field.selected_pattern == this;

		Input in = gc.getInput();

		this.setY(this.getY() + MainState.allv_y);
		this.setX(this.getX() + MainState.allv_x);

		if (this.getClickBox().contains(in.getMouseX(), in.getMouseY())
				&& MainState.isMouseInPlayArea(gc)) {
			this.hovered = true;
			MainState.curpatterninfo = this.getXcoor() + " " + this.getYcoor()
					+ " (" + this.getPatternState() + ") ";
		} else {
			this.hovered = false;
		}

		if (this.hovered && in.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
			MainState.field.clicked = this;

		} else {
			if (MainState.field.clicked == this)
				MainState.field.clicked = null;
		}

		// Pattern aus Bildschirmrand?
		if (this.getX() > gc.getWidth()
				|| this.getX() + img.getWidth()
						* MainState.curpatternscale < 0
				|| this.getY() > gc.getHeight()
				|| this.getY() + img.getHeight()
						* MainState.curpatternscale < 0)
			this.rendered = false; // --> Nicht rendern
		else
			this.rendered = true;

		// Spezifischer Update Kontext jeder Pattern-Art
		updateContext();
		// Spezifischer Update Kontext f�r die Info eines Pattern jeder
		// Pattern-Art
		updatePatternInfo();
		// System.out.println("Lots Of Love"); LOL starring
	}
	
	private void updateAnimation(){
		if (i >= delay){
			i = 0;
			if (frame+1 > frame_end)
				frame = frame_start;
			else {
				//Image source = this.ps.getSource();
				//setImg(source.getSubImage(frame*source.getWidth()/frame_total, 0, source.getWidth()/frame_total, source.getHeight()));
				frame++;
			}
				
		}
		i++;
	}

	/**
	 * 
	 * Diese Methode gibt die direkt angrenzenden Nachbarn eines Feldes zur�ck.
	 * Die Felder werden nach Himmelsrichtungen
	 * 
	 * no: Nordosten, so: Suedosten, sw: Suedwesten, nw: Nordwesten
	 * 
	 * geordnet.
	 * 
	 * no nw P so sw
	 * 
	 * Gibt null zur�ck, wenn es einen der Pattern nicht gibt! Bitte bei
	 * Verwendung null checken!
	 * 
	 * @return ArrayList<Pattern> direkt angrenzende Nachbarn
	 */
	public ArrayList<Pattern> getPatternNeighbours() {

		ArrayList<Pattern> ps = new ArrayList<Pattern>();
		ArrayList<Point> dirs = new ArrayList<Point>();
		dirs.add(new Point(-1, 0)); // NO
		dirs.add(new Point(0, 1)); // SO
		dirs.add(new Point(1, 0)); // SW
		dirs.add(new Point(0, -1)); // NW

		for (Point dir : dirs) {

			boolean success = false;

			for (Pattern pt : MainState.field) {

				if (success == false) { // Suche noch nicht erfolgreich
					if (pt.getXCoordinate() == this.getXCoordinate()
							+ dir.getX()
							&& pt.getYCoordinate() == this.getYCoordinate()
									+ dir.getY()) {
						ps.add(pt);
						success = true;
					}
				}
			}

			if (success == false)
				ps.add(null);
		}

		return ps;
	}

	/**
	 * 
	 * Diese Methode gibt die indirekt angrenzenden Nachbarn eines Feldes
	 * zur�ck. Die Felder sind demnach �ber die Punkte des Feldes verkn�pft und
	 * teilen sich nicht eine Kante. Die Felder werden nach Himmelsrichtungen
	 * 
	 * n: Norden, no: Nordosten, o: Osten, so:S�dosten, s: S�den, sw:S�dwesten,
	 * w: Westen, nw:Nordwesten
	 * 
	 * geordnet.
	 * 
	 * N no O nw P so W sw S
	 * 
	 * Gibt null zur�ck, wenn es einen der Pattern nicht gibt! Bitte bei
	 * Verwendung null checken!
	 * 
	 * @return ArrayList<Pattern> anliegende Pattern
	 */
	public ArrayList<Pattern> getPatternNears() {

		ArrayList<Pattern> ps = new ArrayList<Pattern>();
		ArrayList<Point> dirs = new ArrayList<Point>();
		dirs.add(new Point(-1, -1)); // N
		dirs.add(new Point(-1, 0)); // NO
		dirs.add(new Point(-1, 1)); // O
		dirs.add(new Point(0, 1)); // SO
		dirs.add(new Point(1, 1)); // S
		dirs.add(new Point(1, 0)); // SW
		dirs.add(new Point(1, -1)); // W
		dirs.add(new Point(0, -1)); // NW

		for (Point dir : dirs) {

			boolean success = false;

			for (Pattern pt : MainState.field) {

				if (success == false) { // Suche noch nicht erfolgreich
					if (pt.getXCoordinate() == this.getXCoordinate()
							+ dir.getX()
							&& pt.getYCoordinate() == this.getYCoordinate()
									+ dir.getY()) {
						ps.add(pt);
						success = true;
					}
				}
			}

			if (success == false)
				ps.add(null);
		}

		return ps;
	}

	public abstract void updateContext();

	@Override
	public void draw(Graphics g) {
		if (this.isRendered()) {

			Color filter = Color.white;

			if (this.hovered) { // GEHOVERED
				if (MainState.field.clicked == this) { // GEKLICKT
					filter = new Color(100, 255, 255); // GEKLICKT
				} else { // GEHOVERED
					if (this instanceof Wiese
							&& MainState.curpatternstate != null
							&& MainState.curpatternstate != PatternState.WIESE)
						filter = new Color(255, 100, 100); // GEHOVERED + WIESE
					else
						filter = new Color(200, 200, 150); // nur GEHOVERED
				}

			} else if (selected) { // AUSGEW�HLT
				filter = new Color(150, 255, 150);
			} // NORMAL => WEI�

			// Image img = ResourceManager.pttrn_chemfab.getScaledCopy((float)
			// MainState.curpatternscale);//this.getCurrentImage().getScaledCopy((float)
			// MainState.curpatternscale);

			
			Image source = this.ps.getSource();
			Image img = source.getSubImage(frame*ResourceManager.pattern_width, 0, ResourceManager.pattern_width, ResourceManager.pattern_height);
			
			img.getScaledCopy((float) MainState.curpatternscale).draw(this.getX(), this.getY(), filter);
			/*
			 * D�BUG g.setColor(Color.green); g.drawRect(getX(), getY(),
			 * img.getWidth(), img.getHeight()); g.drawString(this.getX() +
			 * " | " + this.getY(), this.getX()-10, this.getY()-10);
			 */
		}

		g.setColor(Color.pink); 
		g.setLineWidth(2F);
		if(this.getClickBox() != null) 
			g.draw(this.getClickBox());

		if (this instanceof Gie�er) {
			Gie�er gi = (Gie�er) this;
			if (!gi.isWorking()) {
				setHoverY(this.ycoor
						+ (int) Math.sin(System.currentTimeMillis() / 10000));

			}
		}
	}

	public String toString() {
		return "Pattern(" + this.getId() + ", " + this.getPatternState() + ")";
	}

	/**
	 * Updated Pattern-Info (HashMap) der Werte eines Objektes
	 * 
	 * @return void //TODO �berall implementieren
	 */
	public abstract void updatePatternInfo();

	// //////////////////////////////////////// Setter & Getter ///////////

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

	public Shape getClickBox() {
		return this.clickbox;
	}

	public void setClickBox(Shape s) {
		this.clickbox = s;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return die X-Koordinate des Patterns auf der Map m
	 */
	public int getXCoordinate() {
		return xcoor;
	}

	/**
	 * @param xcoor
	 *            Die X-Koordinate des Patterns auf der Map m
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
	 * @param xcoor
	 *            Die Y-Koordinate des Patterns auf der Map m
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
	 * @param pattern_info
	 *            the pattern_info to set
	 */
	public void setPatternInfo(HashMap<String, Object> pattern_info) {
		this.pattern_info = pattern_info;
	}

	public void putPatternInfo(String s, Object o) {
		pattern_info.putIfAbsent(s, o);
	}

	public void clearPatternInfo() {
		pattern_info.clear();
	}

	/**
	 * @return the hovery
	 */
	public int getHoverY() {
		return hovery;
	}

	/**
	 * @param hovery
	 *            the hovery to set
	 */
	public void setHoverY(int hovery) {
		this.hovery = hovery;
	}



	public int getFrame() {
		return frame;
	}

	public void setFrame(int frame) {
		this.frame = frame;
	}

	public int getFrame_start() {
		return frame_start;
	}

	public void setFrame_start(int frame_start) {
		this.frame_start = frame_start;
	}

	public int getFrame_end() {
		return frame_end;
	}

	public void setFrame_end(int frame_end) {
		this.frame_end = frame_end;
	}

	public int getFrame_total() {
		return frame_total;
	}

	public void setFrame_total(int frame_total) {
		this.frame_total = frame_total;
	}

	public int getDelay() {
		return delay;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}

}
