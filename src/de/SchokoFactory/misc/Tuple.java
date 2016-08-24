package de.SchokoFactory.misc;


/**
 * 
 * Ein Tuple(siehe Python) ist ein Object, das zwei Werte aufnehmen kann!
 * 
 * @author Maximilian
 *
 * @param <X> Wert 1
 * @param <Y> Wert 2
 */
public class Tuple<X, Y> { 
  public final X x; 
  public final Y y; 
  public Tuple(X x, Y y) { 
    this.x = x; 
    this.y = y; 
  } 
} 