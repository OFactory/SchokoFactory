package de.OFactory.SchokoFactory.game;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import de.OFactory.SchokoFactory.simulation.Market;

public class GameSave implements Serializable{
	
	private static final long serialVersionUID = 657370429531156194L;

	public final static String SAVE_PATH_DIR = "saves";
	
	private String name = "unnamed";
	public Map map;
	public Market market;
	
	public GameSave(String name, Map map, Market market){
		setName(name);
		setMap(map);
		setMarket(market);
	}
	
	public GameSave(String readlocation){
		
		GameSave tmp = read(readlocation);
		
		setName(tmp.getName());
		setMap(tmp.getMap());
		setMarket(tmp.getMarket());
	}
	
	/** Speichert den Spielstand im
	 * Standardspeicherort.
	 */
	public void save(){
		save(getStandardSaveString(name));
	}
	
	/**
	 * Liest den Spielstand im 
	 * Standardspeicherort + Name.
	 * @param name
	 */
	public static GameSave read(String name){
		return readPath(getStandardSaveString(name));
	}
	
	/**
	 * Speichert den gesamten Spielstand mitsamt seinen
	 * Marktinformationen und der Karte mit allen Pattern
	 * und deren Informationen. WIP
	 * 
	 * @param path Der Speicherort
	 */
	public void save(String path){ 

		System.out.println("(1/2) Initialisiere die Speicherung des Speicherstandes "
				+ "\n\t" + this
				+ "\n in \"" + path + "\"..." );
		
        try {
        	File f = new File(path);
        	FileOutputStream fileOut = new FileOutputStream(f);
        	ObjectOutputStream out = new ObjectOutputStream(fileOut);
        	out.flush();
        	out.writeObject(this);
        	out.close();
        	fileOut.close();
        	System.out.println("(2/2)Serialisierter Speicherstand wurden erfolgreich in \"" + path + "\" gespeichert!" );
        } catch (IOException e) {
			System.err.println("ERROR <101>: Speicherstand konnte nicht in \"" + path + "\" gespeichert werden!");
			e.printStackTrace();
		}
	    
	}
	
	/**
	 * Liest den gesamten Spielstand mitsamt seinen
	 * Marktinformationen und der Karte mit allen Pattern
	 * und deren Informationen aus dem angebenen Pfad.
	 * 
	 * Sollte die Datei nicht gefunden werden oder in der Datei
	 * kein GameSave Objekt gelesen werden können, so gibt die Funktion
	 * null zurück. WIP
	 * 
	 * @param path Der Lesepfad
	 * @return Der ausgelesene Speicherstand // null(siehe oben)
	 */
	public static GameSave readPath(String path){
		
		System.out.println("(1/2) Initialisiere das Lesen des Speicherstandes in \"" + path + "\"..." );
		GameSave read = null;
		try {
			FileInputStream fileIn = new FileInputStream(new File(path));
			ObjectInputStream in = new ObjectInputStream(fileIn);
			read = (GameSave) in.readObject();
			in.close();
			fileIn.close();
			System.out.println("(2/2) Das Objekt wurden erfolgreich geladen:\n\t" + read );
			
			
		}catch (IOException e) {
			System.err.println("ERROR <102>: Speicherstand konnte nicht in \"" + path + "\" gelesen werden!");
			e.printStackTrace();
		}catch (ClassNotFoundException c){
			System.err.println("ERROR <103>: Es konnte kein GameSave-Objekt in \"" + path + "\" gefunden werden!");
			c.printStackTrace();
		}
		
		return read;
	}
	
	public String toString(){
		return " + " + getName() + "[GameSave]:\n\t + Markt: " + getMarket() + "\n\t + Map: "  + getMap();
	}
	
	public String getStandardSaveString(){
		return SAVE_PATH_DIR + "/" + getName() + ".sf";
	}
	
	public static String getStandardSaveString(String name){
		return SAVE_PATH_DIR + "/" + name + ".sf";
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public Market getMarket() {
		return market;
	}

	public void setMarket(Market market) {
		this.market = market;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
