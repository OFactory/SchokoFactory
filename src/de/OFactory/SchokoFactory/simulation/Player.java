package de.OFactory.SchokoFactory.simulation;

public class Player {
	
	Market market;
	String name;
	double money;
	
	
	public Player(Market market, String name, double money) {
		
		this.market = market;
		this.name = name;
		this.money = money;
		
	}
	
	double getMoney() {
		return this.money;
	}
	
	String getName() {
		return this.name;
	}
	
	void setMoney(int money) {
		this.money = money;
	}
	
	void setName(String name) {
		this.name = name;
	}

}
