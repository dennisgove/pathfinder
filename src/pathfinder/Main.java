package pathfinder;

import java.util.Random;

public class Main {

  public static void main(String[] args) throws InterruptedException {

    // Object: Get to the bottom-right corner of the game board
    // To Start:
    //    VS Code: Click the Debug button directly above this method
    //    Eclipse: right-click on Main.java and choose "Run as Java Application"
    //             ^^^ generally the f5 key is a shortcut for this in both

    int width = 10;
    int height = 10;
    int numberRocks = 20 ;

    Random myRandom = new Random();

    new GameBoard()
    .setMoveSelector((board, position) -> {
      // We need to decide the appropriate next move

      // Move[] array = new Move[]{ Move.DOWN,Move.DOWN,Move.DOWN,Move.DOWN,Move.DOWN,Move.DOWN,Move.DOWN, Move.RIGHT,Move.RIGHT,Move.RIGHT,Move.RIGHT,Move.RIGHT,Move.RIGHT,Move.RIGHT,Move.RIGHT,Move.RIGHT, Move.LEFT, Move.UP };

      // while(true){
      //   int idx = myRandom.nextInt(array.length);
      //   Move move = array[idx];

      //   Position nextPosition = position.move(move);
        
      //   if(board.getSpotAt(nextPosition) == Spot.EMPTY || board.getSpotAt(nextPosition) == Spot.GOAL){
      //     return move;
      //   }
      // }

      return Move.RIGHT;
    })
    .play(width, height, numberRocks);
  }
}
