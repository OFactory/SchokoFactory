package de.OFactory.SchokoFactory.game.patterns;



import java.io.Serializable;

import de.OFactory.SchokoFactory.game.Pattern;
import de.OFactory.SchokoFactory.game.PatternState;
import de.OFactory.SchokoFactory.main.MainState;

public class Tank extends Pattern implements Serializable{

	private static final long serialVersionUID = 0L;
	private int capacity = 1000; // 1000l Startkapatzität
	private int stored_amount = 0; // stored anzahl {l}

	public Tank(int x, int y, int id, int xcoor, int ycoor) {
		super(x, y, PatternState.TANK, id, xcoor, ycoor);

		
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
			setFrame(0);
		} else if (this.stored_amount < 0.4*this.capacity) { // 21-40%
			setFrame(1);
		} else if (this.stored_amount < 0.6*this.capacity) { // 41-60%
			setFrame(2);
		} else if (this.stored_amount < 0.8*this.capacity) { // 61-80%
			setFrame(3);
		} else if (this.stored_amount < 1*  this.capacity) { // 81-99%
			setFrame(4);
		} else if (this.stored_amount == 1* this.capacity) { // 100%
			setFrame(5);
		}
		
		
			
	}


	
	@Override
	public void updatePatternInfo() {
		this.putPatternInfo("Kapazität", stored_amount + "/" + capacity);
		
	}

}
