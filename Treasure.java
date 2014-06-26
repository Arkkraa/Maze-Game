/**
 *Treasure.java
 *Class Treasure contains the methods needed to build a Treasure object in the maze, which is also a random occupant.
 *
 *@author akramins
 *@version 5/13/2013
 */
import java.util.Scanner;
public class Treasure extends RandomOccupant{
	private boolean found;
	//Treasure constructor builds and sets the treasure of the square it resides in.
	public Treasure(Maze maze){
		super(maze);
		maze.getSquare(location().row(), location().col()).setTreasure(this);
		found = false;
	}
	public Treasure(Maze maze, long seed){
		super(maze, seed);
		maze.getSquare(location().row(), location().col()).setTreasure(this);
		found = false;
	}
	public Treasure(Maze maze, Square location){
		super(maze, location);
		found = false;
		location.setTreasure(this);
	}
	//returns whether the treasure has been found.
	public boolean found(){
		return found;
	}
	//sets the found value to true.
	public void setFound(){
		this.found = true;
	}
	//overrides the move method to do nothing.
	public void move(){}
	public void moveTo(Square newLoc){
		if(super.location().treasure() != null) super.location().setTreasure(null);
		super.moveTo(newLoc);
		super.location().setTreasure(this);

	}
	public String toText(char delimiter){
		return 	super.toText(delimiter) + delimiter + found();
	}
	public void toObject(Scanner input){
		super.toObject(input);
		found = input.nextBoolean();
	}

}
