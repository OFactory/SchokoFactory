package de.OFactory.SchokoFactory.game;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import sun.util.calendar.LocalGregorianCalendar.Date;

public class Map extends ArrayList<Pattern>{

	public final static String SAVE_PATH_DIR = "src/saves";
	
	private static final long serialVersionUID = 1L;
	private String name = "unnamed";
	private Date lastedit;
	private int height;
	private int width;
	
	//TODO Info ADDEN
	
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
	
	public void saveMap(){
		saveMap(Map.SAVE_PATH_DIR + "/" + name + ".sf");
	}
	
	/** Speichert die Map im angebenem Dateipfad 
	 *  in Form des mapstring-Formats(siehe getSaveString())
	 *  
	 * @param String path | Dateipfad
	 * @return Map m | Die ausgelesene Karte(Speicherstand)
	 */
	public void saveMap(String path){
		
		System.out.println("Saving Map \"" + name + "\" to \"" + path + "\"...");
		
		File f = new File(path);
		
		try {
			
			FileWriter fw = new FileWriter(f);
			fw.write(this.getSaveString());
			fw.close();
			System.out.println("Succesfully saved Map \"" + name + "\"!");
			
		} catch (IOException e) {
			System.err.println("ERROR <003>: Datei \"" + path + "\" konnte nicht gefunden werden!");
			e.printStackTrace();
		}
	}
	
	
	public String toString(){
		return "Map(name=" + name + "; " + super.toString() + ")"; //Bsp. Map[name=test; ArrayList[Pattern....]]
	}
	
	// STATIC
	
	/** Liest eine Karte(Speicherstand) aus einem angebebenem String
	 *  
	 * @param String mapstring | Der String der Map 
	 * @return Map m | Die ausgelesene Karte(Speicherstand)
	 * @return null, wenn der MapString inkorrekt ist!
	 */
	public static Map readMap(String mapstring){
		Map m = new Map("return");
		
		return m;
	}
	
	/** Liest eine Karte(Speicherstand) aus einer angebebenen Datei heraus
	 *  
	 * @param path | Der Dateipfad der Datei
	 * @return Map map | Die ausgelesene Karte(Speicherstand)
	 * @return null, wenn die Datei inkorrekt ist!
	 */
	public static Map readSavedMap(String path){
		File f = new File(path);
		
		String mapstring = null;
		
		try {
			
			FileReader fr = new FileReader(f);
			mapstring = fr.toString();
			fr.close();
			
		} catch (IOException e) {
			System.err.println("ERROR <003>: Datei \"" + path + "\" konnte nicht gefunden werden!");
			e.printStackTrace();
		}
		
		
		
		return readMap(mapstring);
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
