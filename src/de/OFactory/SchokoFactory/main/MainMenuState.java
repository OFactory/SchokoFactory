package de.OFactory.SchokoFactory.main;

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

import de.OFactory.SchokoFactory.inventory.Button;

public class MainMenuState  extends BasicGameState {
	
	// Variablen
	public static Button play;
	public static Button test;
	public static Image header;
	
	public static float headersize;
	
	
	
	//-------------------------------------------------------------------------

	// Spielmethoden
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		
		play = new Button(0, 350, 350, 600, 100, "Spielen", 20);
		test = new Button(1, 350, 500, 600, 100, "Test", Color.blue, Color.green, Color.red, 20);
		header = new Image("assets/textures/gui/header.png");
		
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		
		headersize += 0.02;

		play.update(gc.getInput());
		test.update(gc.getInput());
		
		if(gc.getInput().isKeyPressed(Input.KEY_1))
			sbg.enterState(1, new FadeOutTransition(), new FadeInTransition());
		
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		
		g.setColor(new Color(180, 180, 180));
		g.fillRect(0, 0, gc.getWidth(), gc.getHeight());
		
		play.draw(g);
		test.draw(g);
		g.drawString("MainMenu", 50, 50);
		
		float headerscale = (float) (1.5 + (Math.sin(headersize)/10));
		header.draw(650 - (header.getWidth()*headerscale)/2, 200 - (header.getHeight()*headerscale)/2, headerscale);
	}
	
	//-------------------------------------------------------------------------

	@Override
	public int getID() {
		return 0;
	}

}
