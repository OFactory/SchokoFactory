package de.OFactory.SchokoFactory.game.patterns;

import de.OFactory.SchokoFactory.game.Map;
import de.OFactory.SchokoFactory.game.Pattern;
import de.OFactory.SchokoFactory.game.PatternState;

public class Kakaoplantage extends Pattern{
	
	public Kakaoplantage(Map map,int x, int y, int id, int xcoor, int ycoor) {
		super(map, x, y, PatternState.KAKAOPLANTAGE, id, xcoor, ycoor);
		
		//TODO KAKAOPLANTAGE Bild fehlt noch! 
	}

	@Override
	public void updateContext() {
		
	}
}
