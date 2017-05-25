package de.OFactory.SchokoFactory.game.patterns;

import de.OFactory.SchokoFactory.game.Map;
import de.OFactory.SchokoFactory.game.Pattern;
import de.OFactory.SchokoFactory.game.PatternState;

public class PRBüro extends Pattern{
	public PRBüro(Map map, int x, int y, int id, int xcoor, int ycoor) {
		super(map, x, y, PatternState.PRBÜRO, id, xcoor, ycoor);
		
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
