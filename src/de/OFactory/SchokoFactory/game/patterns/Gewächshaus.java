package de.OFactory.SchokoFactory.game.patterns;

import org.newdawn.slick.Image;

import de.OFactory.SchokoFactory.game.Map;
import de.OFactory.SchokoFactory.game.Pattern;
import de.OFactory.SchokoFactory.game.PatternState;

public class Gewächshaus extends Pattern{
	
	String plants = "Kakao";
	
	public Gewächshaus(Map map,int x, int y, int id, int xcoor, int ycoor) {
		super(map, x, y, PatternState.GEWÄCHSHAUS, id, xcoor, ycoor);
		
		//TODO KAKAOPLANTAGE Bild fehlt noch! 
	}

	@Override
	public void updateContext() {
		Image source = this.getPatternState().getSource();
		if (this.plants == "Kakao"){
			setFrame(0);
		} else  // -> Zucker
			setFrame(1);
	}

	@Override
	public void updatePatternInfo() {
		// XXX Mögliche Attribute: Effizienz, Fruchtbarkeit des Bodens, Qualität		
	}
}
