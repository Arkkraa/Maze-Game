/**
 *Occupant.java
 *Class Occupant controls the where an occupant starts and moves to.
 *
 *@author akramins
 *@version 5/13/2013
 */
public abstract class Occupant implements DelimitedTextIO{
	private Square location;
	public Occupant(){}
	public Occupant(Square start){
		location = start;
	}
	public Square location(){
		return location;
	}
	public void moveTo(Square newLoc){
		location = newLoc;
	}
	public String toText(char delimiter){
		return getClass().getName() + delimiter +
		location.row() + delimiter + location.col();
	}
	
}
