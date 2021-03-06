package de.OFactory.SchokoFactory.simulation;

import java.io.Serializable;

import de.OFactory.SchokoFactory.main.MainState;

public class Player implements Serializable{

	private static final long serialVersionUID = -2354318203231470496L;
	
	Market market;
	public Stock stock;
	String name;
	
	private boolean liquide = true;
	private double zinsen = 1;
	private double money;
	private int absatz = 1;
	private double preis = 1;
	private double umsatz;
	
	private double marktanteil = (double)1/3;
	
	
	private double werbefaktor = 1;
	private double qualitaet = 1;
	private double bekanntheit = 1;

	
	private int zuschuss;
	
	private double altqualitaet = 1;
	private double altwerbefaktor = 1;
	private int moegAbs;
	
	private int rest;
	private int diff_bedarf;
	
	private double ausgaben;
	
	private double[] preisliste = new double[] {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
	private double[] qualiliste = new double[] {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
	private double[] werbeliste = new double[] {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
	
	public Player(Market market, String name, double money) {

		this.market = market;
		this.name = name;
		this.money = money;
		
		this.stock = new Stock();
		this.stock.addFlüssigeSchokolade(321.0);
		this.stock.addTafeln(1000);

		
	}
	/**First part of the calculation. Gets inputs and works out possible sales(ger.: Absatz). Returns possible sales to Game.**/
	public void calculateMoegAbs() {
    	
    	// 15 Tage Verschiebung
    	int item = (int)MainState.m.getTime() % 15;
    	
    	werbeliste[item] = this.werbefaktor;
    	double werbefaktor = 0;		// Variable werbefaktor wird hier noch als Summe aus der Liste verwendet.
        for (double p:this.werbeliste)
        	werbefaktor += p;
        werbefaktor /= this.werbeliste.length;
        
    	qualiliste[item] = this.qualitaet;
    	double qualitaet = 0;		// Variable qualitaet wird hier noch als Summe aus der Liste verwendet.
        for (double p:this.qualiliste)
        	qualitaet += p;
        qualitaet /= this.qualiliste.length;
        
    	preisliste[item] = this.preis;
    	double preis = 0;		// Variable preis wird hier noch als Summe aus der Liste verwendet.
        for (double p:this.preisliste)
        	preis += p;
        preis /= this.preisliste.length;


        bekanntheit += werbefaktor-1;
        moegAbs = (int)((0.01*bekanntheit+0.99) * (double)MainState.m.getBedarf()* (marktanteil+0.005) * MainState.m.getBoni() * Math.pow(werbefaktor / altwerbefaktor, 4)  / preis  * (qualitaet / altqualitaet));
        
        altqualitaet = qualitaet;
        altwerbefaktor = werbefaktor;
    }
    
    public void calculateDiff() {
    	
    	if (MainState.m.getSummeAbs() != 0) {
    		
    		double moegMarktanteil = (double)moegAbs/MainState.m.getSummeMoegAbs();

	    	moegAbs = (int)(moegMarktanteil * MainState.m.getSummeAbs());

    	} else {

    		moegAbs = 0;
    	}
    	
    	int tafeln = this.stock.getTafeln();
    	
    	if (moegAbs > tafeln) {
    		absatz = this.stock.getTafeln();
    		setRest(0);
    		setDiff_bedarf(moegAbs - tafeln);	
    	} else {
    		absatz = moegAbs;
    		setRest(tafeln - moegAbs);
    		setDiff_bedarf(0);
    	}
    }
    
	public void investQuality(double amount) {
		this.addMoney(-amount);
		this.setQualitaet(this.getQualitaet() + amount / 1 / 10000);
	}

    public void investAdverts(double amount) {
    	this.addMoney(-amount);
    	this.setWerbefaktor(1+amount/10000);
    }
	
    public void finalCalculation() {
    	
    	umsatz = absatz * preis;
    	money += umsatz;

    	this.stock.addTafeln(-absatz);
    	
        if (MainState.m.getSummeAbs() != 0) {
        	marktanteil = (float)Math.round((float)absatz/MainState.m.getSummeAbs() *100d) /100d;  // runde auf 2 Nachkommastellen
        } else {
        	marktanteil = 0;
        }

        //System.out.println("Zinsen: " + -money / Math.pow(umsatz,2) * MainState.m.getSummeUms() * 0.0167 / 5);
        
        if (money < 0 && liquide) {
            zinsen = -money / Math.pow(umsatz,2) * MainState.m.getSummeUms() * 0.0167 / 5;
            
            if (zinsen < 0.03) 
                zinsen = 0.03;
            money += zinsen*money;
            if (zinsen > 0.20) 
                liquide = false;
            	System.out.println(name + " bankrott ______________________________________________________________");

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
	public double getAusgaben() {
		return ausgaben;
	}
	public void setAusgaben(double ausgaben) {
		this.ausgaben = ausgaben;
	}
	public void addAusgaben(double delta) {
		this.ausgaben += delta;
	}
	public double[] getPreisliste() {
		return preisliste;
	}
	public void setPreisliste(double[] preisliste) {
		this.preisliste = preisliste;
	}
	public double[] getQualiliste() {
		return qualiliste;
	}
	public void setQualiliste(double[] qualiliste) {
		this.qualiliste = qualiliste;
	}

}
