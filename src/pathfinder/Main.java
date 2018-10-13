package pathfinder;

public class Main {

  public static void main(String[] args) throws InterruptedException {

    // Object: Get to the bottom-right corner of the game board
    // To Start: right-click on Main.java and choose "Run as Java Application"
    //           ^^^ generally the f5 key is a shortcut for this

    int width = 10;
    int height = 10;
    int numberRocks = 0;

    new GameBoard()
    .setMoveSelector((board, position) -> {
      // We need to decide the appropriate next move

      return Move.RIGHT;
    })
    .play(width, height, numberRocks);
  }
}
