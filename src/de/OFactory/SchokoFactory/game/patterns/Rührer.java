package de.OFactory.SchokoFactory.game.patterns;

import de.OFactory.SchokoFactory.game.Pattern;
import de.OFactory.SchokoFactory.game.PatternFrame;
import de.OFactory.SchokoFactory.game.PatternState;

public class Rührer extends Pattern{

	private boolean active = false;
	
	
	public Rührer(int x, int y, int id) {
		super(x, y, PatternState.RÜHRER, id);
		this.setFrameLoop(PatternFrame.RÜHRER_S, PatternFrame.RÜHRER_E);
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
