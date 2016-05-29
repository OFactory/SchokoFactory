package de.OFactory.SchokoFactory.game.patterns;

import de.OFactory.SchokoFactory.game.Pattern;
import de.OFactory.SchokoFactory.game.PatternFrame;
import de.OFactory.SchokoFactory.game.PatternState;

public class Gieﬂer extends Pattern{
	
	public Gieﬂer(int x, int y, int id) {
		super(x, y, PatternState.GIEﬂER, id);
		
		this.setDelay(4);
		this.setFrameLoop(PatternFrame.GIEﬂER_S, PatternFrame.GIEﬂER_E);
	}

	@Override
	public void updateContext() {
		
	}
}
