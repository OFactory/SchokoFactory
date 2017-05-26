package de.OFactory.SchokoFactory.game.patterns;

import java.io.Serializable;

import de.OFactory.SchokoFactory.game.Pattern;
import de.OFactory.SchokoFactory.game.PatternState;

public class Molkerei extends Pattern implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5L;

	public Molkerei( int x, int y, int id, int xcoor, int ycoor) {
		super(x, y, PatternState.MOLKEREI, id, xcoor, ycoor);
		
	}

	@Override
	public void updateContext() {
		//TODO Molkerei alt adden! Bild fehlt :(
	}

	@Override
	public void updatePatternInfo() {
		// XXX Mögliche Attribute: Effizienz, Anzahl Kühe, Qualität
		
	}
}
