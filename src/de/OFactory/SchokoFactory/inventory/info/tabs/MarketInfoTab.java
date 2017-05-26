package de.OFactory.SchokoFactory.inventory.info.tabs;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.ShapeFill;
import org.newdawn.slick.fills.GradientFill;
import org.newdawn.slick.geom.Rectangle;

import de.OFactory.SchokoFactory.game.GameFonts;
import de.OFactory.SchokoFactory.inventory.CakeChart;
import de.OFactory.SchokoFactory.inventory.LinesChart;
import de.OFactory.SchokoFactory.inventory.info.InfoPanel;
import de.OFactory.SchokoFactory.inventory.info.Tab;
import de.OFactory.SchokoFactory.main.Daily;
import de.OFactory.SchokoFactory.main.MainState;

public class MarketInfoTab extends Tab implements Daily{
	
	private CakeChart anteilchart;
	public LinesChart wachstumschart; // oder als LineChart()
	public static final Color INFO_FONT_COLOR = new Color(50, 100, 200);
	
	public MarketInfoTab(InfoPanel ip, Image img) {
		super(ip, img, "Marktinfo");
		// TODO Auto-generated constructor stub
		int offx = getInfoPanel().getX();
		int offy = getInfoPanel().getY();
		anteilchart = new CakeChart(1, offx + 35, offy + 540, 60, "möchte-gern-CakeChart");
		wachstumschart = new LinesChart(offx + 35, offy + 690, 290, 180); // oder als LineChart()
		MainState.dailys.add(this);
		
	}

	@Override
	public void drawContent(Graphics g) {
		
		int offx = getInfoPanel().getX();
		int offy = getInfoPanel().getY();
		
		Rectangle s1 = new Rectangle(offx, offy, MainState.ip.getWidth(), 190);
		ShapeFill f1 = new GradientFill(offx, offy + 170, new Color(217, 216, 216),  offx, offy+190, new Color(205, 205, 205));
		g.fill( s1, f1);
		Rectangle s2 = new Rectangle(offx, offy + 320, MainState.ip.getWidth(), 200);
		ShapeFill f2 = new GradientFill(offx, offy + 470, new Color(217, 216, 216),  offx, offy + 520, new Color(205, 205, 205));
		g.fill( s2, f2);
		
		
		GameFonts.MED.drawString( offx + 30, offy + 20,  "Marktinformation",                                Color.black);
		GameFonts.SUB.drawString( offx + 25, offy + 50,  "Datum: "        + MainState.m.getDateString(),    INFO_FONT_COLOR);
 
		GameFonts.MED.drawString( offx + 30, offy + 90,  "Markt",                                           Color.black);
		GameFonts.SUB.drawString( offx + 25, offy + 120, "Bedarf: "       + MainState.m.getBedarf(),        INFO_FONT_COLOR);
		GameFonts.SUB.drawString( offx + 25, offy + 140, "Absatz: "       + MainState.m.getSummeAbs(),      INFO_FONT_COLOR);
		GameFonts.SUB.drawString( offx + 25, offy + 160, "Wachstum: "     + MainState.m.getZuwachsString(), INFO_FONT_COLOR);

		GameFonts.MED.drawString( offx + 30, offy + 200, "Spieler",                                         Color.black);
		GameFonts.SUB.drawString( offx + 25, offy + 230, "Absatz: "       + MainState.p.getAbsatz(),        INFO_FONT_COLOR);
		GameFonts.SUB.drawString( offx + 25, offy + 250, "Bekanntheit: "  + round(MainState.p.getBekanntheit(),2), INFO_FONT_COLOR);
		GameFonts.SUB.drawString( offx + 25, offy + 270, "Marktanteil: "  + MainState.p.getMarktanteil(),   INFO_FONT_COLOR);
		GameFonts.SUB.drawString( offx + 25, offy + 290, "Produktmenge: " + MainState.p.stock.getTafeln(),  INFO_FONT_COLOR);
		GameFonts.SUB.drawString( offx + 25, offy + 310, "Qualität: "     + round(MainState.p.getQualitaet(),2),     INFO_FONT_COLOR);
		
		GameFonts.MED.drawString( offx + 30, offy + 350, "AI2",                                              Color.black);
		GameFonts.SUB.drawString( offx + 25, offy + 380, "Absatz: "       + MainState.ai2.getAbsatz(),       INFO_FONT_COLOR);
		GameFonts.SUB.drawString( offx + 25, offy + 400, "Bekanntheit: "  + round(MainState.ai2.getBekanntheit(),2), INFO_FONT_COLOR);
		GameFonts.SUB.drawString( offx + 25, offy + 420, "Marktanteil: "  + MainState.ai2.getMarktanteil(),  INFO_FONT_COLOR);
		GameFonts.SUB.drawString( offx + 25, offy + 440, "Produktmenge: " + MainState.ai2.stock.getTafeln(), INFO_FONT_COLOR);
		GameFonts.SUB.drawString( offx + 25, offy + 460, "Qualität: "     + round(MainState.ai2.getQualitaet(),2), INFO_FONT_COLOR);
		GameFonts.SUB.drawString( offx + 25, offy + 480, "Geld: "         + MainState.ai2.getMoney(),        INFO_FONT_COLOR);
		
		int[] slices = {MainState.p.getAbsatz(), MainState.ai1.getAbsatz(), MainState.ai2.getAbsatz()};
		anteilchart.setSlices(slices);
		anteilchart.draw(g);
		anteilchart.setX(offx+20);
		
		//wachstumschart.addPoint(MainState.m.getSummeAbs());

		wachstumschart.draw(g);
		wachstumschart.setX(offx+35);
		
	}

	public void day() {
		int[] points = {MainState.m.getSummeAbs(), MainState.p.getAbsatz(), MainState.ai1.getAbsatz(), MainState.ai2.getAbsatz(), MainState.p.getMoegAbs(), MainState.ai1.getMoegAbs(), MainState.ai2.getMoegAbs()};

		this.wachstumschart.addPoints(points);
	}
	
	public double round(double d, int digits) {
		return (float)Math.round((float)d *Math.pow(10, digits)) /Math.pow(10, digits);
	}
	
	
	@Override
	public void updateContent(GameContainer gc) {
		// TODO Auto-generated method stub
		
	}

}
