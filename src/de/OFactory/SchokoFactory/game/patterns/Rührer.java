package de.OFactory.SchokoFactory.game.patterns;

import de.OFactory.SchokoFactory.game.Map;
import de.OFactory.SchokoFactory.game.Pattern;
import de.OFactory.SchokoFactory.game.PatternFrame;
import de.OFactory.SchokoFactory.game.PatternState;

public class Rührer extends Pattern{

	private boolean active = false;
	
	
	public Rührer(Map map, int x, int y, int id, int xcoor, int ycoor) {
		super(map, x, y, PatternState.RÜHRER, id, xcoor, ycoor);
		this.setFrameLoop(PatternFrame.RÜHRER_S, PatternFrame.RÜHRER_E);
		this.setDelay(4);
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

	@Override
	public void updatePatternInfo() {
		// XXX M�gliche Attribute: Effizienz, Qualit�t(Verschmutzung des R�hrers etc.)
		
	}

}
