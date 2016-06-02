package de.OFactory.SchokoFactory.inventory.info.tabs;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import de.OFactory.SchokoFactory.game.GameFonts;
import de.OFactory.SchokoFactory.inventory.info.InfoPanel;
import de.OFactory.SchokoFactory.inventory.info.Tab;

public class MarketInfoTab extends Tab{

	public MarketInfoTab(InfoPanel ip, Image img) {
		super(ip, img, "Marktinfo");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void drawContent(Graphics g) {
		GameFonts.SUB.drawString(getInfoPanel().getX() + 10, getInfoPanel().getY()+20, "Marktinformation", Color.black);
		
	}

	@Override
	public void updateContent(GameContainer gc) {
		// TODO Auto-generated method stub
		
	}

}
