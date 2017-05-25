package de.OFactory.SchokoFactory.game;

import java.util.HashMap;

import org.newdawn.slick.Image;

import de.OFactory.SchokoFactory.main.ResourceManager;

public enum PatternState {
	TANK        	(0L, "Tank",		  ResourceManager.pttrn_tank,	  	30, false),
	RÜHRER  		(1L, "Rührer", 		  ResourceManager.pttrn_ruehrer,    50, true),
	CHEMIEFABRIK	(2L, "Chemiefabrik",  ResourceManager.pttrn_chemfab,	60, false),
	GIEßER			(3L, "Gießer", 		  ResourceManager.pttrn_giesser,	70, true),
	LAGER			(4L, "Lagerhalle", 	  ResourceManager.pttrn_lager, 		80, false),
	MOLKEREI		(5L, "Molkerei",	  ResourceManager.pttrn_molkerei,   90, true),
	FARM			(6L, "Farm", 		  ResourceManager.pttrn_farm,		100, true),
	FELD			(7L, "Feld", 	      ResourceManager.pttrn_feld,		150, true),
	GEWÄCHSHAUS		(8L, "Gewächshaus",   ResourceManager.pttrn_gewaechshaus,150, true),
	HOF				(9L, "Hof", 	      ResourceManager.pttrn_hof,		150, true), 
	LABOR			(10L, "Labor", 		  ResourceManager.pttrn_labor,		150, true),
	PRBÜRO			(11L, "PRBuero", 	  ResourceManager.pttrn_prbuero,	150, true),
	WIESE			(12L, "Wiese",		  ResourceManager.pttrn_gras,       0, false);
	
	private String name;
	private Long id;
	private Image source;
	private int price = 0;
	private boolean work;
	
	private PatternState(Long id, String name, Image source, int price, boolean work){
		this.name = name;
		this.id = id;
		this.price = price;
		this.setSource(source);
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



	public Image getSource() {
		return source;
	}

	public void setSource(Image source) {
		this.source = source;
	}

	
}
