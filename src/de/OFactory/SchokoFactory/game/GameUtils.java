package de.OFactory.SchokoFactory.game;


import java.util.Random;

import org.lwjgl.opengl.Display;

import de.OFactory.SchokoFactory.main.Main;

public class GameUtils {
	
	public static void refreshSize(){
		
		if(Display.getWidth() != Main.width || Display.getHeight() != Main.height){
			
			System.out.println("Display Dimensions: [ " + Display.getWidth() + " ; " + Display.getHeight() + " ]");
			Main.width = Display.getWidth();
			Main.height = Display.getHeight();
		}
	}
	
	/** Liefert eine Zufallsganzzahl 
	 * zwischen zwei angegebenen Werten an
	 *  
	 * @param:  int min: minimaler Wert
	 *          int max: maximiler Wert
	 * @return: random number r | min >= r <= max
	 */	
	public static int randInt(int min, int max) {

	    Random rand = new Random();

	    // nextInt ist normalerweise exklusive des obersten Wertes
	    // deshalb fügen wir 1 hinzu um den obersten Wert inklusiv zu machen.
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
	
}
