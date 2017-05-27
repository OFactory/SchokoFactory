package de.OFactory.SchokoFactory.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.security.auth.RefreshFailedException;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import de.OFactory.SchokoFactory.game.GameFonts;
import de.OFactory.SchokoFactory.game.GameSave;
import de.OFactory.SchokoFactory.inventory.MainMenuButton;
import de.OFactory.SchokoFactory.inventory.MapButton;

public class SelectMapState extends BasicGameState{

	ArrayList<MapButton> buttons = new ArrayList<MapButton>();
	MapButton selected;
	Image bg, fg;
	
	private int scroll = 0;
	private int size;
	
	MainMenuButton ready, refresh, generate, back;
	
	public void init(final GameContainer gc, final StateBasedGame sbg) throws SlickException {
		
		bg = ResourceManager.loadImage("assets/textures/gui/selectmapbg.png");
		fg = ResourceManager.loadImage("assets/textures/gui/selectmapfg.png");
		
		refreshMapList(gc, sbg);
		
//		Button generate = new Button(-1, 0, listOfFiles.length * height, gc.getWidth(), height, "Neue Map(20x20)", 0);
//		generate.addActionListener(new ActionListener() {
//			
//			public void actionPerformed(ActionEvent e) {
//				sbg.enterState(1,  new FadeOutTransition(), new FadeInTransition());
//				MainState.field = Map.generateMap(20, 20);
//				SimpleDateFormat df = new SimpleDateFormat( "dd-MM-YYYY HH-mm-ss,S" );
//				MainState.gs.setName("" + df.format(new Date()));
//				MainState.pausescreen.setShow(false);
//				
//			}
//		});
//  	buttons.add(generate);
//		
//		Button exit = new Button(-1, 0, (listOfFiles.length+1) * height, gc.getWidth(), height, "Zurück", 0);
//		exit.addActionListener(new ActionListener() {
//			
//			public void actionPerformed(ActionEvent e) {
//				sbg.enterState(0,  new FadeOutTransition(), new FadeInTransition());
//				
//			}
//		});
//		buttons.add(exit);
		
		ready    = new MainMenuButton(0, 50,   900, 400, 80, "Fertig");
		ready.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				sbg.enterState(1);
				MainState.overwriteGameSave(GameSave.read(selected.getContent()));
				MainState.pausescreen.setShow(false);
				
			}
		});
		refresh  = new MainMenuButton(0, 450,  900, 400, 80, "Aktualisieren");
		refresh.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				refreshMapList(gc, sbg);
			}
		});
		generate = new MainMenuButton(0, 850,  900, 400, 80, "Neue Map");
		back     = new MainMenuButton(0, 1250, 900, 400, 80, "Zurück");
		back.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				sbg.enterState(0);
				
			}
		});
	}
	
	public void refreshMapList(GameContainer gc, final StateBasedGame sbg){
		
		buttons.clear();
		int start = 235;
		int height = 60;
		
		File folder = new File("saves");
		File[] listOfFiles = folder.listFiles();
		
		for (int i = 0; i < listOfFiles.length; i++) {
		      if (listOfFiles[i].isFile() && listOfFiles[i].getPath().endsWith(".sf")) {
		    	  
		    	  
		    	  final File f = listOfFiles[i];
		    	  final MapButton b = new MapButton(i,
		    			  0,
		    			  start + height*i,
		    			  gc.getWidth(),
		    			  height,
		    			  f.getName().replaceAll(".sf", ""));
		    	  
		    	  b.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						selected = b;
						
						if(selected == b){
							sbg.enterState(1);
							MainState.overwriteGameSave(GameSave.readPath(f.getPath()));
							MainState.pausescreen.setShow(false);
						}
					}
				  });
		    	  
		    	  
		        buttons.add(b);
		      }
		}
	}

	public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
		
		bg.draw(0, 0, gc.getWidth(), gc.getHeight());
		
		for(MapButton b : buttons){
			b.draw(g);
		}
		
		fg.draw(0, 0, gc.getWidth(), gc.getHeight());
		GameFonts.ALTE_HAAS_100.drawString(150, 50, "Mapauswahl");
		ready.draw(g);
		refresh.draw(g);
		generate.draw(g);
		back.draw(g);
		
		
	}

	public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
		Input in = gc.getInput();
		in.clearKeyPressedRecord();	
		
		for(MapButton b : buttons){
			b.update(in);
		}
		
		ready.update(in);
		refresh.update(in);
		generate.update(in);
		back.update(in);
		
	}
	

	@Override
	public int getID() {
		return 2;
	}

}
