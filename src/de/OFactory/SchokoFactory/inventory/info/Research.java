package de.OFactory.SchokoFactory.inventory.info;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import org.newdawn.slick.GameContainer;

import de.OFactory.SchokoFactory.main.Updateable;

/**
 * Warnung: Research zeichnet sich nicht selber sondern wird vom
 * überstehenden Sciencescreen gezeichnet!
 * 
 * @author Maximilian
 *
 */
public class Research implements Updateable{
	
	private String name;
	private String description;
	private ArrayList<Research> dependencies;
	private int cost;
	private ActionListener effect;
	
	private boolean active  = false;
	private boolean buyable = false;
	
	
	public Research(String name, String description, ArrayList<Research> dependencies, int cost, ActionListener effect){
		setName(name);
		setDescription(name);
		setDependencies(dependencies);
		setCost(cost);
		setEffect(effect);
	}
	
	/**
	 * Update-Funktion.
	 * Hauptsächliche Aufgabe:
	 * Nachschauen, ob alle Abhängigkeiten erfüllt sind und buyable umstellen.
	 * @param gc Gamecontainer
	 */
	public void update(GameContainer gc) {

		setBuyable(true);
		
		if(dependencies != null){
			for(Research r : getDependencies()){
				if (!r.isActive()) {
					setBuyable(false);
					break;
				}
			}
		}

	}
	


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public ArrayList<Research> getDependencies() {
		return dependencies;
	}


	public void setDependencies(ArrayList<Research> dependencies) {
		this.dependencies = dependencies;
	}


	public int getCost() {
		return cost;
	}


	public void setCost(int cost) {
		this.cost = cost;
	}


	public ActionListener getEffect() {
		return effect;
	}


	public void setEffect(ActionListener effect) {
		this.effect = effect;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isBuyable() {
		return buyable;
	}

	public void setBuyable(boolean buyable) {
		this.buyable = buyable;
	}

	
}
