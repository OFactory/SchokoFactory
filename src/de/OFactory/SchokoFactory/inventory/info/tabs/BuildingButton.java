package de.OFactory.SchokoFactory.inventory.info.tabs;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import de.OFactory.SchokoFactory.game.GameFonts;
import de.OFactory.SchokoFactory.game.PatternState;
import de.OFactory.SchokoFactory.inventory.Button;
import de.OFactory.SchokoFactory.inventory.info.InfoPanel;
import de.OFactory.SchokoFactory.main.MainState;

public class BuildingButton extends Button{
	
	
	private final static int BUTTON_HEIGHT = 80;
	
	private InfoPanel ip;
	private PatternState ps;

	public BuildingButton(int ID, InfoPanel ip, PatternState ps) {
		super(ID, ip.getX(), 0, ip.getWidth(), BUTTON_HEIGHT, ps.getName(), 0);
		// TODO Auto-generated constructor stub
		
		this.setInfoPanel(ip);
		this.ps = ps;
		this.clicked_color		    = new Color(100, 100, 100);
		this.hovered_color			= new Color(150, 150, 255);
		this.normal_color  			= new Color( 65, 105, 225);
		new Color(255, 105,  65);
	}

	/**
	 * Buildingbutton UPDATE
	 * 
	 */
	@Override
	public void update(Input in){
		
		
		if(this.shape.contains(in.getMouseX(), in.getMouseY())) {
			this.hovered = true;
		} else {
			this.hovered = false;
		}
		
		
		if(this.hovered){
			if(in.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)){
				//System.out.println(this);
				MainState.curpatternstate = ps;
			}
		}
		
		
		if(MainState.curpatternstate == ps)
			this.clicked = true;
		else
			this.clicked = false;
		
		
		
	}
	
	/**
	 * Buildingbutton DRAW
	 * 
	 */
	@Override
	public void draw(Graphics g){
		
		Color dsp_color = new Color(0, 0, 0);
		
		if(clicked) {
			dsp_color = clicked_color;
			
			if(hovered){ // Durschnittsfarbe
				dsp_color = new Color((clicked_color.getRed()+hovered_color.getRed())/2, (clicked_color.getGreen()+hovered_color.getGreen())/2, (clicked_color.getBlue()+hovered_color.getBlue())/2);
			}
		} else if(this.hovered)
			dsp_color = hovered_color;
		else
			dsp_color = normal_color;
		
		if(MainState.p.getMoney() < this.getPatternState().getPrice()){
			dsp_color = Color.red.darker();
		}
		
		
		g.setColor(dsp_color);
		
		g.fill(this.shape);
		
		g.setColor(Color.black);
		g.draw(this.shape);
		
		
		// On TOP
		
		//Siehe DisplayFrame in PatternFrame.java
		MainState.patternimg[ps.getDisplayFrame()].getScaledCopy(0.3F).draw(x + 15, y-50);
		
		
		
		
		if(this.content != "") {
			GameFonts.SUB.drawString( x + 100,
					y + (int) (height*0.3),
					content,
					new Color(256 - g.getColor().getRed(), 256 - g.getColor().getGreen(), 256 - g.getColor().getBlue()));
		}
		
		if(this.getPatternState().getPrice() > 0){
			
			String str_price = "-" + getPatternState().getPrice() + "$";
			
			GameFonts.SUB.drawString(x + width - GameFonts.SUB.getWidth(str_price) - 5,
					y + height - GameFonts.SUB.getHeight() - 5,
					str_price,
					Color.red);
		}
		
	}

	public PatternState getPatternState() {
		return ps;
	}

	public void setPatternState(PatternState ps) {
		this.ps = ps;
	}

	/**
	 * @return the ip
	 */
	public InfoPanel getInfoPanel() {
		return ip;
	}

	/**
	 * @param ip the ip to set
	 */
	public void setInfoPanel(InfoPanel ip) {
		this.ip = ip;
	}
}
