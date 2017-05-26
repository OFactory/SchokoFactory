package de.OFactory.SchokoFactory.main;

import org.newdawn.slick.Input;
import org.newdawn.slick.KeyListener;
import org.newdawn.slick.MouseListener;

import de.OFactory.SchokoFactory.game.GameSave;
import de.OFactory.SchokoFactory.game.PatternState;

public class MainStateListener implements MouseListener, KeyListener{

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
		/* ZOOM DEAKTIVIERT
		//Mauszeiger im richtigem Bereich? > Fenster
		if((in.getMouseX() > 0 && in.getMouseX() < MainState.gc.getWidth() - MainState.ip.getWidth()) &&
				in.getMouseY() < MainState.gc.getHeight() && in.getMouseY() > MainState.pile.getHeight() ){
			
			//Zoomfunktion //Zooom im richtigem Berreich?
			if(MainState.curpatternscale + change/GameSettings.ZOOM_STEP >= GameSettings.ZOOM_MIN &&
					MainState.curpatternscale + change/GameSettings.ZOOM_STEP <= GameSettings.ZOOM_MAX){
				
				double delta_scale = change/GameSettings.ZOOM_STEP;
				
				MainState.curpatternscale += delta_scale; //ZOOM IN/OUT
				MainState.field.zoomMap(
						delta_scale,
						in.getMouseX(),
						in.getMouseY()); //Map neu anordnen 
				
				// Cam_Pos ändern
				//MainState.view_dimensions.setBounds(x, y, width, height);

			}
		}
		*/
		
		// InfoPanel Scroll
		
		//Mauszeiger im richtigen Bereich? > InfoPanel
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

	public void keyPressed(int key, char c) {
		//System.out.println(key+";"+c);

		switch(c) { 
		case 't':
			MainState.curpatternstate = PatternState.TANK;
			System.out.println("t");
			break;
		case 'w':
			MainState.curpatternstate = PatternState.WIESE;
			break;
		case 'c':
			MainState.curpatternstate = PatternState.CHEMIEFABRIK;
			break;
		case 'r':
			MainState.curpatternstate = PatternState.RÜHRER;
			break;
		case 'l':
			MainState.curpatternstate = PatternState.LAGER;
			break;
		case 'm':
			MainState.curpatternstate = PatternState.MOLKEREI;
			break;
		case 'f':
			MainState.curpatternstate = PatternState.FARM;
			break;
		case 'g':
			MainState.curpatternstate = PatternState.GIEßER;
			break;
		case 'ö':
			MainState.curpatternstate = PatternState.LABOR;
			break;
		case 'k':
			MainState.curpatternstate = PatternState.FELD;
			break;
		case 'q':
			MainState.curpatternstate = PatternState.GEWÄCHSHAUS;
			break;
		case 'n':
			MainState.curpatternstate = null;
			break;
		
		case 'p':
			MainState.cam_pos.setLocation(0, 0);
			break;
		case '2':
			MainState.molten_chokolate += 100;
			break;
			
		case 'e':
			MainState.ecoscreen.toggle();
			break;
			
		case ' ':
			MainState.run = !MainState.run;
			break;
			
		case 's':
			GameSave.saveMainState();
			break;
			
		default:
			break;
		
		}
	}

	public void keyReleased(int key, char c) {
		// TODO Auto-generated method stub
		
	}
}
