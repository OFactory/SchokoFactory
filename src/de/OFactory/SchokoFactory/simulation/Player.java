package de.OFactory.SchokoFactory.simulation;

public class Player {
	
	Market market;
	String name;
	
	private boolean liquide = true;
	private double zinsen = 1;
	private double money;
	private int absatz = 1;
	private double preis = 1;
	private double umsatz;
	
	private double marktanteil;
	private int produktmenge = 15000;
	
	
	private double werbefaktor = 1;
	private double qualitaet = 1;
	private double bekanntheit = 1;

	
	private int zuschuss;
	
	private double altqualitaet = 1;
	private double altwerbefaktor = 1;
	private int moegAbs;
	
	private int rest;
	private int diff_bedarf;
	
	public Player(Market market, String name, double money) {
		
		this.market = market;
		this.name = name;
		this.money = money;
		
		
	}
	/**First part of the calculation. Gets inputs and works out possible sales(ger.: Absatz). Returns possible sales to Game.**/
    public void calculateMoegAbs() {
        
        //get();
        bekanntheit *= Math.pow(werbefaktor,0.9) * qualitaet / altqualitaet;
        moegAbs = (int)(this.bekanntheit * this.market.getBedarf() * this.market.getBoni() / altwerbefaktor * werbefaktor / Math.pow(preis,1.2));

    }
    
    public void calculateDiff() {
    	double moegMarktanteil = 0;
    	if (market.getSummeAbs() != 0) 
    		moegMarktanteil = moegAbs/market.getSummeAbs();

    	moegAbs = (int)(moegMarktanteil * market.getSummeAbs());
    	System.out.println(moegAbs+","+produktmenge);
    	if (moegAbs > produktmenge) {
    		absatz = produktmenge;
    		System.out.println("___zu wenige Produkte | Absatz = "+absatz);
    		System.out.println("____________________  | Produk = "+produktmenge);
    		setRest(0);
    		setDiff_bedarf(moegAbs - produktmenge);	
    	} else {
    		absatz = moegAbs;
    		setRest(produktmenge - moegAbs);
    		setDiff_bedarf(0);
    	}
    }

    public void finalCalculation() {
    	
    	umsatz = absatz * preis;
    	money += umsatz;
    	//System.out.print(produktmenge+", ");
    	//if (produktmenge != absatz)
    	produktmenge -= absatz/2;
        //System.out.println(produktmenge+", "+absatz);
        if (market.getSummeAbs() != 0) {
        	marktanteil = (float)Math.round((float)absatz/market.getSummeAbs() *100d) /100d;  // runde auf 2 Nachkommastellen
        } else {
        	marktanteil = 0;
        }

        if (money < 0 && liquide) {
            zinsen = -money / Math.pow(umsatz,2) * market.getSummeUms() * 0.0167 / 5;
            if (zinsen < 0.03) 
                zinsen = 0.03;
            money += zinsen*money;
            if (zinsen > 0.20) 
                liquide = false;

        } else
            zinsen = 0;
    	
    }

	/**Get inputs from GUI(in Market.java).**/
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
    
    
    
    // Setter und Getter ( und Adder)
    
	public double getMoney() {
		return this.money;
	}
	public void setMoney(double money) {

		this.money = money;
	}
	public void addMoney(double delta) {
		
		this.money += delta;
	}
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
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
	public int getAbsatz() {
		return absatz;
	}
	public void setAbsatz(int absatz) {
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
	public void addProduktmenge(int delta) {
		
		this.produktmenge += delta;
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
	public int getRest() {
		return rest;
	}
	public void setRest(int rest) {
		this.rest = rest;
	}
	public int getDiff_bedarf() {
		return diff_bedarf;
	}
	public void setDiff_bedarf(int diff_bedarf) {
		this.diff_bedarf = diff_bedarf;
	}
	public int getMoegAbs() {
		return moegAbs;
	}

}
