package de.OFactory.SchokoFactory.simulation;

public class SimpleAI extends Player {
	
	private int fabriken = 4;
	private int produktion = 200;
	private int einmaligeFabrikkosten = 4000;
	private int laufendeFabrikkosten = 50;
	
	public SimpleAI(Market market, String name, double money) {
		super(market, name, money);
	}
	
	public void think() {
		//System.out.println(name+" hmmmm");
		// mehr Fabriken benötigt?
		
		if (this.getMoegAbs() > this.getAbsatz()*(1+0.5/(float)this.getFabriken())) {		// Der Faktor 1+1/(float)this.getFabriken())
			if ( this.getMoney() >= einmaligeFabrikkosten ) {							// sorgt für eine Berücksichtigung des Produktonsanstiegs.
				buildFactory();															// (fabriken+1) / fabriken ist 1+1/fabriken
			}	else System.out.println("Fabrik zu teuer");								// Bei 4 Fabriken: 1.25 (25% Produktionsanstieg nach Kauf)
		}
		if (this.getProduktmenge() > 0) {
			if ( this.getMoney() >= this.getProduktmenge()/10 ) {
				System.out.println("genug Geld");
				investQuality(this.getProduktmenge()/10);
			}	
			else System.out.println("zu teuer");
		}
	}
	private void investQuality(double amount) {
		this.addMoney(-amount);
		this.setQualitaet(this.getQualitaet() + amount / this.getQualitaet() / 2200);
	}
	
	private void buildFactory() {
		this.addMoney(-einmaligeFabrikkosten);
		this.addAusgaben(einmaligeFabrikkosten);
		fabriken += 1;
		System.out.println("build a factory | #"+fabriken);
	}
	
	public void runFactories() {
		//System.out.println(name+" working");
		produce();
	}
	
	public void produce() {
		this.addProduktmenge(fabriken * produktion);
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
}
