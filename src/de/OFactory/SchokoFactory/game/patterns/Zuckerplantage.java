package de.OFactory.SchokoFactory.game.patterns;

import de.OFactory.SchokoFactory.game.Map;
import de.OFactory.SchokoFactory.game.Pattern;
import de.OFactory.SchokoFactory.game.PatternState;

public class Zuckerplantage extends Pattern{
	public Zuckerplantage(Map map, int x, int y, int id) {
		super(map, x, y, PatternState.ZUCKERPLANTAGE, id);
		
		//XXX BILD FEHLT NOCH
	}

	@Override
	public void updateContext() {
		
	}
}
