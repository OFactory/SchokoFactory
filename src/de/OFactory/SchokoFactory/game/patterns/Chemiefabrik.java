package de.OFactory.SchokoFactory.game.patterns;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import de.OFactory.SchokoFactory.game.Pattern;
import de.OFactory.SchokoFactory.game.PatternState;
import de.OFactory.SchokoFactory.inventory.Button;


public class Chemiefabrik extends Pattern implements Serializable{

	private static final long serialVersionUID = 2L;

	public Chemiefabrik(int x, int y, int id, int xcoor, int ycoor) {
		super(x, y, PatternState.CHEMIEFABRIK, id, xcoor, ycoor);
		//start,end,total,delay

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