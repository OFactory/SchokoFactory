package de.OFactory.SchokoFactory.inventory.info.tabs;

import java.util.HashMap;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import de.OFactory.SchokoFactory.game.GameFonts;
import de.OFactory.SchokoFactory.game.Pattern;
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
		
		int ml = 10; //margin-left
		
		
		int x = getInfoPanel().getX();
		int y = getInfoPanel().getY();
		Pattern p = MainState.selected_pattern;
		
		GameFonts.SUB.drawString(x + ml, y+20, "Gebäudeinformation", Color.black);
		
		if(MainState.selected_pattern != null){
			GameFonts.MED.drawString(x + ml, y+50, p.getPatternState().getName(), Color.black);
			GameFonts.MED.drawString(x + ml, y+70, p.getId() + ""				, Color.black);
			
			int start = 400;
			int step  = 20;
			HashMap<String, Object> info = p.getPatternInfo();
			//System.out.println(info);
			
			int i = 0;
			for(String s : info.keySet()){
				GameFonts.MED.drawString(x + ml, start + step*i, s + ": " + info.get(s), Color.black);
				i++;
			}
			
			//TODO Pattern Information
			
			double scale = PATTERN_IMAGE_SCALE/MainState.selected_pattern.getCurrentImage().getWidth();
			MainState.selected_pattern.getCurrentImage().getScaledCopy((float) scale).draw(x + 20, y-80);
		} else {
			GameFonts.SUB.drawString(x + 10, y+50, "Kein Gebäude ausgewählt!", Color.black);
		}
		
		
	}

	@Override
	public void updateContent(GameContainer gc) {
		// TODO Auto-generated method stub
		
	}

}
