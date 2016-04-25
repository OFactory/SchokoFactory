package de.OFactory.SchokoFactory.main;

import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.util.ResourceLoader;

public class ResourceManager {
	
	public static Image loadImage(String file){
		try {
			return new Image(file);
		} catch (SlickException e) {
			System.out.println("ERROR(001): Konnte Datei " + file + " nicht laden!" );
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	public static TrueTypeFont loadFont(String file){
		// Font von .ttf Datei Laden
		try {
			InputStream inputStream	= ResourceLoader.getResourceAsStream(file);
	 
			Font awtFont = Font.createFont(Font.TRUETYPE_FONT, inputStream);
			awtFont = awtFont.deriveFont(24f); // Schriftgröße
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
