package de.OFactory.SchokoFactory.inventory.info.tabs;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import de.OFactory.SchokoFactory.game.GameFonts;
import de.OFactory.SchokoFactory.inventory.BlockChart;
import de.OFactory.SchokoFactory.inventory.CakeChart;
import de.OFactory.SchokoFactory.inventory.LineChart;
import de.OFactory.SchokoFactory.inventory.info.InfoPanel;
import de.OFactory.SchokoFactory.inventory.info.Tab;
import de.OFactory.SchokoFactory.main.MainState;

public class MarketInfoTab extends Tab{
	
	private CakeChart anteilchart;
	public BlockChart wachstumschart; // oder als LineChart()
	public static final Color INFO_FONT_COLOR = new Color(50, 100, 200);
	
	public MarketInfoTab(InfoPanel ip, Image img) {
		super(ip, img, "Marktinfo");
		// TODO Auto-generated constructor stub
		int offx = getInfoPanel().getX();
		int offy = getInfoPanel().getY();
		anteilchart = new CakeChart(1, offx + 20, offy + 500, 100, "möchte-gern-CakeChart");
		wachstumschart = new BlockChart(offx + 20, offy + 720, 300, 160); // oder als LineChart()
	}

	@Override
	public void drawContent(Graphics g) {
		
		int offx = getInfoPanel().getX();
		int offy = getInfoPanel().getY();
		
		
		GameFonts.MED.drawString( offx + 10, offy + 20,  "Marktinformation",                                Color.black);
		GameFonts.SUB.drawString( offx + 10, offy + 50,  "Datum: "        + MainState.m.getDateString(),    INFO_FONT_COLOR);
 
		GameFonts.MED.drawString( offx + 10, offy + 90,  "Markt",                                           Color.black);
		GameFonts.SUB.drawString( offx + 10, offy + 120, "Bedarf: "       + MainState.m.getBedarf(),        INFO_FONT_COLOR);
		GameFonts.SUB.drawString( offx + 10, offy + 140, "Absatz: "       + MainState.m.getSummeAbs(),      INFO_FONT_COLOR);
		GameFonts.SUB.drawString( offx + 10, offy + 160, "Wachstum: "     + MainState.m.getZuwachsString(), INFO_FONT_COLOR);

		GameFonts.MED.drawString( offx + 10, offy + 200, "Spieler",                                         Color.black);
		GameFonts.SUB.drawString( offx + 10, offy + 230, "Absatz: "       + MainState.p.getAbsatz(),        INFO_FONT_COLOR);
		GameFonts.SUB.drawString( offx + 10, offy + 250, "Bekanntheit: "  + MainState.p.getBekanntheit(),   INFO_FONT_COLOR);
		GameFonts.SUB.drawString( offx + 10, offy + 270, "Marktanteil: "  + MainState.p.getMarktanteil(),   INFO_FONT_COLOR);
		GameFonts.SUB.drawString( offx + 10, offy + 290, "Produktmenge: " + MainState.p.getProduktmenge(),  INFO_FONT_COLOR);
		GameFonts.SUB.drawString( offx + 10, offy + 310, "Qualität: "     + MainState.p.getQualitaet(),     INFO_FONT_COLOR);
		
		GameFonts.MED.drawString( offx + 10, offy + 350, "Beispiel AI",                                     Color.black);
		GameFonts.SUB.drawString( offx + 10, offy + 380, "Absatz: "       + MainState.ai.getAbsatz(),       INFO_FONT_COLOR);
		GameFonts.SUB.drawString( offx + 10, offy + 400, "Bekanntheit: "  + MainState.ai.getBekanntheit(),  INFO_FONT_COLOR);
		GameFonts.SUB.drawString( offx + 10, offy + 420, "Marktanteil: "  + MainState.ai.getMarktanteil(),  INFO_FONT_COLOR);
		GameFonts.SUB.drawString( offx + 10, offy + 440, "Produktmenge: " + MainState.ai.getProduktmenge(), INFO_FONT_COLOR);
		GameFonts.SUB.drawString( offx + 10, offy + 460, "Qualität: "     + MainState.ai.getQualitaet(),    INFO_FONT_COLOR);
		GameFonts.SUB.drawString( offx + 10, offy + 480, "Geld: "         + MainState.ai.getMoney(),        INFO_FONT_COLOR);
		
		int[] slices = {MainState.p.getAbsatz(), MainState.ai.getAbsatz(), MainState.ai.getAbsatz()};
		anteilchart.setSlices(slices);
		anteilchart.draw(g);
		anteilchart.setX(offx+20);
		
		//wachstumschart.addPoint(MainState.m.getSummeAbs());
		wachstumschart.draw(g);
		
	}

	@Override
	public void updateContent(GameContainer gc) {
		// TODO Auto-generated method stub
		
	}

}
