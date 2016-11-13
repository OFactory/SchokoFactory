package de.OFactory.SchokoFactory.game;

import java.util.HashMap;

public enum PatternState {
	TANK        	(0L, "Tank",		  PatternFrame.TANK_0,	          30, false),
	RÜHRER  		(1L, "Rührer", 		  PatternFrame.RÜHRER_S,          50, true),
	CHEMIEFABRIK	(2L, "Chemiefabrik",  PatternFrame.CHEMIE_S,		  60, false),
	GIEßER			(3L, "Gießer", 		  PatternFrame.GIEßER_S,		  70, true),
	LAGERHALLE		(4L, "Lagerhalle", 	  PatternFrame.LAGERHALLE_NORMAL, 80, false),
	MOLKEREI		(5L, "Molkerei",	  PatternFrame.MOLKEREI_MODERN,   90, true),
	FARM			(6L, "Farm", 		  PatternFrame.FARM,			 100, true),
	WEIZENFELD		(7L, "Weizenfeld", 	  PatternFrame.TANK_0,			 150, true), //TODO BILDER
	KAKAOPLANTAGE	(8L, "Kakaoplantage", PatternFrame.TANK_0,			2300, true), //TODO BILDER
	ZUCKERPLANTAGE	(9L, "Zuckerplantage",PatternFrame.TANK_0,   	   60000, true), //TODO BILDER
	WIESE			(10L, "Wiese",		  PatternFrame.WIESE_1,            0, false);
	
	private String name;
	private Long id;
	private int displayframe;
	private int price = 0;
	private boolean work;
	
	private PatternState(Long id, String name, int displayframe, int price, boolean work){
		this.name = name;
		this.id = id;
		this.price = price;
		this.displayframe = displayframe;
		this.work = work;
	}
	
	public int getPrice() {
		return price;
	}

	public boolean isWorking() {
		return work;
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

	
}
