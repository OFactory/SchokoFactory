package de.OFactory.SchokoFactory.game.patterns;

import de.OFactory.SchokoFactory.game.GameUtils;
import de.OFactory.SchokoFactory.game.Map;
import de.OFactory.SchokoFactory.game.Pattern;
import de.OFactory.SchokoFactory.game.PatternState;

public class Wiese extends Pattern{
	
	public int type = 0;
	

	public Wiese(Map map, int x, int y, int id, int xcoor, int ycoor) {
		super(map, x, y, PatternState.WIESE, id, xcoor, ycoor);
		
		
		this.type = GameUtils.randInt(0, 3);
		this.setPatternFrame(36 + type);
		
		
		
		
	}
	

	@Override
	public void updateContext() {
		
		
		
	}
	
	

}
