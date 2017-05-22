package de.OFactory.SchokoFactory.inventory.info;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import de.OFactory.SchokoFactory.main.Drawable;
import de.OFactory.SchokoFactory.main.MainState;
import de.OFactory.SchokoFactory.main.Updateable;

public class ScienceScreen implements Drawable, Updateable{

	private boolean active;
	private int width, height;
	
	private ArrayList<Research> researches;
	
	public ScienceScreen(int width, int height){
		setWidth(width);
		setHeight(height);
		
		initResearches();
		
	}
	
	/** Initialisiert alle Researches.
	 *  Hier bitte Änderungen vornehmen!
	 */
	public void initResearches(){
		Research erdbeeraroma = new Research("Erdbeeraroma",
				"Das leckere Erdbeeraroma erhöht die Geschmackswerte und kommt super in den Sommmerzeiten an!",
				null,
				5, 
				new ActionListener() {
					
					public void actionPerformed(ActionEvent e) {
						MainState.p.setQualitaet(MainState.p.getQualitaet()+0.001); 
						// TODO Diese Zeile macht noch überhaupt keinen Sinn! Bitte überarbeiten eventuell verschiedene Qualitätsaspekte hinzufügen
						// VORSICHT: Das ActionEvent wird jeden Tag gecallt!
					}
				});
		
		researches.add(erdbeeraroma);
		
		Research weisse_schokolade = new Research("Weiße Schokolade",
				"Die Weiße Schokolade erhöht die Geschmackswerte sehr stark. Boni für Feiertage haben jedoch keinen Effekt, da an Feiertagen hauptsächlich nur dunkle Schokolade gegessen wird.",
				(ArrayList<Research>) Arrays.asList(erdbeeraroma),
				15,
				new ActionListener() {
					
					public void actionPerformed(ActionEvent e) {
						MainState.p.setQualitaet(MainState.p.getQualitaet()+0.005); 
						// TODO Siehe oben
						
					}
				});
		
		researches.add(weisse_schokolade);
		
		Research bessere_giessschablonen = new Research("Bessere Gießschablonen", 
				"Durch die Verbesserung der Gießschablonen ist die Form der Schokolade besser erhalten und die Qualität steigt.",
				null,
				5, 
				new ActionListener() {
					
					public void actionPerformed(ActionEvent e) {
						MainState.p.setQualitaet(MainState.p.getQualitaet()+0.001); 
						// TODO Siehe oben
						
					}
				});
		
		researches.add(bessere_giessschablonen);
	}
	
	public void update(GameContainer gc) {
		// TODO Auto-generated method stub
		
	}

	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

}
