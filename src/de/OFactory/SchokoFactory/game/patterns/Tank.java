package de.OFactory.SchokoFactory.game.patterns;


import org.newdawn.slick.Image;

import de.OFactory.SchokoFactory.game.Map;
import de.OFactory.SchokoFactory.game.Pattern;
import de.OFactory.SchokoFactory.game.PatternState;
import de.OFactory.SchokoFactory.main.MainState;

public class Tank extends Pattern{
	
	private int capacity = 1000; // 1000l Startkapatzität
	private int stored_amount = 0; // stored anzahl {l}

	public Tank(Map map, int x, int y, int id, int xcoor, int ycoor) {
		super(map, x, y, PatternState.TANK, id, xcoor, ycoor);

		
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
		
		
		Image source = this.getPatternState().getSource();
		if(		   this.stored_amount < 0.2*this.capacity) { // 1-20%
			setImg(source.getSubImage(0*source.getWidth()/6, 0, source.getWidth()/6, source.getHeight()));
		} else if (this.stored_amount < 0.4*this.capacity) { // 21-40%
			setImg(source.getSubImage(1*source.getWidth()/6, 0, source.getWidth()/6, source.getHeight()));
		} else if (this.stored_amount < 0.6*this.capacity) { // 41-60%
			setImg(source.getSubImage(2*source.getWidth()/6, 0, source.getWidth()/6, source.getHeight()));
		} else if (this.stored_amount < 0.8*this.capacity) { // 61-80%
			setImg(source.getSubImage(3*source.getWidth()/6, 0, source.getWidth()/6, source.getHeight()));
		} else if (this.stored_amount < 1*  this.capacity) { // 81-99%
			setImg(source.getSubImage(4*source.getWidth()/6, 0, source.getWidth()/6, source.getHeight()));
		} else if (this.stored_amount == 1* this.capacity) { // 100%
			setImg(source.getSubImage(5*source.getWidth()/6, 0, source.getWidth()/6, source.getHeight()));
		}
		
		
			
	}


	
	@Override
	public void updatePatternInfo() {
		this.putPatternInfo("Kapazität", stored_amount + "/" + capacity);
		
	}

}
