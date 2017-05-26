package de.OFactory.SchokoFactory.game.patterns;

import java.io.Serializable;

import de.OFactory.SchokoFactory.game.Pattern;
import de.OFactory.SchokoFactory.game.PatternState;

public class Gew�chshaus extends Pattern implements Serializable{
	

	private static final long serialVersionUID = 8L;
	
	String plants = "Kakao";
	
	public Gew�chshaus(int x, int y, int id, int xcoor, int ycoor) {
		super(x, y, PatternState.GEW�CHSHAUS, id, xcoor, ycoor);
		
		//TODO KAKAOPLANTAGE Bild fehlt noch! 
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
		// XXX M�gliche Attribute: Effizienz, Fruchtbarkeit des Bodens, Qualit�t		
	}
}
