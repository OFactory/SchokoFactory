package de.OFactory.SchokoFactory.game.patterns;

import org.newdawn.slick.Image;

import de.OFactory.SchokoFactory.game.Map;
import de.OFactory.SchokoFactory.game.Pattern;
import de.OFactory.SchokoFactory.game.PatternState;

public class Feld extends Pattern{
	
	String plants = "Zucker";
	
	public Feld(Map map, int x, int y, int id, int xcoor, int ycoor) {
		super(map, x, y, PatternState.FELD, id, xcoor, ycoor);
		

		//XXX BILD FEHLT NOCH
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
		// XXX Mögliche Attribute: Effizienz, Fruchtbarkeit, Qualität
		
	}
}
