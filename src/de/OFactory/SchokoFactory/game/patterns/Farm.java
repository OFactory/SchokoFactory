package de.OFactory.SchokoFactory.game.patterns;

import java.io.Serializable;

import de.OFactory.SchokoFactory.game.Pattern;
import de.OFactory.SchokoFactory.game.PatternState;

public class Farm extends Pattern implements Serializable{

	private static final long serialVersionUID = 6L;

	public Farm(int x, int y, int id, int xcoor, int ycoor) {
		super(x, y, PatternState.FARM, id, xcoor, ycoor);
		

	}

	@Override
	public void updateContext() {
		
	}

	@Override
	public void updatePatternInfo() {
		// XXX Mögliche Attribute: Effizienz, Fruchtbarkeit des Bodens
		
	}
}
