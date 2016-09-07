package de.OFactory.SchokoFactory.game.patterns;

import de.OFactory.SchokoFactory.game.Map;
import de.OFactory.SchokoFactory.game.Pattern;
import de.OFactory.SchokoFactory.game.PatternFrame;
import de.OFactory.SchokoFactory.game.PatternState;
import de.OFactory.SchokoFactory.main.MainState;

public class Gieﬂer extends Pattern{
	
	public Gieﬂer(Map map, int x, int y, int id, int xcoor, int ycoor) {
		super(map, x, y, PatternState.GIEﬂER, id, xcoor, ycoor);
		
		this.setDelay(6);
		this.setFrameLoop(PatternFrame.GIEﬂER_S, PatternFrame.GIEﬂER_E);
	}

	@Override
	public void updateContext() {
		
	}
	
	// Produziert 100 Tafeln aus dem nichts bei jedem Aufruf. Idealer Weise t‰glich.
	@Override
	public void work() {
		MainState.p.setProduktmenge(MainState.p.getProduktmenge() + 100 );
	}

	@Override
	public void updatePatternInfo() {
		// XXX Mˆgliche Attribute: Effizienz, Gegoﬂenes Produkt
		
	}
}
