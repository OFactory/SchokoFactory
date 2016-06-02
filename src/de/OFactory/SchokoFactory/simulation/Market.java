package de.OFactory.SchokoFactory.simulation;

import java.util.List;
import java.lang.Math;



public class Market {
	
	private int time;
	private List<Player> players;
	
	private int bedarf;
	private double eco = 1;
	private double boni;
	private int summeMoegAbs;
	private int summeAbs;
	private double summeUms;
	private double zuwachs;
	private int summeAbsAlt;
	
	public Market() {
		
		System.out.println("Hello, I'm the market! How are you?");
		
	}
	
	/** processed every day **/
	public void day() {
		
		calculation();
		
		this.time++;
		System.out.println(getTimeString());
		
	}
	
	/** Calculate economic development of market and players **/
	private void calculation() {
		
		double werbefaktoren = getWerbefaktoren();
		bedarf *= Math.pow(this.eco , 2) * boni * (Math.pow(werbefaktoren,0.9) + 10)/11;
		
		summeMoegAbs = getMoegAbs();
		summeAbs = (summeMoegAbs+bedarf)/2;
		
		//calculateShift(); wäre mal guut... (!)
		
		summeAbs = 0;
		summeUms = 0;
		for (Player p:players) {
			summeAbs += p.getAbsatz();
			summeUms += p.getAbsatz() * p.getPreis();
		}

		zuwachs = summeAbs/summeAbsAlt;
		summeAbsAlt = summeAbs;
		
	}
	
	
	
	
	
	
	
	/** Produkt der Werbefaktoren**/
	private double getWerbefaktoren() {
		double werbefaktoren = 1;
		for (Player p:players) {
			werbefaktoren *= p.getWerbefaktor();
		}
		return werbefaktoren;
	}
	
	/** Summe der moeglichen Absaetze**/
	private int getMoegAbs() {
		int summe = 0;
		for (Player p:players) {
			summe += p.getMoegAbs();
		}
		return summe;
	}
	/** Summe der Absaetze**/
	private int getAbs() {
		int summe = 0;
		for (Player p:players) {
			summe += p.getAbsatz();
		}
		return summe;
	}
	/** Summe der Umsaetze**/
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
	
	public String getTimeString() {
		int year = time/360;
		int month = (time - year*360)/30;
		int day = time- year*360 - month*30 ;
		
		return (year)+":"+(month+1)+":"+(day+1);	// ++ Denn es gibt keinen 0. Monat/Tag
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
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

