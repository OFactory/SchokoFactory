package de.OFactory.SchokoFactory.game.patterns;

import de.OFactory.SchokoFactory.game.Map;
import de.OFactory.SchokoFactory.game.Pattern;
import de.OFactory.SchokoFactory.game.PatternFrame;
import de.OFactory.SchokoFactory.game.PatternState;
import de.OFactory.SchokoFactory.game.Workable;
import de.OFactory.SchokoFactory.main.MainState;

public class Gie�er extends Pattern implements Workable{
	
	private int produktion = 100;
	
	public Gie�er(Map map, int x, int y, int id, int xcoor, int ycoor) {
		super(map, x, y, PatternState.GIE�ER, id, xcoor, ycoor);
		
		this.setDelay(6);
		this.setFrameLoop(PatternFrame.GIE�ER_S, PatternFrame.GIE�ER_E);
	}

	@Override
	public void updateContext() {
		
	}
	
	// Produziert 100 Tafeln aus dem nichts bei jedem Aufruf. Idealer Weise t�glich.
	public void work() {
		System.out.println(MainState.p.getProduktmenge());
		MainState.p.setProduktmenge(MainState.p.getProduktmenge() + produktion );
	}

	@Override
	public void updatePatternInfo() {
		// XXX M�gliche Attribute: Effizienz, Gego�enes Produkt
		
	}
}
