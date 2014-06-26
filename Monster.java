/**
 *Monster.java
 *Class Monster builds a monster object, which is also a random occupant.
 *
 *@author akramins
 *@version 5/13/2013
 */
public class Monster extends RandomOccupant{
	//Monster constructor
	public Monster(Maze maze){
		super(maze);
	}
	//Monster constructor with a seed.
	public Monster(Maze maze, long seed){
		super(maze, seed);
	}
	//Monster constructor at a given location.
	public Monster(Maze maze, Square location){
		super(maze, location);
	}
}
