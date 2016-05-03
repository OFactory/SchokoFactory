package de.OFactory.SchokoFactory.inventory;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

import de.OFactory.SchokoFactory.main.Drawable;
import de.OFactory.SchokoFactory.main.MainState;
import de.OFactory.SchokoFactory.main.Updateable;

public class BuyButton implements Updateable, Drawable{

	Image noneimg;
	Image hoveredimg;
	Image activeimg;
	
	boolean hovered = false;
	boolean active = false;
	
	int x;
	int y;
	
	
	
	public BuyButton(int none, int hovered, int active, int x, int y, String cost){
		
		this.noneimg    = MainState.buybuttonimg[none];
		this.activeimg  = MainState.buybuttonimg[active];
		this.hoveredimg = MainState.buybuttonimg[hovered];
		
		this.x = x;
		this.y = y;
	}
	
	public void draw(Graphics g) {
		if(this.active)
			this.activeimg.draw(x, y);
		else if(this.hovered)
			this.hoveredimg.draw(x, y);
		else
			this.noneimg.draw(x, y);
		
		
	}

	public void update(GameContainer gc) {
		Input in = gc.getInput();
		
		if((in.getMouseX() > this.x && in.getMouseX() < this.x + this.noneimg.getWidth()) 
				&& (in.getMouseY() > this.y && in.getMouseY() < this.y + this.noneimg.getHeight())){
			this.hovered = true;
			
			if(in.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)){
				this.active = true;
			}
			
			
		} else {
			this.hovered = false;
		}
		
		
	}

}
