package de.OFactory.SchokoFactory.game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import de.OFactory.SchokoFactory.game.patterns.Wiese;
import de.OFactory.SchokoFactory.main.MainState;
import sun.util.calendar.LocalGregorianCalendar.Date;

public class Map extends ArrayList<Pattern>{

	public final static String SAVE_PATH_DIR = "saves";
	
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
	
	/** Erstellt ein Feld (=Liste) von Patterns aus einer Angebebenen Breite und Höhe
	 *  mit folgenedem Schema:
	 *  	
	 *  	h = 3 | w = 3	
	 *  		  0
	 *  		1	2
	 *  	  3   4	  5
	 *			6   7
	 *            8
	 *              
	 *  @param int pattern_width | Breite des Feldes
	 *  @param int pattern_height | Höhe des Feldes
	 *  @return Map ps | Das Feld bestehend aus Pattern
	 */
	public static Map generateMap(int pattern_width, int pattern_height) {
		
		int i = 0;
		
		Map ps = new Map();
		ps.setHeight(pattern_height);
		ps.setWidth(pattern_width);
		
		int grid_width = 0; // Anzahl Spalten in nter Reihe
		
		for(int y = 0; y < pattern_width*2-1; y++){ // für jede Reihe
			if(y >= pattern_width){ // nte Reihe erreicht(max width)
				grid_width--;
			} else {
				grid_width = y;
			}
			
			for(int x = 0; x < grid_width; x++){ // für jede Spalte
								    									
				Wiese w = new Wiese(
						(int) (  x*MainState.TEXTURE_WIDTH * MainState.curpatternscale +//Normal Placement
								( (pattern_width-grid_width)*MainState.TEXTURE_WIDTH * MainState.curpatternscale ) / 2 - //Verschiebung durch Anzahl Patterns in Reihe
								pattern_width*MainState.TEXTURE_WIDTH*MainState.curpatternscale / 3),
						(int) (  y*MainState.TEXTURE_HEIGHT * MainState.curpatternscale - //Normal Placement
								pattern_height*MainState.TEXTURE_HEIGHT * MainState.curpatternscale / 1.5), 
						i ); // i = ID
				ps.add(w);
				i++;
			}
		}
	
		return ps;
		
	}
	
	/**
	 * Restrukturiert
	 * 
	 * @param curpatternscale Skalierung
	 */
	public void restructureMap(float curpatternscale){
		int grid_width = 0; // Anzahl Spalten in nter Reihe
		int i = 0;
		
		
		for(int y = 0; y < this.getWidth()*2-1; y++){ // für jede Reihe
			if(y >= this.getHeight()){ // nte Reihe erreicht(max width)
				grid_width--;
			} else {
				grid_width = y;
			}
			
			for(int x = 0; x < grid_width; x++){ // für jede Spalte
								    									
				this.get(i).setX( (int) (  x*MainState.TEXTURE_WIDTH * curpatternscale +//Normal Placement
								( (this.getWidth()-grid_width)*MainState.TEXTURE_WIDTH * curpatternscale ) / 2 - //Verschiebung durch Anzahl Patterns in Reihe
								this.getWidth()*MainState.TEXTURE_WIDTH*curpatternscale / 3));
				this.get(i).setY((int) (  y*MainState.TEXTURE_HEIGHT * curpatternscale - //Normal Placement
								this.getHeight()*MainState.TEXTURE_HEIGHT * curpatternscale / 1.5));
				i++;
			}
		}
	}
	
	/**
	 * Zoomt eine Map an die eine bestimmte Stelle (zx,zy) heran bzw. heraus.
	 * Hierbei kommt es darauf an, ob currpatternscale größer oder kleiner als die vorgergegangene Skalierung ist,
	 * und ob die Map herangezoomt bzw. herausgezoomt werden soll.
	 * Hierbei bewegen sich alle Pattern gleichmäßig um die Zoomstelle herum,
	 * ähnlich wie die Pixel bei der Zoom-Funktion von Bildern.
	 * 
	 * @param curpatternscale Skalierung
	 * @param zx X-Position des Zoom-Punktes
	 * @param zy Y-Position des Zoom-Punktes
	 */
	public void zoomMap(float curpatternscale, int zx, int zy){
		int grid_width = 0; // Anzahl Spalten in nter Reihe
		int i = 0;
		int twidth  = (int) (MainState.TEXTURE_WIDTH * curpatternscale);
		int theight = (int) (MainState.TEXTURE_HEIGHT * curpatternscale);
		
		
		for(int y = 0; y < this.getWidth()*2-1; y++){ // für jede Reihe
			if(y >= this.getHeight()){ // nte Reihe erreicht(max width)
				grid_width--;
			} else {
				grid_width = y;
			}
			
			for(int x = 0; x < grid_width; x++){ // für jede Spalte
				
				Pattern p = this.get(i);
				
				p.setX( (int) ( zx - MainState.gc.getWidth()/2 + 
								x* twidth+//Normal Placement
								( (this.getWidth()-grid_width)*twidth ) / 2 - //Verschiebung durch Anzahl Patterns in Reihe
								this.getWidth()*twidth / 3));
				p.setY( (int) ( zy - MainState.gc.getHeight()/2 + 
								y*theight - //Normal Placement
								this.getHeight()*theight / 1.5));
				
				
				
				i++;
			}
		}
	}
	
	
	/** Strukturiert ein Feld mit Übergabe eines Arrays aus PatternStates
	 *  mit folgendem Format: (siehe auch generateMap(int, int))
	 *  	
	 *  	h = 3 | w = 3	
	 *  		  0
	 *  		1	2
	 *  	  3   4	  5
	 *			6   7
	 *            8
	 *              
	 *  @param ArrayList<PatternState> ps | Liste von PatternStates für die Map
	 *  @return Map m | Das Feld bestehend aus Pattern
	 */
	public static Map structureMap(ArrayList<PatternState> ps) {
		
		int i = 0;
		
		
		int pattern_width =  (int) Math.sqrt(ps.size()) + 1;
		int pattern_height = (int) Math.sqrt(ps.size()) + 1;
		
		
		Map m = new Map();
		m.setWidth(pattern_width);
		m.setHeight(pattern_height);
		
		
		int grid_width = 0; // Anzahl Spalten in nter Reihe
		
		for(int y = 0; y < pattern_width*2-1; y++){ // für jede Reihe
			if(y >= pattern_width){ // nte Reihe erreicht(max width)
				grid_width--;
			} else {
				grid_width = y;
			}
			
			for(int x = 0; x < grid_width; x++){ // für jede Spalte
				
				int px = (int) (  x*MainState.TEXTURE_WIDTH * MainState.curpatternscale +//Normal Placement
						( (pattern_width-grid_width)*MainState.TEXTURE_WIDTH * MainState.curpatternscale ) / 2 - //Verschiebung durch Anzahl Patterns in Reihe
						pattern_width*MainState.TEXTURE_WIDTH*MainState.curpatternscale / 3);
				int py = (int) (  y*MainState.TEXTURE_HEIGHT * MainState.curpatternscale - //Normal Placement
						pattern_height*MainState.TEXTURE_HEIGHT * MainState.curpatternscale / 1.5);
				
				Pattern p = null;
				PatternState state = ps.get(i);
				
				p = Pattern.getInstance(px, py, state, i);
				//TODO Alle anderen PatternStates adden! Allgemeinde Methode
				
				
				m.add(p);
				i++;
			}
		}
		
		return m;
		
		
		
	}
	
	/** Gibt den Speicherstring zurück.
	 *  
	 * @return Str sb = der Sepicherstring. Format (WIP)
	 */
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
		return "Map(name=" + name + "; size= " + this.getWidth() + "*" + this.getHeight() + " ;" + super.toString() + ")"; //Bsp. Map[name=test; ArrayList[Pattern....]]
	}
	
	// STATIC
	
	/** Liest eine Karte(Speicherstand) aus einem angebebenem String
	 *  
	 * @param String mapstring | Der String der Map 
	 * @return Map m | Die ausgelesene Karte(Speicherstand)
	 * @return null, wenn der MapString inkorrekt ist!
	 */
	public static Map readMap(String mapstring){
		String name = "";
		ArrayList<PatternState> ps = new ArrayList<PatternState>();
		
		String[] mapstrings = mapstring.split(" ");
		
		for(String s : mapstrings){
			if( GameUtils.isNumeric(s) ){
				ps.add(PatternState.getById(Long.parseLong(s)));
				
			} else {
				name = s;
			}
		}
		
		Map m = Map.structureMap(ps); //Strukturieren
		m.setName(name);
		
		
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
		System.out.println("Reading " + path + " ...");
		String mapstring = null;
		
		try {
			
			BufferedReader br = new BufferedReader(new FileReader(f));
			mapstring = br.readLine();
			
			br.close();
			
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
