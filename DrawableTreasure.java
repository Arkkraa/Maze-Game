/**
 *DrawableTreasure.java
 *Class DrawableTreasure draws the treasure in a specified maze.
 *
 *@author akramins
 *@version 5/13/2013
 */
public class DrawableTreasure extends Treasure implements Drawable{
	public DrawableTreasure(Maze maze){
		super(maze);
	}
	public DrawableTreasure(Maze maze, long seed){
		super(maze, seed);
	}
	public DrawableTreasure(Maze maze, Square location){
		super(maze, location);
	}
	public void draw(){}
}
