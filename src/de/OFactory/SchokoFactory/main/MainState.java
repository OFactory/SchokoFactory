package de.OFactory.SchokoFactory.main;

import java.util.Arrays;

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
import de.OFactory.SchokoFactory.game.Map;
import de.OFactory.SchokoFactory.game.Pattern;
import de.OFactory.SchokoFactory.game.PatternState;
import de.OFactory.SchokoFactory.game.patterns.Wiese;
import de.OFactory.SchokoFactory.inventory.Stockpile;
import de.OFactory.SchokoFactory.inventory.info.InfoPanel;
import de.OFactory.SchokoFactory.inventory.info.tabs.BuildTab;
import de.OFactory.SchokoFactory.inventory.info.tabs.BuildingInfoTab;
import de.OFactory.SchokoFactory.inventory.info.tabs.EnviromentTab;
import de.OFactory.SchokoFactory.inventory.info.tabs.MarketInfoTab;

import de.OFactory.SchokoFactory.simulation.Market;
import de.OFactory.SchokoFactory.simulation.Player;

/** 
 * Der Hauptstate des Spieles (=MainState)
 * In diesem findet das eigentliche Spiel statt
 * 
 * @extends BasicGameState: Standard SpielStatus
 * @author Maximilian
 */

public class MainState extends BasicGameState{

	//Zeug fürs GUI
	
	public static MainStateListener msl; //Listener
	public static GameContainer gc;
	
	//Zeug für Pattern
	
	public static Map field;
	
	public static float curpatternscale = 0.7F;
	public static Image   patternimg_raw = ResourceManager.loadImage("assets/textures/patterns/patterns.png");
	public static Image[] patternimg = ResourceManager.loadPics(patternimg_raw, 50); //Bild splitten -> Einzelne Bilder (Image[])
	public static Pattern hoveredpattern; //Gehoverter Pattern
	public static Pattern clicked;        //Geklickter Pattern
	
	public static int allv_y; //Geschwindigkeit y
	public static int allv_x; //Geschwindidkeit  x
	
	public static final  int TEXTURE_WIDTH = 200;
	public static final  int TEXTURE_HEIGHT = 64;
	public static String curpatterninfo;
	public static PatternState curpatternstate = null;
	public static Pattern selected_pattern = null; // ausgewähltes Pattern
	
	//Zeug fürs Inventar

	public static Stockpile pile;
	public static float curbuttonscale = 0.5F;
	public static Image   buybuttonimg_raw = ResourceManager.loadImage("assets/textures/gui/buy_buttons.png").getScaledCopy(curbuttonscale);
	public static Image[] buybuttonimg = ResourceManager.loadPics(buybuttonimg_raw, 6);
	//public static BuyButton b1;
	//public static BuyButton b2;
	public static InfoPanel ip;
	
	
	//Zeug für Zeit -> Simulationsberechnungen
	public static long last;
	public static long delta_t;
	
	// Zeug für Markt
	public static Player p; //Spieler
	
	
	
	//-------------------------------------------------------------------------
	
	//Spielvariablen
	
	public static float balance;
	public static int molten_chokolate;
	public static int free_molten_chokolate;
	
	public static Market m;
	
	
	
	
	
	
	
	
	//-------------------------------------------------------------------------
	
	// Spielmethoden
	
	/** Die Initialisationsmethode des States MainState
	 *              
	 *  @param GameContainer gc | MainState's GameContainer Instanz
	 *  @param StateBasedGame sbg | Die Instanz des Spiels
	 *  @throws SlickException: Falls Etwas beim Initialisieren schief läuft
	 */
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		
		
		
		// - Market
		MainState.m = new Market();
		p = new Player(m, "P", 100000);
		MainState.m.setPlayer(Arrays.asList(
				p,
				new Player(m,"P2",100000),
				new Player(m,"P3",100000)
				));
		
		pile = new Stockpile(0.05); // Stockpile generieren
		//field = Map.generateMap(GameSettings.STANDARD_MAP_SIZE_WIDTH, GameSettings.STANDARD_MAP_SIZE_HEIGHT); // Feld generieren
		//field.setName("Test");
		
		//long time_1 = System.currentTimeMillis();
		field = Map.readSavedMap("saves/Test.sf");
		//System.out.println(System.currentTimeMillis()-time_1);
		
		//System.out.println(field);
		//System.out.println(field.getSaveString());
		
		// InfoPanel + TABS
		int x =      gc.getWidth()/5*4;
		int y =      gc.getHeight()/20;
		int width =  gc.getWidth()/20*4;
		int height = gc.getHeight()/20*19;
		
		ip = new InfoPanel(x, y, width, height);
		ip.setTabs(Arrays.asList(
				new BuildingInfoTab(ip, patternimg[3]),
				new BuildTab(ip, patternimg[10]),
				new MarketInfoTab(ip, patternimg[1]),
				new EnviromentTab(ip, patternimg[2])));
		ip.switchTab(1);
		
		msl = new MainStateListener();
		gc.getInput().addMouseListener(MainState.msl); //MouseListener
		
		// TESTAREA Inc. ------------------------------------------------------------
		
		//b1 = new BuyButton(0, 1, 2, gc.getWidth()/80*65, gc.getHeight()/15, "-30");
		//b2 = new BuyButton(3, 4, 5, gc.getWidth()/80*73, gc.getHeight()/15, "-30");
		
		molten_chokolate = 3600;
		free_molten_chokolate = molten_chokolate;
		
		// TESTAREA End. ------------------------------------------------------------
		

	}
	
	
	
	/** Die Update Methode,
	 *  hier wird alles berechnet #Mathe15Punkte
	 *  
	 *  @param GameContainer gc | MainState's GameContainer Instanz
	 *  @param StateBasedGame sbg | Die Instanz des Spiels
	 *  @param int delta | Zeit, die bis zum letzten Aufruf vergangen ist #Komplex #wurzelausminuseins
	 *  @throws SlickException: Falls Etwas beim Berechnen schief läuft
	 */
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		
		Input in = gc.getInput(); //Inputinstanz holen
		patternMovement(gc, in); // Bewegung der Pattern
		
		MainState.gc = gc;
		
		if(clicked != null){ // Clicked Pattern
			
			if(curpatternstate == null){ // Kein Gebäude ausgewählt: nur Auswahlmöglichkeit
				if(clicked instanceof Wiese) {
					selected_pattern = null; // keine Auswahl beim Klicken auf leeres Feld
				} else {
					selected_pattern = clicked; // Gebäude auswählen
					ip.switchTab(0); // Tab wechseln
				}
			} else {
			
				if(clicked instanceof Wiese){ //Feld "leer" ( = Wiese)
					if(curpatternstate != PatternState.WIESE)
						field.set(clicked.getId(), Pattern.getInstance(MainState.field, clicked.getX(), clicked.getY(), curpatternstate, clicked.getId(), clicked.getXCoordinate(), clicked.getYCoordinate()));
				} else { //Feld hat ein Gebäude
					if(curpatternstate == PatternState.WIESE) //Gebäude entfernen (-> Wiese) 
						field.set(clicked.getId(), new Wiese(MainState.field, clicked.getX(), clicked.getY(), clicked.getId(), clicked.getXCoordinate(), clicked.getYCoordinate()));
					else // Keine Wiese: Gebäude Auswählen
						selected_pattern = clicked;
						
				}
			}
				
		}
		
		
		
		GameUtils.refreshSize(); // Testen, ob Größe sich verändert hat -> Ausprinten
		
		for(Pattern p : field) //jedes Pattern zeichnen
			if(p != null)
				p.update(gc);
		
		if(in.isKeyPressed(Input.KEY_S)){ // Speichertest
			field.saveMap();
		}
		
		pile.update(gc); //Stockpiles updaten
		
		//Tag berechnen
		delta_t = System.currentTimeMillis() - last;
		
		if(delta_t >= GameSettings.DAY_MILIS){ //Ein Tag(Siehe GameSettings.DAY_MILIS) geht verüber
			last = 0;
			m.day();
		}
		
		if(last == 0)
			last = System.currentTimeMillis(); //last neu ausrechnen
		
		
		// TESTAREA Inc. --------------------------------------
		
		//m.day();
		
		//b1.update(gc);
		//b2.update(gc);
		ip.update(gc);
		
		// TESTAREA End. --------------------------------------
	}
	
	/** Ändert den aktuellen Pattern-Zustand curpatternstate
	 *  Ändert die aktuelle Geschwindigkeit in x und y Richtung zur vektorisierten Konstante GameSettings.PATTERN_MOVEMENT_SPEED
	 *  Erstellt Bereiche im Fenster up,left,right,down mit denen der Spieler über das Feld scrollen kann
	 *  
	 *  @param GameContainer gc | MainState's GameContainer Instanz
	 *  @param Input in |  MainState's Input Instanz
	 */
	private void patternMovement(GameContainer gc, Input in) {
		
		if(in.isKeyDown(Input.KEY_T))
			MainState.curpatternstate = PatternState.TANK;
		if(in.isKeyDown(Input.KEY_W))
			MainState.curpatternstate = PatternState.WIESE;
		if(in.isKeyDown(Input.KEY_C))
			MainState.curpatternstate = PatternState.CHEMIEFABRIK;
		if(in.isKeyDown(Input.KEY_R))
			MainState.curpatternstate = PatternState.RÜHRER;
		if(in.isKeyDown(Input.KEY_L))
			MainState.curpatternstate = PatternState.LAGERHALLE;
		if(in.isKeyDown(Input.KEY_M))
			MainState.curpatternstate = PatternState.MOLKEREI;
		if(in.isKeyDown(Input.KEY_F))
			MainState.curpatternstate = PatternState.FARM;
		if(in.isKeyDown(Input.KEY_G))
			MainState.curpatternstate = PatternState.GIEßER;
		if(in.isKeyDown(Input.KEY_N))
			MainState.curpatternstate = null;
		if(in.isKeyDown(Input.KEY_2))
			MainState.molten_chokolate += 100;
		
		
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
	
	
	/** Die Render Methode,
	 *  hier wird alles gezeichnet
	 *  
	 *  @param GameContainer gc | MainState's GameContainer Instanz
	 *  @param StateBasedGame sbg | Die Instanz des Spiels
	 *  @param Graphics g | MainState's Grafikinstanz, hiermit zeichnet man (Bsp.: g.fillRect(x, y, w, h))
	 *  @throws SlickException: Falls Etwas beim Zeichnen schief läuft #KeineFarbeMehr #lowbudget
	 */
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {

		
		g.setColor(new Color(69, 166, 76)); //TODO Farben auslagern
		g.fillRect(0, 0, Main.width, Main.height); //Hintergrund
	
		
		for(Pattern p : field) //Alle Patterns in field Zeichnen
			if(p != null)
				p.draw(g); 
	
		
		//Kaufmenü zeichnen (Rechts)
//		g.setColor(new Color(220, 220, 220));
//		g.fillRect(gc.getWidth()/5*4, 0, gc.getWidth()/5, gc.getHeight());  
//		g.setColor(Color.black); 
//		g.drawRect(gc.getWidth()/5*4, 0, gc.getWidth()/5, gc.getHeight());
		
		//Stockpiles Zeichnen (Oben)
		pile.draw(g);
		
		//Developer Info
		g.setColor(new Color(200, 200, 200, 0.5F));
		g.fillRect(0, gc.getHeight()/20, gc.getWidth()/4, gc.getHeight()/6);	
		g.setColor(Color.black);
		g.drawRect(0, gc.getHeight()/20, gc.getWidth()/4, gc.getHeight()/6);
		
		g.setColor(new Color(0, 20, 200));
		g.drawString("State: MainState", 10, 50);
		g.drawString("CurPattern: " + curpatterninfo,   10,  80);
		g.drawString("CurState: "   + curpatternstate,  10, 100);
		g.drawString("Selected: "   + selected_pattern, 10, 120);
		
		// TESTAREA Inc. --------------------------
		
		ip.draw(g);
		
		//Buttons #just4funs
		//b1.draw(g);
		//b2.draw(g);
		
		
		// TESTARE End. ---------------------------
	}
	
	//-------------------------------------------------------------------------

	@Override
	public int getID() {
		return 1;
	}
	
	@Override
	public void mouseWheelMoved(int change) {
		msl.mouseWheelMoved(change);
	}	
	
	@Override
	public void mousePressed(int button, int x, int y) {
		msl.mousePressed(button, x, y);
	}

}
