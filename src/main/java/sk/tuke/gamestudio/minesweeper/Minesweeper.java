package sk.tuke.gamestudio.minesweeper;

import sk.tuke.gamestudio.minesweeper.consoleui.ConsoleUI;
import sk.tuke.gamestudio.minesweeper.consoleui.UserInterface;


/**
 * Main application class.
 */
public class Minesweeper {
    /**
     * User interface.
     */

    long startMillis;

    private static Minesweeper instance;  //Singleton pattern

    /**
     * Constructor.
     */
    private Minesweeper() {
        instance = this;  //Singleton




        final UserInterface userInterface = new ConsoleUI();
        userInterface.play();

       // startMillis = System.currentTimeMillis();


    }

    public static Minesweeper getInstance() {
        if (instance == null) {
            new Minesweeper();
        }
        return instance;
    }



  /*  public int getPlayingSeconds() {
        return (int) ((System.currentTimeMillis() - startMillis) / 1000);
    }

   public void setStartTime(long startMillis) {
        this.startMillis = startMillis;
    }*/



    /**
     * Main method.
     *
     * @param args arguments
     */
    public static void main(String[] args) {
        Minesweeper.getInstance();
        //Minesweeper sweeper = new Minesweeper();


    }

    /*public BestTimes getBestTimes() {
        return bestTimes;
    }*/


}
