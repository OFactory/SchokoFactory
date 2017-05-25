package de.OFactory.SchokoFactory.game.patterns;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.newdawn.slick.Image;

import de.OFactory.SchokoFactory.game.Map;
import de.OFactory.SchokoFactory.game.Pattern;
import de.OFactory.SchokoFactory.game.PatternState;
import de.OFactory.SchokoFactory.inventory.Button;
import de.OFactory.SchokoFactory.main.ResourceManager;

public class Chemiefabrik extends Pattern{

	private boolean working = true;
	private int produktion = 100;
	private int einmaligeFabrikkosten = 200;
	private int laufendeFabrikkosten = 50;
	
	private int alter = 0;
	private int effizienz = 100;
	
	
	public Chemiefabrik(Map map, int x, int y, int id, int xcoor, int ycoor) {
		super(map, x, y, PatternState.CHEMIEFABRIK, id, xcoor, ycoor);
		//start,end,total,delay
		setImg(ResourceManager.pttrn_giesser.getSubImage(0,0,709,944));
		setAnimation(0,7,9,3);

	}

	@Override
	public void updateContext() {
		
		//setImg(source.getSubImage(frame*source.getWidth()/9, 0, source.getWidth()/9, source.getHeight()));

	}

	@Override
	public void updatePatternInfo() {
		// XXX Mögliche Attribute: Effizienz, Bedenklichkeit bzgl. "Chemie"
		Button b = new Button(200, 0, 0, 0, 0, "Forschung");
		b.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
							
			}
		});
		clearPatternInfo();
		putPatternInfo("Forschungsbutton", b);
	}

}