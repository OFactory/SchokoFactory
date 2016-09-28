package de.OFactory.SchokoFactory.simulation;

public class SimpleAI extends Player {

	
	
	public SimpleAI(Market market, String name, double money) {
		super(market, name, money);
	}
	
	public void think() {
		System.out.println(name+" hmmmm");
	}
	
	public void runFactory() {
		System.out.println(name+" working");
	}
}
