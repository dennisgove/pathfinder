# Pathfinder

Pathfinder is a game whose goal is to get from the top-left corner to the bottom-right corner of our game board, avoiding any rocks in our way. The board is a standard rectangular shape of some width by some height with rocks randomly placed throughout the board. There is guaranteed to be at least one valid path, though most board setups will contain many valid paths.

### Our Goal

For each turn, the board will ask us if we want to move `LEFT`, `RIGHT`, `UP`, or `DOWN` and it is up to us to decide which is the best move to make. Our goal is to get to the bottom-right corner WITHOUT going off the board or running into a rock.

### Our Board

This shows a 10 x 10 board with 10 randomly placed rocks. The player appears in the top-right corner, denoted by the `o` character. Rocks are denoted by the `@` character and open spaces with a `.`.

```
         Starting Board                            Winning Board
________________________________          ________________________________
| o  .  .  .  .  .  .  .  .  @ |          | .  .  .  .  .  .  .  .  .  @ |
| @  .  .  .  .  .  .  @  .  . |          | @  .  .  .  .  .  .  @  .  . |
| .  .  .  .  .  .  .  .  .  . |          | .  .  .  .  .  .  .  .  .  . |
| @  .  .  @  @  .  .  .  .  . |          | @  .  .  @  @  .  .  .  .  . |
| .  .  .  .  .  .  .  .  .  . |          | .  .  .  .  .  .  .  .  .  . |
| .  .  @  .  .  .  .  .  .  . |          | .  .  @  .  .  .  .  .  .  . |
| .  @  .  @  .  .  .  .  .  . |          | .  @  .  @  .  .  .  .  .  . |
| .  .  .  .  .  .  .  .  .  . |          | .  .  .  .  .  .  .  .  .  . |
| .  .  .  .  .  .  .  .  .  . |          | .  .  .  .  .  .  .  .  .  . |
| @  .  .  .  .  .  .  .  .  . |          | @  .  .  .  .  .  .  .  .  $ |
________________________________          ________________________________
                                                                         
                                                                             
          Hit a Rock                               Went off Board
________________________________          ________________________________
| .  .  .  .  .  .  .  .  .  @ |          | .  .  .  .  .  .  .  .  .  @ |
| @  .  .  .  .  .  .  @  .  . |          | @  .  .  .  .  .  .  @  .  . |
| .  .  .  .  .  .  .  .  .  . |          | .  .  .  .  .  .  .  .  .  . |
| @  .  .  X  @  .  .  .  .  . |          | @  .  .  @  @  .  .  .  .  . X
| .  .  .  .  .  .  .  .  .  . |          | .  .  .  .  .  .  .  .  .  . |
| .  .  @  .  .  .  .  .  .  . |          | .  .  @  .  .  .  .  .  .  . |
| .  @  .  @  .  .  .  .  .  . |          | .  @  .  @  .  .  .  .  .  . |
| .  .  .  .  .  .  .  .  .  . |          | .  .  .  .  .  .  .  .  .  . |
| .  .  .  .  .  .  .  .  .  . |          | .  .  .  .  .  .  .  .  .  . |
| @  .  .  .  .  .  .  .  .  . |          | @  .  .  .  .  .  .  .  .  $ |
________________________________          ________________________________

```
