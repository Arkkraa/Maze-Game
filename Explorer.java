/**
 *Explorer.java
 *Class Explorer builds an Explorer object
 *
 *@author akramins
 *@version 5/13/2013
 */
import java.awt.event.KeyEvent;
import java.util.Scanner;

public class Explorer extends Occupant{
	private String name;
	private Maze maze;
	public Explorer(Maze maze){
		this.maze = maze;
		name = new String();
	}
	public Explorer(Square location, Maze maze, String name){
		super(location);
		this.maze = maze;
		this.name = name;
		maze.lookAround(location);
	}
	//returns the name of the explorer.
	public String name(){
		return this.name;
	}
	//moves the explorer to a given square based on a key event.
	public void move(int key){
		int row = super.location().row();
		int col = super.location().col();
		if(key == KeyEvent.VK_UP || key == KeyEvent.VK_KP_UP){ //Checking for keyevent and in what direction
			if(!super.location().wall(Square.UP)){ //checking for a wall in the direction.
				moveTo(maze.getSquare(row - 1, col));
			}  	 
		}
		else if(key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_KP_RIGHT){
			if(!super.location().wall(Square.RIGHT)){
				moveTo(maze.getSquare(row, col + 1));
			}
		}
		else if(key == KeyEvent.VK_DOWN || key == KeyEvent.VK_KP_DOWN){
			if(!super.location().wall(Square.DOWN)){
				moveTo(maze.getSquare(row + 1, col));
			}
		}
		else if(key == KeyEvent.VK_LEFT || key == KeyEvent.VK_KP_LEFT){
			if(!super.location().wall(Square.LEFT)){
				moveTo(maze.getSquare(row, col - 1));
			}
		}
	}
	//moveTo moves the explorer to a given square.
	public void moveTo(Square s){
		super.moveTo(s);
		s.enter();
		maze.lookAround(s); //looks around for walls on its sides.
	}
	public String toText(char delimiter){
		return  super.toText(delimiter) + delimiter + name(); 
	}
	public void toObject(Scanner input){
		moveTo(maze.getSquare(input.nextInt(), input.nextInt()));
		name = input.next();
	}
}
