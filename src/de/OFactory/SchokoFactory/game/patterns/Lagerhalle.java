package de.OFactory.SchokoFactory.game.patterns;

import de.OFactory.SchokoFactory.game.Pattern;
import de.OFactory.SchokoFactory.game.PatternFrame;
import de.OFactory.SchokoFactory.game.PatternState;

public class Lagerhalle extends Pattern{
	public Lagerhalle(int x, int y, int id) {
		super(x, y, PatternState.LAGERHALLE, id);
		
		this.setPatternFrame(PatternFrame.LAGERHALLE_NORMAL);
	}

	@Override
	public void updateContext() {
		//TODO Start der Reihe einbuilden!
	}
}
