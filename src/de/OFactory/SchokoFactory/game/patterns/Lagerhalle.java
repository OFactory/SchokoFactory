package de.OFactory.SchokoFactory.game.patterns;

import org.newdawn.slick.Image;

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
		Image source = this.getPatternState().getSource();
		
		if ((sop instanceof Lagerhalle)) //Rechts unten Lagerhalle
			setImg(source.getSubImage(2*source.getWidth()/3, 0, source.getWidth()/3, source.getHeight()));
			
		else if(!(swp instanceof Lagerhalle)) //Links unten KEINE Lagerhalle
			setImg(source.getSubImage(0*source.getWidth()/3, 0, source.getWidth()/3, source.getHeight()));
		
		else
			setImg(source.getSubImage(1*source.getWidth()/3, 0, source.getWidth()/3, source.getHeight()));
		
	}

	@Override
	public void updatePatternInfo() {
		// XXX Mögliche Attribute: Kapazität
		
	}
	
  
}
