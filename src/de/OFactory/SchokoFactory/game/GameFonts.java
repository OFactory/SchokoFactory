package de.OFactory.SchokoFactory.game;

import java.awt.Font;

import org.newdawn.slick.TrueTypeFont;


/**
 * Alle Fonts die verwnedet werden!
 * WARNUNG: Es dauert ewig ewig das ganze Zeug zu laden!
 * TODO FIXIT
 * 
 * @author Maximilian
 *
 */
public class GameFonts {
	
	public static TrueTypeFont MAIN  = new TrueTypeFont(new Font("Calibri", Font.BOLD, 32), true);
	public static TrueTypeFont MED   = new TrueTypeFont(new Font("Calibri", Font.PLAIN, 25), true);
	public static TrueTypeFont SUB   = new TrueTypeFont(new Font("Calibri", Font.PLAIN, 20), true);
	public static TrueTypeFont SMALL = new TrueTypeFont(new Font("Calibri", Font.PLAIN, 15), true); 
}
