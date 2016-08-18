package de.OFactory.SchokoFactory.game.patterns;

import de.OFactory.SchokoFactory.game.Map;
import de.OFactory.SchokoFactory.game.Pattern;
import de.OFactory.SchokoFactory.game.PatternFrame;
import de.OFactory.SchokoFactory.game.PatternState;

public class Gie�er extends Pattern{
	
	public Gie�er(Map map, int x, int y, int id, int xcoor, int ycoor) {
		super(map, x, y, PatternState.GIE�ER, id, xcoor, ycoor);
		
		this.setDelay(6);
		this.setFrameLoop(PatternFrame.GIE�ER_S, PatternFrame.GIE�ER_E);
	}

	@Override
	public void updateContext() {
		
	}

	@Override
	public void updatePatternInfo() {
		// XXX M�gliche Attribute: Effizienz, Gego�enes Produkt
		
	}
}
