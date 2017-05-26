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
	
	private int kakao;
	private int zucker;
	private int milch;
	private int zusatzstoffe;
	private int flüssigeSchokolade;
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
	
	
	
	
	public void addKakao(int menge){
		setKakao(getKakao() + menge);
	}

	public int getKakao() {
		return kakao;
	}

	public void setKakao(int kakao) {
		this.kakao = kakao;
	}

	
	
	public void addZucker(int menge){
		setKakao(getZucker() + menge);
	}
	
	public int getZucker() {
		return zucker;
	}

	public void setZucker(int zucker) {
		this.zucker = zucker;
	}

	
	
	public void addMilch(int menge){
		setKakao(getMilch() + menge);
	}
	
	public int getMilch() {
		return milch;
	}

	public void setMilch(int milch) {
		this.milch = milch;
	}

	
	
	public void addZusatzstoffe(int menge){
		setKakao(getZusatzstoffe() + menge);
	}
	
	public int getZusatzstoffe() {
		return zusatzstoffe;
	}

	public void setZusatzstoffe(int zusatzstoffe) {
		this.zusatzstoffe = zusatzstoffe;
	}

	
	
	public void addFlüssigeSchokolade(int menge){
		setKakao(getFlüssigeSchokolade() + menge);
	}
	
	public int getFlüssigeSchokolade() {
		return flüssigeSchokolade;
	}

	public void setFlüssigeSchokolade(int flüssigeSchokolade) {
		this.flüssigeSchokolade = flüssigeSchokolade;
	}

	
	
	public void addTafeln(int menge){
		setKakao(getTafeln() + menge);
	}
	
	public int getTafeln() {
		return tafeln;
	}

	public void setTafeln(int tafeln) {
		this.tafeln = tafeln;
	}

}
