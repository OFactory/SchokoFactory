package de.OFactory.SchokoFactory.inventory.info.tabs;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import de.OFactory.SchokoFactory.game.GameFonts;
import de.OFactory.SchokoFactory.game.PatternState;
import de.OFactory.SchokoFactory.inventory.info.InfoPanel;
import de.OFactory.SchokoFactory.inventory.info.Tab;

public class BuildTab extends Tab{

	ArrayList<BuildingButton> buildings = new ArrayList<BuildingButton>();
	
	public BuildTab(InfoPanel ip, Image img) {
		super(ip, img, "Geb�ude");
		
		addBuildings();
	}

	@Override
	public void drawContent(Graphics g) {
		GameFonts.MAIN.drawString(getInfoPanel().getX()+20, getInfoPanel().getY()+50, "Geb�ude", Color.black);
		//GameFonts.SUB.drawString(getInfoPanel().getX()+20, getInfoPanel().getY()+60, "Kauf alles MAMA!", Color.gray);
		//GameFonts.SUB.drawString(getInfoPanel().getX()+20, getInfoPanel().getY()+80, "" + this.getInfoPanel().getScroll(), Color.black);
		
		
		int count = this.getInfoPanel().getY()+105;
		int step = 80;
		
		for(BuildingButton b : buildings){
			b.setY(count);
			b.draw(g);
			count += step;
		}
		
	}

	@Override
	public void updateContent(GameContainer gc) {
		for(BuildingButton b : buildings){
			b.update(gc.getInput());
		}
		
	}
	
	private void addBuildings(){
		this.addBuilding(PatternState.CHEMIEFABRIK);
		this.addBuilding(PatternState.TANK);
		this.addBuilding(PatternState.R�HRER);
		this.addBuilding(PatternState.HOF);
		this.addBuilding(PatternState.MOLKEREI);
		this.addBuilding(PatternState.FARM);
		this.addBuilding(PatternState.FELD);
		this.addBuilding(PatternState.LAGER);
		this.addBuilding(PatternState.PRB�RO);
		this.addBuilding(PatternState.GIE�ER);
		this.addBuilding(PatternState.LABOR);
	}
	
	
	public void addBuilding(PatternState state){
		buildings.add(new BuildingButton(0, getInfoPanel(), state));
	}
	
	

}
