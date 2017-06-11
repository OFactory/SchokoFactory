package de.OFactory.SchokoFactory.game.patterns;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import de.OFactory.SchokoFactory.game.Pattern;
import de.OFactory.SchokoFactory.game.PatternState;
import de.OFactory.SchokoFactory.inventory.Button;
import de.OFactory.SchokoFactory.main.MainState;


public class Chemiefabrik extends Pattern implements Serializable{

	private boolean working = true;
	private int produktion = 1000;
	private int einmaligeFabrikkosten = 200;
	private int laufendeFabrikkosten = 0;
	
	private int alter = 0;
	private int effizienz = 100;
	
	private static final long serialVersionUID = 2L;

	public Chemiefabrik(int x, int y, int id, int xcoor, int ycoor) {
		super(x, y, PatternState.CHEMIEFABRIK, id, xcoor, ycoor);
		//start,end,total,delay

		setAnimation(0,7,9,3);

	}

	@Override
	public void updateContext() {
		
		//setImg(source.getSubImage(frame*source.getWidth()/9, 0, source.getWidth()/9, source.getHeight()));

	}

	// Produziert 1000 Zusatzstoffe aus dem nichts bei jedem Aufruf. Idealer Weise täglich.
	public void work() {
		
		alter++;
		if(this.isWorking()){
			MainState.p.stock.addZusatzstoffe(produktion);
			System.out.print("Gießer");
			MainState.p.addMoney(-laufendeFabrikkosten);
			MainState.p.addAusgaben(laufendeFabrikkosten);
		}
	}
	
	@Override
	public void updatePatternInfo() {
		// XXX Mögliche Attribute: Effizienz, Bedenklichkeit bzgl. "Chemie"
		Button b = new Button(200, 0, 0, 0, 0, "Forschung");
		b.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
							
			}
		});
		clearPatternInfo();
		putPatternInfo("Forschungsbutton", b);
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