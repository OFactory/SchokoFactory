package de.OFactory.SchokoFactory.main;

import org.newdawn.slick.Input;
import org.newdawn.slick.MouseListener;

import de.OFactory.SchokoFactory.game.Pattern;

public class MainStateListener implements MouseListener{

	//TODO Mausvariablen
	
	
	public void setInput(Input input) {
		// TODO Auto-generated method stub
		
	}

	public boolean isAcceptingInput() {
		// TODO Auto-generated method stub
		return false;
	}

	public void inputEnded() {
		// TODO Auto-generated method stub
		
	}

	public void inputStarted() {
		// TODO Auto-generated method stub
		
	}

	public void mouseWheelMoved(int change) {
		System.out.println(change);
		MainState.curpatternscale += change/300F;
		MainState.field.restructureMap(MainState.curpatternscale);
		
		System.out.println(MainState.curpatternscale);
		
		MainState.patternimg = ResourceManager.loadPics(ResourceManager.loadImage("res/img/assets/texture/patterns/patterns.png").getScaledCopy(MainState.curpatternscale), 50); //Bild splitten -> Einzelne Bilder (Image[])
		for(Pattern p : MainState.field){
			p.updateTexture();
		}
		
	}

	public void mouseClicked(int button, int x, int y, int clickCount) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(int button, int x, int y) {
		// TODO Auto-generated method stub
		System.out.println("Pressed:"+button+" "+x+" "+y);
		
	}

	public void mouseReleased(int button, int x, int y) {
		// TODO Auto-generated method stub
		
	}

	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
		// TODO Auto-generated method stub
		
	}

	public void mouseDragged(int oldx, int oldy, int newx, int newy) {
		// TODO Auto-generated method stub
		
	}
}
