package pathfinder;

import java.util.Random;

public class Main {

  static Random random = new Random();

  public static void main(String[] args) throws InterruptedException {

    int width = 10;
    int height = 10;
    int numberOfRocks = 1;

    Board board = new Board(width, height);
    board.resetLayout(numberOfRocks);

    board.print("Starting state");

    int moveNumber = 0;
    do{
      Thread.sleep(1200);
      moveNumber = moveNumber + 1;
      makeMove(board);

      board.print("Move: " + moveNumber);
    }while(!board.isEndgame() && moveNumber < 100);

    if(board.didWin()){
      System.out.println("You Win!");
    }
    else{
      System.out.println("You Lose!");
    }
  }

  private static void makeMove(Board board){

    int newX = board.getPlayerX();
    int newY = board.getPlayerY();

    int val = random.nextInt(100);
    if(val < 40){
      // Move right
      newX = board.getPlayerX() + 1;
    }
    else if(val < 50){
      // Move left
      newX = board.getPlayerX() - 1;
    }
    else if(val < 90){
      // Move down
      newY = board.getPlayerY() + 1;
    }
    else{
      // Move up
      newY = board.getPlayerY() - 1;
    }

    board.moveTo(newX, newY);
  }
}
