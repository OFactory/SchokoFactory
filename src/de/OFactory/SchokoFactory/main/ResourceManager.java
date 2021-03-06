package de.OFactory.SchokoFactory.main;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.util.ResourceLoader;

public class ResourceManager {
	
	public static int pattern_height = 944;
	public static int pattern_width = 709;
	
	public static Image pttrn_hof          = loadImage("assets/textures/patterns/pttrn_hof.png");
	public static Image pttrn_molkerei     = loadImage("assets/textures/patterns/pttrn_molkerei.png");
	public static Image pttrn_farm         = loadImage("assets/textures/patterns/pttrn_farm.png");
	public static Image pttrn_feld         = loadImage("assets/textures/patterns/pttrn_feld.png");
	public static Image pttrn_gewaechshaus = loadImage("assets/textures/patterns/pttrn_gewaechshaus.png");
	public static Image pttrn_chemfab      = loadImage("assets/textures/patterns/pttrn_chemfab.png");
	public static Image pttrn_labor        = loadImage("assets/textures/patterns/pttrn_labor.png");
	public static Image pttrn_ruehrer      = loadImage("assets/textures/patterns/pttrn_ruehrer.png");
	public static Image pttrn_tank         = loadImage("assets/textures/patterns/pttrn_tank.png");
	public static Image pttrn_giesser      = loadImage("assets/textures/patterns/pttrn_giesser.png");
	public static Image pttrn_lager        = loadImage("assets/textures/patterns/pttrn_lager.png");
	public static Image pttrn_prbuero      = loadImage("assets/textures/patterns/pttrn_prbuero.png");
	public static Image pttrn_gras         = loadImage("assets/textures/patterns/pttrn_gras.png");
	
	// SOUNDS
	public static Sound snd_click          = loadSound("assets/sounds/click.wav");
	public static Sound snd_hover          = loadSound("assets/sounds/hover.wav");
	
	public static Image loadImage(String file){
		try {
			return new Image(file);
		} catch (SlickException e) {
			System.err.println("ERROR<001>: Konnte Datei " + file + " nicht laden!" );
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	private static Sound loadSound(String file) {
		try {
			return new Sound(file);
		} catch (SlickException e) {
			System.err.println("ERROR<001>: Konnte Datei " + file + " nicht laden!" );
			e.printStackTrace();
		}
		
		return null;
	}

	public static void loadImages(){
		// Patterns
		/*Image pttrn_hof = loadImage("assets/textures/patterns/pttrn_hof.png");
		Image pttrn_molkerei = loadImage("assets/textures/patterns/pttrn_molkerei.png");
		Image pttrn_farm = loadImage("assets/textures/patterns/pttrn_farm.png");
		Image pttrn_feld = loadImage("assets/textures/patterns/pttrn_feld.png");
		Image pttrn_gewaechshaus = loadImage("assets/textures/patterns/pttrn_gewaechshaus.png");
		Image pttrn_chemfab = loadImage("assets/textures/patterns/pttrn_chemfab.png");
		Image pttrn_labor = loadImage("assets/textures/patterns/pttrn_labor.png");
		Image pttrn_ruehrer = loadImage("assets/textures/patterns/pttrn_ruehrer.png");
		Image pttrn_tank = loadImage("assets/textures/patterns/pttrn_tank.png");
		Image pttrn_giesser = loadImage("assets/textures/patterns/pttrn_giesser.png");
		Image pttrn_lager = loadImage("assets/textures/patterns/pttrn_lager.png");
		Image pttrn_prbuero = loadImage("assets/textures/patterns/pttrn_prbuero.png");*/
	}
	
	 public static UnicodeFont deriveUnicodeFont(String name, int size) {
	      try {
	         java.awt.Font original = java.awt.Font.createFont(
	               java.awt.Font.PLAIN, new File("assets/textures/gui/font/" + name
	                     + ".ttf"));

	         UnicodeFont derived = new UnicodeFont(original, size, false, false);

	         derived.getEffects().add(new ColorEffect(java.awt.Color.white));
	         derived.addAsciiGlyphs();
	         derived.loadGlyphs();

	         return derived;
	      } catch (FontFormatException e) {
	         e.printStackTrace();
	      } catch (IOException e) {
	         e.printStackTrace();     
	      } catch (SlickException e) {
			e.printStackTrace();
		}

	      return null;
	   }
	
	public static TrueTypeFont loadFont(String file){
		// Font von .ttf Datei Laden
		try {
			InputStream inputStream	= ResourceLoader.getResourceAsStream(file);
	 
			Font awtFont = Font.createFont(Font.TRUETYPE_FONT, inputStream);
			awtFont = awtFont.deriveFont(100f); // Schriftgröße
			return new TrueTypeFont(awtFont, false);
	 
		} catch (Exception e) {
			
			System.err.println("Error <003>: Schriftart \"" + file + "\" konnte nicht geladen werden");
			e.printStackTrace(); 
			
			return null;
		}
		
	}
	
	public static Image[] loadPics(String path, int pics){
		
		Image[] anim = new Image[pics];
		Image source = loadImage(path);
		
		for(int x=0; x<pics; x++){
			anim[x] = source.getSubImage(x*source.getWidth()/pics, 0, source.getWidth()/pics, source.getHeight());
		}
		
		return anim;
		
	}
	
	public static Image[] loadPics(Image source, int pics){
		
		Image[] anim = new Image[pics];
		
		for(int x=0; x<pics; x++){
			anim[x] = source.getSubImage(x*source.getWidth()/pics, 0, source.getWidth()/pics, source.getHeight());
		}
		
		return anim;
		
	}
	
	
}
