package de.OFactory.SchokoFactory.game.patterns;


import de.OFactory.SchokoFactory.game.Pattern;
import de.OFactory.SchokoFactory.game.PatternFrame;
import de.OFactory.SchokoFactory.game.PatternState;
import de.OFactory.SchokoFactory.main.MainState;

public class Tank extends Pattern{
	
	private int capacity = 1000; // 1000l Startkapatzität
	private int stored_amount; // stored anzahl

	public Tank(int x, int y, int id) {
		super(x, y, PatternState.TANK, id);
		this.setPatternFrame(PatternFrame.TANK_0);
		
	}

	@Override
	public void updateContext() {
		if( MainState.free_molten_chokolate - this.capacity >= 0){
			MainState.free_molten_chokolate -= this.capacity;
			this.stored_amount = this.capacity;
			
		} else {
			if( MainState.free_molten_chokolate <= capacity){
				this.stored_amount = MainState.free_molten_chokolate;
				MainState.free_molten_chokolate = 0;
			}
		}
		
		double fill = this.stored_amount/this.capacity;
		
		if(fill < 0.20){
			this.setPatternFrame(PatternFrame.TANK_0);
		} else if (fill < 0.40) {
			this.setPatternFrame(PatternFrame.TANK_20);
		} else if (fill < 0.60) {
			this.setPatternFrame(PatternFrame.TANK_40);
		} else if (fill < 0.80) {
			this.setPatternFrame(PatternFrame.TANK_60);
		} else if (fill < 1) {
			this.setPatternFrame(PatternFrame.TANK_80);
		} else if (fill == 1){
			this.setPatternFrame(PatternFrame.TANK_100);
		}
		
		
			
	}

}
