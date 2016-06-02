package de.OFactory.SchokoFactory.simulation;

import java.util.List;



public class Market {
	
	int time;
	List<Player> players;
	
	public Market() {
		
		System.out.println("Hello, I'm the market! How are you?");
		
	}
	
	/** processed every day **/
	public void day() {
		
		
		this.time++;
		System.out.println(getTimeString());
	}
	public void addPlayer(Player p){
		this.players.add(p);
	}
	
	public void setPlayer(List<Player> players) {
		this.players = players;
	}
	
	public void removePlayer(Player p){
		this.players.remove(p);
	}

	public List<Player> getPlayers() {
		return this.players;
	}
	
	public String getTimeString() {
		int year = time/360;
		int month = (time - year*360)/30;
		int day = time- year*360 - month*30 ;
		
		return (year)+":"+(month+1)+":"+(day+1);	// ++ Denn es gibt keinen 0. Monat/Tag
	}
	
}

