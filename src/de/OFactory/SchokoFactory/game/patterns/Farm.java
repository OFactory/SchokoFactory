package de.OFactory.SchokoFactory.game.patterns;

import de.OFactory.SchokoFactory.game.Pattern;
import de.OFactory.SchokoFactory.game.PatternFrame;
import de.OFactory.SchokoFactory.game.PatternState;

public class Farm extends Pattern{
	public Farm(int x, int y, int id) {
		super(x, y, PatternState.FARM, id);
		
		this.setPatternFrame(PatternFrame.FARM);
	}

	@Override
	public void updateContext() {
		
	}
}
