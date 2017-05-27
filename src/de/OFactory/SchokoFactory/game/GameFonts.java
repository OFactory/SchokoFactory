package de.OFactory.SchokoFactory.game;

import java.awt.Font;

import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.UnicodeFont;

import de.OFactory.SchokoFactory.main.ResourceManager;


/**
 * Alle Fonts die verwnedet werden!
 * WARNUNG: Es dauert ewig ewig das ganze Zeug zu laden!
 * TODO FIXIT
 * 
 * @author Maximilian
 *
 */
public class GameFonts {
	
	public static TrueTypeFont MAIN  = new TrueTypeFont(new Font("Calibri", Font.BOLD,  32), true);
	public static TrueTypeFont MED   = new TrueTypeFont(new Font("Calibri", Font.PLAIN, 25), true);
	public static TrueTypeFont SUB   = new TrueTypeFont(new Font("Calibri", Font.PLAIN, 20), true);
	public static TrueTypeFont SMALL = new TrueTypeFont(new Font("Calibri", Font.PLAIN, 15), true); 
	
	public static UnicodeFont ALTE_HAAS_200;
	public static UnicodeFont ALTE_HAAS_50;
	public static UnicodeFont ALTE_HAAS_100;
	
	public static void loadFont(){
		System.out.println("Lade Schriftarten...");
		ALTE_HAAS_200   = ResourceManager.deriveUnicodeFont("AlteHaasGroteskBold", 200);
		ALTE_HAAS_50    = ResourceManager.deriveUnicodeFont("AlteHaasGroteskBold", 50);
		ALTE_HAAS_100   = ResourceManager.deriveUnicodeFont("AlteHaasGroteskBold", 100);
	}
}
