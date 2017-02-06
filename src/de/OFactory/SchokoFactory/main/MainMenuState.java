package de.OFactory.SchokoFactory.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import de.OFactory.SchokoFactory.inventory.Button;

public class MainMenuState  extends BasicGameState {
	
	// Variablen
	public static Button play;
	public static Button test;
	public static Image header;
	public static Image bg;
	
	public static float headersize;
	
	
	
	//-------------------------------------------------------------------------

	// Spielmethoden
	
	public void init(GameContainer gc, final StateBasedGame sbg) throws SlickException {
		
		play = new Button(0, gc.getWidth()/4, (int) (gc.getWidth()/4D), gc.getWidth()/2, gc.getHeight()/7, "Spielen", 0);
		play.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				sbg.enterState(2,  new FadeOutTransition(), new FadeInTransition());
				
			}
		});
		test = new Button(1, gc.getWidth()/4, (int) (gc.getWidth()/2.8D), gc.getWidth()/2, gc.getHeight()/7, "Beenden", 0);
		test.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				System.exit(0); // Programm beenden
				
			}
		});

	
		header = new Image("assets/textures/gui/header.png");
		bg     = new Image("assets/textures/gui/menu_bg.jpg");
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		
		headersize += 0.02;

		play.update(gc.getInput());
		test.update(gc.getInput());
		
		
		if(gc.getInput().isKeyPressed(Input.KEY_1))
			sbg.enterState(1, new FadeOutTransition(), new FadeInTransition());
		
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		
		
		/*
		g.setColor(new Color(180, 180, 180));
		g.fillRect(0, 0, gc.getWidth(), gc.getHeight());*/
		bg.draw(0, 0, gc.getWidth(), gc.getHeight());
		
		play.draw(g);
		test.draw(g);
		g.drawString("MainMenu - WARNUNG: DIESER HAUPTSCREEN DIENT NUR ZUR BELUSTIGUNG NOAHS", 50, 50);
		
		float headerscale = (float) (1.5 + (Math.sin(headersize)/10));
		header.draw(gc.getWidth()/2 - (header.getWidth()*headerscale)/2, gc.getHeight()/3 - (header.getHeight()*headerscale)/2, headerscale);
	}
	
	//-------------------------------------------------------------------------

	@Override
	public int getID() {
		return 0;
	}

}
