package pathfinder;

public class Main {

  public static void main(String[] args) throws InterruptedException {

    // Object: Get to the bottom-right corner of the game board
    // To Start:
    //    VS Code: Click the Debug button directly above this method
    //    Eclipse: right-click on Main.java and choose "Run as Java Application"
    //             ^^^ generally the f5 key is a shortcut for this in both

    int width = 10;
    int height = 10;
    int numberRocks = 30;


    new GameBoard()
    .setMoveSelector((board, position) -> {
      // We need to decide the appropriate next move
      Spot spotRight = board.getSpotAt(position.right());
      Spot spotDown = board.getSpotAt(position.down());
      Spot spotUp = board.getSpotAt(position.up());
      Spot spotLeft = board.getSpotAt(position.left());

      if(false == board.haveVisited(position.right()) && spotRight != Spot.ROCK && spotRight != Spot.OFF_BOARD){
        return Move.RIGHT;
      }
      if(false == board.haveVisited(position.down()) && spotDown != Spot.ROCK && spotDown != Spot.OFF_BOARD){
        return Move.DOWN;
      }
      if(false == board.haveVisited(position.left()) && spotLeft != Spot.ROCK && spotLeft != Spot.OFF_BOARD){
        return Move.LEFT;
      }
      if(false == board.haveVisited(position.up()) && spotUp != Spot.ROCK && spotUp != Spot.OFF_BOARD){
        return Move.UP;
      }


      if(spotUp != Spot.ROCK && spotUp != Spot.OFF_BOARD){
        return Move.UP;
      }
      if(spotLeft != Spot.ROCK && spotLeft != Spot.OFF_BOARD){
        return Move.LEFT;
      }
      if(spotDown != Spot.ROCK && spotDown != Spot.OFF_BOARD){
        return Move.DOWN;
      }
      // if(spotRight != Spot.ROCK && spotRight != Spot.OFF_BOARD){
        return Move.RIGHT;
      // }

    })
    .play(width, height, numberRocks);
  }
}
