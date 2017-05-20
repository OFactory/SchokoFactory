package de.OFactory.SchokoFactory.game.patterns;


import de.OFactory.SchokoFactory.game.Map;
import de.OFactory.SchokoFactory.game.Pattern;
import de.OFactory.SchokoFactory.game.PatternFrame;
import de.OFactory.SchokoFactory.game.PatternState;
import de.OFactory.SchokoFactory.inventory.Button;

public class Chemiefabrik extends Pattern{

	public Chemiefabrik(Map map, int x, int y, int id, int xcoor, int ycoor) {
		super(map, x, y, PatternState.CHEMIEFABRIK, id, xcoor, ycoor);
		
		this.setDelay(9);
		this.setFrameLoop(PatternFrame.CHEMIE_S, PatternFrame.CHEMIE_E);
	}

	@Override
	public void updateContext() {
		
	}

	@Override
	public void updatePatternInfo() {
		// XXX Mögliche Attribute: Effizienz, Bedenklichkeit bzgl. "Chemie"
		Button b = new Button(200, 0, 0, 0, 0, "Forschung");
		clearPatternInfo();
		putPatternInfo("Forschungsbutton", b);
	}

}