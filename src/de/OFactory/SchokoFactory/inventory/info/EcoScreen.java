package de.OFactory.SchokoFactory.inventory.info;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import de.OFactory.SchokoFactory.game.GameFonts;
import de.OFactory.SchokoFactory.inventory.CakeChart;
import de.OFactory.SchokoFactory.inventory.LinesChart;
import de.OFactory.SchokoFactory.main.Drawable;
import de.OFactory.SchokoFactory.main.MainState;
import de.OFactory.SchokoFactory.main.Updateable;

public class EcoScreen  implements Drawable, Updateable{

	//Position
	private int x;
	private int y;
	private int width;
	private int height;
	
	private boolean show;
	
	private CakeChart anteilchart;
	public LinesChart wachstumschart;
	public static final Color INFO_FONT_COLOR = new Color(50, 100, 200);
	public static final Color BOX_COLOR = new Color(220, 220, 220);
	
	
	
	public EcoScreen(int x, int y, int width, int height) {
		
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		int offx = this.x + 20;
		int offy = this.y + 20;
		
		anteilchart = new CakeChart(1, offx + 20, offy + 625, 150, "m�chte-gern-CakeChart");
		wachstumschart = new LinesChart(offx + 20 + 300 + 20, offy + 625, 500, 285);
		
	}

	@Override
	public void update(GameContainer gc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics g) {
		if (this.show) {
			//Hintergrund
			g.setColor(InfoPanel.BG_COLOR);
			g.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
			g.setColor(Color.black);
			g.drawRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
			
			//Inhalt
			int offx = this.x + 20;
			int offy = this.y + 65;
			
			// Datenbox
			g.setColor(BOX_COLOR);
			g.fillRect(offx, offy + 10, 270, 530);
			
					
			
			GameFonts.MAIN.drawString( offx + 55, offy - 30,  "Economy Screen",           		                    Color.black);
			
			GameFonts.MED.drawString( offx + 10, offy + 20,  "Marktinformation",                                Color.black);
			GameFonts.SUB.drawString( offx + 10, offy + 50,  "Datum: "        + MainState.m.getDateString(),    INFO_FONT_COLOR);
	 
			GameFonts.MED.drawString( offx + 10, offy + 90,  "Markt",                                           Color.black);
			GameFonts.SUB.drawString( offx + 10, offy + 120, "Bedarf: "       + MainState.m.getBedarf(),        INFO_FONT_COLOR);
			GameFonts.SUB.drawString( offx + 10, offy + 140, "Absatz: "       + MainState.m.getSummeAbs(),      INFO_FONT_COLOR);
			GameFonts.SUB.drawString( offx + 10, offy + 160, "Wachstum: "     + MainState.m.getZuwachsString(), INFO_FONT_COLOR);

			GameFonts.MED.drawString( offx + 10, offy + 200, "Spieler",                                         Color.black);
			GameFonts.SUB.drawString( offx + 10, offy + 230, "Absatz: "       + MainState.p.getAbsatz(),        INFO_FONT_COLOR);
			GameFonts.SUB.drawString( offx + 10, offy + 250, "Bekanntheit: "  + round(MainState.p.getBekanntheit(),2), INFO_FONT_COLOR);
			GameFonts.SUB.drawString( offx + 10, offy + 270, "Marktanteil: "  + MainState.p.getMarktanteil(),   INFO_FONT_COLOR);
			GameFonts.SUB.drawString( offx + 10, offy + 290, "Produktmenge: " + MainState.p.getProduktmenge(),  INFO_FONT_COLOR);
			GameFonts.SUB.drawString( offx + 10, offy + 310, "Qualit�t: "     + round(MainState.p.getQualitaet(),2),     INFO_FONT_COLOR);
			
			GameFonts.MED.drawString( offx + 10, offy + 350, "Beispiel AI",                                     Color.black);
			GameFonts.SUB.drawString( offx + 10, offy + 380, "Absatz: "       + MainState.ai.getAbsatz(),       INFO_FONT_COLOR);
			GameFonts.SUB.drawString( offx + 10, offy + 400, "Bekanntheit: "  + round(MainState.ai.getBekanntheit(),2), INFO_FONT_COLOR);
			GameFonts.SUB.drawString( offx + 10, offy + 420, "Marktanteil: "  + MainState.ai.getMarktanteil(),  INFO_FONT_COLOR);
			GameFonts.SUB.drawString( offx + 10, offy + 440, "Produktmenge: " + MainState.ai.getProduktmenge(), INFO_FONT_COLOR);
			GameFonts.SUB.drawString( offx + 10, offy + 460, "Qualit�t: "     + round(MainState.ai.getQualitaet(),2), INFO_FONT_COLOR);
			GameFonts.SUB.drawString( offx + 10, offy + 480, "Geld: "         + MainState.ai.getMoney(),        INFO_FONT_COLOR);
			
			
			
			
			//Diagrammbox
			g.fillRect(offx, offy + 550, 950, 350);
			
			int[] slices = {MainState.p.getAbsatz(), MainState.ai.getAbsatz(), MainState.ai.getAbsatz()};
			anteilchart.setSlices(slices);
			anteilchart.draw(g);
			anteilchart.setX(offx+20);
			
			//wachstumschart.addPoint(MainState.m.getSummeAbs());

			wachstumschart.draw(g);
		}
		
		
	}
	
	public double round(double d, int digits) {
		return (float)Math.round((float)d *Math.pow(10, digits)) /Math.pow(10, digits);
	}
	
	public void toggle() {
		if (this.show) 
			this.show = false;
		else
			this.show = true;
	}
	
	public boolean isShow() {
		return show;
	}

	public void setShow(boolean show) {
		this.show = show;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

}
