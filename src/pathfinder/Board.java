package pathfinder;

import java.util.Random;

public class Board {

  public enum Piece {
    OFF_BOARD,
    GOAL,
    EMPTY,
    ROCK
  }

  private Random rand = new Random();

  private final int width;
  private final int height;

  private Piece[][] board;
  private int playerX;
  private int playerY;

  public Board(int width, int height){
    this.width = width;
    this.height = height;
  }

  public int getWidth(){
    return width;
  }

  public int getHeight(){
    return height;
  }

  public int getPlayerX(){
    return playerX;
  }

  public int getPlayerY(){
    return playerY;
  }

  public Piece getPieceAt(int x, int y){
    if(x >= 0 && x < width && y >= 0 && y < height){
      return board[x][y];
    }

    return Piece.OFF_BOARD;
  }

  public void moveTo(int x, int y){
    playerX = x;
    playerY = y;
  }

  public void resetLayout(int totalRocks){
    board = new Piece[width][height];
    for(int x = 0; x < width; ++x){
      for(int y = 0; y < height; ++y){
        board[x][y] = Piece.EMPTY;
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

        if(Piece.EMPTY == board[x][y]){
          // Set the space to have a rock
          board[x][y] = Piece.ROCK;

          // Check that the board is still valid after placing a rock
          if(isValidBoard()){
            // The board is valid with this rock placed so we can go to the next rock
            break;
          }

          // The board is not valid with this rock placed here so remove it and try again
          board[x][y] = Piece.EMPTY;
        }
      }
    }

    board[width - 1][height - 1] = Piece.GOAL;

    playerX = 0;
    playerY = 0;
  }

  public boolean isEndgame(){
    return didWin() || didLose();
  }

  public boolean didWin(){
    return !didLose() && (Piece.GOAL == board[playerX][playerY]);
  }

  public boolean didLose(){
    return
        playerX < 0 ||
        playerX >= width ||
        playerY < 0 ||
        playerY >= height ||
        Piece.ROCK == board[playerX][playerY];
  }

  public void print(){
    print(null);
  }

  public void print(String header){
    System.out.println(new String(new char[(getWidth() * 3) + 10]).replace("\0", "-"));
    if(null != header){
      System.out.println(header);
    }
    System.out.println();
    System.out.println(toString());

  }

  @Override
  public String toString(){
    StringBuilder builder = new StringBuilder();

    for(int y = 0; y < height; ++y){
      for(int x = 0; x < width; ++x){

        if(0 == x){
          builder.append("|");
        }

        builder.append(" ");
        switch(board[x][y]){
          case EMPTY:
          case GOAL:
            if(playerX == x && playerY == y){
              builder.append("o");
            }
            else{
              builder.append(".");
            }
            break;
          case ROCK:
            if(playerX == x && playerY == y){
              builder.append("*");
            }
            else{
              builder.append("X");
            }
            break;
        }
        builder.append(" ");

        if(width - 1 == x){
          builder.append("|");
        }

      }

      builder.append("\n");
    }

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

    if(Piece.ROCK == board[fromX][fromY]){
      return 0;
    }

    if(fromX == endX && fromY == endY){
      return 1;
    }

    return numberOfPaths(fromX + 1, fromY) + numberOfPaths(fromX, fromY + 1);
  }
}
