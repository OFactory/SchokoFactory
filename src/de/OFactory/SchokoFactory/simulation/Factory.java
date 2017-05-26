/**
 * 
 */
package de.OFactory.SchokoFactory.simulation;

import de.OFactory.SchokoFactory.game.Pattern;
import de.OFactory.SchokoFactory.game.Workable;
import de.OFactory.SchokoFactory.main.Daily;
import de.OFactory.SchokoFactory.main.MainState;

/**
 * Klasse, die die Abl‰ufe der Produktion der Fabrik kontrolliert. 
 * @author Hans Olischl‰ger
 *
 */
public class Factory implements Daily{
	
	

	public Factory() {
		MainState.dailys.add(this);
	}

	public void day() {
		run();
		
    	MainState.p.setPreis(MainState.inpreis);
    	MainState.p.investAdverts(MainState.inwerbung);
    	MainState.p.investQuality(MainState.inqualitaet);
	}
	
	public void run() {
		// t‰glicher Ablauf bei der Fabrik
		
		// aktuell vereinfachte Simulation:
		// 		Gieﬂer produzieren Tafeln aus Geld
		
		// durch alle arbeitenden Patterns iterieren und work() aufrufen
		for(Pattern p : MainState.field){
			if(p instanceof Workable){ // Wenn Pattern etwas arbeiten kann
				((Workable) p).work();
			} 
				
				
		}
		
	}

}
