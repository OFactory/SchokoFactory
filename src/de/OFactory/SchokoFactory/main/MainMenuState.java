package de.OFactory.SchokoFactory.main;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ShadowEffect;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import de.OFactory.SchokoFactory.game.GameFonts;
import de.OFactory.SchokoFactory.inventory.MainMenuButton;

public class MainMenuState  extends BasicGameState {
	
	// Variablen
	public static MainMenuButton single;
	public static MainMenuButton multi;
	public static MainMenuButton options;
	public static MainMenuButton close;
	public static Image header;
	public static Image bg;
	
	public static float headersize;
	
	
	
	//-------------------------------------------------------------------------

	// Spielmethoden
	
	public void init(GameContainer gc, final StateBasedGame sbg) throws SlickException {
		
		GameFonts.loadFont();
		
		single = new MainMenuButton(0, 150, 600, 500, 60, "Einzelspieler");
		single.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				sbg.enterState(2);
				
			}
		});
		
		multi   = new MainMenuButton(1, 150, 660, 500, 60, "Mehrspieler");
		options = new MainMenuButton(2, 150, 720, 500, 60, "Optionen");
		close   = new MainMenuButton(3, 150, 780, 500, 60, "Beenden");
		close.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				System.exit(0); // Programm beenden
				
			}
		});

	
		//header = new Image("assets/textures/gui/header.png");
		bg     = new Image("assets/textures/gui/Schokolade.jpg");
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		
		headersize += 0.02;

		Input in = gc.getInput();
		
		single.update(in);
		multi.update(in);
		options.update(in);
		close.update(in);
		
		
		if(gc.getInput().isKeyPressed(Input.KEY_1))
			sbg.enterState(1);
		
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		
		
		/*
		g.setColor(new Color(180, 180, 180));
		g.fillRect(0, 0, gc.getWidth(), gc.getHeight());*/
		bg.draw(0, 0, gc.getWidth(), gc.getHeight());
		
		single.draw(g);
		multi.draw(g);
		options.draw(g);
		close.draw(g);
		
		
		if(GameFonts.ALTE_HAAS_200 != null){
			
			UnicodeFont tmp = GameFonts.ALTE_HAAS_200;
			tmp.getEffects().add(new ShadowEffect(Color.BLACK, 5, 5, 0.5F));
			tmp.addAsciiGlyphs();
			tmp.loadGlyphs();
			tmp.drawString(100, 150, "SchokoFactory");
			
//			tmp = GameFonts.ALTE_HAAS_SMALL;
//			tmp.drawString(100, 600, "Einzelspieler");
//			tmp.drawString(100, 660, "Mehrspieler");
//			tmp.drawString(100, 720, "Optionen");
//			tmp.drawString(100, 780, "Beenden");
			
		}
	
	}
	
	//-------------------------------------------------------------------------

	@Override
	public int getID() {
		return 0;
	}

}
