package pathfinder;

@FunctionalInterface
public interface MoveSelector {
  public Move apply(GameBoard board, Position position);
}
