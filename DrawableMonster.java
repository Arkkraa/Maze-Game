/**
 *DrawableMonster.java
 *Class DrawableMonster draws a monster 
 *
 *@author akramins
 *@version 5/13/2013
 */
public class DrawableMonster extends Monster implements Drawable{
	public DrawableMonster(Maze maze){
		super(maze);
	}
	public DrawableMonster(Maze maze, long seed){
		super(maze, seed);
	}
	public DrawableMonster(Maze maze, Square location){
		super(maze, location);
	}
	public void draw(){}
	
}
