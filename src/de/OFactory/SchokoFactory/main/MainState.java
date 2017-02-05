package de.OFactory.SchokoFactory.main;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import de.OFactory.SchokoFactory.game.GameSettings;
import de.OFactory.SchokoFactory.game.GameUtils;
import de.OFactory.SchokoFactory.game.Map;
import de.OFactory.SchokoFactory.game.Pattern;
import de.OFactory.SchokoFactory.game.PatternState;

import de.OFactory.SchokoFactory.inventory.Button;
import de.OFactory.SchokoFactory.inventory.Pausepile;
import de.OFactory.SchokoFactory.inventory.Stockpile;
import de.OFactory.SchokoFactory.inventory.info.EcoScreen;
import de.OFactory.SchokoFactory.inventory.info.InfoPanel;
import de.OFactory.SchokoFactory.inventory.info.tabs.BuildTab;
import de.OFactory.SchokoFactory.inventory.info.tabs.BuildingInfoTab;
import de.OFactory.SchokoFactory.inventory.info.tabs.EnviromentTab;
import de.OFactory.SchokoFactory.inventory.info.tabs.MarketInfoTab;
import de.OFactory.SchokoFactory.simulation.BetterAI;
import de.OFactory.SchokoFactory.simulation.Factory;
import de.OFactory.SchokoFactory.simulation.Market;
import de.OFactory.SchokoFactory.simulation.Player;
import de.OFactory.SchokoFactory.simulation.SimpleAI;

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
	public static Graphics g;
	
	//Zeug für Pattern
	
	public static Map field;
	
	public static double curpatternscale = 0.7D;
	public static Image   patternimg_raw = ResourceManager.loadImage("assets/textures/patterns/patterns.png");
	public static Image[] patternimg = ResourceManager.loadPics(patternimg_raw, 50); //Bild splitten -> Einzelne Bilder (Image[])

	
	public static int allv_y; //Geschwindigkeit y
	public static int allv_x; //Geschwindidkeit  x
	
	public static final  int TEXTURE_WIDTH = 200;
	public static final  int TEXTURE_HEIGHT = 64;
	public static String curpatterninfo;
	public static PatternState curpatternstate = null;

	
	//Max Dist
	public static Rectangle view_dimensions = new Rectangle(-100, -100, 800, 600);
	public static Point     cam_pos = new Point( 0, 0);
	
	//Zeug fürs Inventar

	public static Stockpile pile;
	public static Pausepile pausepile;
	public static float curbuttonscale = 0.5F;
	public static Image   buybuttonimg_raw = ResourceManager.loadImage("assets/textures/gui/buy_buttons.png").getScaledCopy(curbuttonscale);
	public static Image[] buybuttonimg = ResourceManager.loadPics(buybuttonimg_raw, 6);
	//public static BuyButton b1;
	//public static BuyButton b2;s
	public static InfoPanel ip;
	public static EcoScreen ecoscreen;
	
	
	
	//Zeug für Zeit -> Simulationsberechnungen
	public static long last;
	public static long delta_t;
	
	public static ArrayList<Daily> dailys = new ArrayList<Daily>();
	
	// Zeug für Markt
	public static Player  p; //Spieler
	public static SimpleAI  ai1; //Spieler
	public static BetterAI  ai2; //Spieler
	public static Market  m;
	public static Factory f; //Engine für work();
	
	//Zeug für Markeinstellungen
	public static TextField txt_preis;
	public static TextField txt_werbung;
	public static TextField txt_qualitaet;
	public static Button    btn_bestätigen;
	
	public static double inpreis = 1;
	public static double inwerbung;
	public static double inqualitaet;
	
	//-------------------------------------------------------------------------
	
	//Spielvariablen
	
	public static float balance;
	public static int molten_chokolate;
	public static int free_molten_chokolate;
	
	
	
	//Tabs
	public static MarketInfoTab mtab;
	

	// Spiel läuft oder pausiert
	public static boolean run = true;
	
	
	//-------------------------------------------------------------------------
	
	// Spielmethoden
	
	/** Die Initialisationsmethode des States MainState
	 *              
	 *  @param GameContainer gc | MainState's GameContainer Instanz
	 *  @param StateBasedGame sbg | Die Instanz des Spiels
	 *  @throws SlickException: Falls Etwas beim Initialisieren schief läuft
	 */
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		
		
		System.out.println("\n\n SchokoFactory MainState          -   Game-Log \n");	
		
		// - Initialisierung der Objekte für die Marktsimulation
		MainState.f = new Factory();					// Reihenfolge beachten, damit die day()-Methoden in der richtigen Reihenfolge ausgeführt werden.
		MainState.p = new Player(m, "P1", 1800);		// Wichtig: fabrik, dann ais, dann markt
		MainState.ai1 = new SimpleAI(m,"P2",1000);
		MainState.ai2 = new BetterAI(m,"P3",1000);
		
		MainState.m = new Market(); 
		MainState.m.setPlayer(Arrays.asList(
				p,
				ai1,
				ai2));
		
		
		// - Initialisierung der Map | Auslesen des Speichers und Generieren der Map
		field = Map.readSavedMap("saves/Test.sf");
		//field = Map.generateMap(20, 20);// GameSettings.STANDARD_MAP_SIZE_HEIGHT); // Feld generieren //GameSettings.STANDARD_MAP_SIZE_WIDTH
				//field.setName("Test");
		
		
		// - Initialisierung Stockpile und Unterklassen
		pile      = new Stockpile(50); // Stockpile generieren
		pausepile = new Pausepile(50, 30);
		
		// - Initialisierung InfoPanel und Unterklassen wie z.B Tabs
		int x =      gc.getWidth()/5*4;
		int y =      80;//gc.getHeight()/14;
		int width =  gc.getWidth()/20*4;
		int height = gc.getHeight()-y;
		
		ip = new InfoPanel(x, y, width, height);
		mtab = new MarketInfoTab(ip, patternimg[1]);
		ip.setTabs(Arrays.asList(
				new BuildingInfoTab(ip, patternimg[3]),
				new BuildTab(ip, patternimg[10]),
				mtab,
				new EnviromentTab(ip, patternimg[2])));
		ip.switchTab(2);

		// - Initialisierung des Economy-Screens und Inhalten
		ecoscreen = new EcoScreen(0, y, gc.getWidth(), height);	
		@SuppressWarnings("unchecked")
		ArrayList<Integer>[] lines1 = (ArrayList<Integer>[]) new ArrayList[] {new ArrayList<Integer>(),new ArrayList<Integer>(),new ArrayList<Integer>(),new ArrayList<Integer>(),new ArrayList<Integer>(),new ArrayList<Integer>(),new ArrayList<Integer>()};
		@SuppressWarnings("unchecked")
		ArrayList<Integer>[] lines2 = (ArrayList<Integer>[]) new ArrayList[] {new ArrayList<Integer>(),new ArrayList<Integer>(),new ArrayList<Integer>(),new ArrayList<Integer>(),new ArrayList<Integer>(),new ArrayList<Integer>(),new ArrayList<Integer>()};

		mtab.wachstumschart.setLines(lines1);
		ecoscreen.wachstumschart.setLines(lines2);
		
		msl = new MainStateListener();
		gc.getInput().addMouseListener(MainState.msl); //MouseListener
		gc.getInput().addKeyListener(MainState.msl);
		
		// - Inputs für Preis, Werbe- und Qualitätsinvestitionen 		(hässliche Kackscheiße, die ins InfoPanel gehört)
		txt_preis = new TextField(gc, gc.getDefaultFont(), 800, 760, 200, 20);
		txt_werbung = new TextField(gc, gc.getDefaultFont(), 800, 800, 200, 20);
		txt_qualitaet = new TextField(gc, gc.getDefaultFont(), 800, 840, 200, 20);
		
		txt_preis.setText("1");
		txt_werbung.setText("0");
		txt_qualitaet.setText("0");
		
		btn_bestätigen = new Button(0, 800, 870, 200, 35, "set",2);
		btn_bestätigen.addActionListener(new ActionListener() {		
			public void actionPerformed(ActionEvent e) {
				try {
					inwerbung  = Double.parseDouble(txt_werbung.getText());
					inpreis    = Double.parseDouble(txt_preis.getText());
					inqualitaet = Double.parseDouble(txt_qualitaet.getText());
					

					
				} catch(Exception ex){
					System.err.println("ERROR 003: Konnte " + txt_preis.getText() + " / " + txt_werbung.getText() + " / " + txt_qualitaet.getText() + " nicht zu einem Integer konvertieren!");
					ex.printStackTrace();
				}
				
			}
		});
		

		// - damit die Tanks nicht so traurig aussehen [bleibt hier nicht mehr lange]
		molten_chokolate = 3600;
		free_molten_chokolate = molten_chokolate;
		
		

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
		
		if(in.isKeyPressed(Input.KEY_ESCAPE)) {
			Display.destroy();
		}
		if(in.isKeyPressed(Input.KEY_SPACE)) {
			run = !run;
		}
		
		
		GameUtils.refreshSize(); // Testen, ob Größe sich verändert hat -> Ausprinten
		

		
		//Tag berechnen
		delta_t = System.currentTimeMillis() - last;
		
		if(delta_t >= GameSettings.DAY_MILIS && run){ //Ein Tag(Siehe GameSettings.DAY_MILIS) geht verüber
			last = 0;
			
			long t1 = System.currentTimeMillis();

			for(Daily d:dailys)
				d.day();
					
			System.out.println("Berechnungsdauer der Simulation (Tag "+m.getTime()+"): " + (System.currentTimeMillis() - t1) + " ms");

		}
		
		if(last == 0)
			last = System.currentTimeMillis(); //last neu ausrechnen
		
		ip.update(gc);
		pile.update(gc); //Stockpiles updaten
		pausepile.update(gc);
		ecoscreen.update(gc);
		field.update(gc);
		btn_bestätigen.update(gc.getInput());
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
		
		if(in.isKeyDown(Input.KEY_P))
			MainState.cam_pos.setLocation(0, 0);
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
		
		Shape up = new Rectangle(-1, -1, Display.getWidth()+1, 20);
		Shape left = new Rectangle(-1, -1, 20, Display.getHeight()+2);
		Shape right = new Rectangle(Display.getWidth()-20, -1, 20, Display.getHeight()+2);
		Shape down = new Rectangle(-1, Display.getHeight()-20, Display.getWidth()+1, Display.getHeight()+1);
		
		if(up.contains(in.getMouseX(), in.getMouseY()))
			MainState.allv_y = + GameSettings.PATTERN_MOVEMENT_SPEED;
		if(left.contains(in.getMouseX(), in.getMouseY()))
			MainState.allv_x = + GameSettings.PATTERN_MOVEMENT_SPEED;
		if(right.contains(in.getMouseX(), in.getMouseY()))
			MainState.allv_x = - GameSettings.PATTERN_MOVEMENT_SPEED;
		if(down.contains(in.getMouseX(), in.getMouseY()))
			MainState.allv_y = - GameSettings.PATTERN_MOVEMENT_SPEED;
		
		
		
		//cam_pos.setLocation(cam_pos.getX() + allv_x, cam_pos.getY() + allv_y);
		if(!view_dimensions.contains((float) cam_pos.getX(), (float) cam_pos.getY())){
			cam_pos.setLocation(cam_pos.getX() - allv_x, cam_pos.getY() - allv_y);
			allv_x = 0;
			allv_y = 0;
		}
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
		
		//g.scale(curpatternscale, curpatternscale);
		
		g.setColor(Color.black);
		//g.setColor(new Color(69, 166, 76)); //TODO Farben auslagern
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
		pausepile.draw(g);
		
		//Developer Info
		g.setColor(new Color(200, 200, 200, 0.4F));
		g.fillRect(0, 80, gc.getWidth()/6, gc.getHeight()/7);	
		g.setColor(Color.black);
		
		g.setColor(new Color(0, 20, 200, 150));
		g.drawString("State: MainState", 10, 90);
		g.drawString("CurPattern: " + curpatterninfo,    					  10,  110);
		g.drawString("CurState: "   + curpatternstate,  					  10, 130);
		g.drawString("Selected: "   + Map.selected_pattern,       				  10, 150);
		g.drawString("cam_pos:  "   + cam_pos.getX() + ", " + cam_pos.getY(), 10, 170);
		g.drawString("pat_cale: "   + curpatternscale,                        10, 190);
		
		
		g.setColor(Color.white);
		txt_preis.render(gc, g);
		txt_werbung.render(gc, g);
		txt_qualitaet.render(gc, g);
		
		g.drawString("Preis:",   720, 760);
		g.drawString("Werbung:", 720, 800);
		g.drawString("Qualität:", 720, 840);
		
		g.drawString(""+p.getPreis(),   1010, 760);
		g.drawString(""+inwerbung, 1010, 800);
		g.drawString(""+inqualitaet, 1010, 840);
		
		btn_bestätigen.draw(g);
		

		ip.draw(g);
		ecoscreen.draw(g);

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
	
	@Override
	public void mouseClicked(int button, int x, int y, int clickCount) {
		msl.mouseClicked(button, x, y, clickCount);
	}
	
	@Override
	public void keyPressed(int key, char c) {
		msl.keyPressed(key, c);
	}
	
}
