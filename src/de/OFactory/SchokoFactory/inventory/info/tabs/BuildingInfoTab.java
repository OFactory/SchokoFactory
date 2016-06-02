package de.OFactory.SchokoFactory.inventory.info.tabs;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import de.OFactory.SchokoFactory.game.GameFonts;
import de.OFactory.SchokoFactory.inventory.info.InfoPanel;
import de.OFactory.SchokoFactory.inventory.info.Tab;

public class BuildingInfoTab extends Tab{

	public BuildingInfoTab(InfoPanel ip, Image img) {
		super(ip, img, "Gebäudeinformation");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void drawContent(Graphics g) {
		GameFonts.SUB.drawString(getInfoPanel().getX() + 10, getInfoPanel().getY()+20, "Gebäudeinformation", Color.black);
	}

}
