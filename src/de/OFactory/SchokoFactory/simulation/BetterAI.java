package de.OFactory.SchokoFactory.simulation;

public class BetterAI extends AI {
	
	private int fabriken = 2;
	private int produktion = 100;
	private int einmaligeFabrikkosten = 200;
	private int laufendeFabrikkosten = 0;
	private int lastProduktmenge;
	private int diff;
	
	public BetterAI(Market market, String name, double money) {
		super(market, name, money);

	}
	
	public void think() {
		//System.out.println(name+" hmmmm");
		// mehr Fabriken benötigt?
		//System.out.println((1+0.94/(float)this.getFabriken())+" : "+this.getFabriken());
		
		
		// Ausgleichsmechaniken
		diff = this.getProduktmenge() - lastProduktmenge;
		if (this.getMoegAbs() > this.getAbsatz()*(1+0.5/(float)this.getFabriken())) {		// Der Faktor 1+1/(float)this.getFabriken())
			if ( this.getMoney() >= einmaligeFabrikkosten ) {							// sorgt für eine Berücksichtigung des Produktonsanstiegs.
				buildFactory();															// (fabriken+1) / fabriken ist 1+1/fabriken
			}
		}
		if (this.getProduktmenge() > lastProduktmenge) {
			//noetige Investition 'invest'
			int invest = this.getProduktmenge()/10;
			if ( this.getMoney()-1000 >= invest ) {
				System.out.println("genug Geld");
				investQuality(this.getProduktmenge()/10);
			}
			else if (this.getMoney() > 1000)
				investQuality(this.getMoney()-1000);
			
			else{
				System.out.println("zu teuer");
				this.setPreis(this.getPreis()-0.01);
			}
		} else {
			if ( this.getMoney() >= einmaligeFabrikkosten ) {							
				
				for (int i=0; i < (diff+produktion)/produktion;i++) 
					buildFactory();		
			}	else {																	
				System.out.println("Fabrik zu teuer -> Preis erhöhen");
				this.setPreis(this.getPreis()+0.01);
			}
		}
		
		// gönnung
		if (this.getMoney() > 1000) {
			if (diff > 0)
				investQuality(this.getMoney()/8);
			if (diff < 0) 
				for (int i=0; i < this.getMoney()*0.3/200;i++) 
					buildFactory();	
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
		lastProduktmenge = this.getProduktmenge();
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

	public int getDiff() {
		return diff;
	}

	public void setDiff(int diff) {
		this.diff = diff;
	}
}
