package de.OFactory.SchokoFactory.game.patterns;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import de.OFactory.SchokoFactory.game.Map;
import de.OFactory.SchokoFactory.game.Pattern;
import de.OFactory.SchokoFactory.game.PatternFrame;
import de.OFactory.SchokoFactory.game.PatternState;
import de.OFactory.SchokoFactory.game.Workable;
import de.OFactory.SchokoFactory.main.Drawable;
import de.OFactory.SchokoFactory.main.MainState;
import de.OFactory.SchokoFactory.main.ResourceManager;

public class Gieﬂer extends Pattern implements Workable, Drawable{
	
	private boolean working = true;
	private int produktion = 100;
	private int einmaligeFabrikkosten = 200;
	private int laufendeFabrikkosten = 50;
	
	private int alter = 0;
	private int effizienz = 100;
	
	public Gieﬂer(Map map, int x, int y, int id, int xcoor, int ycoor) {
		super(map, x, y, PatternState.GIEﬂER, id, xcoor, ycoor);
		
		this.setDelay(6);
		this.setFrameLoop(PatternFrame.GIEﬂER_S, PatternFrame.GIEﬂER_E);
		
		// Kosten:
		MainState.p.addMoney(-400);
		MainState.p.addAusgaben(400);
	}

	@Override
	public void updateContext() {
		
	}
	
	@Override
	public void draw(Graphics g){
		super.draw(g);
		if(!this.isWorking()){
			//Bild laden
			Image phover = ResourceManager.loadImage("assets/textures/patterns/hovers/pause_hover.png");
			float xpos = getX() + getCurrentImage().getWidth()/2 - phover.getWidth()/2;
			float ypos = (float) (getY() + getCurrentImage().getHeight()/3 + 2*Math.sin(System.currentTimeMillis()/200));
			
			phover.draw(xpos, ypos, 0.05F);
			
		}
		
		
	}
	
	// Produziert 100 Tafeln aus dem nichts bei jedem Aufruf. Idealer Weise t‰glich.
	public void work() {
		
		alter++;
		
		if(this.isWorking()){
			MainState.p.addProduktmenge(produktion);
			MainState.p.addMoney(-laufendeFabrikkosten);
			MainState.p.addAusgaben(laufendeFabrikkosten);
		}
	}

	@Override
	public void updatePatternInfo() {
		getPatternInfo().clear();
		// XXX Mˆgliche Attribute: Effizienz, Gegoﬂenes Produkt, Ger‰tealter, laufende Kosten
		if(isWorking())
			putPatternInfo("Status", "Arbeitend");
		else
			putPatternInfo("Status", "Deaktiviert");
		
		putPatternInfo("Gegoﬂenes Produkt", "Schokolade");
		putPatternInfo("Laufende Kosten",   laufendeFabrikkosten);
		putPatternInfo("Ger‰tealter",       alter + " Tage");
		putPatternInfo("Effizienz",         effizienz + "%");
		
	}

	public int getEinmaligeFabrikkosten() {
		return einmaligeFabrikkosten;
	}
	public void setEinmaligeFabrikkosten(int einmaligeFabrikkosten) {
		this.einmaligeFabrikkosten = einmaligeFabrikkosten;
	}
	public int getLaufendeFabrikkosten() {
		return laufendeFabrikkosten;
	}
	public void setLaufendeFabrikkosten(int laufendeFabrikkosten) {
		this.laufendeFabrikkosten = laufendeFabrikkosten;
	}

	/**
	 * @return the working
	 */
	public boolean isWorking() {
		return working;
	}

	/**
	 * @param working the working to set
	 */
	public void setWorking(boolean working) {
		if(working){
			this.setFrameLoop(PatternFrame.GIEﬂER_S, PatternFrame.GIEﬂER_E);
		} else {
			this.setPatternFrame(PatternFrame.GIEﬂER_S);
		
		}
		this.working = working;
	}
}
