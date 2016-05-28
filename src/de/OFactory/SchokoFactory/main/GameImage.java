package de.OFactory.SchokoFactory.main;

public enum GameImage
{
	Patterns("res/img/assets/texture/patterns/patterns.png"),
	header("res/img/gui/header.png");
	
	GameImage(String path) {
		System.out.println("new image initialized at" + path);
		this.path = path;
	}
	
}

