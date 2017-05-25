package de.OFactory.SchokoFactory.game.patterns;

import de.OFactory.SchokoFactory.game.Map;
import de.OFactory.SchokoFactory.game.Pattern;
import de.OFactory.SchokoFactory.game.PatternState;

public class Lagerhalle extends Pattern{
	
	public static final int LAGERKAPAZITÄT_NORMAL = 2000;
	
	
	public Lagerhalle(Map map, int x, int y, int id, int xcoor, int ycoor) {
		super(map, x, y, PatternState.LAGER, id, xcoor, ycoor);
		

	}

	@Override
	public void updateContext() {
		
		//System.out.println("LOL");
		
		Pattern swp = this.getPatternNeighbours().get(2);
		Pattern sop = this.getPatternNeighbours().get(1);
		
		if ((sop instanceof Lagerhalle)) //Rechts unten Lagerhalle
			setFrame(2);
			
		else if(!(swp instanceof Lagerhalle)) //Links unten KEINE Lagerhalle
			setFrame(0);
		
		else
			setFrame(1);
		
	}

	@Override
	public void updatePatternInfo() {
		// XXX Mögliche Attribute: Kapazität
		
	}
	
  
}
