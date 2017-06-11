package de.OFactory.SchokoFactory.game.patterns;

import java.io.Serializable;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import de.OFactory.SchokoFactory.game.Pattern;
import de.OFactory.SchokoFactory.game.PatternState;
import de.OFactory.SchokoFactory.game.Workable;
import de.OFactory.SchokoFactory.main.Drawable;
import de.OFactory.SchokoFactory.main.MainState;
import de.OFactory.SchokoFactory.main.ResourceManager;

public class Gießer extends Pattern implements Workable, Drawable, Serializable{
	
	private static final long serialVersionUID = 3L;
	
	private boolean working = true;
	private int produktion = 1000;
	private int einmaligeFabrikkosten = 200;
	private int laufendeFabrikkosten = 0;
	
	private int alter = 0;
	private int effizienz = 100;
	

	
	public Gießer(int x, int y, int id, int xcoor, int ycoor) {
		super(x, y, PatternState.GIEßER, id, xcoor, ycoor);

		//start,end,total,delay
		setAnimation(0,3,5,2);
		
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
			float xpos = getX() + ResourceManager.pattern_width/2 - phover.getWidth()/2;
			float ypos = (float) (getY() + ResourceManager.pattern_height/3 + 2*Math.sin(System.currentTimeMillis()/200));
			
			phover.draw(xpos, ypos, 0.05F);
			
		}
		
		
	}
	
	// Produziert 100 Tafeln aus dem nichts bei jedem Aufruf. Idealer Weise täglich.
	public void work() {
		
		alter++;

		if(this.isWorking()){
			MainState.p.stock.addTafeln(produktion);
			MainState.p.stock.addFlüssigeSchokolade(-produktion/10); // 1 Liter Schokolade zu 10 Tafeln
			MainState.p.addMoney(-laufendeFabrikkosten);
			MainState.p.addAusgaben(laufendeFabrikkosten);
		}
	}

	@Override
	public void updatePatternInfo() {
		getPatternInfo().clear();
		// XXX Mögliche Attribute: Effizienz, Gegoßenes Produkt, Gerätealter, laufende Kosten
		if(isWorking())
			putPatternInfo("Status", "Arbeitend");
		else
			putPatternInfo("Status", "Deaktiviert");
		
		putPatternInfo("Gegoßenes Produkt", "Schokolade");
		putPatternInfo("Laufende Kosten",   laufendeFabrikkosten);
		putPatternInfo("Gerätealter",       alter + " Tage");
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

		this.working = working;
	}
}
