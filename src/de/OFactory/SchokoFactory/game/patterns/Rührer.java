package de.OFactory.SchokoFactory.game.patterns;

import de.OFactory.SchokoFactory.game.Pattern;
import de.OFactory.SchokoFactory.game.PatternFrame;
import de.OFactory.SchokoFactory.game.PatternState;

public class R�hrer extends Pattern{

	private boolean active = false;
	
	
	public R�hrer(int x, int y, int id) {
		super(x, y, PatternState.R�HRER, id);
		this.setFrameLoop(PatternFrame.R�HRER_S, PatternFrame.R�HRER_E);
		this.setDelay(5);
	}

	@Override
	public void updateContext() {
		// TODO Auto-generated method stub
		
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}
