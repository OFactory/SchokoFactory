/**
 * 
 */
package de.OFactory.SchokoFactory.simulation;

/**
 * @author Hans Olischläger
 *
 */
public class Stock {

	/**
	 * Alles was einem Spieler gehört kommt hier hin.
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
	private double flüssigeSchokolade;
	private int tafeln;

	
	
	public Stock(int kakao, int zucker, int milch, int zusatzstoffe, int flüssigeSchokolade, int tafeln) {
		this.kakao = kakao;
		this.zucker = zucker;
		this.milch = milch;
		this.zusatzstoffe = zusatzstoffe;
		this.flüssigeSchokolade = flüssigeSchokolade;
		this.tafeln = tafeln;
	}
	
	public Stock() {
		this.kakao = 0;
		this.zucker = 0;
		this.milch = 0;
		this.zusatzstoffe = 0;
		this.flüssigeSchokolade = 0;
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

	
	
	public void addFlüssigeSchokolade(double menge){
		setFlüssigeSchokolade(getFlüssigeSchokolade() + menge);
		
	}
	
	public double getFlüssigeSchokolade() {
		return flüssigeSchokolade;
	}

	public void setFlüssigeSchokolade(double flüssigeSchokolade) {
		this.flüssigeSchokolade = flüssigeSchokolade;
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
