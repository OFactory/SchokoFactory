package de.OFactory.SchokoFactory.game;

import java.util.ArrayList;

import sun.util.calendar.LocalGregorianCalendar.Date;

public class Map extends ArrayList<Pattern>{

	private static final long serialVersionUID = 1L;
	private String name = "unnamed";
	private Date lastedit;
	private int height;
	private int width;
	
	public Map(){
		super();
	}
	
	public Map(String name){
		super();
		this.setName(name);
	}
	
	public String getSaveString(){
		StringBuilder sb = new StringBuilder("");
		//TODO ordentlich encrypten	
		sb.append(name + " " /*+ lastedit + " " + height + " " + width + " "*/);
		
		for(Pattern p : this){
			//sb.append(Long.toBinaryString(p.getPatternState().getId()) + " "); Binary could be an Option #SOVIELEMÖGLICHKEITEN
			sb.append(p.getPatternState().getId() + " ");
		}
		
		return sb.toString();
	}
	
	public String toString(){
		return "Map(name=" + this.name + "; " + super.toString() + ")"; //Bsp. Map[name=test; ArrayList[Pattern....]]
	}
	
	
	// Getter und Setter

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getLastedit() {
		return lastedit;
	}

	public void setLastedit(Date lastedit) {
		this.lastedit = lastedit;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
	

}
