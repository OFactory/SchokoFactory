package de.OFactory.SchokoFactory.simulation;

import de.OFactory.SchokoFactory.main.Daily;
import de.OFactory.SchokoFactory.main.MainState;

public class SimpleAI extends AI implements Daily{
	

	private static final long serialVersionUID = 2789964915069056347L;
	
	private int fabriken = 2;
	private int produktion = 100;
	private int einmaligeFabrikkosten = 200;
	private int laufendeFabrikkosten = 50;
	private int lastProduktmenge;
	private int diff;
	
	public SimpleAI(Market market, String name, double money) {
		super(market, name, money);
		MainState.dailys.add(this);
	}
	
	public void day() {
		runFactories();
		think();
	}
	
	public void think() {

		// mehr Fabriken benötigt?
		
		diff = this.stock.getTafeln() - lastProduktmenge;
		if (this.getMoegAbs() > this.getAbsatz()*(1+0.5/(float)this.getFabriken())) {		// Der Faktor 1+1/(float)this.getFabriken())
			if ( this.getMoney() >= einmaligeFabrikkosten ) {							// sorgt für eine Berücksichtigung des Produktonsanstiegs.
				buildFactory();															// (fabriken+1) / fabriken ist 1+1/fabriken
			}																			// Bei 4 Fabriken: 1.25 (25% Produktionsanstieg nach Kauf)
		}
		if (this.stock.getTafeln() > 0) {
			if ( this.getMoney() >= this.stock.getTafeln()/10 ) {
				investQuality(this.stock.getTafeln()/10);
			}	

		}
	}

	
	private void buildFactory() {
		this.addMoney(-einmaligeFabrikkosten);
		this.addAusgaben(einmaligeFabrikkosten);
		fabriken += 1;
		System.out.println("SimpleAI: build a factory | #"+fabriken);
	}
	
	public void runFactories() {
		produce();
	}
	
	public void produce() {
		lastProduktmenge = this.stock.getTafeln();
		this.stock.addTafeln(fabriken * produktion);
		this.addMoney(fabriken * -laufendeFabrikkosten);
		this.addAusgaben(fabriken * laufendeFabrikkosten);
	}

	
	
	
	public int getFabriken() {
		return fabriken;
	}
	public void setFabriken(int fabriken) {
		this.fabriken = fabriken;
	}
	public int getProduktion() {
		return produktion;
	}
	public void setProduktion(int produktion) {
		this.produktion = produktion;
	}

	public int getLaufendeFabrikkosten() {
		return laufendeFabrikkosten;
	}

	public void setLaufendeFabrikkosten(int laufendeFabrikkosten) {
		this.laufendeFabrikkosten = laufendeFabrikkosten;
	}
	
	public int getDiff() {
		return diff;
	}

	public void setDiff(int diff) {
		this.diff = diff;
	}
}
