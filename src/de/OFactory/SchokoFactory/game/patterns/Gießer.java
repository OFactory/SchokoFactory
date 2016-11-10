package de.OFactory.SchokoFactory.game.patterns;

import de.OFactory.SchokoFactory.game.Map;
import de.OFactory.SchokoFactory.game.Pattern;
import de.OFactory.SchokoFactory.game.PatternFrame;
import de.OFactory.SchokoFactory.game.PatternState;
import de.OFactory.SchokoFactory.game.Workable;
import de.OFactory.SchokoFactory.main.MainState;

public class Gießer extends Pattern implements Workable{
	
	private int produktion = 200;
	private int einmaligeFabrikkosten = 4000;
	private int laufendeFabrikkosten = 50;
	
	public Gießer(Map map, int x, int y, int id, int xcoor, int ycoor) {
		super(map, x, y, PatternState.GIEßER, id, xcoor, ycoor);
		
		this.setDelay(6);
		this.setFrameLoop(PatternFrame.GIEßER_S, PatternFrame.GIEßER_E);
		
		// Kosten:
		MainState.p.addMoney(-2000);
		MainState.p.addAusgaben(2000);
	}

	@Override
	public void updateContext() {
		
	}
	
	// Produziert 100 Tafeln aus dem nichts bei jedem Aufruf. Idealer Weise t�glich.
	public void work() {
		MainState.p.addProduktmenge(produktion);
		MainState.p.addMoney(-laufendeFabrikkosten);
		MainState.p.addAusgaben(laufendeFabrikkosten);
	}

	@Override
	public void updatePatternInfo() {
		// XXX Mögliche Attribute: Effizienz, Gego�enes Produkt, Ger�tealter, laufende Kosten
		
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
}
