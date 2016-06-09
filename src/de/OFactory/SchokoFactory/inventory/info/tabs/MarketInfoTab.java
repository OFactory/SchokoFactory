package de.OFactory.SchokoFactory.inventory.info.tabs;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import de.OFactory.SchokoFactory.game.GameFonts;
import de.OFactory.SchokoFactory.inventory.info.InfoPanel;
import de.OFactory.SchokoFactory.inventory.info.Tab;
import de.OFactory.SchokoFactory.main.MainState;

public class MarketInfoTab extends Tab{

	public static final Color INFO_FONT_COLOR = new Color(50, 100, 200);
	
	public MarketInfoTab(InfoPanel ip, Image img) {
		super(ip, img, "Marktinfo");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void drawContent(Graphics g) {
		
		int offx = getInfoPanel().getX();
		int offy = getInfoPanel().getY();
		
		
		GameFonts.MED.drawString( offx + 10, offy + 20,  "Marktinformation",                               Color.black);
		GameFonts.SUB.drawString( offx + 10, offy + 50,  "Datum: "        + MainState.m.getDateString(),   INFO_FONT_COLOR);
 
		GameFonts.MED.drawString( offx + 10, offy + 90,  "Markt",                                          Color.black);
		GameFonts.SUB.drawString( offx + 10, offy + 120, "Bedarf: "       + MainState.m.getBedarf(),       INFO_FONT_COLOR);
		GameFonts.SUB.drawString( offx + 10, offy + 140, "Wachstum: "     + MainState.m.getZuwachsString(),INFO_FONT_COLOR);

		GameFonts.MED.drawString( offx + 10, offy + 180, "Spieler",                                        Color.black);
		GameFonts.SUB.drawString( offx + 10, offy + 210, "Absatz: "       + MainState.p.getAbsatz(),       INFO_FONT_COLOR);
		GameFonts.SUB.drawString( offx + 10, offy + 230, "Bekanntheit: "  + MainState.p.getBekanntheit(),  INFO_FONT_COLOR);
		GameFonts.SUB.drawString( offx + 10, offy + 250, "Marktanteil: "  + MainState.p.getMarktanteil(),  INFO_FONT_COLOR);
		GameFonts.SUB.drawString( offx + 10, offy + 270, "Produktmenge: " + MainState.p.getProduktmenge(), INFO_FONT_COLOR);
		GameFonts.SUB.drawString( offx + 10, offy + 290, "Qualität: "     + MainState.p.getQualitaet(),    INFO_FONT_COLOR);
		
	}

	@Override
	public void updateContent(GameContainer gc) {
		// TODO Auto-generated method stub
		
	}

}
