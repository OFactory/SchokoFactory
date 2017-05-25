package de.OFactory.SchokoFactory.game.patterns;

import de.OFactory.SchokoFactory.game.Map;
import de.OFactory.SchokoFactory.game.Pattern;
import de.OFactory.SchokoFactory.game.PatternState;
import de.OFactory.SchokoFactory.main.ResourceManager;

public class R�hrer extends Pattern{

	private boolean active = false;
	
	
	public R�hrer(Map map, int x, int y, int id, int xcoor, int ycoor) {
		super(map, x, y, PatternState.R�HRER, id, xcoor, ycoor);
		
		setImg(ResourceManager.pttrn_giesser.getSubImage(0,0,709,944));
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
		// XXX M�gliche Attribute: Effizienz, Qualit�t(Verschmutzung des R�hrers etc.)
		
	}

}
