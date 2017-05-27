package de.OFactory.SchokoFactory.inventory;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;

import de.OFactory.SchokoFactory.game.GameFonts;
import de.OFactory.SchokoFactory.game.GameUtils;
import de.OFactory.SchokoFactory.main.ResourceManager;

public class MapButton extends Button{

	private Color color;
	private boolean last;
	
	public MapButton(int ID, int x, int y, int width, int height, String content) {
		super(ID, x, y, width, height, content);
		
		color = new Color(GameUtils.randInt(0, 255)/255F, GameUtils.randInt(0, 255)/255F, GameUtils.randInt(0, 255)/255F, 0.6F);
		shape = new Rectangle(x, y, width, height);
		System.out.println(color);
	}
	
	@Override
	public void draw(Graphics g){
		if(hovered){
			g.setColor(color);
			g.fillRect(x, y, width, height);
		} else {
			g.setColor(new Color(color.r, color.g, color.b, 0.3F));
			g.fillRect(x, y, width, height);
		}
		g.setColor(Color.white);
		GameFonts.ALTE_HAAS_50.drawString(x+10, y, content);
		
	}
	
	@Override
	public void update(Input in){
		super.update(in);
		
		
		
		
		if(hovered && hovered != last){
			ResourceManager.snd_hover.play();
		}
		
		
		last = hovered;
	}

}
