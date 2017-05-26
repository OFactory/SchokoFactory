package de.OFactory.SchokoFactory.game.patterns;

import java.io.Serializable;
import java.util.ArrayList;

import de.OFactory.SchokoFactory.game.Pattern;
import de.OFactory.SchokoFactory.game.PatternState;
import de.OFactory.SchokoFactory.game.Workable;
import de.OFactory.SchokoFactory.main.MainState;

public class Farm extends Pattern implements Serializable, Workable{

	private static final long serialVersionUID = 6L;
	private ArrayList<Pattern> felder = new ArrayList<Pattern>(8);
	
	private boolean working = true;
	private int produktion = 100;
	private int einmaligeFabrikkosten = 200;
	private int laufendeFabrikkosten = 0;
	
	private int alter = 0;
	private int effizienz = 100;
	
	//private String plants = "Zucker";

	public Farm(int x, int y, int id, int xcoor, int ycoor) {
		super(x, y, PatternState.FARM, id, xcoor, ycoor);
		
		// Felder
		ArrayList<Pattern> nears = this.getPatternNears();
		
		for (Pattern p : nears) {
			if (p instanceof Wiese){
				MainState.field.set(p.getId(), Pattern.getInstance(MainState.field, p.getX(), p.getY(), PatternState.FELD, p.getId(), p.getXCoordinate(), p.getYCoordinate()));
				felder.add(p);
				//((Feld) p).setPlants(this.plants);
				
			}
			
		}

	}


	public void work() {
		
		setAlter(getAlter() + 1);
		
		if(this.isWorking()){
			for (Pattern p : felder) {
				if ( p instanceof Feld && ((Feld) p).getPlants() == "Kakao") {
					MainState.p.stock.addKakao(produktion/8);			// auf jedem Feld 0.125 kg Kakao / Zucker
				} else {	// -> Zucker								// insgesamt 100 -> Produktion 4 PE
					MainState.p.stock.addZucker(produktion/8);
				}
			}

			MainState.p.addMoney(-laufendeFabrikkosten);
			MainState.p.addAusgaben(laufendeFabrikkosten);
		}
	}
	
	
	@Override
	public void updateContext() {
		
	}

	@Override
	public void updatePatternInfo() {
		// XXX Mögliche Attribute: Effizienz, Fruchtbarkeit des Bodens
		
	}

	
	
	public ArrayList<Pattern> getFelder() {
		return felder;
	}

	public void setFelder(ArrayList<Pattern> felder) {
		this.felder = felder;
	}


	public boolean isWorking() {
		return working;
	}


	public void setWorking(boolean working) {
		this.working = working;
	}


	public int getProduktion() {
		return produktion;
	}


	public void setProduktion(int produktion) {
		this.produktion = produktion;
	}


	public int getEinmaligeFabrikkosten() {
		return einmaligeFabrikkosten;
	}


	public void setEinmaligeFabrikkosten(int einmaligeFabrikkosten) {
		this.einmaligeFabrikkosten = einmaligeFabrikkosten;
	}


	public int getLaufendeFabrikkosten() {
		return laufendeFabrikkosten;
	}


	public void setLaufendeFabrikkosten(int laufendeFabrikkosten) {
		this.laufendeFabrikkosten = laufendeFabrikkosten;
	}


	public int getAlter() {
		return alter;
	}


	public void setAlter(int alter) {
		this.alter = alter;
	}


	public int getEffizienz() {
		return effizienz;
	}


	public void setEffizienz(int effizienz) {
		this.effizienz = effizienz;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
