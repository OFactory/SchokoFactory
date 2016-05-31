package de.OFactory.SchokoFactory.main;

public enum GameImage
{
	PATTERN("res/img/assets/texture/patterns/patterns.png"),
	HEADER("res/img/gui/header.png");
	
	private String file;
	
	GameImage(String file) {
		System.out.println("new image initialized at" + file);
		this.file = file;
	}
	public String getPath(){
		return this.file;
	}
}

