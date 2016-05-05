package de.OFactory.SchokoFactory.game;

import java.util.HashMap;

public enum PatternState {
	TANK(0L, "Tank"),
	R�HRER(1L, "R�hrer"),
	CHEMIEFABRIK(2L, "Chemiefabrik"),
	GIE�ER(3L, "Gie�er"),
	LAGERHALLE(4L, "Lagerhalle"),
	MOLKEREI(5L, "Molkerei"),
	FARM(6L, "Farm"),
	WEIZENFELD(7L, "Weizenfeld"),
	KAKAOPLANTAGE(8L, "Kakaoplantage"),
	ZUCKERPLANTAGE(9L, "Zuckerplantage"),
	WIESE(10L, "Wiese");
	
	private String name;
	private Long id;
	
	private PatternState(Long id, String name){
		this.name = name;
		this.id = id;
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
	
}
