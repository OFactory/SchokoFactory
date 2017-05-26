package de.OFactory.SchokoFactory.game.patterns;

import java.io.Serializable;

import de.OFactory.SchokoFactory.game.Pattern;
import de.OFactory.SchokoFactory.game.PatternState;
import de.OFactory.SchokoFactory.game.Workable;
import de.OFactory.SchokoFactory.main.MainState;


public class Rührer extends Pattern implements Workable, Serializable{

	private static final long serialVersionUID = 1L;
	private boolean active = false;
	
	private boolean working = true;
	private int produktion = 100;
	private int einmaligeFabrikkosten = 200;
	private int laufendeFabrikkosten = 0;
	
	private int alter = 0;
	private int effizienz = 100;
	
	
	public Rührer(int x, int y, int id, int xcoor, int ycoor) {
		super(x, y, PatternState.RÜHRER, id, xcoor, ycoor);
		

		//start,end,total,delay
		this.setAnimation(0,7,9,3);
		
	}
	

	@Override
	public void updateContext() {
		// TODO Auto-generated method stub
		
	}
	
	
	public void work() {
		
		setAlter(getAlter() + 1);
		
		if(this.isWorking()){
			MainState.p.stock.addFlüssigeSchokolade(produktion);
			MainState.p.stock.addMilch(-produktion*0.5); // 0.5 Liter Milch zu 1 Liter flüssigen Schokolade
			MainState.p.stock.addKakao(-produktion*0.25); // 0.25 kg Kakao zu 1 Liter flüssigen Schokolade
			MainState.p.stock.addZucker(-produktion*0.25); // 0.25 kg Zucker zu 1 Liter flüssigen Schokolade
			MainState.p.stock.addZucker(-produktion*0.05); // 5 g Zusatzstoffe zu 1 Liter flüssigen Schokolade
			MainState.p.addMoney(-laufendeFabrikkosten);
			MainState.p.addAusgaben(laufendeFabrikkosten);
		}
	}
	
	@Override
	public void updatePatternInfo() {
		getPatternInfo().clear();
		// XXX Mögliche Attribute: Effizienz, Gegoßenes Produkt, Gerätealter, laufende Kosten
		if(isWorking())
			putPatternInfo("Status", "Arbeitend");
		else
			putPatternInfo("Status", "Deaktiviert");
		
		putPatternInfo("Produkt", "Schokolade");
		putPatternInfo("Laufende Kosten",   laufendeFabrikkosten);
		putPatternInfo("Gerätealter",       alter + " Tage");
		putPatternInfo("Effizienz",         effizienz + "%");
		
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}



	public boolean isWorking() {
		return working;
	}


	public void setWorking(boolean working) {
		this.working = working;
	}


	public int getAlter() {
		return alter;
	}


	public void setAlter(int alter) {
		this.alter = alter;
	}


	public int getEinmaligeFabrikkosten() {
		return einmaligeFabrikkosten;
	}


	public void setEinmaligeFabrikkosten(int einmaligeFabrikkosten) {
		this.einmaligeFabrikkosten = einmaligeFabrikkosten;
	}

}
