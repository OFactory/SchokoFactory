package de.OFactory.SchokoFactory.inventory.info.tabs;

import org.newdawn.slick.Color;
import org.newdawn.slick.Input;

import de.OFactory.SchokoFactory.game.PatternState;
import de.OFactory.SchokoFactory.inventory.Button;
import de.OFactory.SchokoFactory.inventory.info.InfoPanel;

public class BuildingButton extends Button{
	
	private InfoPanel ip;
	private PatternState ps;
	

	public BuildingButton(int ID, InfoPanel ip, PatternState ps, Integer price) {
		super(ID, ip.getX(), 0, ip.getWidth(), 40, ps.getName() + "   " + price, 0);
		// TODO Auto-generated constructor stub
		
		this.ip = ip;
		this.ps = ps;
		this.clicked_color = new Color(100, 100, 100);
		this.hovered_color = new Color(150, 150, 255);
		this.normal_color  = new Color(	65,105,225);
	}

	@Override
	public void update(Input in){
		
		
		if(this.shape.contains(in.getMouseX(), in.getMouseY())) {
			this.hovered = true;
		} else {
			this.hovered = false;
		}
		
		BuildTab bt = null;
		
		if(this.hovered){
			if(in.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)){
				if(ip.getCurrentTab() instanceof BuildTab){
					System.out.println(this);
					bt = (BuildTab) ip.getCurrentTab();
					bt.setCurrentBuilding(this);
				}
			}
		}
		
		
		if(bt != null){
			if(bt.getCurrentBuilding() == this)
				this.clicked = true;
			else
				this.clicked = false;
		}
		
		
		
	}

	public PatternState getPs() {
		return ps;
	}

	public void setPs(PatternState ps) {
		this.ps = ps;
	}
}
