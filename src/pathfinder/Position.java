package pathfinder;

/**
 * Describes a position in a x/y coordinate system.
 */
public class Position {

  /**Position left to right, starting at 0*/
  public int x;

  /**Position top to bottom, starting at 0*/
  public int y;

  public Position(int x, int y){
    this.x = x;
    this.y = y;
  }

  /**Return what the position would be if we moved left*/
  public Position left(){
    return move(Move.LEFT);
  }

  /**Return what the position would be if we moved right*/
  public Position right(){
    return move(Move.RIGHT);
  }

  /**Return what the position would be if we moved up*/
  public Position up(){
    return move(Move.UP);
  }

  /**Return what the position would be if we moved down*/
  public Position down(){
    return move(Move.DOWN);
  }

  /**
   * Return what the position would be if we moved in a direction
   * @param move The {@link Move} to make
   */
  public Position move(Move move){
    switch(move){
      case UP:
        return new Position(x, y - 1);
      case DOWN:
        return new Position(x, y + 1);
      case LEFT:
        return new Position(x - 1, y);
      case RIGHT:
        return new Position(x + 1, y);
    }

    return new Position(x, y);
  }

  @Override
  public int hashCode() {
    return x * y;
  }

  @Override
  public boolean equals(Object obj) {
    if(obj instanceof Position){
      Position other = (Position)obj;
      return x == other.x && y == other.y;
    }

    return false;
  }

  @Override
  public String toString(){
    return String.format("x=%d, y=%d", x, y);
  }
}
