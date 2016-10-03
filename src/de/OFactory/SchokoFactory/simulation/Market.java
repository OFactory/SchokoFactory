package de.OFactory.SchokoFactory.simulation;

import java.util.ArrayList;
import java.util.List;



public class Market {
	
	private int startingYear = 2016;
	private long time = 0; // 1. Januar 2016
	private List<Player> players;
	
	private int bedarf = 2000;
	private double eco = Math.pow(3.7, (double)1/360);
	
	private double boni = 1;
	private int summeMoegAbs = 0;
	private int summeAbs;
	private double summeUms;

	private double zuwachs;
	private int summeAbsAlt = 2000;
	
	private ArrayList<Player> p_mit_diff = new ArrayList<Player>();
	private ArrayList<Player> p_ohne_diff = new ArrayList<Player>();
	
	
	
	public Market() {
		System.out.println("eco "+eco);
	}
	
	/** processed every day **/
	public void day() {
		
		calculation();
		
		this.time++;
		System.out.println("<Markt> [ " + getDateString() + " ]" );
		System.out.println("<Markt> Tagesbilanz");	// zum leichteren Debuggen der Markt- und Produktionssimlation
		//System.out.println(" | Bedarf: " + this.bedarf);
		/**System.out.println(" | SummMoegAbsatz: " + this.getMoegAbs());
		for (int i = 0; i < players.size(); i++) {
			System.out.println(" | P"+i+"  Money: " + players.get(i).getMoney());
			System.out.println(" | P"+i+"  Umsatz: " + players.get(i).getUmsatz());
			System.out.println(" | P"+i+"  Ausgaben: " + players.get(i).getAusgaben());
			//System.out.println(" _ P"+i+"  Anteil: " + players.get(i).getMarktanteil());
		}**/
		
		// Ausgaben zur�cksetzen f�r den n�chsten Tag
		for (Player p: players) {
			p.setAusgaben(0);
		}
	}
	
	/** Calculate economic development of market and players **/
	private void calculation() {

		bedarf = (int)((59*bedarf + summeAbsAlt) / 60 * Math.pow(this.eco , 2) * boni * (Math.pow(getWerbefaktoren(),0.9) + 330)/331);
		// Bedarf gleicht sich an den alten Absatz �ber 2 Monate (60 Tage) an, Gew�hnungseffekt
		
		summeMoegAbs = getMoegAbs();
		summeAbs = (summeMoegAbs+bedarf)/2;
		System.out.println("("+ summeMoegAbs+"+"+bedarf+")/2 = " + summeAbs);
		calculateShift();
		
		summeAbs = 0;
		setSummeUms(0);
		for (Player p:players) {
			summeAbs += p.getAbsatz();
			setSummeUms(getSummeUms() + p.getAbsatz() * p.getPreis());
			System.out.println("+ "+p.getAbsatz());
		}
		System.out.println("= "+summeAbs);

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
		if (diff_ges < rest_ges)	// was ist kleiner | es kann nur so viel Bedarf umverteilt werden, wie andere Unternehmen im Lager �brig haben.
			trans = diff_ges;
		else
			trans = rest_ges;
		
		if (trans != 0) {			// kommt es zu Verschiebungen?
			System.out.println("shift it ___________-");
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
	 * Summe der M�glichen Abs�tze aller Spieler
	 * 
	 * @return int Summer der M�glichen Abs�tze
	 */
	private int getMoegAbs() {
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
	 * Summe der Ums�tze aller Spieler
	 * 
	 * @return int Summe Ums�tze
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
	 * Gibt den Datumsstring der aktullen Zeit time im Format dd.mm.yyyy zur�ck.
	 * Hierbei werden f�hrende Nullen verwendet. z.B. 01.05.2016
	 * 
	 * @return String, das Datum in Stringform
	 */
	public String getDateString() {
		long year = getYear();
		long month = getMonth();
		long day = getDay();
		
		//return (year)+":"+(month+1)+":"+(day+1);	// ++ Denn es gibt keinen 0. Monat/Tag
		return String.format("%02d", day+1) + "." + String.format("%02d", month+1) + "." + String.format("%04d", year+startingYear);
		// F�hrende Nullen: dd.mm.yyyy
		// z.B. 01.05.0020
	}
	
	public String getZuwachsString() {
		String s = "";
		double z = getZuwachs();
		if (z == 0 || z==1)
			return "�0%";
		else if (z > 1)
			s += "+";		// - Zeichen wird automatisch gesetzt

		s += String.format("%.2f", (z-1)*100) + "%";
		
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

