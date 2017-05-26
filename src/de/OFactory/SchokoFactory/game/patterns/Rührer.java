package de.OFactory.SchokoFactory.game.patterns;

import java.io.Serializable;

import de.OFactory.SchokoFactory.game.Pattern;
import de.OFactory.SchokoFactory.game.PatternState;


public class Rührer extends Pattern implements Serializable{

	private static final long serialVersionUID = 1L;
	private boolean active = false;
	
	
	public Rührer(int x, int y, int id, int xcoor, int ycoor) {
		super(x, y, PatternState.RÜHRER, id, xcoor, ycoor);
		

		//start,end,total,delay
		this.setAnimation(0,7,9,3);
		
	}

	@Override
	public void updateContext() {
		// TODO Auto-generated method stub
		
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public void updatePatternInfo() {
		// XXX Mögliche Attribute: Effizienz, Qualität(Verschmutzung des Rührers etc.)
		
	}

}
