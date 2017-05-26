package de.OFactory.SchokoFactory.game.patterns;

import java.io.Serializable;

import de.OFactory.SchokoFactory.game.Pattern;
import de.OFactory.SchokoFactory.game.PatternState;

public class Hof extends Pattern implements Serializable{
	
	private static final long serialVersionUID = 9L;

	public Hof(int x, int y, int id, int xcoor, int ycoor) {
		super(x, y, PatternState.HOF, id, xcoor, ycoor);
		
		//TODO KAKAOPLANTAGE Bild fehlt noch! 
	}

	@Override
	public void updateContext() {
		
	}

	@Override
	public void updatePatternInfo() {
		// XXX Mögliche Attribute: Effizienz, Fruchtbarkeit des Bodens, Qualität		
	}
}
