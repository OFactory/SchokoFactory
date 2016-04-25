package de.OFactory.SchokoFactory.game.patterns;


import org.newdawn.slick.Image;

import de.OFactory.SchokoFactory.game.Pattern;
import de.OFactory.SchokoFactory.game.PatternFrame;
import de.OFactory.SchokoFactory.game.PatternState;
import de.OFactory.SchokoFactory.main.MainState;

public class Chemiefabrik extends Pattern{

	public Chemiefabrik(int x, int y, int id) {
		super(x, y, PatternState.CHEMIEFABRIK, id);
		
		this.setDelay(9);
		this.setFrameLoop(PatternFrame.CHEMIE_S, PatternFrame.CHEMIE_E);
	}

	@Override
	public void updateContext() {
		
	}

}