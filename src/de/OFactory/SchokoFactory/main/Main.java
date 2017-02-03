package de.OFactory.SchokoFactory.main;


import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.StateBasedGame;

/** 
 * Hauptklasse des Spiels
 * beinhaltet main() 
 * 
 * @extends StateBasedGame | Ein Spiel, dass auf verschiedenen Statuus besteht
 * @author Maximilian
 *
 */
public class Main extends StateBasedGame{
	
	
	//Variablen

	public static final int BASE_HEIGTH = 1000;
	public static final int BASE_WIDTH  = 1500;
	
	public static int height = BASE_HEIGTH;
	public static int width  = BASE_WIDTH;
	public static String company_name = "SchokoFactory GMbH";
	public static double money = 900;
	
	public static TrueTypeFont djb_speak_the_truth;
	
	
	//-------------------------------------------------------------------------
	
	// Spielmethoden
	
	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		this.addState(new SelectMapState());
		
		//this.addState(new MainState());
		
		
		
		//this.addState(new MainMenuState());
		
		
		
		
	}
	
	
	//-------------------------------------------------------------------------
	
	//Starten des Spiels
	
	public static void main(String[] args) throws LWJGLException {
		try {
			AppGameContainer app = new AppGameContainer(new Main("SchokoFactory")); // Spiel Title = Basic
			app.setDisplayMode(BASE_WIDTH, BASE_HEIGTH, false); // Fenstergröße
			app.setTargetFrameRate(60); // Framerate auf 60 beschränkt
			
			//Resizable setzen
			Display.setResizable(true);
			
			/*
			DisplayMode[] modes = Display.getAvailableDisplayModes();
			System.out.println("Mögliche Auflösungen:     (alles andere gibt Fehlermeldungen)");
	        for (int i=0;i<modes.length;i++) {
	            DisplayMode current = modes[i];
	            System.out.println("  "+current.getWidth() + "x" + current.getHeight());
	        } */

			
			app.setVSync(true); // VSync benutzen
			app.start(); // Spiel starten
			
			loadResources();
			
			
		} catch (SlickException e) {
			System.err.println("ERROR <001>: Fenster konnte nicht erstellt werden!");
			e.printStackTrace();
		}

		
		
	}
	
	private static void loadResources() {
		
		
	}


	public Main(String title) {
		super(title);
	}
	

	
}
