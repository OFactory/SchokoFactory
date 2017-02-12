package de.OFactory.SchokoFactory.inventory;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.ShapeFill;
import org.newdawn.slick.fills.GradientFill;
import org.newdawn.slick.geom.Rectangle;

import de.OFactory.SchokoFactory.game.GameFonts;
import de.OFactory.SchokoFactory.main.MainState;


public class Pausepile extends Stockpile{

	private int offset = 0;
	
	public Pausepile(int off, int height) {
		super(height);
		this.offset = off;
		// TODO Auto-generated constructor stub
	}
	
	public void update(GameContainer gc){
		this.shape = new Rectangle(0, offset, gc.getWidth(), this.getHeight());
	}
	
	public void draw(Graphics g){
		//Stockpile Hintergrund mit #Verlauf
		ShapeFill stockpileFill = new GradientFill(0, this.getHeight(), new Color(148, 148, 148),  0, 100, new Color(175, 175, 175));
		g.fill( this.shape, stockpileFill);
		 
		
		g.setColor(Color.black);
		
		//Zeugs drauf printen
		if (!MainState.run)
			GameFonts.MAIN.drawString( 1400, offset-3,  "II", Color.black);
		else
			GameFonts.MAIN.drawString( 1400, offset-4,  ">", Color.black);
		GameFonts.SUB.drawString( 1500, offset+2,  "(Pause mit Leertaste)", Color.black);
	}

}
