package de.OFactory.SchokoFactory.main;

import java.io.File;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class SelectMapState extends BasicGameState{

	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		// TODO Auto-generated method stub
		
	}

	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.drawString("Maps:", 50, 50);
		
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
