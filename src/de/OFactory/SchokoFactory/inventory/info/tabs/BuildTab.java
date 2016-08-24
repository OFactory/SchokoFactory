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

	//ArrayList<Tuple<PatternState, Integer>> buildings = new ArrayList<Tuple<PatternState,Integer>>();
	ArrayList<BuildingButton> buildings = new ArrayList<BuildingButton>();
	
	public BuildTab(InfoPanel ip, Image img) {
		super(ip, img, "Gebäude");
		
		addBuildings();
		System.out.println(buildings);
	}

	@Override
	public void drawContent(Graphics g) {
		GameFonts.MAIN.drawString(getInfoPanel().getX()+20, getInfoPanel().getY()+20, "Gebäude", Color.black);
		GameFonts.SUB.drawString(getInfoPanel().getX()+20, getInfoPanel().getY()+60, "Kauf alles MAMA!", Color.gray);
		GameFonts.SUB.drawString(getInfoPanel().getX()+20, getInfoPanel().getY()+80, "" + this.getInfoPanel().getScroll(), Color.black);
		
		
		int count = this.getInfoPanel().getY()+120;
		int step = 40;
		
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
		this.addBuilding(PatternState.CHEMIEFABRIK, 50);
		this.addBuilding(PatternState.TANK, 20);
		this.addBuilding(PatternState.RÜHRER, 30);
		this.addBuilding(PatternState.FARM, 50);
		this.addBuilding(PatternState.MOLKEREI, 50);
		this.addBuilding(PatternState.ZUCKERPLANTAGE, 50);
	}
	
	
	public void addBuilding(PatternState state, int price){
		buildings.add(new BuildingButton(0, getInfoPanel(), state, price));
	}

}
