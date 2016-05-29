package de.OFactory.SchokoFactory.game.patterns;


import de.OFactory.SchokoFactory.game.Pattern;
import de.OFactory.SchokoFactory.game.PatternFrame;
import de.OFactory.SchokoFactory.game.PatternState;
import de.OFactory.SchokoFactory.main.MainState;

public class Tank extends Pattern{
	
	private int capacity = 1000; // 1000l Startkapatzität
	private int stored_amount = 0; // stored anzahl

	public Tank(int x, int y, int id) {
		super(x, y, PatternState.TANK, id);
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
		

		if(this.stored_amount < 0.2*this.capacity){
			this.setPatternFrame(PatternFrame.TANK_0);
		} else if (this.stored_amount < 0.4*this.capacity) {
			this.setPatternFrame(PatternFrame.TANK_20);
		} else if (this.stored_amount < 0.6*this.capacity) {
			this.setPatternFrame(PatternFrame.TANK_40);
		} else if (this.stored_amount < 0.8*this.capacity) {
			this.setPatternFrame(PatternFrame.TANK_60);
		} else if (this.stored_amount < 1*this.capacity) {
			this.setPatternFrame(PatternFrame.TANK_80);
		} else if (this.stored_amount == 1*this.capacity){
			this.setPatternFrame(PatternFrame.TANK_100);
		}
		
		
			
	}

}
