package pathfinder;

import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Main {

  public static void main(String[] args) throws InterruptedException {

    // Object: Get to the bottom-right corner of the game board
    // To Start:
    //    VS Code: Click the Debug button directly above this method
    //    Eclipse: right-click on Main.java and choose "Run as Java Application"
    //             ^^^ generally the f5 key is a shortcut for this in both

    int width = 40;
    int height = 10;
    int numberRocks = 150;

    List<Move> movesToMake = new ArrayList<>();

    new GameBoard()
    .setMoveSelector((board, position) -> {
      // We need to decide the appropriate next move

      if(0 == movesToMake.size()){
        movesToMake.addAll(findMoves(board, position, new HashSet<>()));
      }

      return movesToMake.remove(0);
    })
    .play(width, height, numberRocks);
  }

  private static List<Move> findMoves(GameBoard board, Position from, Set<Position> alreadyChecked){
    if(alreadyChecked.contains(from)){
      return null;
    }

    alreadyChecked.add(from);

    switch(board.getSpotAt(from)){
      case OFF_BOARD:
      case ROCK:
        return null;
      case GOAL:
        return new ArrayList<>();
    }

    List<Move> after;
    
    after = findMoves(board, from.right(), alreadyChecked);
    if(null != after){
      after.add(0, Move.RIGHT);
      return after;
    }

    after = findMoves(board, from.down(), alreadyChecked);
    if(null != after){
      after.add(0, Move.DOWN);
      return after;
    }

    after = findMoves(board, from.up(), alreadyChecked);
    if(null != after){
      after.add(0, Move.UP);
      return after;
    }

    after = findMoves(board, from.left(), alreadyChecked);
    if(null != after){
      after.add(0, Move.LEFT);
      return after;
    }

    return null;
  }
}
