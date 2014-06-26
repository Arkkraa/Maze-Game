/**
 *RandomOccupant.java
 *Class RandomOccupant builds a random occupant object which is also an occupant in the maze.
 *
 *@author akramins
 *@version 5/13/2013
 */
import java.util.Random;
import java.util.Scanner;
public abstract class RandomOccupant extends Occupant{
	private Random generator;
	private Maze maze;
	//constructs a random occupant in the maze at a random location.
	public RandomOccupant(Maze maze){
		super();
		generator = new Random();
		this.maze = maze;
		super.moveTo(this.maze.getSquare(generator.nextInt(this.maze.rows()), generator.nextInt(this.maze.cols())));

	}

	//constructs a random occupant in the maze with a seeded random location.
	public RandomOccupant(Maze maze, long seed){
		super();
		generator = new Random(seed);
		this.maze = maze;
		super.moveTo(this.maze.getSquare(generator.nextInt(this.maze.rows()), generator.nextInt(this.maze.cols())));

	}

	//constructs a random occupant in the maze at a given location.
	public RandomOccupant(Maze maze, Square location){
		super(location);
		this.maze = maze;
		generator = new Random();
	}

	//Method move randomly moves the random occupant around the maze. 
	public void move(){
		int row = super.location().row();
		int col = super.location().col();
		int g = generator.nextInt(4);
		while(super.location().wall(g)){
		   g = generator .nextInt(4);
		}
        if(g == Square.UP){
            super.moveTo(maze.getSquare(row - 1, col));		
		}
		else if(g == Square.RIGHT){
			super.moveTo(maze.getSquare(row, col + 1));
		}
		else if(g == Square.DOWN){
			super.moveTo(maze.getSquare(row + 1, col));
		}
		else if(g == Square.LEFT){
			super.moveTo(maze.getSquare(row, col - 1));
		}
	}
	public void toObject(Scanner input){
		super.moveTo(maze.getSquare(input.nextInt(), input.nextInt()));
	}
}
