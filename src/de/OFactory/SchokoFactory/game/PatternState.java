package de.OFactory.SchokoFactory.game;

import java.util.HashMap;

public enum PatternState {
	TANK        	(0L, "Tank",		  PatternFrame.TANK_0),
	RÜHRER  		(1L, "Rührer", 		  PatternFrame.RÜHRER_S),
	CHEMIEFABRIK	(2L, "Chemiefabrik",  PatternFrame.CHEMIE_S),
	GIEßER			(3L, "Gießer", 		  PatternFrame.GIEßER_S),
	LAGERHALLE		(4L, "Lagerhalle", 	  PatternFrame.LAGERHALLE_NORMAL),
	MOLKEREI		(5L, "Molkerei",	  PatternFrame.MOLKEREI_MODERN),
	FARM			(6L, "Farm", 		  PatternFrame.FARM),
	WEIZENFELD		(7L, "Weizenfeld", 	  PatternFrame.TANK_0), //TODO BILDER
	KAKAOPLANTAGE	(8L, "Kakaoplantage", PatternFrame.TANK_0), //TODO BILDER
	ZUCKERPLANTAGE	(9L, "Zuckerplantage",PatternFrame.TANK_0), //TODO BILDER
	WIESE			(10L, "Wiese",		  PatternFrame.WIESE_1);
	
	private String name;
	private Long id;
	private int displayframe;
	
	private PatternState(Long id, String name, int displayframe){
		this.name = name;
		this.id = id;
		this.setDisplayFrame(displayframe);
	}
	
	private static final HashMap<Long, PatternState> byId = new HashMap<Long, PatternState>();
    static {
        for (PatternState e : PatternState.values()) {
            if (byId.put(e.getId(), e) != null) {
                throw new IllegalArgumentException("ERROR <002>: Duplicate ID-" + e.getId());
            }
        }
    }

    public static PatternState getById(Long id) {
        return byId.get(id);
    }

	
	public String getName(){
		return this.name;
	}
	
	public long getId(){
		return this.id;
	}


	/**
	 * @return int displayframe | Der Index des zu darstellenden Bildes des Patterns
	 */
	public int getDisplayFrame() {
		return displayframe;
	}


	/**
	 * @param int displayframe | Der Index des zu darstellenden Bildes des Patterns
	 */
	public void setDisplayFrame(int displayframe) {
		this.displayframe = displayframe;
	}
	
}
