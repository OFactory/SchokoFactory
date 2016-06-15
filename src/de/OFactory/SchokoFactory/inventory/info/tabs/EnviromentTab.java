package de.OFactory.SchokoFactory.inventory.info.tabs;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import de.OFactory.SchokoFactory.game.GameFonts;
import de.OFactory.SchokoFactory.inventory.info.InfoPanel;
import de.OFactory.SchokoFactory.inventory.info.Tab;

public class EnviromentTab extends Tab{

	
	
	public EnviromentTab(InfoPanel ip, Image img) {
		super(ip, img, "Umwelt");
		
	}

	@Override
	public void drawContent(Graphics g) {
		GameFonts.MAIN.drawString(getInfoPanel().getX()+20, getInfoPanel().getY()+20, "Umwelt", Color.black);
		GameFonts.SUB.drawString(getInfoPanel().getX()+20, getInfoPanel().getY()+60, "Alles im grünen Bereich!", Color.gray);
		GameFonts.SUB.drawString(getInfoPanel().getX()+20, getInfoPanel().getY()+80, "*Badumtzz*", Color.gray);
	}

	@Override
	public void updateContent(GameContainer gc) {
		
	}

}
