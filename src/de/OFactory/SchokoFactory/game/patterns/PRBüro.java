package de.OFactory.SchokoFactory.game.patterns;

import de.OFactory.SchokoFactory.game.Map;
import de.OFactory.SchokoFactory.game.Pattern;
import de.OFactory.SchokoFactory.game.PatternState;

public class PRB�ro extends Pattern{
	public PRB�ro(Map map, int x, int y, int id, int xcoor, int ycoor) {
		super(map, x, y, PatternState.PRB�RO, id, xcoor, ycoor);
		
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
