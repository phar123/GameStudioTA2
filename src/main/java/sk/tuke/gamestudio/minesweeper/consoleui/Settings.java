package sk.tuke.gamestudio.minesweeper.consoleui;

import java.io.*;

public class Settings implements Serializable {

    private final int rowCount;
    private final int columnCount;
    private final int mineCount;
    public static final Settings BEGINNER = new Settings(2, 2, 0);
    public static final Settings INTERMEDIATE = new Settings(16, 16, 40);
    public static final Settings EXPERT = new Settings(16, 30, 99);
    private static final String SETTING_FILE = System.getProperty("user.home") +
            System.getProperty("file.separator") +
            "minesweeper.settings";



    public Settings(int rowCount, int columnCount, int mineCount) {
        this.rowCount = rowCount;
        this.columnCount = columnCount;
        this.mineCount = mineCount;
    }

    public int getRowCount() {
        return rowCount;
    }

    public int getColumnCount() {
        return columnCount;
    }

    public int getMineCount() {
        return mineCount;
    }

    // public boolean equals(Object o) {
    //  if (this.rowCount == o.
    //  }


   /* @Override
    public boolean equals(Object obj) {
   if (!(obj instanceof Settings)) {
            return false;
        }
        Settings s = (Settings) obj;
        return s.rowCount == rowCount
                && s.columnCount == columnCount
                && s.mineCount == mineCount;
        }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Settings settings = (Settings) o;
        return rowCount == settings.rowCount && columnCount == settings.columnCount && mineCount == settings.mineCount;
    }

    @Override
    public int hashCode() {

        //return Objects.hash(rowCount, columnCount, mineCount);
        return rowCount * columnCount * mineCount;
    }

    public void save() {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(SETTING_FILE));
            oos.writeObject(this);
        } catch (IOException e) {
            System.out.println("Settings not written to object!");
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    // intensional empty

                }
            }
        }
    }
    public static Settings load() {
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(SETTING_FILE));
            Settings s = (Settings) ois.readObject();
            return s;
        } catch (IOException e) {
            System.out.println("Settings read not successful. Using default BEGINNER. ");
        } catch (ClassNotFoundException e) {
            System.out.println("Settings read not successful. Using default BEGINNER.");

        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    //Intentional empty
                }

            }
        }
    return BEGINNER;
    }

    @Override
    public String toString() {
        return "Settings [" + rowCount + "," + columnCount + "," + mineCount + "]";
    }

}

