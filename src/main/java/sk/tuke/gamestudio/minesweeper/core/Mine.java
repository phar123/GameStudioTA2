package sk.tuke.gamestudio.minesweeper.core;

/**
 * Mine tile.
 */
public class Mine extends Tile {

    @Override
    public String toString() {
        if (getState() == State.MARKED) {
            return "M";
        } else if (getState() == State.OPEN){
            return "X";
        } else {
    return "-";
        }
    }
}
