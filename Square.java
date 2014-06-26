/**
 *Square.java
 *Class Square contains the information necessary to build a Square object.
 *
 *@author akramins
 *@version 5/13/2013
 */
import java.util.Scanner;
public class Square implements DelimitedTextIO{
	public static final int SQUARE_SIZE = 50;
	public static final int UP = 0; 
	public static final int RIGHT = 1; //Named Constants
	public static final int DOWN = 2;
	public static final int LEFT = 3;
	private boolean[] walls;
	private boolean seen, inView;   
	private int row, col;
	private Treasure treasure;
	
	//Square Constructor
	public Square(int row, int col){
		this.row = row;
		this.col = col;
		walls = new boolean[4];
	}
	public Square(boolean up, boolean right, boolean down, boolean left, int row, int col){
		walls = new boolean[]{up, right, down, left};
		seen = false;
		inView = false;
		this.row = row;
		this.col = col;
	}

	//Method Wall checks for a wall in the given direction
	public boolean wall(int direction){
		return walls[direction];
	}

	//Method seen returns true or false if that square has been seen.
	public boolean seen(){
		return seen;
	}

	//Method inView returns whether the square is in view or not.
	public boolean inView(){
		return inView;
	}

	//returns the row of the square.
	public int row(){
		return row;
	}

	//returns the col of the square
	public int col(){
		return col;
	}

	//returns the treasure object.
	public Treasure treasure(){
		return treasure;
	}

	//returns the x coordinate of the square
	public int x(){
		return col * SQUARE_SIZE;
	}

	//returns the y coordinate of the square
	public int y(){
		return row * SQUARE_SIZE;
	}
	
	//setInView sets the square in view
	public void setInView(boolean inView){
		this.inView = inView;
		if(seen == false) seen = inView; // If it hasn't been seen make it seen.
	}

	//sets the treasure to a given treasure object
	public void setTreasure(Treasure t){
		this.treasure = t;
	}

	//checks to see if the treasure has been found or not.
	public void enter(){
		if(treasure != null){
			treasure.setFound();
		}
	}
	public String toText(char delimiter){
		return this.getClass().getName() + delimiter +
				row + delimiter +
				col + delimiter +
				walls[UP] + delimiter + 
				walls[RIGHT] + delimiter +
				walls[DOWN] + delimiter + 
				walls[LEFT] + delimiter + 
				seen() + delimiter + 
				inView();
	}
	public void toObject(Scanner input){
		walls[UP] = input.nextBoolean();
		walls[RIGHT] = input.nextBoolean();
		walls[DOWN] = input.nextBoolean();
		walls[LEFT] = input.nextBoolean();
		seen = input.nextBoolean();
		inView = input.nextBoolean();
	}

}
