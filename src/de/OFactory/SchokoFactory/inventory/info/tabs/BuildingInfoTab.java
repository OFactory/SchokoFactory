package de.OFactory.SchokoFactory.inventory.info.tabs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import de.OFactory.SchokoFactory.game.GameFonts;
import de.OFactory.SchokoFactory.game.Pattern;
import de.OFactory.SchokoFactory.game.PatternState;
import de.OFactory.SchokoFactory.game.patterns.Gie�er;
import de.OFactory.SchokoFactory.inventory.Button;
import de.OFactory.SchokoFactory.inventory.info.InfoPanel;
import de.OFactory.SchokoFactory.inventory.info.Tab;
import de.OFactory.SchokoFactory.main.MainState;

public class BuildingInfoTab extends Tab{

	public final float PATTERN_IMAGE_SCALE = 200F;
	private Button work_button;

	public BuildingInfoTab(InfoPanel ip, Image img) {
		super(ip, img, "i Geb�udeinformation");
		// TODO Auto-generated constructor stub
		
		work_button = new Button(15, 1050, 500, 250, 50, "Aus/An- schalten", 0);
		work_button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				System.out.println(e.getActionCommand());
				
				if(MainState.selected_pattern != null && MainState.selected_pattern.getPatternState() == PatternState.GIE�ER){
					Gie�er wa = (Gie�er) MainState.selected_pattern;
					wa.setWorking(!wa.isWorking());
				}
			}
		});
		
	}
	

	@Override
	public void drawContent(Graphics g) {
		
		int ml = 10; //margin-left
		
		
		int x = getInfoPanel().getX();
		int y = getInfoPanel().getY();
		Pattern p = MainState.selected_pattern;
		
		GameFonts.SUB.drawString(x + ml, y+20, "Geb�udeinformation", Color.black);
		
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
			
			//System.out.println(MainState.selected_pattern.getPatternState().isWorking());
			
			if(MainState.selected_pattern.getPatternState().isWorking()){
				work_button.draw(g);
			}
			
		} else {
			GameFonts.SUB.drawString(x + 10, y+50, "Kein Geb�ude ausgew�hlt!", Color.black);
		}
		
		
	}

	@Override
	public void updateContent(GameContainer gc) {
		if(MainState.selected_pattern != null && MainState.selected_pattern.getPatternState().isWorking()){
			work_button.update(gc.getInput());
		}
		
	}

}
