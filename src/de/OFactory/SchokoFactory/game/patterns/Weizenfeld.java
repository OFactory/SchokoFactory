package de.OFactory.SchokoFactory.game.patterns;

import de.OFactory.SchokoFactory.game.Map;
import de.OFactory.SchokoFactory.game.Pattern;
import de.OFactory.SchokoFactory.game.PatternFrame;
import de.OFactory.SchokoFactory.game.PatternState;

public class Weizenfeld extends Pattern{
	public Weizenfeld(Map map, int x, int y, int id) {
		super(map, x, y, PatternState.WEIZENFELD, id);
		
		this.setPatternFrame(PatternFrame.FARM);
		//XXX BILD FEHLT NOCH
	}

	@Override
	public void updateContext() {
		
	}
}
