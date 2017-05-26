package de.OFactory.SchokoFactory.game.patterns;

import java.io.Serializable;

import de.OFactory.SchokoFactory.game.Pattern;
import de.OFactory.SchokoFactory.game.PatternState;
import de.OFactory.SchokoFactory.game.Workable;
import de.OFactory.SchokoFactory.main.MainState;

public class Hof extends Pattern implements Serializable, Workable{
	
	private static final long serialVersionUID = 9L;
	
	private Hof partner;
	private Hof main = this;
	
	private boolean working = true;
	private int produktion = 50;
	private int einmaligeFabrikkosten = 200;
	private int laufendeFabrikkosten = 0;
	
	private int alter = 0;
	private int effizienz = 100;
	
	
	public Hof(int x, int y, int id, int xcoor, int ycoor) {
		super(x, y, PatternState.HOF, id, xcoor, ycoor);
		
		
		
		//Pattern sw = this.getPatternNeighbours().get(2);	// Süd-West
		Pattern no = this.getPatternNeighbours().get(0);	// Nord-Ost
		
		System.out.println((no instanceof Hof && ((Hof) no).main.getY() == ((Hof) no).getY()));

		/*if ((main == this) && (sw instanceof Wiese)){
			this.main = this;
			MainState.field.set(sw.getId(), Pattern.getInstance(MainState.field, sw.getX(), sw.getY(), PatternState.HOF, sw.getId(), sw.getXCoordinate(), sw.getYCoordinate()));
			partner = (Hof)sw;
			
			((Hof) sw).setMain(this);
			((Hof) sw).setPartner(this);
			((Hof) sw).setFrame(0);
			
			this.setFrame(1);
			
		}*/
		
		this.setFrame(1);
	}
	
	public void work() {
		
		setAlter(getAlter() + 1);
		
		if(this.isWorking() && this.main == this){
			MainState.p.stock.addMilch(this.main.produktion);
			MainState.p.addMoney(-this.main.laufendeFabrikkosten);
			MainState.p.addAusgaben(this.main.laufendeFabrikkosten);
		}
	}

	@Override
	public void updateContext() {
		
	}

	@Override
	public void updatePatternInfo() {
		getPatternInfo().clear();
		// XXX Mögliche Attribute: Effizienz, Gegoßenes Produkt, Gerätealter, laufende Kosten
		if(isWorking())
			putPatternInfo("Status", "Arbeitend");
		else
			putPatternInfo("Status", "Deaktiviert");
		
		putPatternInfo("Produkt", "Milch");
		putPatternInfo("Laufende Kosten",   laufendeFabrikkosten);
		putPatternInfo("Gerätealter",       alter + " Tage");
		putPatternInfo("Effizienz",         effizienz + "%");
		
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

	public Hof getPartner() {
		return partner;
	}

	public void setPartner(Hof partner) {
		this.partner = partner;
	}

	public Hof getMain() {
		return main;
	}

	public void setMain(Hof main) {
		this.main = main;
	}
}
