package de.OFactory.SchokoFactory.game.patterns;


import de.OFactory.SchokoFactory.game.Map;
import de.OFactory.SchokoFactory.game.Pattern;
import de.OFactory.SchokoFactory.game.PatternFrame;
import de.OFactory.SchokoFactory.game.PatternState;
import de.OFactory.SchokoFactory.main.MainState;

public class Tank extends Pattern{
	
	private int capacity = 1000; // 1000l Startkapatzitšt
	private int stored_amount = 0; // stored anzahl {l}

	public Tank(Map map, int x, int y, int id, int xcoor, int ycoor) {
		super(map, x, y, PatternState.TANK, id, xcoor, ycoor);
		this.setPatternFrame(PatternFrame.TANK_0);
		
	}
	
	
	
	@Override
	public void updateContext() {
		
		
		
		if ( MainState.free_molten_chokolate == 0){}
		else if( MainState.free_molten_chokolate >= this.capacity-this.stored_amount){		// wenn free_molten_chokolate > this.capacity
			MainState.free_molten_chokolate -= this.capacity-this.stored_amount;
			this.stored_amount = this.capacity;
			
		} else {
			if( MainState.free_molten_chokolate < this.capacity-this.stored_amount){			
				this.stored_amount += MainState.free_molten_chokolate;
				MainState.free_molten_chokolate = 0;

			}
		}
		
		

		if(		   this.stored_amount < 0.2*this.capacity) { // 1-20%
			this.setPatternFrame(PatternFrame.TANK_0  );
		} else if (this.stored_amount < 0.4*this.capacity) { // 21-40%
			this.setPatternFrame(PatternFrame.TANK_20 );
		} else if (this.stored_amount < 0.6*this.capacity) { // 41-60%
			this.setPatternFrame(PatternFrame.TANK_40 );
		} else if (this.stored_amount < 0.8*this.capacity) { // 61-80%
			this.setPatternFrame(PatternFrame.TANK_60 );
		} else if (this.stored_amount < 1*  this.capacity) { // 81-99%
			this.setPatternFrame(PatternFrame.TANK_80 );
		} else if (this.stored_amount == 1* this.capacity) { // 100%
			this.setPatternFrame(PatternFrame.TANK_100);
		}
		
		
			
	}


	
	@Override
	public void updatePatternInfo() {
		this.putPatternInfo("Kapazitšt", stored_amount + "/" + capacity);
		
	}

}
