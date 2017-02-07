package de.OFactory.SchokoFactory.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import de.OFactory.SchokoFactory.game.Map;
import de.OFactory.SchokoFactory.inventory.Button;

public class SelectMapState extends BasicGameState{

	ArrayList<Button> buttons = new ArrayList<Button>();
	
	public void init(GameContainer gc, final StateBasedGame sbg) throws SlickException {
		// TODO Auto-generated method stub
		File folder = new File("saves");
		File[] listOfFiles = folder.listFiles();
		int height = gc.getHeight()/(listOfFiles.length + 2);
		
		// TODO Auto-generated method stub
		for (int i = 0; i < listOfFiles.length; i++) {
		      if (listOfFiles[i].isFile()) {
		    	  
		    	  
		    	  final File f = listOfFiles[i];
		    	  Button b = new Button(i, 0, height*i, gc.getWidth(), height, f.getName(), 0);
		    	  b.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e) {
						

						
						sbg.enterState(1,  new FadeOutTransition(), new FadeInTransition());
						MainState.field = Map.readSavedMap(f.getPath());
						MainState.pausescreen.setShow(false);
					}
				  });
		    	  
		    	  
		        buttons.add(b);
		      } else if (listOfFiles[i].isDirectory()) {
		    	  //ORDNER
		      }
		}
		
		Button generate = new Button(-1, 0, listOfFiles.length * height, gc.getWidth(), height, "Neue Map(20x20)", 0);
		generate.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				sbg.enterState(1,  new FadeOutTransition(), new FadeInTransition());
				MainState.field = Map.generateMap(20, 20);
				SimpleDateFormat df = new SimpleDateFormat( "dd-MM-YYYY HH-mm-ss,S" );
				MainState.field.setName("" + df.format(new Date()));
				MainState.pausescreen.setShow(false);
				
			}
		});
		buttons.add(generate);
		
		Button exit = new Button(-1, 0, (listOfFiles.length+1) * height, gc.getWidth(), height, "Zurück", 0);
		exit.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				sbg.enterState(0,  new FadeOutTransition(), new FadeInTransition());
				
			}
		});
		buttons.add(exit);
	}

	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		
		for(Button b : buttons){
			b.draw(g);
		}
		g.drawString("Maps:(auch für NOAH LOL)", 50, 50);
	}

	public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
		
		gc.getInput().clearKeyPressedRecord();
		
		for(Button b : buttons){
			b.update(gc.getInput());
		}
		
	}
	

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 2;
	}

}
