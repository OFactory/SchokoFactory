package de.OFactory.SchokoFactory.game.patterns;

import de.OFactory.SchokoFactory.game.Map;
import de.OFactory.SchokoFactory.game.Pattern;
import de.OFactory.SchokoFactory.game.PatternFrame;
import de.OFactory.SchokoFactory.game.PatternState;

public class Molkerei extends Pattern {
	public Molkerei(Map map, int x, int y, int id, int xcoor, int ycoor) {
		super(map, x, y, PatternState.MOLKEREI, id, xcoor, ycoor);
		
		this.setPatternFrame(PatternFrame.MOLKEREI_MODERN);
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
