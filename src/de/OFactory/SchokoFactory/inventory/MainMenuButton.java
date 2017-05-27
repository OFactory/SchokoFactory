package de.OFactory.SchokoFactory.inventory;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;

import de.OFactory.SchokoFactory.game.GameFonts;
import de.OFactory.SchokoFactory.main.ResourceManager;

public class MainMenuButton extends Button{

	private boolean last;
	
	public MainMenuButton(int ID, int x, int y, int width, int height, String content) {
		super(ID, x, y, width, height, content);
		
		this.shape = new Rectangle(x, y, width, height);
	}
	
	@Override
	public void draw(Graphics g){
		if(hovered){
			g.setColor(new Color(50,216,230, 0.5F));
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
