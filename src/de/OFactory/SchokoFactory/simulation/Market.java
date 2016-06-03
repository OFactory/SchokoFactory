package de.OFactory.SchokoFactory.simulation;

import java.util.List;
import java.lang.Math;



public class Market {
	
	private long time = 725759; // 1. Januar 2016
	private List<Player> players;
	
	private int bedarf;
	private double eco = 1;
	private double boni;
	private int summeMoegAbs;
	int summeAbs;
	@SuppressWarnings("unused")
	double summeUms;
	@SuppressWarnings("unused") //PLS USE
	private double zuwachs;
	private int summeAbsAlt = 1;
	
	public Market() {
		
		System.out.println("<Markt> Hello, I'm the market! How are you?");
		
	}
	
	/** processed every day **/
	public void day() {
		
		calculation();
		
		this.time++;
		System.out.println("<Markt> [ " + getDateString() + " ]" );
		
	}
	
	/** Calculate economic development of market and players **/
	private void calculation() {

		bedarf = (int)(summeAbs * Math.pow(this.eco , 2) * boni * (Math.pow(getWerbefaktoren(),0.9) + 10)/11);
		
		summeMoegAbs = getMoegAbs();
		summeAbs = (summeMoegAbs+bedarf)/2;
		
		//calculateShift(); wäre mal guut... (!)
		
		summeAbs = 0;
		summeUms = 0;
		for (Player p:players) {
			summeAbs += p.getAbsatz();
			summeUms += p.getAbsatz() * p.getPreis();
		}

		if(summeAbsAlt != 0)		// Division by zero abhalten
			zuwachs = summeAbs/summeAbsAlt;
		summeAbsAlt = summeAbs;
		
	}
	
	
	
	
	

	/**
	 * Produkt der Werbefaktoren aller Spieler
	 * 
	 * @return int Produkt der Werbefaktoren
	 */
	private double getWerbefaktoren() {
		double werbefaktoren = 1;
		for (Player p:players) {
			werbefaktoren *= p.getWerbefaktor();
		}
		return werbefaktoren;
	}
	
	/** 
	 * Summer der Möglichen Absätze aller Spieler
	 * 
	 * @return int Summer der Möglichen Absätze
	 */
	private int getMoegAbs() {
		int summe = 0;
		for (Player p:players) {
			summe += p.getMoegAbs();
		}
		
		return summe;
	}
	
	/** 
	 * Summe der Absaetze aller Spieler
	 * 
	 * @return int Summe
	 */
	@SuppressWarnings("unused")
	private int getAbs() {
		int summe = 0;
		for (Player p:players) {
			summe += p.getAbsatz();
		}
		
		return summe;
	}
	
	/** 
	 * Summe der Umsätze aller Spieler
	 * 
	 * @return int Summe Umsätze
	 */
	@SuppressWarnings("unused")
	private int getUms() {
		int summe = 0;
		for (Player p:players) {
			summe += p.getUmsatz();
		}
		
		return summe;
	}
	
	public void addPlayer(Player p){
		this.players.add(p);
	}
	
	public void setPlayer(List<Player> players) {
		this.players = players;
	}
	
	public void removePlayer(Player p){
		this.players.remove(p);
	}

	public List<Player> getPlayers() {
		return this.players;
	}
	
	/**
	 * Gibt den Datumsstring der aktullen Zeit time im Format dd.mm.yyyy zurück.
	 * Hierbei werden führende Nullen verwendet. z.B. 01.05.2016
	 * 
	 * @return String, das Datum in Stringform
	 */
	public String getDateString() {
		long year = time/360;
		long month = (time - year*360)/30;
		long day = time- year*360 - month*30 ;
		
		//return (year)+":"+(month+1)+":"+(day+1);	// ++ Denn es gibt keinen 0. Monat/Tag
		return String.format("%02d", day+1) + "." + String.format("%02d", month+1) + "." + String.format("%04d", year);
		// Führende Nullen: dd.mm.yyyy
		// z.B. 01.05.0020
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public int getBedarf() {
		return bedarf;
	}

	public void setBedarf(int bedarf) {
		this.bedarf = bedarf;
	}

	public double getEco() {
		return eco;
	}

	public void setEco(double eco) {
		this.eco = eco;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public double getBoni() {
		return boni;
	}

	public void setBoni(double boni) {
		this.boni = boni;
	}

	public int getSummeAbs() {
		return summeAbs;
	}

	public void setSummeAbs(int summeAbs) {
		this.summeAbs = summeAbs;
	}
	
}

