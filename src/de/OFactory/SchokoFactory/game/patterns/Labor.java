package de.OFactory.SchokoFactory.game.patterns;

import de.OFactory.SchokoFactory.game.Map;
import de.OFactory.SchokoFactory.game.Pattern;
import de.OFactory.SchokoFactory.game.PatternState;

public class Labor extends Pattern{
	public Labor(Map map, int x, int y, int id, int xcoor, int ycoor) {
		super(map, x, y, PatternState.LABOR, id, xcoor, ycoor);
		
		setAnimation(0,7,8,3);

	}

	@Override
	public void updateContext() {
		
	}

	@Override
	public void updatePatternInfo() {
		// XXX Mögliche Attribute: Effizienz, Fruchtbarkeit, Qualität(Verunreinigungen des Zuckers)
		
	}
}
