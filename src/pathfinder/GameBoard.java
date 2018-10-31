package pathfinder;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class GameBoard {

  private int delay = 1000;

  private int width;
  private int height;

  private Spot[][] board;
  private Position position;
  private HashMap<Position, Integer> visited;
  private MoveSelector moveSelector;

  /**
   * Set the function to be called when the next move must be selected
   * @param moveSelector The {@link MoveSelector} function to call when we need to make a move
   */
  public GameBoard setMoveSelector(MoveSelector moveSelector){
    this.moveSelector = moveSelector;
    return this;
  }

  /**
   * Begin and play a new game to completion
   * @param width The total width of the board, > 0
   * @param height The total height of the board, > 0
   * @param numberRocks The total number of rocks, >= 0
   */
  public void play(int width, int height, int numberRocks) throws InterruptedException{
    this.width = width;
    this.height = height;
    this.visited = new HashMap<>();

    resetLayout(numberRocks);

    int currentMove = 0;

    while(!isEndgame()){
      print(String.format("Board after %d move%s", currentMove, currentMove == 1 ? "" : "s"));
      Thread.sleep(delay);

      Move selectedMove = moveSelector.apply(this, position);
      moveTo(position.move(selectedMove));

      ++currentMove;
    }

    print(String.format("End of Game: %s", didWin() ? "You won!" : "You lost  :("));
  }

  /**
   * Return whether the player has already visited the given position
   * @param position {@link Position} to check
   * @return true if player has visited the position, false else
   */
  public boolean haveVisited(Position position){
    return visited.containsKey(position) && visited.get(position) > 0;
  }

  /**
   * Return the {@link Spot} on the board at the given position
   * @param position {@link Position} to get spot for
   * @return {@link Spot} of the board at the provided position
   */
  public Spot getSpotAt(Position position){
    if(position.x >= 0 && position.x < width && position.y >= 0 && position.y < height){
      return board[position.x][position.y];
    }

    return Spot.OFF_BOARD;
  }

  private void moveTo(Position position){
    this.position = position;

    visited.putIfAbsent(position, 0);
    visited.put(position, visited.get(position) + 1);
  }

  public int visitedCount(Position position){
    return visited.getOrDefault(position, 0);
  }

  private void resetLayout(int totalRocks){
    Random rand = new Random();

    board = new Spot[width][height];
    for(int x = 0; x < width; ++x){
      for(int y = 0; y < height; ++y){
        board[x][y] = Spot.EMPTY;
      }
    }

    // For each rock, find a random place on the board to put it
    for(int rocks = 0; rocks < totalRocks; ++rocks){

      // Continue searching for a spot until we find a valid one.
      // If we find a valid spot we will break out of this loop
      // via the `break;` statement
      while(true){
        int x = rand.nextInt(width);
        int y = rand.nextInt(height);

        if(Spot.EMPTY == board[x][y]){
          // Set the space to have a rock
          board[x][y] = Spot.ROCK;

          // Check that the board is still valid after placing a rock
          if(isValidBoard()){
            // The board is valid with this rock placed so we can go to the next rock
            break;
          }

          // The board is not valid with this rock placed here so remove it and try again
          board[x][y] = Spot.EMPTY;
        }
      }
    }

    board[width - 1][height - 1] = Spot.GOAL;

    moveTo(new Position(0, 0));
  }

  private boolean isEndgame(){
    return didWin() || didLose();
  }

  private boolean didWin(){
    return !didLose() && (Spot.GOAL == board[position.x][position.y]);
  }

  private boolean didLose(){
    return
        position.x < 0 ||
        position.x >= width ||
        position.y < 0 ||
        position.y >= height ||
        Spot.ROCK == board[position.x][position.y];
  }

  private void print(String header){
    System.out.println(new String(new char[(width * 3) + 10]).replace("\0", "-"));

    System.out.println();
    if(null != header){
      System.out.println(header);
    }
    System.out.println(String.format("Position is %s", position));
    System.out.println(toString());

  }

  @Override
  public String toString(){
    StringBuilder builder = new StringBuilder();

    builder.append("_");
    for(int x = 0; x < width; ++x){
      if(position.y < 0 && x == position.x){
        builder.append("_X_");
      }
      else{
        builder.append("___");
      }
    }
    builder.append("_\n");

    for(int y = 0; y < height; ++y){
      for(int x = 0; x < width; ++x){

        if(0 == x){
          if(position.x < 0 && y == position.y){
            builder.append("X");
          }
          else{
            builder.append("|");
          }
        }

        builder.append(" ");
        switch(board[x][y]){
          case EMPTY:
            if(position.x == x && position.y == y){
              builder.append("o");
            }
            else{
              builder.append(".");
            }
            break;
          case GOAL:
            if(position.x == x && position.y == y){
              builder.append("$");
            }
            else{
              builder.append(".");
            }
            break;
          case ROCK:
            if(position.x == x && position.y == y){
              builder.append("X");
            }
            else{
              builder.append("@");
            }
            break;
          case OFF_BOARD:
            break; // never gonna happen
        }
        builder.append(" ");

        if(width - 1 == x){
          if(position.x >= width && y == position.y){
            builder.append("X");
          }
          else{
            builder.append("|");
          }
        }

      }

      builder.append("\n");
    }

    builder.append("_");
    for(int x = 0; x < width; ++x){
      if(position.y >= height && x == position.x){
        builder.append("_X_");
      }
      else{
        builder.append("___");
      }
    }
    builder.append("_\n");

    return builder.toString();
  }

  private boolean isValidBoard(){
    return numberOfPaths(0, 0) > 0;
  }

  private int numberOfPaths(int fromX, int fromY){
    int endX = width - 1;
    int endY = height - 1;

    if(fromX >= width || fromY >= height){
      return 0;
    }

    if(Spot.ROCK == board[fromX][fromY]){
      return 0;
    }

    if(fromX == endX && fromY == endY){
      return 1;
    }

    return numberOfPaths(fromX + 1, fromY) + numberOfPaths(fromX, fromY + 1);
  }
}
