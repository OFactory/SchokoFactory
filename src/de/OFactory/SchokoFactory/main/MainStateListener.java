package de.OFactory.SchokoFactory.main;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseListener;

public class MainStateListener implements MouseListener{
	@Override
	public void mouseWheelMoved(mouseWheelEvent e){
		MainState.curScale = getWheelRotation();
	}
}
