package de.OFactory.SchokoFactory.inventory.info.tabs;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import de.OFactory.SchokoFactory.game.GameFonts;
import de.OFactory.SchokoFactory.inventory.info.InfoPanel;
import de.OFactory.SchokoFactory.inventory.info.Tab;
import de.OFactory.SchokoFactory.main.MainState;

public class BuildingInfoTab extends Tab{

	public final float PATTERN_IMAGE_SCALE = 200F;

	public BuildingInfoTab(InfoPanel ip, Image img) {
		super(ip, img, "i Gebäudeinformation");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void drawContent(Graphics g) {
		GameFonts.SUB.drawString(getInfoPanel().getX() + 10, getInfoPanel().getY()+20, "Gebäudeinformation", Color.black);
		
		if(MainState.selected_pattern != null){
			GameFonts.MED.drawString(getInfoPanel().getX() + 10, getInfoPanel().getY()+50, MainState.selected_pattern.getPatternState().getName(), Color.black);
			GameFonts.MED.drawString(getInfoPanel().getX() + 10, getInfoPanel().getY()+70, MainState.selected_pattern.getId() + "", Color.black);
			
			//TODO Pattern Information
			
			double scale = PATTERN_IMAGE_SCALE/MainState.selected_pattern.getCurrentImage().getWidth();
			MainState.selected_pattern.getCurrentImage().getScaledCopy((float) scale).draw(getInfoPanel().getX() + 20, getInfoPanel().getY()-80);
		} else {
			GameFonts.SUB.drawString(getInfoPanel().getX() + 10, getInfoPanel().getY()+50, "Kein Gebäude ausgewählt!", Color.black);
		}
		
		
	}

	@Override
	public void updateContent(GameContainer gc) {
		// TODO Auto-generated method stub
		
	}

}
