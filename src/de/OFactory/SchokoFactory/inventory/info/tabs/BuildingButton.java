package de.OFactory.SchokoFactory.inventory.info.tabs;

import org.newdawn.slick.Color;

import de.OFactory.SchokoFactory.game.PatternState;
import de.OFactory.SchokoFactory.inventory.Button;
import de.OFactory.SchokoFactory.inventory.info.InfoPanel;

public class BuildingButton extends Button{

	public BuildingButton(int ID, InfoPanel ip, PatternState ps, Integer price) {
		super(ID, ip.getX(), 0, ip.getWidth(), 40, ps.getName() + "   " + price, 0);
		// TODO Auto-generated constructor stub
		
		
		this.clicked_color = new Color(100, 100, 100);
		this.hovered_color = new Color(150, 150, 255);
		this.normal_color  = new Color(	65,105,225);
	}

}
