package de.OFactory.SchokoFactory.simulation;

import java.util.List;



public class Market {
	
	int time;
	List<Player> players;
	
	public Market() {
		
		System.out.println("Hello, I'm the market! How are you?");
		
	}
	
	/** processed every day **/
	private void day() {
		
		
		this.time ++;
		
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
	
/*	public String getTimeString() {
		while () {
		return ""+time
	}*/
}