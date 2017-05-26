package de.OFactory.SchokoFactory.simulation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import de.OFactory.SchokoFactory.main.Daily;
import de.OFactory.SchokoFactory.main.MainState;



public class Market implements Daily, Serializable{
	

	private static final long serialVersionUID = -3165749406689670676L;
	
	private int startingYear = 2016;
	private long time = 0; // 1. Januar 2016
	private List<Player> players;
	
	private double bedarf = 450;
	private double eco = 1; //Math.pow(1.01, (double)1/360);
	
	private double boni = 1;
	private int summeMoegAbs = 0;
	private int summeAbs;
	private double summeUms;

	private double zuwachs;
	private int summeAbsAlt = 450;
	
	private ArrayList<Player> p_mit_diff = new ArrayList<Player>();
	private ArrayList<Player> p_ohne_diff = new ArrayList<Player>();
	
	//private ArrayList<Integer>[] listeAbsatz;
	
	
	
	public Market() {
		MainState.dailys.add(this);
	}
	
	/** processed every day **/
	public void day() {

		calculation();
		saveData();
		this.time++;
		
		//printStuff();

		
		// Ausgaben zurücksetzen für den nächsten Tag
		for (Player p: players) {
			p.setAusgaben(0);
		}
	}
	
	private void saveData() {
		//int[] absaetze = {MainState.m.getSummeAbs(), MainState.p.getAbsatz(), MainState.ai1.getAbsatz(), MainState.ai2.getAbsatz(), MainState.p.getMoegAbs(), MainState.ai1.getMoegAbs(), MainState.ai2.getMoegAbs()};
		//listeAbsatz.append(absaetze);
		
	}

	@SuppressWarnings("unused")
	private void printStuff() {
		System.out.println("<Markt> [ " + getDateString() + " ]" );
		System.out.println("<Markt> Tagesbilanz");	// zum leichteren Debuggen der Markt- und Produktionssimlation
		//System.out.println(" | Bedarf: " + this.bedarf);
		for (int i = 0; i < players.size(); i++) {
			System.out.println(" | P"+i+"  produktmenge: " + players.get(i).getProduktmenge());
		}
	}
	
	/** Calculate economic development of market and players **/
	private void calculation() {

		bedarf = (59*bedarf + summeAbsAlt) / 60 * Math.pow(this.eco , 2) * boni * (Math.pow(getWerbefaktoren(),0.9) + 330)/331;
		// Bedarf gleicht sich an den alten Absatz über 2 Monate (60 Tage) an, Gewöhnungseffekt
		bedarf = (float)Math.round((float)bedarf *100d) /100d;
		summeMoegAbs = calcMoegAbs();
		summeAbs = (int)(summeMoegAbs+bedarf)/2;
		calculateShift();
		
		summeAbs = 0;
		setSummeUms(0);
		for (Player p:players) {
			summeAbs += p.getAbsatz();
			setSummeUms(getSummeUms() + p.getAbsatz() * p.getPreis());
		}

		
		if(summeAbsAlt != 0)		// Division by zero auffangen
			zuwachs = (double)summeAbs/(double)summeAbsAlt;
		

			

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
//			System.out.println("shift it _ f: "+f+"\ntrans: "+trans);
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
				System.out.println("____-_"+p.getName()+": "+p.getAbsatz()+"______");
			}
		}
	}
	
	
	

	/**
	 * Produkt der Werbefaktoren aller Spieler
	 * 
	 * @return double Produkt der Werbefaktoren
	 */
	private double getWerbefaktoren() {
		double werbefaktoren = 1;
		for (Player p:players) 
			werbefaktoren *= p.getWerbefaktor();

		return werbefaktoren;
	}
	
	/** 
	 * Summe der Möglichen Absätze aller Spieler
	 * 
	 * @return int Summer der Möglichen Absätze
	 */
	public int calcMoegAbs() {
		int summe = 0;
		for (Player p: players) {
			p.calculateMoegAbs();
			summe += p.getMoegAbs();
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

		//System.out.println(a+", "+b+", "+c);
		
		if(c != 0){
			double f = a / b / c;
			return f;
		} else {
			double f = 1;
			return f;
		}
		
		/*
		try {
			double f = a / b / c;
			return f;
		}
		finally
		{
			double f = 1;
			return f;
		}
		*/
		
		
		
		// ich will eigentlich except(Python) benutzen aber das gibt es nicht :|
		// -> einfach if abfragen

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
		z = (float)Math.round((float)z *100d) /100d;
		s += z + "%";
		
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
	
	public int getSummeMoegAbs() {
		return this.summeMoegAbs;
	}
	
	public void setSummeMoegAbs(int value) {
		this.summeMoegAbs = value;
	}

	public double getBedarf() {
		return bedarf;
	}

	public void setBedarf(double bedarf) {
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

