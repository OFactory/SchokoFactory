package de.OFactory.SchokoFactory.inventory;

import java.util.ArrayList;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.ShapeFill;
import org.newdawn.slick.fills.GradientFill;
import org.newdawn.slick.geom.Rectangle;

import de.OFactory.SchokoFactory.game.GameFonts;
import de.OFactory.SchokoFactory.main.Drawable;
import de.OFactory.SchokoFactory.main.MainState;
import de.OFactory.SchokoFactory.main.Updateable;


public class Stockpile implements Drawable, Updateable{
	
	protected ArrayList<String> piles = new ArrayList<String>();
	protected double padding_top;
	protected Rectangle shape;
	protected int height;
	
	public Stockpile(int height){
		
		this.height = height;
		this.shape = new Rectangle(0, 0, 100, 20);
		
	}
	
	public int getHeight(){
		return height;
	}

	public void update(GameContainer gc) {	
		this.shape.setWidth(Display.getWidth());
		this.shape.setHeight(getHeight());
		
		
		this.piles.clear();
		this.piles.add(MainState.m.getDateString());
		this.piles.add("Bundesregierung erhöht Steuern ... ");//Infolge dessen steigt der Milchpreis um 3.6% ...");
		this.piles.add(MainState.p.getMoney() + " EUR");
		this.piles.add(MainState.molten_chokolate+" Liter");
		this.piles.add(MainState.p.stock.getTafeln() + " produktmenge");
		
		
	}

	public void draw(Graphics g) {
		
		//Stockpile Hintergrund mit #Verlauf
		ShapeFill stockpileFill = new GradientFill(0, this.getHeight(), new Color(203, 201, 201),  0, 0, new Color(230, 230, 230));
		g.fill(this.shape, stockpileFill);
		 
		
		g.setColor(Color.black);
		
		//Zeugs drauf printen
		//Font font = new Font("Verdana", Font.BOLD, (int) (this.shape.getHeight()/2));
		//TrueTypeFont ttf = new TrueTypeFont(font, true);

		//ttf.drawString(8, 10, "LOOOL");
		
		g.setColor(Color.white);
		
		int padding = 20;
		float textheight = this.shape.getHeight()/3;
		float space = this.shape.getWidth()/this.piles.size();
		
		g.setColor(new Color(135, 135, 135));
		g.setFont(GameFonts.SUB);
		
		for(String s : this.piles){	
			g.drawString(s, space*this.piles.indexOf(s)+padding, textheight);
		}
	}
	
	
}
