package de.OFactory.SchokoFactory.simulation;

import java.util.ArrayList;
import java.util.List;



public class Market {
	
	private int startingYear = 2016;
	private long time = 0; // 1. Januar 2016
	private List<Player> players;
	
	private int bedarf = 2000;
	private double eco = 1;
	private double boni = 1;
	private int summeMoegAbs = 1000;
	private int summeAbs;
	private double summeUms;

	private double zuwachs;
	private int summeAbsAlt = 1800;
	
	private ArrayList<Player> p_mit_diff = new ArrayList<Player>();
	private ArrayList<Player> p_ohne_diff = new ArrayList<Player>();
	
	
	
	public Market() {
		

		
	}
	
	/** processed every day **/
	public void day() {
		
		calculation();
		
		this.time++;
		System.out.println("<Markt> [ " + getDateString() + " ]" );
		
	}
	
	/** Calculate economic development of market and players **/
	private void calculation() {

		bedarf = (int)(summeAbsAlt * Math.pow(this.eco , 2) * boni * (Math.pow(getWerbefaktoren(),0.9) + 10)/11);
		
		summeMoegAbs = getMoegAbs();
		summeAbs = (summeMoegAbs+bedarf)/2;
		
		calculateShift();
		
		summeAbs = 0;
		setSummeUms(0);
		for (Player p:players) {
			summeAbs += p.getAbsatz();
			setSummeUms(getSummeUms() + p.getAbsatz() * p.getPreis());
		}

		if(summeAbsAlt != 0) {		// Division by zero auffangen
			zuwachs = summeAbs/summeAbsAlt;
			System.out.println("Absatzverlauf: "+summeAbsAlt+"/"+summeAbs);
			}

			

		summeAbsAlt = summeAbs;
		
		for (Player p : players) 
			p.finalCalculation();
	}
	
	/**Calculates the shift of requirements between the players/clients.**/
	private void calculateShift() {
		
		for (Player p: players) {
			p.calculateDiff();
			if (p.getDiff_bedarf() >= 0)	// einsortieren
				p_mit_diff.add(p);
			else
				p_ohne_diff.add(p);
			
		}
		
		int diff_ges = getDiff_ges();
		int rest_ges = getRest_ges();
		int trans;
		if (diff_ges < rest_ges)	// was ist kleiner | es kann nur so viel Bedarf umverteilt werden, wie andere Unternehmen im Lager übrig haben.
			trans = diff_ges;
		else
			trans = rest_ges;
		
		if (trans != 0) {			// kommt es zu Verschiebungen?
			
			double f = getF();
			trans *= f;
			
			int divisor = 0;
			for (Player p: p_ohne_diff)
				divisor += p.getMoegAbs();
			for (Player p: p_ohne_diff) {
				double q = p.getMoegAbs() / divisor;
				p.setZuschuss((int)(trans * q));
				if (p.getZuschuss() > p.getRest())
					p.setZuschuss(p.getRest());
				
				p.setRest( p.getRest()-p.getZuschuss() );
				p.setAbsatz( p.getAbsatz()+p.getZuschuss() );
			}
		}
	}
	
	
	

	/**
	 * Produkt der Werbefaktoren aller Spieler
	 * 
	 * @return int Produkt der Werbefaktoren
	 */
	private double getWerbefaktoren() {
		double werbefaktoren = 1;
		for (Player p:players) 
			werbefaktoren *= p.getWerbefaktor();

		return werbefaktoren;
	}
	
	/** 
	 * Summer der Möglichen Absätze aller Spieler
	 * 
	 * @return int Summer der Möglichen Absätze
	 */
	private int getMoegAbs() {
		int summe = 0;
		for (Player p: players) {
			p.calculateMoegAbs();
			p.getMoegAbs();
		}

		
		return summe;
	}
	
	private int getDiff_ges() {
		int summe = 0;
		for (Player p: players) // hier darf players benutzt werden, da alle Player ohne diff diff_bedarf = 0 haben.
			summe += p.getDiff_bedarf();

		return summe;		
	}
	
	private int getRest_ges() {
		int summe = 0;
		for (Player p: players)  // hier darf players benutzt werden, da alle Player mit diff rest = 0 haben.
			summe += p.getRest();

		return summe;		
	}
	
	private double getF() {
		
		int a = 0;
		for (Player p: p_mit_diff)
			a += p.getMoegAbs()*p.getDiff_bedarf();

		int b = 0;
		for (Player p: p_mit_diff)
			b += p.getDiff_bedarf();

		int c = 0;
		for (Player p: p_ohne_diff)
			c += p.getMoegAbs();

		
		double f = a / b / c;
		
		return f;
	}
	
	/** 
	 * Summe der Absaetze aller Spieler
	 * 
	 * @return int Summe
	 */
	@SuppressWarnings("unused")
	private int getAbs() {
		int summe = 0;
		for (Player p:players)
			summe += p.getAbsatz();

		
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
		long year = getYear();
		long month = getMonth();
		long day = getDay();
		
		//return (year)+":"+(month+1)+":"+(day+1);	// ++ Denn es gibt keinen 0. Monat/Tag
		return String.format("%02d", day+1) + "." + String.format("%02d", month+1) + "." + String.format("%04d", year+startingYear);
		// Führende Nullen: dd.mm.yyyy
		// z.B. 01.05.0020
	}
	
	public String getZuwachsString() {
		String s = "";
		double z = getZuwachs();
		if (z == 0 || z==1)
			return "±0%";
		else if (z > 1)
			s += "+";		// - Zeichen wird automatisch gesetzt
		s += (z-1)*100 + "%";
		
		return s;
	}
	
	public long getYear() {
		long year = time/360;
		return year;
	}
	
	public long getMonth() {
		long month = (time - getYear()*360)/30;
		return month;
	}
	
	public long getDay() {
		long day = time- getYear()*360 - getMonth()*30 ;
		return day;
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

	public double getSummeUms() {
		return summeUms;
	}

	public void setSummeUms(double summeUms) {
		this.summeUms = summeUms;
	}

	public int getStartingYear() {
		return startingYear;
	}

	public void setStartingYear(int startingYear) {
		this.startingYear = startingYear;
	}

	public double getZuwachs() {
		return zuwachs;
	}

	public void setZuwachs(double zuwachs) {
		this.zuwachs = zuwachs;
	}
	
}

