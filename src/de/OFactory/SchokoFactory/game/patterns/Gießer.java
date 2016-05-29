package de.OFactory.SchokoFactory.game.patterns;

import de.OFactory.SchokoFactory.game.Pattern;
import de.OFactory.SchokoFactory.game.PatternFrame;
import de.OFactory.SchokoFactory.game.PatternState;

public class Gie�er extends Pattern{
	
	public Gie�er(int x, int y, int id) {
		super(x, y, PatternState.GIE�ER, id);
		
		this.setDelay(4);
		this.setFrameLoop(PatternFrame.GIE�ER_S, PatternFrame.GIE�ER_E);
	}

	@Override
	public void updateContext() {
		
	}
}
