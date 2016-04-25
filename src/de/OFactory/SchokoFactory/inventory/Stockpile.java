package de.OFactory.SchokoFactory.inventory;

import java.util.ArrayList;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.ShapeFill;
import org.newdawn.slick.fills.GradientFill;
import org.newdawn.slick.geom.Rectangle;

import de.OFactory.SchokoFactory.main.Drawable;
import de.OFactory.SchokoFactory.main.Main;
import de.OFactory.SchokoFactory.main.Updateable;

public class Stockpile implements Drawable, Updateable{
	
	protected ArrayList<String> piles = new ArrayList<String>();
	protected double padding_top;
	protected Rectangle shape;
	
	public Stockpile(double padding_top){
		this.padding_top = padding_top;
		
		this.shape = new Rectangle(0, 0, 100, 20);
		
	}
	
	public int getHeight(){
		return (int) (padding_top*Display.getHeight());

	}

	@Override
	public void update(GameContainer gc) {	
		this.shape.setWidth(Display.getWidth());
		this.shape.setHeight(getHeight());
		
		
		this.piles.clear();
		this.piles.add(Main.company_name);
		this.piles.add(Main.money + " EUR");
		this.piles.add("some random info");
		this.piles.add("5t0p l00k1ng @me");
		this.piles.add("1337");
		
	}

	@Override
	public void draw(Graphics g) {
		
		//Stockpile Hintergrund mit #Verlauf
		ShapeFill stockpileFill = new GradientFill(0, 2, new Color(42, 81, 170), 0, this.shape.getWidth(), new Color(74, 107, 182));
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
		
		for(String s : this.piles){	
			g.drawString(s, space*this.piles.indexOf(s)+padding, textheight);
		}
	}
	
	
}
