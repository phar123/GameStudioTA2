package sk.tuke.gamestudio.minesweeper.core;

import sk.tuke.gamestudio.minesweeper.Minesweeper;

import java.lang.reflect.MalformedParametersException;
import java.util.Random;

/**
 * Field represents playing field and game logic.
 */
public class Field {
    /**
     * Playing field tiles.
     */
    private final Tile[][] tiles;

    private final int rowCount;

    private final int columnCount;

    private final int mineCount;

    private GameState state = GameState.PLAYING;
    private long startMillis;

    /**
     * Constructor.
     *
     * @param rowCount    row count
     * @param columnCount column count
     * @param mineCount   mine count
     */
    public Field(int rowCount, int columnCount, int mineCount) {
        this.rowCount = rowCount;
        this.columnCount = columnCount;
        this.mineCount = mineCount;
        tiles = new Tile[rowCount][columnCount];

        System.out.println("Row: "+ rowCount);
        System.out.println("Column: "+ columnCount);
        System.out.println("Count: "+ mineCount);

        //generate the field content
        generate();

    }

    /**
     * Opens tile at specified indeces.
     *
     * @param row    row number
     * @param column column number
     */
    public void openTile(int row, int column) {
        //System.out.print("Som v openTile!!!" + row + column);
        Tile tile = tiles[row][column];
        if (tile.getState() == Tile.State.CLOSED) {
            tile.setState(Tile.State.OPEN);
            if (tile instanceof Clue
                    && ((Clue) tile).getValue() == 0) {
                getOpenAdjacentTiles(row, column);
            }
            if (tile instanceof Mine) {
                state = GameState.FAILED;
                return;
            }

            if (isSolved()) {
                state = GameState.SOLVED;
                return;
            }
        }
    }


    private void getOpenAdjacentTiles(int row, int column) {
        for (int rowOffset = -1; rowOffset <= 1; rowOffset++) {
            int actRow = row + rowOffset;
            if (actRow >= 0 && actRow < rowCount) {
                for (int columnOffset = -1; columnOffset <= 1; columnOffset++) {
                    int actColumn = column + columnOffset;
                    if (actColumn >= 0 && actColumn < columnCount) {
                        openTile(actRow, actColumn);
                    }
                }
            }
        }
    }




    /**
     * Marks tile at specified indeces.
     *
     * @param row    row number
     * @param column column number
     */
   public void markTile(int row, int column) {
       //throw new UnsupportedOperationException("Method markTile not yet implemented");
       // Tile tile = tiles[row][column];
       if (tiles[row][column].getState() == Tile.State.MARKED) {
           tiles[row][column].setState(Tile.State.CLOSED);
       } else {

           tiles[row][column].setState(Tile.State.MARKED);
       }
   }
    /**
     * Generates playing field.
     */
    private void generate() {
        //throw new UnsupportedOperationException("Method generate not yet implemented");

        if (mineCount >= (rowCount*columnCount))
            throw new MalformedParametersException("Count of mines too large");


        Random r = new Random();
        int m = 0;
        while (m < mineCount){
           int pos_x = r.nextInt(rowCount);
           int pos_y = r.nextInt(columnCount);

          /*  System.out.println("Random X: " + pos_x);
            System.out.println("Random Y: " + pos_y);*/

            if (tiles[pos_x][pos_y] == null) {
                tiles[pos_x][pos_y] = new Mine();
                m++;
            }
        }

        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
            if (tiles[i][j] == null )
                tiles[i][j] = new Clue(countAdjacentMines(i,j));

               /* System.out.print(tiles[i][j].getState() + " ,");*/
            }
           /* Minesweeper.getInstance().setStartTime(System.currentTimeMillis());
            System.out.println();*/

        }

    }



    /**
     * Returns true if game is solved, false otherwise.
     *
     * @return true if game is solved, false otherwise
     */
    private boolean isSolved() {
        //throw new UnsupportedOperationException("Method isSolved not yet implemented");
 int allclues = rowCount*columnCount;
 int openclues = getNumberOf(Tile.State.OPEN);
 if (allclues -openclues == mineCount)
     return true;
 else
     return false;

    }

    /**
     * Returns number of adjacent mines for a tile at specified position in the field.
     *
     * @param row    row number.
     * @param column column number.
     * @return number of adjacent mines.
     */
    private int countAdjacentMines(int row, int column) {
        int count = 0;
        for (int rowOffset = -1; rowOffset <= 1; rowOffset++) {
            int actRow = row + rowOffset;
            if (actRow >= 0 && actRow < getRowCount()) {
                for (int columnOffset = -1; columnOffset <= 1; columnOffset++) {
                    int actColumn = column + columnOffset;
                    if (actColumn >= 0 && actColumn < getColumnCount()) {
                        if (tiles[actRow][actColumn] instanceof Mine) {
                            count++;
                        }
                    }
                }
            }
        }

        return count;
    }

    /**
     * Field row count. Rows are indexed from 0 to (rowCount - 1).
     */
    public int getRowCount() {
        return rowCount;
    }

    /**
     * Column count. Columns are indexed from 0 to (columnCount - 1).
     */
    public int getColumnCount() {
        return columnCount;
    }

    /**
     * Mine count.
     */
    public int getMineCount() {
        return mineCount;
    }

    /**
     * Game state.
     */
    public GameState getState() {
        return state;
    }

    public void setState(GameState state) {
        this.state = state;
    }

    public Tile getTile(int row, int column) {
        if (tiles[row][column]!= null && tiles[0].length != 0)
            return tiles[row][column];
        return null;
    }

 /*  public void markTile(int row, int column) { // Temporary public
       Tile tile = tiles[row][column];
        if (tiles[row][column]!= null && tiles[0].length != 0) {

            if (tile.getState() == Tile.State.CLOSED) {
                tile.setState(Tile.State.MARKED);

            }
            return;
        }
            if (tile.getState() == Tile.State.MARKED) {
                tile.setState(Tile.State.CLOSED);
                return;
            }

                    return;
        }

  */
 private int getNumberOf(Tile.State state) {
     int n = 0;
     for (int row = 0; row < rowCount; row++) {
         for (int col = 0; col < columnCount; col++) {
             if (tiles[row][col].getState() == state) ;
             n++;
         }
     }
     return n;

 }
public int getRemainingMineCount() {
   return getMineCount()- (this.getNumberOf(Tile.State.MARKED));

}
//doplnene 3.8.
    public int getPlayingSeconds() {
     return (int) ((System.currentTimeMillis() - startMillis) / 1000);
    }
public int getScore() {
     return rowCount * columnCount * 10 - getPlayingSeconds();
}


    }

