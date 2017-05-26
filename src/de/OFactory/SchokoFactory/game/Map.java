package de.OFactory.SchokoFactory.game;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;

import de.OFactory.SchokoFactory.game.patterns.Wiese;
import de.OFactory.SchokoFactory.main.MainState;
import de.OFactory.SchokoFactory.main.Updateable;

public class Map extends ArrayList<Pattern> implements Updateable{

	private static final long serialVersionUID = 1L;
	
	private int height;
	private int width;
	
	public Pattern hoveredpattern; //Gehoverter Pattern
	public Pattern clicked;        //Geklickter Pattern
	public Pattern selected_pattern = null; // ausgew�hltes Pattern
	
	//TODO Info ADDEN
	
	public Map(){
		super();
	}
	
	public void update(GameContainer gc) {
		if(clicked != null){ // Clicked Pattern

			if(MainState.curpatternstate == null){ // Kein Geb�ude ausgew�hlt: nur Auswahlm�glichkeit
				if(clicked instanceof Wiese) {
					selected_pattern = null; // keine Auswahl beim Klicken auf leeres Feld
				} else {
					selected_pattern = clicked; // Geb�ude ausw�hlen
					MainState.ip.switchTab(0); // Tab wechseln
				}
			} else {
			
				if(clicked instanceof Wiese){ //Feld "leer" ( = Wiese)
					if(MainState.curpatternstate != PatternState.WIESE) {

						if (!(MainState.curpatternstate == PatternState.GIE�ER && MainState.p.getMoney() < 200)){
							set(clicked.getId(), Pattern.getInstance(this, clicked.getX(), clicked.getY(), MainState.curpatternstate, clicked.getId(), clicked.getXCoordinate(), clicked.getYCoordinate()));
						}
						MainState.curpatternstate = null;
					}
				} else { //Feld hat ein Geb�ude
					if(MainState.curpatternstate == PatternState.WIESE) //Geb�ude entfernen (-> Wiese) 
						set(clicked.getId(), new Wiese(clicked.getX(), clicked.getY(), clicked.getId(), clicked.getXCoordinate(), clicked.getYCoordinate()));
					else // Keine Wiese: Geb�ude Ausw�hlen
						selected_pattern = clicked;
						
				}
			}
			

		}
						
		for(Pattern p : this) //jedes Pattern zeichnen
			if(p != null)
				p.update(gc);
		
//		if(gc.getInput().isKeyPressed(Input.KEY_S)){ 
//			saveMap();
//		}
	}
	
	/** Erstellt ein Feld (=Liste) von Patterns aus einer Angebebenen Breite und H�he
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
	 *  @param int pattern_height | H�he des Feldes
	 *  @return Map ps | Das Feld bestehend aus Pattern
	 */
	public static Map generateMap(int pattern_width, int pattern_height) {
		
		int i = 0;
		
		
		Map ps = new Map();
		ps.setHeight(pattern_height);
		ps.setWidth(pattern_width);
		
		int grid_width = 0; // Anzahl Spalten in nter Reihe
		
		int xcoor = 1;
		int ycoor = 1;
		
		

		
		for(int y = 0; y < (pattern_width+1)*2-1; y++){ // f�r jede Reihe
			if(y >= pattern_width+1){ // nte Reihe erreicht(max width)
				grid_width--;
				
				xcoor = pattern_width;// muss bei bugfix(pattern_width ist immer 1 gr��er) ge�ndert werden
				ycoor = y - pattern_height+1; //sp�ter nur noch +1
			} else {
				grid_width = y;
				
				xcoor = y;
				ycoor = 1;
			}
			
			for(int x = 0; x < grid_width; x++){ // f�r jede Spalte
				

				
				Wiese w = new Wiese( (int) (  x*MainState.TEXTURE_WIDTH * MainState.curpatternscale +//Normal Placement
								( ((pattern_width+1)-grid_width)*MainState.TEXTURE_WIDTH * MainState.curpatternscale ) / 2 - //Verschiebung durch Anzahl Patterns in Reihe
								(pattern_width+1)*MainState.TEXTURE_WIDTH*MainState.curpatternscale / 3),
						(int) (  y*MainState.TEXTURE_HEIGHT * MainState.curpatternscale - //Normal Placement
								(pattern_height+1)*MainState.TEXTURE_HEIGHT * MainState.curpatternscale / 1.5), 
						i, xcoor, ycoor ); // i = ID
				ps.add(w);
				
				if (y >= pattern_width+1) {
					xcoor--;
					ycoor++;
				} else {
					xcoor--;
					ycoor++;
				}
				
					
				
				
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
		
		
		for(int y = 0; y < this.getWidth()*2-1; y++){ // f�r jede Reihe
			if(y >= this.getHeight()){ // nte Reihe erreicht(max width)
				grid_width--;
			} else {
				grid_width = y;
			}
			
			for(int x = 0; x < grid_width; x++){ // f�r jede Spalte
								    									
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
	 * Hierbei kommt es darauf an, ob currpatternscale gr��er oder kleiner als die vorhergegangene Skalierung ist,
	 * und ob die Map herangezoomt bzw. herausgezoomt werden soll.
	 * Hierbei bewegen sich alle Pattern gleichm��ig um die Zoomstelle herum,
	 * �hnlich wie die Pixel bei der Zoom-Funktion von Bildern.
	 * 
	 * @param curpatternscale Skalierung
	 * @param zx X-Position des Zoom-Punktes
	 * @param zy Y-Position des Zoom-Punktes
	 */
	public void zoomMap(double in_scale, int zx, int zy){
		
		double scale = -in_scale;
		
		for(Pattern p: this){
			int rel_x = zx - p.getX();
			int rel_y = zy - p.getY();
			
			p.setX(p.getX() + (int) Math.round(rel_x*scale));
			p.setY(p.getY() + (int) Math.round(rel_y*scale));
		}

	}
	
	
	/** Strukturiert ein Feld mit �bergabe eines Arrays aus PatternStates
	 *  mit folgendem Format: (siehe auch generateMap(int, int))
	 *  	
	 *  	h = 3 | w = 3	
	 *  		  0
	 *  		1	2
	 *  	  3   4	  5
	 *			6   7
	 *            8
	 *              
	 *  @param ArrayList<PatternState> ps | Liste von PatternStates f�r die Map
	 *  @return Map m | Das Feld bestehend aus Pattern
	 */
	public static Map structureMap(ArrayList<PatternState> ps) {
		
		int i = 0;
		
		
		int pattern_width =  (int) Math.sqrt(ps.size())  + 1;
		int pattern_height = (int) Math.sqrt(ps.size())  + 1;


		
		Map m = new Map();
		m.setWidth(pattern_width - 1);
		m.setHeight(pattern_height - 1);

		int xcoor = 1;
		int ycoor = 1;
		
		int grid_width = 0; // Anzahl Spalten in nter Reihe
		
		for(int y = 0; y < pattern_width*2-1; y++){ // f�r jede Reihe
			if(y >= pattern_width){ // nte Reihe erreicht(max width)
				grid_width--;
				xcoor = pattern_width-1;
				ycoor = y - pattern_height+2;
			} else {
				grid_width = y;
				xcoor = y;
				ycoor = 1;
			}
			
			for(int x = 0; x < grid_width; x++){ // f�r jede Spalte
				
				int px = (int) (  x*MainState.TEXTURE_WIDTH * MainState.curpatternscale +//Normal Placement
						( (pattern_width-grid_width)*MainState.TEXTURE_WIDTH * MainState.curpatternscale ) / 2 - //Verschiebung durch Anzahl Patterns in Reihe
						pattern_width*MainState.TEXTURE_WIDTH*MainState.curpatternscale / 3);
				int py = (int) (  y*MainState.TEXTURE_HEIGHT * MainState.curpatternscale - //Normal Placement
						pattern_height*MainState.TEXTURE_HEIGHT * MainState.curpatternscale / 1.5);
				
				Pattern p = null;
				PatternState state = ps.get(i);
				
				p = Pattern.getInstance(m, px, py, state, i, xcoor, ycoor);
				//TODO Alle anderen PatternStates adden! Allgemeinde Methode
				
				
				m.add(p);
				i++;
				
				if (y >= pattern_width) {
					xcoor--;
					ycoor++;
				} else {
					xcoor--;
					ycoor++;
				}
			}
		}
		
		return m;
		
		
		
	}
	
//	/** Gibt den Speicherstring zur�ck.
//	 *  
//	 * @return Str sb = der Sepicherstring. Format (WIP)
//	 */
//	public String getSaveString(){
//		StringBuilder sb = new StringBuilder("");
//		//TODO ordentlich encrypten	
//		sb.append(name + " " /*+ lastedit + " " + height + " " + width + " "*/);
//		
//		for(Pattern p : this){
//			//sb.append(Long.toBinaryString(p.getPatternState().getId()) + " "); Binary could be an Option #SOVIELEM�GLICHKEITEN
//			sb.append(p.getPatternState().getId() + " ");
//		}
//		
//		return sb.toString();
//	}
	
//	public void saveMap(){
//		saveMap(Map.SAVE_PATH_DIR + "/" + name + ".sf");
//	}
	
//	/** Speichert die Map im angebenem Dateipfad 
//	 *  in Form des mapstring-Formats(siehe getSaveString())
//	 *  
//	 * @param String path | Dateipfad
//	 * @return Map m | Die ausgelesene Karte(Speicherstand)
//	 */
//	public void saveMap(String path){
//		
//		System.out.println("Saving Map \"" + name + "\" to \"" + path + "\"...");
//		
//		File f = new File(path);
//		
//		try {
//			
//			FileWriter fw = new FileWriter(f);
//			fw.write(this.getSaveString());
//			fw.close();
//			System.out.println("Succesfully saved Map \"" + name + "\"!");
//			
//		} catch (IOException e) {
//			System.err.println("ERROR <003>: Datei \"" + path + "\" konnte nicht gefunden werden!");
//			e.printStackTrace();
//		}
//	}
	
	
	public String toString(){
		return "Map(size= " + this.getWidth() + "*" + this.getHeight() + ")"; //Bsp. Map[name=test; ArrayList[Pattern....]]
	}
	
	// STATIC
	
//	/** Liest eine Karte(Speicherstand) aus einem angebebenem String
//	 *  
//	 * @param String mapstring | Der String der Map 
//	 * @return Map m | Die ausgelesene Karte(Speicherstand)
//	 * @return null, wenn der MapString inkorrekt ist!
//	 */
//	public static Map readMap(String mapstring){
//		String name = "";
//		ArrayList<PatternState> ps = new ArrayList<PatternState>();
//		
//		String[] mapstrings = mapstring.split(" ");
//		
//		for(String s : mapstrings){
//			if( GameUtils.isNumeric(s) ){
//				ps.add(PatternState.getById(Long.parseLong(s)));
//				
//			} else {
//				name = s;
//			}
//		}
//		
//		Map m = Map.structureMap(ps); //Strukturieren
//		m.setName(name);
//		
//		
//		return m;
//	}
	
//	/** Liest eine Karte(Speicherstand) aus einer angebebenen Datei heraus
//	 *  
//	 * @param path | Der Dateipfad der Datei
//	 * @return Map map | Die ausgelesene Karte(Speicherstand)
//	 * @return null, wenn die Datei inkorrekt ist!
//	 */
//	public static Map readSavedMap(String path){
//		File f = new File(path);
//		System.out.println("Reading " + path + " ...");
//		String mapstring = null;
//		
//		try {
//			
//			BufferedReader br = new BufferedReader(new FileReader(f));
//			mapstring = br.readLine();
//			
//			br.close();
//			
//		} catch (IOException e) {
//			System.err.println("ERROR <003>: Datei \"" + path + "\" konnte nicht gefunden werden!");
//			e.printStackTrace();
//		}
//		
//		
//		
//		return readMap(mapstring);
//	}
	
	// Getter und Setter

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
