package de.OFactory.SchokoFactory.simulation;

public class SimpleAI extends Player {
	
	private int fabriken = 4;
	private int produktion = 100;
	private int einmaligeFabrikkosten = 2000;
	private int laufendeFabrikkosten = 30;
	
	public SimpleAI(Market market, String name, double money) {
		super(market, name, money);
	}
	
	public void think() {
		//System.out.println(name+" hmmmm");
		// mehr Fabriken benötigt?
		if (this.getMoegAbs() > this.getProduktmenge()) {
			if ( this.getMoney() >= einmaligeFabrikkosten ) {
				buildFactory();
			}	else System.out.println("Fabrik zu teuer");
		}
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
