package de.OFactory.SchokoFactory.game.patterns;

import java.io.Serializable;

import de.OFactory.SchokoFactory.game.GameUtils;
import de.OFactory.SchokoFactory.game.Pattern;
import de.OFactory.SchokoFactory.game.PatternState;
import de.OFactory.SchokoFactory.main.MainState;


public class Wiese extends Pattern implements Serializable{
	
	private static final long serialVersionUID = 12L;
	
	private int type = 0;
	
	public Wiese(int x, int y, int id, int xcoor, int ycoor) {
		super(x, y, PatternState.WIESE, id, xcoor, ycoor);
		
		
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
		// XXX Mögliche Attribute: Fruchtbarkeit des Bodens
		
	}
	
	

}
