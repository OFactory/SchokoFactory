package de.OFactory.SchokoFactory.game.patterns;

import de.OFactory.SchokoFactory.game.Pattern;
import de.OFactory.SchokoFactory.game.PatternFrame;
import de.OFactory.SchokoFactory.game.PatternState;

public class Molkerei extends Pattern {
	public Molkerei(int x, int y, int id) {
		super(x, y, PatternState.MOLKEREI, id);
		
		this.setPatternFrame(PatternFrame.MOLKEREI_MODERN);
	}

	@Override
	public void updateContext() {
		//TODO Molkerei alt adden! Bild fehlt :(
	}
}
