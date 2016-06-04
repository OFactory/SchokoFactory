package de.OFactory.SchokoFactory.game.patterns;

import de.OFactory.SchokoFactory.game.Map;
import de.OFactory.SchokoFactory.game.Pattern;
import de.OFactory.SchokoFactory.game.PatternFrame;
import de.OFactory.SchokoFactory.game.PatternState;

public class Lagerhalle extends Pattern{
	public Lagerhalle(Map map, int x, int y, int id, int xcoor, int ycoor) {
		super(map, x, y, PatternState.LAGERHALLE, id, xcoor, ycoor);
		
		this.setPatternFrame(PatternFrame.LAGERHALLE_NORMAL);
	}

	@Override
	public void updateContext() {
		
		//System.out.println("LOL");
		
		//Testet ob Links oben auch eine Lagerhalle steht
		Pattern nop = this.getPatternNeighbours().get(0);
		Pattern swp = this.getPatternNeighbours().get(2);
		if(nop == null || nop instanceof Lagerhalle){
				if(!(swp instanceof Lagerhalle)) //Links unten KEINE Lagerhalle
					this.setCurrentImagePosition(PatternFrame.LAGERHALLE_ENDE);
				else
					this.setCurrentImagePosition(PatternFrame.LAGERHALLE_NORMAL);
		} else {
			this.setCurrentImagePosition(PatternFrame.LAGERHALLE_NORMAL);
		}
		
		
	}
	
  
}
