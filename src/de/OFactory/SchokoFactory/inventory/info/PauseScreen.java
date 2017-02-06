package de.OFactory.SchokoFactory.inventory.info;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import de.OFactory.SchokoFactory.inventory.Button;
import de.OFactory.SchokoFactory.main.Drawable;
import de.OFactory.SchokoFactory.main.MainState;
import de.OFactory.SchokoFactory.main.Updateable;

public class PauseScreen implements Drawable, Updateable{
	
	private boolean active;
	private int width;
	private int height;
	
	Button hauptmenü;
	
	public PauseScreen(GameContainer gc){
		setWidth(gc.getWidth());
		setHeight(gc.getHeight());
		
		hauptmenü = new Button(20, gc.getWidth()/3, (int) (gc.getHeight()/2D), gc.getWidth()/3, gc.getHeight()/10, "Hauptmenü", 0);
		hauptmenü.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				MainState.field.saveMap();
				MainState.sbg.enterState(2);
				
			}
		});
	}

	public void update(GameContainer gc) {
		if(gc.getInput().isKeyDown(Input.KEY_ESCAPE)){
			MainState.run = !MainState.run;
			toggle();
		}
		
		if(active){
			hauptmenü.update(gc.getInput());
		}
	}

	public void draw(Graphics g) {
		if(active){
			Color display = new Color(0.5F, 0.5F, 0.5F, 0.4F);
			g.setColor(display);
			g.fillRect(0, 0, width, height);
			hauptmenü.draw(g);
		}
		
		
		
	}
	
	public void toggle(){
		active = !active;
	}
	
	/**
	 * @return the active
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * @param active the active to set
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	
	
	
	
}
