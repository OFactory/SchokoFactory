package de.OFactory.SchokoFactory.game.patterns;

import de.OFactory.SchokoFactory.game.GameUtils;
import de.OFactory.SchokoFactory.game.Map;
import de.OFactory.SchokoFactory.game.Pattern;
import de.OFactory.SchokoFactory.game.PatternState;
import de.OFactory.SchokoFactory.main.MainState;


public class Wiese extends Pattern{
	
	private int type = 0;


	public Wiese(Map map, int x, int y, int id, int xcoor, int ycoor) {
		super(map, x, y, PatternState.WIESE, id, xcoor, ycoor);
		
		
		this.type = GameUtils.randInt(0, 12);
		
		setFrame(type);
		
		
	}
	

	@Override
	public void updateContext() {
		if(this.hovered) { //ROT DARSTELLUNG DES MainState.curpatternstate
			if(MainState.curpatternstate != null && MainState.curpatternstate != PatternState.WIESE){
				//this.setImg(MainState.curpatternstate.getDisplayFrame());
			}
		}
		
		
	}


	@Override
	public void updatePatternInfo() {
		// XXX M�gliche Attribute: Fruchtbarkeit des Bodens
		
	}
	
	

}
