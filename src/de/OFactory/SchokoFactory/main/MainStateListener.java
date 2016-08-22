package de.OFactory.SchokoFactory.main;

import org.newdawn.slick.Input;
import org.newdawn.slick.MouseListener;

import de.OFactory.SchokoFactory.game.GameSettings;

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
		
		Input in = MainState.gc.getInput();
		
		
		// Hauptspiel ZOOM:
		
		//Mauszeiger im richtigem Bereich? > Fenster
		if((in.getMouseX() > 0 && in.getMouseX() < MainState.gc.getWidth() - MainState.ip.getWidth()) &&
				in.getMouseY() < MainState.gc.getHeight() && in.getMouseY() > MainState.pile.getHeight() ){
			
			//Zoomfunktion //Zooom im richtigem Berreich?
			if(MainState.curpatternscale + change/GameSettings.ZOOM_STEP >= GameSettings.ZOOM_MIN &&
					MainState.curpatternscale + change/GameSettings.ZOOM_STEP <= GameSettings.ZOOM_MAX){
				
				MainState.curpatternscale += change/GameSettings.ZOOM_STEP; //ZOOM IN/OUT
				MainState.field.zoomMap(
						MainState.curpatternscale,
						in.getMouseX(),
						in.getMouseY()); //Map neu anordnen
				//TODO Zoomfunktion verbessern!
			}
		}
		
		// InfoPanel Scroll
		
		//Mauszeuger im richtigen Bereich? > InfoPanel
		if((in.getMouseX() > MainState.gc.getWidth() - MainState.ip.getWidth() && in.getMouseX() < MainState.gc.getWidth()) &&
				in.getMouseY() > 0 && in.getMouseY() < MainState.ip.getHeight()) {
			
			MainState.ip.addScroll( - change); //change + = nach oben | change - = nach unten
			
			
		}
		
		
		
	}

	public void mouseClicked(int button, int x, int y, int clickCount) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(int button, int x, int y) {
		// TODO Auto-generated method stub
		
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
