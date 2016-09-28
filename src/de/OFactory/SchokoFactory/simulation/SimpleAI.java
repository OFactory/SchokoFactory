package de.OFactory.SchokoFactory.simulation;

public class SimpleAI extends Player {
	
	private int fabriken = 3;
	private int produktion = 100;
	private int einmaligeFabrikkosten = 2000;
	
	public SimpleAI(Market market, String name, double money) {
		super(market, name, money);
	}
	
	public void think() {
		//System.out.println(name+" hmmmm");
		// mehr Fabriken benötigt?
		if (this.getMoegAbs() > this.getProduktmenge()) {
			buildFactory();
		}
	}
	
	private void buildFactory() {
		this.addMoney(-einmaligeFabrikkosten);
		fabriken += 1;
		System.out.println("build a factory | #"+fabriken);
	}
	
	public void runFactories() {
		//System.out.println(name+" working");
		produce();
	}
	
	public void produce() {
		this.addProduktmenge(fabriken * produktion);
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
}
