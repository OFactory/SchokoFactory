package de.OFactory.SchokoFactory.simulation;

public class AI extends Player {

	
	private int fabriken = 2;
	private int produktion = 100;
	private int einmaligeFabrikkosten = 200;
	private int laufendeFabrikkosten = 50;
	private int lastProduktmenge;
	private int diff;
	
	public AI(Market market, String name, double money) {
		super(market, name, money);
		
	}
	public void think() {
		
	}
	
	public void runFactories() {
		
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
	public int getLastProduktmenge() {
		return lastProduktmenge;
	}
	public void setLastProduktmenge(int lastProduktmenge) {
		this.lastProduktmenge = lastProduktmenge;
	}
	public int getDiff() {
		return diff;
	}
	public void setDiff(int diff) {
		this.diff = diff;
	}

}

