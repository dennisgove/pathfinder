package pathfinder;

/**
 * Moves we can make on the board<br>
 * {@link Move.LEFT} Move left<br>
 * {@link Move.RIGHT} Move right<br>
 * {@link Move.UP} Move up<br>
 * {@link Move.DOWN} Move down
 */
public enum Move {
  LEFT,
  RIGHT,
  UP,
  DOWN;

  public static Move opposite(Move incoming){
    switch(incoming){
      case DOWN:
        return Move.UP;
      case UP:
        return Move.DOWN;
      case RIGHT:
        return Move.LEFT;
      case LEFT:
      default:
        return Move.RIGHT;

    }
  }
}
