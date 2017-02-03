package de.OFactory.SchokoFactory.main;

import java.io.File;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import de.OFactory.SchokoFactory.inventory.TextView;

public class SelectMapState extends BasicGameState{

	TextView txt_test;
	
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		// TODO Auto-generated method stub
		
		txt_test = new TextView("Dies ist ein Test! Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.", 200, 200, 500);
		
	}

	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.drawString("Maps:", 50, 50);
		txt_test.draw(g);
		
		File folder = new File("saves");
		File[] listOfFiles = folder.listFiles();

	    for (int i = 0; i < listOfFiles.length; i++) {
	      if (listOfFiles[i].isFile()) {
	        g.drawString("saves/" + listOfFiles[i].getName().subSequence(0, listOfFiles[i].getName().length() - 3), 50, 50 + (i+1) * 20);
	      } else if (listOfFiles[i].isDirectory()) {
	    	  //ORDNER
	      }
	    }
		
	}

	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		// TODO Auto-generated method stub
		
	}
	

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 2;
	}

}
