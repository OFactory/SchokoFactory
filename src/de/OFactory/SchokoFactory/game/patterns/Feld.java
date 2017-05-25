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
			setImg(source.getSubImage(0*source.getWidth()/2, 0, source.getWidth()/2, source.getHeight()));
		} else  // -> Zucker
			setImg(source.getSubImage(1*source.getWidth()/2, 0, source.getWidth()/2, source.getHeight()));
			
	}

	@Override
	public void updatePatternInfo() {
		// XXX Mögliche Attribute: Effizienz, Fruchtbarkeit, Qualität
		
	}
}
