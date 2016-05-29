package de.OFactory.SchokoFactory.main;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.StateBasedGame;

import de.OFactory.SchokoFactory.game.GameSettings;
import de.OFactory.SchokoFactory.game.GameUtils;
import de.OFactory.SchokoFactory.game.Map;
import de.OFactory.SchokoFactory.game.Pattern;
import de.OFactory.SchokoFactory.game.PatternState;
import de.OFactory.SchokoFactory.game.patterns.Chemiefabrik;
import de.OFactory.SchokoFactory.game.patterns.Rührer;
import de.OFactory.SchokoFactory.game.patterns.Tank;
import de.OFactory.SchokoFactory.game.patterns.Wiese;

public class MainLoop
{	
	//Zeug für Pattern
	
	public static Map field;
	
	public static float curpatternscale = 0.6F;
	public static Image   patternimg_raw = ResourceManager.loadImage("res/img/assets/texture/patterns/patterns.png").getScaledCopy(curpatternscale);
	public static Image[] patternimg = ResourceManager.loadPics(patternimg_raw, 50); //Bild splitten -> Einzelne Bilder (Image[])
	public static Pattern hoveredpattern; //Gehoverter Pattern
	public static Pattern clicked;        //Geklickter Pattern
	
	public static int allv_y; //Geschwindigkeit y
	public static int allv_x; //Geschwindidkeit  x
	
	public static final  int TEXTURE_WIDTH = 200;
	public static final  int TEXTURE_HEIGHT = 64;
	public static String curpatterninfo;
	public static PatternState curpatternstate;
	
	
	//Zeug aus dem Konstruktor
	
	GameContainer gc;
	StateBasedGame sbg;
	int delta;
	
	MainLoop(GameContainer gc, StateBasedGame sbg, int delta)
	{
		this.gc = gc;
		this.sbg = sbg;
		this.delta = delta;
	}
	
	void loopPass()
	// ein Schleifenduchgang	//früher in MainState als update(GameContainer gc, StateBasedGame sbg, int delta)
	{
		Input in = gc.getInput(); //Inputinstanz holen
		patternMovement(gc, in); // Bewegung der Pattern

		if(clicked != null){ // Clicked Pattern
			
			if(clicked instanceof Wiese){ //Feld "leer" ( = Wiese)
				if(curpatternstate == PatternState.TANK) //TANK
					field.set(clicked.getId(), new Tank(clicked.getX(), clicked.getY(), clicked.getId()));
				if(curpatternstate == PatternState.CHEMIEFABRIK) //CHEMIEFABRIK
					field.set(clicked.getId(), new Chemiefabrik(clicked.getX(), clicked.getY(), clicked.getId()));
				if(curpatternstate == PatternState.RÜHRER) //RÜHRER
					field.set(clicked.getId(), new Rührer(clicked.getX(), clicked.getY(), clicked.getId()));
				
				//TODO Andere PatternStates hinzufügen
			} else { //Feld hat ein Gebäude
				if(curpatternstate == PatternState.WIESE) //Gebäude entfernen (-> Wiese) 
					field.set(clicked.getId(), new Wiese(clicked.getX(), clicked.getY(), clicked.getId()));
			}
		}
		
		GameUtils.refreshSize(); // Testen, ob Größe sich verändert hat -> Ausprinten
		
		for(Pattern p : field) //jedes Pattern zeichnen
			p.update(gc);
		
		if(in.isKeyPressed(Input.KEY_S)){ // Speichertest
			field.saveMap();
		}
		

	}
	private void patternMovement(GameContainer gc, Input in) {
		
		if(in.isKeyDown(Input.KEY_T))
			MainState.curpatternstate = PatternState.TANK;
		if(in.isKeyDown(Input.KEY_W))
			MainState.curpatternstate = PatternState.WIESE;
		if(in.isKeyDown(Input.KEY_C))
			MainState.curpatternstate = PatternState.CHEMIEFABRIK;
		if(in.isKeyDown(Input.KEY_R))
			MainState.curpatternstate = PatternState.RÜHRER;
		
		
		if(in.isKeyDown(Input.KEY_UP))
			MainState.allv_y = + GameSettings.PATTERN_MOVEMENT_SPEED;
		else if(in.isKeyDown(Input.KEY_DOWN))
			MainState.allv_y = - GameSettings.PATTERN_MOVEMENT_SPEED;
		else
			MainState.allv_y = 0;
		
		if(in.isKeyDown(Input.KEY_LEFT))
			MainState.allv_x = + GameSettings.PATTERN_MOVEMENT_SPEED;
		else if(in.isKeyDown(Input.KEY_RIGHT))
			MainState.allv_x = - GameSettings.PATTERN_MOVEMENT_SPEED;
		else 
			MainState.allv_x = 0;
		
		Shape up = new Rectangle(0, 0, gc.getWidth()/5*4, gc.getHeight()/10);
		Shape left = new Rectangle(0, 0, gc.getWidth()/10, gc.getHeight());
		Shape right = new Rectangle(gc.getWidth()/10*7, 0, gc.getWidth()/10, gc.getHeight());
		Shape down = new Rectangle(0, gc.getHeight()/10*9, gc.getWidth()/5*4, gc.getHeight()/5);
		
		if(up.contains(in.getMouseX(), in.getMouseY()))
			MainState.allv_y = + GameSettings.PATTERN_MOVEMENT_SPEED;
		if(left.contains(in.getMouseX(), in.getMouseY()))
			MainState.allv_x = + GameSettings.PATTERN_MOVEMENT_SPEED;
		if(right.contains(in.getMouseX(), in.getMouseY()))
			MainState.allv_x = - GameSettings.PATTERN_MOVEMENT_SPEED;
		if(down.contains(in.getMouseX(), in.getMouseY()))
			MainState.allv_y = - GameSettings.PATTERN_MOVEMENT_SPEED;
	}
	
}