package de.OFactory.SchokoFactory.game.patterns;

import java.awt.Point;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Polygon;

import de.OFactory.SchokoFactory.game.GameSettings;
import de.OFactory.SchokoFactory.game.GameUtils;
import de.OFactory.SchokoFactory.game.Pattern;
import de.OFactory.SchokoFactory.game.PatternFrame;
import de.OFactory.SchokoFactory.game.PatternState;
import de.OFactory.SchokoFactory.main.MainState;
import de.OFactory.SchokoFactory.main.ResourceManager;

public class Wiese extends Pattern{
	
	public int type = 0;
	

	public Wiese(int x, int y, int id) {
		super(x, y, PatternState.WIESE, id);
		
		
		this.type = GameUtils.randInt(0, 3);
		this.setPatternFrame(36 + type);
		
		
		
		
	}
	

	@Override
	public void updateContext() {
		
		
		
	}
	
	

}
