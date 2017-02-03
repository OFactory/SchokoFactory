package de.OFactory.SchokoFactory.inventory;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;

import de.OFactory.SchokoFactory.main.Drawable;

/**
 *  Diese Klasse kann dynamisch Text darstellen ohne 
 *  Buchstaben auszugrenzen.
 *  
 * @author Maximilian
 *
 */
public class TextView implements Drawable{

	public static final int TEXTALIGN_CENTER = 0;
	public static final int TEXTALIGN_LEFT   = 1;
	public static final int TEXTALIGN_TOP    = 1;
	public static final int TEXTALIGN_RIGHT  = 2;
	public static final int TEXTALIGN_BOTTOM = 2;
	
	public static final int SPACE_X = 8; // GET THE REFERENCE?
	public static final int SPACE_Y = 30;
	
	private String text;
	private Font font = new TrueTypeFont(new java.awt.Font("Arial", 20, 20), true);
	private int x,y;
	
	//Eigenschaften
	private int max_width;
	private int max_height;
	private int line_amount = 1;
	
	private int vertical_alignment   = TEXTALIGN_TOP;
	private int horizontal_alignment = TEXTALIGN_LEFT;
			
	
	private int margin_left, margin_top, margin_right, margin_bottom = 5;
	
	private boolean height_lock = false; // true: Höhe verändert sich nicht(max_height)
	private boolean line_lock   = false; // true: Neue Zeilen nur mit >\n<
	
	public TextView(String text, int x, int y, int max_width){
		setText(text);
		setMaxWidth(max_width);
		setLocation(x, y);
		
	}

	/**
	 * TextView zeichnen
	 * @param Graphics g: Graphicinstanz
	 */
	public void draw(Graphics g) {
		g.setColor(Color.white);
		g.drawRect(x, y, max_width, line_amount * ( getFont().getHeight("A") + SPACE_Y ) + getMarginTop());
		flow();
	}
	
	// GETTER UND SETTER
	
	public void flow(){
		

		int cur_x = getX() + getMarginLeft();
		int cur_y = getY() + getMarginTop();
		
		for(String wort : getText().split(" ") ){
			
			if(getFont().getWidth(wort) < getMaxWidth() - getMarginRight() - cur_x){ // Passt wort in Zeile?
				getFont().drawString(cur_x, cur_y, wort);
				cur_x += getFont().getWidth(wort) + SPACE_X;
			} else { // Nein
				cur_x = getX() + getMarginLeft();
				cur_y += SPACE_Y;
				getFont().drawString(cur_x, cur_y, wort);
				cur_x += getFont().getWidth(wort);
				line_amount++;
				
			}
		}
		
		
			
		
		
	}
	
	public void setFont(Font font){
		this.font = font;
	}
	
	public Font getFont(){
		return font;
	}
	
	public void setLocation(int x, int y){
		setX(x);
		setY(y);
	}
	
	public void setMargin(int margin){
		margin_left = margin;
		margin_top = margin;
		margin_right = margin;
		margin_bottom = margin;
	}
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getMaxWidth() {
		return max_width;
	}

	public void setMaxWidth(int max_width) {
		this.max_width = max_width;
	}

	public int getMaxHeight() {
		return max_height;
	}

	public void setMaxHeight(int max_height) {
		this.max_height = max_height;
	}

	public int getVerticalTextAlignment() {
		return vertical_alignment;
	}

	public void setVerticalTextAlignment(int vertical_alignment) {
		this.vertical_alignment = vertical_alignment;
	}

	public int getHorizontalTextAlignment() {
		return horizontal_alignment;
	}

	public void setHorizontalTextAlignment(int horizontal_alignment) {
		this.horizontal_alignment = horizontal_alignment;
	}

	public int getMarginLeft() {
		return margin_left;
	}

	public void setMarginLeft(int margin_left) {
		this.margin_left = margin_left;
	}

	public int getMarginTop() {
		return margin_top;
	}

	public void setMagrinTop(int magrin_top) {
		this.margin_top = magrin_top;
	}

	public int getMarginRight() {
		return margin_right;
	}

	public void setMarginRight(int margin_right) {
		this.margin_right = margin_right;
	}

	public int getMarginBottom() {
		return margin_bottom;
	}

	public void setMargin_Bottom(int margin_bottom) {
		this.margin_bottom = margin_bottom;
	}

	public boolean isHeightLocked() {
		return height_lock;
	}

	public void setHeightLocked(boolean height_lock) {
		this.height_lock = height_lock;
	}

	public boolean isLineLocked() {
		return line_lock;
	}

	public void setLineLocked(boolean line_lock) {
		this.line_lock = line_lock;
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

	
	
	
}
