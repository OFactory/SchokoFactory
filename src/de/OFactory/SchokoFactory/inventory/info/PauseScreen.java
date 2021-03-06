package de.OFactory.SchokoFactory.inventory.info;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import de.OFactory.SchokoFactory.game.GameSave;
import de.OFactory.SchokoFactory.inventory.Button;
import de.OFactory.SchokoFactory.main.Drawable;
import de.OFactory.SchokoFactory.main.MainState;
import de.OFactory.SchokoFactory.main.Updateable;

public class PauseScreen implements Drawable, Updateable{
	
	private boolean active;
	private int width;
	private int height;
	
	Button hauptmen�;
	private boolean show;
	
	public PauseScreen(GameContainer gc){
		setWidth(gc.getWidth());
		setHeight(gc.getHeight());
		
		hauptmen� = new Button(20, gc.getWidth()/3, (int) (gc.getHeight()/2D), gc.getWidth()/3, gc.getHeight()/10, "Hauptmen�", 0);
		hauptmen�.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				GameSave.saveMainState();
				MainState.sbg.enterState(2);
				active = false;
			}
		});
	}

	public void update(GameContainer gc) {
		
		Input in = gc.getInput();
		in.enableKeyRepeat();
		
		if(in.isKeyDown(Input.KEY_ESCAPE)) 
			show = true;
		else {
			if (show)				
				toggle();
			show = false;
		}
		
		if(active){
			hauptmen�.update(gc.getInput());
		}
	}

	public boolean isShow() {
		return show;
	}

	public void setShow(boolean show) {
		this.show = show;
	}

	public void draw(Graphics g) {
		if(active){
			Color display = new Color(0.5F, 0.5F, 0.5F, 0.4F);
			g.setColor(display);
			g.fillRect(0, 0, width, height);
			hauptmen�.draw(g);
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
