package de.OFactory.SchokoFactory.game.patterns;

import java.util.ArrayList;

import de.OFactory.SchokoFactory.game.Map;
import de.OFactory.SchokoFactory.game.Pattern;
import de.OFactory.SchokoFactory.game.PatternFrame;
import de.OFactory.SchokoFactory.game.PatternState;

public class Lagerhalle extends Pattern{
	public Lagerhalle(Map map, int x, int y, int id) {
		super(map, x, y, PatternState.LAGERHALLE, id);
		
		this.setPatternFrame(PatternFrame.LAGERHALLE_NORMAL);
	}

	@Override
	public void updateContext() {
		
		//Testet ob Links oben auch eine Lagerhalle steht
		
		
	}
	
  /**
    * 
    * Diese Methode gibt die unmittelbaren Nachbarn eines Feldes zurück.
    * Die Felder werden nach Himmelsrichtungen(no:Nordosten,so:Suedosten,sw:Suedwesten,nw:Nordwesten)
    * geordnet.   
    * 
    * 
    * @return ArrayList<Pattern> unmittelbare Nachbarn
    */
	public ArrayList<Pattern> getPatternNeighbours(){
	   
		ArrayList<Pattern> ps = new ArrayList<Pattern>();
	    int no, so, sw, nw;
	    int grid_width = this.getMap().getWidth();  //grid_width muss richtig von GamePanel übergeben werden um diese Funktion zum Erfolg zu bringen.
	    boolean offsetted = false;
	    no = this.getId() - grid_width;   
	    so = this.getId() + grid_width;
	    sw = this.getId() + grid_width - 1;
	    nw = this.getId() - grid_width - 1;
	    
	    if (this.getId() / 50 % 2 == 0) {
	      offsetted = true;
	    }
	    
	    System.out.println("[Pattern] Eingerückt: " + offsetted);
	    if (offsetted) {  
	      no += 1;
	      so += 1;
	      sw += 1;
	      nw += 1;
	    }
	    
	    int[] neighbours = {no, so, sw, nw};
	    //System.out.println((char)neighbours);
	    System.out.print("[Pattern] Die Nachbarn dieses Feldes sind: ");
	    for (int i = 0; i <= 3 ; ++i){ 		    	
	    	for(Pattern p : this.getMap()){
				
				if(p.getId() == neighbours[i]){
					ps.add(p);
				}
				
			}
		}

	    return ps;
	  }
}
