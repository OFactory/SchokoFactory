package de.OFactory.SchokoFactory.main;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import de.OFactory.SchokoFactory.game.GameSettings;
import de.OFactory.SchokoFactory.game.GameUtils;
import de.OFactory.SchokoFactory.game.Pattern;
import de.OFactory.SchokoFactory.game.PatternState;
import de.OFactory.SchokoFactory.game.patterns.Chemiefabrik;
import de.OFactory.SchokoFactory.game.patterns.Tank;
import de.OFactory.SchokoFactory.game.patterns.Wiese;
import de.OFactory.SchokoFactory.inventory.BuyButton;
import de.OFactory.SchokoFactory.inventory.Stockpile;

public class MainState extends BasicGameState{

	public static Stockpile pile;
	
	public static float curScale = 0.6F;
	
	public static ArrayList<Pattern> field;
	public static Image[] patternimg = ResourceManager.loadPics(ResourceManager.loadImage("res/img/assets/texture/patterns/patterns.png").getScaledCopy(curScale), 50);
	
	
	public static Image[] buybuttonimg = ResourceManager.loadPics(ResourceManager.loadImage("res/img/gui/buy_inventory/buy_buttons.png").getScaledCopy(0.5F), 6);
	
	public static Pattern hoveredpattern;
	public static Pattern clicked;
	public static int allv_y;
	public static int allv_x;
	
	public static final  int TEXTURE_WIDTH = 200;
	public static final  int TEXTURE_HEIGHT = 64;
	public static String curpatterninfo;
	
	public static PatternState curpatternstate;
	
	
	public static BuyButton b1;
	public static BuyButton b2;
	
	//-------------------------------------------------------------------------
	
	//Spielvariablen
	
	public static float balance;
	public static int molten_chokolate;
	public static int free_molten_chokolate;
	
	
	
	
	
	
	
	
	//-------------------------------------------------------------------------
	
	// Spielmethoden
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		pile = new Stockpile(0.05);
		
		
		field = createField(20, 20);
		b1 = new BuyButton(0, 1, 2, gc.getWidth()/80*65, gc.getHeight()/15, "-30");
		b2 = new BuyButton(3, 4, 5, gc.getWidth()/80*73, gc.getHeight()/15, "-30");
		
		molten_chokolate = 3000;
		free_molten_chokolate = molten_chokolate;
	}
	
	private static ArrayList<Pattern> createField(int pattern_width, int pattern_height) {
		
		int i = 0;
		
		ArrayList<Pattern> ps = new ArrayList<Pattern>();
		
		int grid_width = 0; // Anzahl Spalten in nter Reihe
		
		for(int y = 0; y < pattern_width*2-1; y++){ // für jede Reihe
			
			
			if(y >= pattern_width){ // nte Reihe erreicht(max width)
				grid_width--;
			} else {
				grid_width = y;
			}
			
			for(int x = 0; x < grid_width; x++){ // für jede Spalte
				
											//Normal Placement					//Verschiebung durch Anzahl Patterns in Reihe				//Normal Placemewnt
				Wiese w = new Wiese(
						(int) (  x*TEXTURE_WIDTH*curScale +  ( (pattern_width-grid_width)*TEXTURE_WIDTH*curScale )/2  - pattern_width*TEXTURE_WIDTH*curScale/3),
						(int) (  y*TEXTURE_HEIGHT*curScale - pattern_height*TEXTURE_HEIGHT*curScale/1.5), 
						i );
				ps.add(w);
				i++;
				
				
			}
		
		}
		
		return ps;
		
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		
		Input in = gc.getInput();
		
		patternMovement(gc, in); // Bewegung der Pattern
		
		b1.update(gc);
		b2.update(gc);
		

		
		if(clicked != null){ // Clicked Pattern
			
			if(MainState.curpatternstate == PatternState.TANK){
				if(clicked instanceof Wiese) {
					MainState.field.set(clicked.getId(), new Tank(clicked.getX(), clicked.getY(), clicked.getId()));
				}
			} else if(MainState.curpatternstate == PatternState.CHEMIEFABRIK){
				if(clicked instanceof Wiese) {
					MainState.field.set(clicked.getId(), new Chemiefabrik(clicked.getX(), clicked.getY(), clicked.getId()));
				}
			} else if(MainState.curpatternstate == PatternState.WIESE){
				if(!(clicked instanceof Wiese)) {
					MainState.field.set(clicked.getId(), new Wiese(clicked.getX(), clicked.getY(), clicked.getId()));
				}
			}
				
			
			//System.out.println("P"+ clicked.getId() + " (" + clicked.getPatternState() + ") ");
		}
		
		
		GameUtils.refreshSize();
		
		for(Pattern p : field)
			p.update(gc);
		
		pile.update(gc);
	}
	
	private void patternMovement(GameContainer gc, Input in) {
		
		if(in.isKeyDown(Input.KEY_T))
			MainState.curpatternstate = PatternState.TANK;
		if(in.isKeyDown(Input.KEY_W))
			MainState.curpatternstate = PatternState.WIESE;
		if(in.isKeyDown(Input.KEY_C))
			MainState.curpatternstate = PatternState.CHEMIEFABRIK;
		
		
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


	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		
		
		
		
		g.setColor(new Color(44, 201, 51));
		g.fillRect(0, 0, Main.width, Main.height);
	
		
		for(Pattern p : field)
			p.draw(g);
	
		
		g.setColor(new Color(220, 220, 220));
		g.fillRect(gc.getWidth()/5*4, 0, gc.getWidth()/5, gc.getHeight());
		
		g.setColor(Color.black);
		g.drawRect(gc.getWidth()/5*4, 0, gc.getWidth()/5, gc.getHeight());
		
		pile.draw(g);
		
		
		g.setColor(new Color(200, 200, 200, 0.5F));
		g.fillRect(0, gc.getHeight()/20, gc.getWidth()/4, gc.getHeight()/6);
		
		g.setColor(Color.black);
		g.drawRect(0, gc.getHeight()/20, gc.getWidth()/4, gc.getHeight()/6);
		
		g.setColor(new Color(0, 20, 200));;
		g.drawString("State: MainState", 10, 50);
		g.drawString("CurPattern: " + curpatterninfo, 10, 80);
		g.drawString("CurState: " + curpatternstate, 10, 100);
		
		b1.draw(g);
		b2.draw(g);
	}
	
	//-------------------------------------------------------------------------

	@Override
	public int getID() {
		return 1;
	}

}
