package de.OFactory.SchokoFactory.game.patterns;


import java.io.Serializable;

import de.OFactory.SchokoFactory.game.Pattern;
import de.OFactory.SchokoFactory.game.PatternState;

public class Feld extends Pattern implements Serializable{

	private static final long serialVersionUID = 7L;
	
	private String plants = "Zucker";
	
	public Feld(int x, int y, int id, int xcoor, int ycoor) {
		super(x, y, PatternState.FELD, id, xcoor, ycoor);
		

		//XXX BILD FEHLT NOCH
	}

	@Override
	public void updateContext() {

		if (this.plants == "Kakao"){
			setFrame(0);
		} else  // -> Zucker
			setFrame(1);
			
	}

	@Override
	public void updatePatternInfo() {
		// XXX Mögliche Attribute: Effizienz, Fruchtbarkeit, Qualität
		
	}

	public String getPlants() {
		return plants;
	}

	public void setPlants(String plants) {
		this.plants = plants;
	}
}
