package pathfinder;

/**
 * Possible spots on the board<br>
 * {@link Spot.OFF_BOARD} Off the board<br>
 * {@link Spot.GOAL} This is the goal spot<br>
 * {@link Spot.EMPTY} This spot has nothing in it<br>
 * {@link Spot.ROCK} This spot has a rock in it
 */
public enum Spot {
  OFF_BOARD,
  GOAL,
  EMPTY,
  ROCK
}
