package de.OFactory.SchokoFactory.game.patterns;

import java.io.Serializable;

import de.OFactory.SchokoFactory.game.Pattern;
import de.OFactory.SchokoFactory.game.PatternState;

public class PRBüro extends Pattern implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 11L;

	public PRBüro(int x, int y, int id, int xcoor, int ycoor) {
		super(x, y, PatternState.PRBÜRO, id, xcoor, ycoor);
		
		//XXX BILD FEHLT NOCH
	}

	@Override
	public void updateContext() {
		
	}

	@Override
	public void updatePatternInfo() {
		// XXX Mögliche Attribute: Effizienz, Fruchtbarkeit, Qualität(Verunreinigungen des Zuckers)
		
	}
}
