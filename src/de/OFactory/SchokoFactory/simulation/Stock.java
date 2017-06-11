/**
 * 
 */
package de.OFactory.SchokoFactory.simulation;

/**
 * @author Hans Olischl�ger
 *
 */
public class Stock {

	/**
	 * Alles was einem Spieler geh�rt kommt hier hin.
	 * 
	 * 
	 * [Geld ist in Player.java]
	 * 
	 */
	
	// Materialien
	
	private double kakao;
	private double zucker;
	private double milch;
	private double zusatzstoffe;
	private double fl�ssigeSchokolade;
	private int tafeln;

	
	
	public Stock(int kakao, int zucker, int milch, int zusatzstoffe, int fl�ssigeSchokolade, int tafeln) {
		this.kakao = kakao;
		this.zucker = zucker;
		this.milch = milch;
		this.zusatzstoffe = zusatzstoffe;
		this.fl�ssigeSchokolade = fl�ssigeSchokolade;
		this.tafeln = tafeln;
	}
	
	public Stock() {
		this.kakao = 0;
		this.zucker = 0;
		this.milch = 0;
		this.zusatzstoffe = 0;
		this.fl�ssigeSchokolade = 0;
		this.tafeln = 0;
	}
	
	
	
	
	public void addKakao(double menge){
		setKakao(getKakao() + menge);
	}

	public double getKakao() {
		return kakao;
	}

	public void setKakao(double kakao) {
		this.kakao = kakao;
	}

	
	
	public void addZucker(double menge){
		setZucker(getZucker() + menge);
	}
	
	public double getZucker() {
		return zucker;
	}

	public void setZucker(double zucker) {
		this.zucker = zucker;
	}

	
	
	public void addMilch(double d){
		setMilch(getMilch() + d);
	}
	
	public double getMilch() {
		return milch;
	}

	public void setMilch(double milch) {
		this.milch = milch;
	}

	
	
	public void addZusatzstoffe(double menge){
		setZusatzstoffe(getZusatzstoffe() + menge);
	}
	
	public double getZusatzstoffe() {
		return zusatzstoffe;
	}

	public void setZusatzstoffe(double zusatzstoffe) {
		this.zusatzstoffe = zusatzstoffe;
	}

	
	
	public void addFl�ssigeSchokolade(double menge){
		setFl�ssigeSchokolade(getFl�ssigeSchokolade() + menge);
		
	}
	
	public double getFl�ssigeSchokolade() {
		return fl�ssigeSchokolade;
	}

	public void setFl�ssigeSchokolade(double fl�ssigeSchokolade) {
		this.fl�ssigeSchokolade = fl�ssigeSchokolade;
	}

	
	
	public void addTafeln(int menge){
		setTafeln(getTafeln() + menge);

	}
	
	public int getTafeln() {
		return tafeln;
	}

	public void setTafeln(int tafeln) {
		this.tafeln = tafeln;
	}

}
