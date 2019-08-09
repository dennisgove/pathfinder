package pathfinder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {

  public static void main(String[] args) throws InterruptedException {

    // Object: Get to the bottom-right corner of the game board
    // To Start:
    //    VS Code: Click the Debug button directly above this method
    //    Eclipse: right-click on Main.java and choose "Run as Java Application"
    //             ^^^ generally the f5 key is a shortcut for this in both

    int width = 30;
    int height = 30;
    int numberRocks = 40;

    List<Move> bestPath = new ArrayList<>();

    new GameBoard()
    .setMoveSelector((board, position) -> {
      // We need to decide the appropriate next move

      // if we have a path, follow it
      // else find best path
      if(bestPath.size() == 0){
        bestPath.addAll(findSomePathToGoal(board, position));
        if(bestPath.size() == 0){
          // error state
          throw new RuntimeException("Should never happen");
        }
      }

      return bestPath.remove(0);
    })
    .play(width, height, numberRocks);
  }

  public static List<Move> findSomePathToGoal(GameBoard board, Position position){
    // figure out the best path

    List<List<Move>> allPaths = new ArrayList<>();

    for(Move move : new Move[]{ Move.RIGHT, Move.LEFT, Move.UP, Move.DOWN}){
      if(isPossibleMove(board, position, move)){
        // good move
        List<Move> possiblePath = new ArrayList<>();
        possiblePath.add(move);
        allPaths.add(possiblePath);        
      }
    }

    for(int idx = 0; idx < allPaths.size(); idx = idx + 1){
      List<Move> somePath = allPaths.get(idx);
    
      List<List<Move>> nextPaths = findNextPaths(board, somePath);

      for(List<Move> checkPath : nextPaths){
        Position currentPosition = new Position(0,0);
        for(Move move : checkPath){
          currentPosition = currentPosition.move(move);
        }

        if(Spot.GOAL == board.getSpotAt(currentPosition)){
          return checkPath;
        }
      }

      allPaths.addAll(nextPaths);
    }

    return new ArrayList<>();

  }

  public static List<List<Move>> findNextPaths(GameBoard board, List<Move> currentPath){
    Position currentPosition = new Position(0,0);
    Set<Position> seenPositions = new HashSet<>();
    for(Move move : currentPath){
      seenPositions.add(currentPosition);
      currentPosition = currentPosition.move(move);
    }

    List<List<Move>> newPaths = new ArrayList<>();

    if(Spot.GOAL == board.getSpotAt(currentPosition)){
      return newPaths;
    }

    // currentPosition is end of path
    for(Move move : new Move[]{ Move.RIGHT, Move.LEFT, Move.UP, Move.DOWN}){
      if(!seenPositions.contains(currentPosition.move(move))){
        if(isPossibleMove(board, currentPosition, move)){
          List<Move> newPath = currentPath.stream().collect(Collectors.toList());
          newPath.add(move);
          newPaths.add(newPath);
        }
      }
    }

    return newPaths;
  }

  public static boolean isPossibleMove(GameBoard board, Position currentPosition, Move nextMove){
    Spot newSpot = board.getSpotAt(currentPosition.move(nextMove));
    return newSpot == Spot.EMPTY || newSpot == Spot.GOAL;
  }

}
