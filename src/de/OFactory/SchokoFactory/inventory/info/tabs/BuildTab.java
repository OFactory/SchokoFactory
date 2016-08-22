package de.OFactory.SchokoFactory.inventory.info.tabs;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import de.OFactory.SchokoFactory.game.GameFonts;
import de.OFactory.SchokoFactory.inventory.info.InfoPanel;
import de.OFactory.SchokoFactory.inventory.info.Tab;

public class BuildTab extends Tab{

	
	
	public BuildTab(InfoPanel ip, Image img) {
		super(ip, img, "Gebäude");
		
	}

	@Override
	public void drawContent(Graphics g) {
		GameFonts.MAIN.drawString(getInfoPanel().getX()+20, getInfoPanel().getY()+20, "Gebäude", Color.black);
		GameFonts.SUB.drawString(getInfoPanel().getX()+20, getInfoPanel().getY()+60, "Kauf alles MAMA!", Color.gray);
		GameFonts.SUB.drawString(getInfoPanel().getX()+20, getInfoPanel().getY()+80, "" + this.getInfoPanel().getScroll(), Color.black);
	}

	@Override
	public void updateContent(GameContainer gc) {
		
	}

}
