package de.OFactory.SchokoFactory.game.patterns;

import java.io.Serializable;

import de.OFactory.SchokoFactory.game.Pattern;
import de.OFactory.SchokoFactory.game.PatternState;

public class PRB�ro extends Pattern implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 11L;

	public PRB�ro(int x, int y, int id, int xcoor, int ycoor) {
		super(x, y, PatternState.PRB�RO, id, xcoor, ycoor);
		
		//XXX BILD FEHLT NOCH
	}

	@Override
	public void updateContext() {
		
	}

	@Override
	public void updatePatternInfo() {
		// XXX M�gliche Attribute: Effizienz, Fruchtbarkeit, Qualit�t(Verunreinigungen des Zuckers)
		
	}
}
