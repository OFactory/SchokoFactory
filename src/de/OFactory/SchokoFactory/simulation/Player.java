package de.OFactory.SchokoFactory.simulation;

public class Player {
	
	Market market;
	String name;
	
	private boolean liquide = true;
	private double zinsen = 1;
	private double money;
	private double absatz;
	private double preis = 1;
	private double umsatz;
	
	private double marktanteil;
	private int produktmenge;
	
	private int fabriken;
	
	private double werbefaktor = 1;
	private double qualitaet = 1;
	private double bekanntheit = 1;

	
	private int zuschuss;
	
	private double altqualitaet;
	private double altwerbefaktor;
	private int moegAbs;
	
	public Player(Market market, String name, double money) {
		
		this.market = market;
		this.name = name;
		this.money = money;
		
		
	}
	/**First part of the calculation. Gets inputs and works out possible sales(ger.: Absatz). Returns possible sales to Game.**/
    public int getMoegAbs() {
        
        //get();


        bekanntheit *= Math.pow(werbefaktor,0.9) * qualitaet / altqualitaet;
        moegAbs = (int)(this.bekanntheit * this.market.getBedarf() * this.market.getBoni() / altwerbefaktor * werbefaktor / Math.pow(preis,1.2));
        return moegAbs;
    }
    
    public void finalCalculation() {
    	
    	umsatz = absatz * preis;
    	money += umsatz;
        produktmenge -= absatz;
        marktanteil = absatz/this.market.summeAbs;

        if (money < 0 && liquide) {
            zinsen = -money / Math.pow(umsatz,2) * this.market.summeUms * 0.0167 * 5;
            if (zinsen < 0.03) 
                zinsen = 0.03;
            money += zinsen*money;
            if (zinsen > 0.20) 
                liquide = false;
            	System.out.println();
        } else
            zinsen = 0;
    	
    }
    
	/**Get inputs from GUI(in Game.py).**/
    @SuppressWarnings("unused") //PLS USE
	private void get() {
    	
        altqualitaet = qualitaet;
        altwerbefaktor = werbefaktor;
        qualitaet += 0;	//Änderung
        money -= 0; //Änderung
        werbefaktor = 1 + 0;//Änderung
        money -= 0;//Änderung
        preis = 0;//Änderung
 
    }
    
	double getMoney() {
		return this.money;
	}
	void setMoney(int money) {
		this.money = money;
	}
	String getName() {
		return this.name;
	}
	void setName(String name) {
		this.name = name;
	}
	
	
	public boolean isLiquide() {
		return liquide;
	}
	public void setLiquide(boolean liquide) {
		this.liquide = liquide;
	}
	public double getZinsen() {
		return zinsen;
	}
	public void setZinsen(double zinsen) {
		this.zinsen = zinsen;
	}
	public double getAbsatz() {
		return absatz;
	}
	public void setAbsatz(double absatz) {
		this.absatz = absatz;
	}
	public double getUmsatz() {
		return umsatz;
	}
	public void setUmsatz(double umsatz) {
		this.umsatz = umsatz;
	}
	public double getMarktanteil() {
		return marktanteil;
	}
	public void setMarktanteil(double marktanteil) {
		this.marktanteil = marktanteil;
	}
	public Market getMarket() {
		return market;
	}
	public void setMarket(Market market) {
		this.market = market;
	}
	public double getPreis() {
		return preis;
	}
	public void setPreis(double preis) {
		this.preis = preis;
	}
	public int getProduktmenge() {
		return produktmenge;
	}
	public void setProduktmenge(int produktmenge) {
		this.produktmenge = produktmenge;
	}
	public int getFabriken() {
		return fabriken;
	}
	public void setFabriken(int fabriken) {
		this.fabriken = fabriken;
	}
	public double getWerbefaktor() {
		return werbefaktor;
	}
	public void setWerbefaktor(double werbefaktor) {
		this.werbefaktor = werbefaktor;
	}
	public double getQualitaet() {
		return qualitaet;
	}
	public void setQualitaet(double qualitaet) {
		this.qualitaet = qualitaet;
	}
	public void setMoegAbs(int moegAbs) {
		this.moegAbs = moegAbs;
	}
	public double getBekanntheit() {
		return bekanntheit;
	}
	public void setBekanntheit(double bekanntheit) {
		this.bekanntheit = bekanntheit;
	}
	public int getZuschuss() {
		return zuschuss;
	}
	public void setZuschuss(int zuschuss) {
		this.zuschuss = zuschuss;
	}
	public double getAltqualitaet() {
		return altqualitaet;
	}
	public void setAltqualitaet(double altqualitaet) {
		this.altqualitaet = altqualitaet;
	}
	public double getAltwerbefaktor() {
		return altwerbefaktor;
	}
	public void setAltwerbefaktor(double altwerbefaktor) {
		this.altwerbefaktor = altwerbefaktor;
	}
	public void setMoney(double money) {
		this.money = money;
	}

}
