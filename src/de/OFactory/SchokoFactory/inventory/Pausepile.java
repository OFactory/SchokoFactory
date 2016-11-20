package de.OFactory.SchokoFactory.inventory;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.ShapeFill;
import org.newdawn.slick.fills.GradientFill;
import org.newdawn.slick.geom.Rectangle;


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
		ShapeFill stockpileFill = new GradientFill(0, this.getHeight(), new Color(146, 146, 146),  0, 0, new Color(175, 175, 175));
		g.fill( this.shape, stockpileFill);
		 
		
		g.setColor(Color.black);
		
		//Zeugs drauf printen
		//Font font = new Font("Verdana", Font.BOLD, (int) (this.shape.getHeight()/2));
		//TrueTypeFont ttf = new TrueTypeFont(font, true);

		//ttf.drawString(8, 10, "LOOOL");
	}

}
