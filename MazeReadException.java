/**
 * Class that contains all the logic to model a Maze with Treasures, Monsters, and an Explorer.
 * 
 * @author Arkadiy Kraminsky
 * @version 5/09/2013
 */
import java.util.*;
import java.io.*;
public class Maze{
  // named constants
  public static final int ACTIVE = 0;
  public static final int EXPLORER_WIN = 1;
  public static final int MONSTER_WIN = 2;
  
  // instance variables
  private Square[][] maze;
  private ArrayList<RandomOccupant> randOccupants;
  private Explorer explorer;
  private int rows;
  private int cols;

  /**
  *Default constructor creates the array list.
  */
  public Maze(){
    randOccupants = new ArrayList<RandomOccupant>();
  }
  
  /**
  * Constructor for objects of class Maze
  */
  public Maze(Square[][] maze, int rows, int cols){
	randOccupants = new ArrayList<RandomOccupant>();
    this.maze = maze;
    this.rows = rows;
    this.cols = cols;
  }
	
  // QUERIES
  public Square getSquare(int row, int col){ 
    return maze[row][col]; 
  }
  public int rows(){
    return rows;
  }
  public int cols(){
    return cols;
  }
  public String explorerName(){
    return explorer.name();
  }
  public Explorer getExplorer(){
    return explorer;
  }
    
  // CHANGE - Implement the following two methods.  I have them stubbed to return dummy values just so it will compile.
  //          Your getRandomOccupant should return the occupant from the ArrayList at the specified index.
  public RandomOccupant getRandomOccupant(int index){
    return randOccupants.get(index);
  }
  public int getNumRandOccupants(){
    return randOccupants.size();
  }
  public void addRandomOccupant(RandomOccupant ro){
    randOccupants.add(ro);
  }
	
  public void setExplorer(Explorer e){
    explorer = e;
  }
	
  public void explorerMove(int key){
    explorer.move(key);
  }
	
  public void randMove(){
    for(RandomOccupant ro : randOccupants){
      ro.move();
    }
  }
	
  /**
  * Returns the status of the game.
  *
  * If all treasures have been found, return EXPLORER_WIN.
  * If not, check each maze occupant, if it is a Monster and
  *   it is in the same location as the Explorer, return
  *   MONSTER_WIN.  Note that you can use == to check locations, do you know why?
  * Otherwise, return ACTIVE.
  */
  public int gameStatus(){
    int status = ACTIVE;
    if(foundAllTreasures()) return EXPLORER_WIN;
    for(RandomOccupant ro : randOccupants){
        if(ro instanceof Monster){
          if(explorer.location() == ro.location()) return MONSTER_WIN;
        }
    }
    return status;
  }

  private boolean foundAllTreasures(){
    boolean foundAll = true;
    for(RandomOccupant ro : randOccupants){
      if(ro instanceof Treasure){
        Treasure t = (Treasure) ro;
        if(!t.found()) return false;
      }
    }    
    return foundAll;
  }
    
  public void lookAround(Square s){
    int row = s.row();
    int col = s.col();
        
    // Clear what was previously in view
    resetInView();
        
    // Set the current square so that we are viewing it (obviously)
    s.setInView(true);
    
    if(!s.wall(Square.UP)){
      getSquare(row - 1, col).setInView(true);
      if(!getSquare(row - 1, col).wall(Square.LEFT)) getSquare(row - 1, col - 1).setInView(true); 
      if(!getSquare(row - 1, col).wall(Square.RIGHT)) getSquare(row - 1, col + 1).setInView(true);
    }
    if(!s.wall(Square.RIGHT)){
      getSquare(row, col + 1).setInView(true);
      if(!getSquare(row, col + 1).wall(Square.UP)) getSquare(row - 1, col + 1).setInView(true); 
      if(!getSquare(row, col + 1).wall(Square.DOWN)) getSquare(row + 1, col + 1).setInView(true);
    }
    if(!s.wall(Square.LEFT)){
      getSquare(row, col - 1).setInView(true);
      if(!getSquare(row, col - 1).wall(Square.UP)) getSquare(row - 1, col - 1).setInView(true);
      if(!getSquare(row, col - 1).wall(Square.DOWN)) getSquare(row + 1, col - 1).setInView(true);
      }  
    if(!s.wall(Square.DOWN)){
      getSquare(row + 1, col).setInView(true);
      if(!getSquare(row + 1, col).wall(Square.LEFT)) getSquare(row + 1, col - 1).setInView(true);
      if(!getSquare(row + 1, col).wall(Square.RIGHT)) getSquare(row + 1, col + 1).setInView(true);
    } 
  
  }
    
  private void resetInView(){
    for (int i = 0; i<rows; i++){
      for (int j = 0; j<cols; j++){
        maze[i][j].setInView(false);
      }
    }
  }

  public void writeMazeToFile(String filename) throws IOException{
      BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
      bw.write(rows()+","+cols());
      bw.newLine();
      for(int i = 0; i < rows(); i++){
        for(int j = 0; j < cols(); j++){
          bw.write(getSquare(i, j).toText(','));
          bw.newLine();
        } 
      }
      bw.write(explorer.toText(','));
      bw.newLine();
      for(int i = 0; i < getNumRandOccupants(); i++){
        bw.write(getRandomOccupant(i).toText(','));
        bw.newLine();
      }
      bw.close();

  }
  public void readMazeFromFile(String filename) throws IOException, FileNotFoundException, MazeReadException{
    Scanner fs = new Scanner(new File(filename));
    String line = fs.nextLine();
    Scanner ls = new Scanner(line);
    ls.useDelimiter(",");
    int count = 1;
    if(!ls.hasNextInt()) throw new MazeReadException("Rows and columns not specified.", line, count);
    int r = ls.nextInt();
    int c = ls.nextInt();
    this.maze = new Square[r][c];
    this.rows = r;
    this.cols = c;
      while(fs.hasNextLine()){
        line = fs.nextLine();
        ls = new Scanner(line);
        ls.useDelimiter(",");
        String type = null;
        try{
          count++;
          type = ls.next();
        }catch(NoSuchElementException e){
          throw new MazeReadException("Line format or other error.", line, count);
        }
        if(type.equals("Square")){
          r = ls.nextInt();
          c = ls.nextInt();
          if(getSquare(r,c) != null) throw new MazeReadException("Duplicate square.", line, count);
          this.maze[r][c] = new Square(r,c);
          try{
            getSquare(r,c).toObject(ls);
          }catch(Exception e){
            throw new MazeReadException("Line format or other error.", line, count);
          }
       }
       else if(type.equals("Explorer")){
          this.explorer = new Explorer(this);
          try{
            explorer.toObject(ls);
          }catch(Exception e){
            throw new MazeReadException("Line format or other error.", line, count);
          }
       }
        else if(type.equals("Treasure")){
          Treasure t = new Treasure(this);
          try{
            t.toObject(ls);
          }catch(Exception e){
            throw new MazeReadException("Line format or other error.", line, count);
          }
          addRandomOccupant(t);
        }
        else if(type.equals("Monster")){
          Monster m = new Monster(this);
          try{
            m.toObject(ls);
         }catch(Exception e){
            throw new MazeReadException("Line format or other error.", line, count);
          }
          addRandomOccupant(m);
        }
        else throw new MazeReadException("Unknown type.", line, count);
      }
    

    
  }  

}
