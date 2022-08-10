package test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sk.tuke.gamestudio.minesweeper.core.Field;
import sk.tuke.gamestudio.minesweeper.core.GameState;
import sk.tuke.gamestudio.minesweeper.core.Mine;
import sk.tuke.gamestudio.minesweeper.core.Tile;


import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class FieldTest {

    private Random randomGenerator = new Random();
    private Field field;
    private int rowCount;
    private int columnCount;
    private int minesCount;

    @BeforeEach
    public void InitTest() {
        rowCount = randomGenerator.nextInt(10) + 5;
        columnCount = rowCount;
        minesCount = Math.max(1, randomGenerator.nextInt(rowCount * columnCount));
        field = new Field(rowCount, columnCount, minesCount);
    }

    @Test
    public void checkMinesCount() {
        int minesCounter = 0;
        for (int row = 0; row < rowCount; row++) {
            for (int column = 0; column < columnCount; column++) {
                if (field.getTile(row, column) instanceof Mine) {
                    minesCounter++;
                }
            }
        }

        assertEquals(minesCount, minesCounter, "Field was initialized incorrectly - " +
                "a different amount of mines was counted in the field than amount given in the constructor.");
    }

    @Test
    public void fieldWithTooManyMines() {
        Field fieldWithTooManyMines = null;
        int higherMineCount = rowCount * columnCount + randomGenerator.nextInt(10) + 1;
        try {
            fieldWithTooManyMines = new Field(rowCount, columnCount, higherMineCount);
        } catch (Exception e) {
            // field with more mines than tiles should not be created - it may fail on exception
        }
        assertTrue((fieldWithTooManyMines == null) || (fieldWithTooManyMines.getMineCount() <= (rowCount * columnCount)));
    }

    @Test
    public void checkFieldInitialization() {

        if (field.getRowCount() == rowCount) {
            assertEquals(rowCount, field.getRowCount(), "Rowcout not equal");
            assertEquals(columnCount, field.getColumnCount(), "Columns not equal");
            assertEquals(minesCount, field.getMineCount(), "MinesCount not Equal");
            assertEquals(GameState.PLAYING, field.getState(), "Game state is not equal");
        }
    }

    @Test
    public void checkMarkTile() {
        int row = 5, col = 5;

        field.markTile(row, col);
        assertEquals(Tile.State.MARKED, field.getTile(row, col), "Check MARKED failed");


    }

  /* @Test
    public void checkNoZeroTile() {
        for (int i = 0; i < rowCount ; i++) {
            for (int j = 0; j < columnCount; j++) {
               if ( (field.getTile(i, j) instanceof Clue) && ((Clue) field.getTile(i, j)).getValue() == 0 ) {
                   field.getTile(i,j).getValue().State.MARKED;
               }

               }

        }



    }
    assertEquals(Tile.State.MARKED, field.getTile(row, col), "Check  failed");
/*
    @Test
    public void checkZeroTile() {


    }
    assertEquals(Tile.State.MARKED, field.getTile(row, col), "Check  failed");


    @Test
    public void checkNotOpenTile() {


    }
    assertEquals(Tile.State.MARKED, field.getTile(row, col), "Check  failed");
*/
}