package de.OFactory.SchokoFactory.game.patterns;

import de.OFactory.SchokoFactory.game.Map;
import de.OFactory.SchokoFactory.game.Pattern;
import de.OFactory.SchokoFactory.game.PatternFrame;
import de.OFactory.SchokoFactory.game.PatternState;

public class Farm extends Pattern{
	public Farm(Map map, int x, int y, int id, int xcoor, int ycoor) {
		super(map, x, y, PatternState.FARM, id, xcoor, ycoor);
		
		this.setPatternFrame(PatternFrame.FARM);
	}

	@Override
	public void updateContext() {
		
	}
}
