/**
 *DrawableSquare.java
 *Class DrawableSquare draws the square on the grid
 *
 *@author akramins
 *@version 5/13/2013
 */
public class DrawableSquare extends Square implements Drawable{
	public DrawableSquare(boolean up, boolean right, boolean down, boolean left, int row, int col){
		super(up, right, down, left, row, col);
	}
	public void draw(){
		//IGNORE THIS PART
		/*(if(super.inView()){
			stroke(0);
			fill(#D9A7FF);
			rect(super.x(), super.y(), super.x(), super.y() + Square.SQUARE_SIZE, super.x() + Square.SQUARE_SIZE, super.y() + Square.SQUARE_SIZE, super.x() + Square.SQUARE_SIZE, super.y());
		}
		if(super.seen() && !super.inView()){
			stroke(0);
			fill(#CB86FF);
			rect(super.x(), super.y(), super.x(), super.y() + Square.SQUARE_SIZE, super.x() + Square.SQUARE_SIZE, super.y() + Square.SQUARE_SIZE, super.x() + Square.SQUARE_SIZE, super.y()))
		}*/
	}
	

}
