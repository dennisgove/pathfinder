package pathfinder;

public class Main {

  public static void main(String[] args) throws InterruptedException {

    int width = 10;
    int height = 10;
    int numberRocks = 0;

    new GameBoard()
    .setMoveSelector((board, position) -> {
      return Move.RIGHT;
    })
    .play(width, height, numberRocks);
  }
}
