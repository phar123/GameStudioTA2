package sk.tuke.gamestudio.minesweeper.consoleui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import sk.tuke.gamestudio.entity.*;
import sk.tuke.gamestudio.minesweeper.Minesweeper;
//import sk.tuke.gamestudio.minesweeper.UserInterface;
//import minesweeper.core.*;
//import service.*;
import sk.tuke.gamestudio.minesweeper.core.Field;
import sk.tuke.gamestudio.minesweeper.core.GameState;
import sk.tuke.gamestudio.service.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Console user interface.
 */
public class ConsoleUI implements UserInterface {
    /**
     * Playing field.
     */
    private Field field;
    String playerName = System.getProperty("user.name");

    /**
     * Input reader.
     */
    private final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    private Throwable ex;
    private Settings setting;
    @Autowired
    private ScoreService scoreService;
   @Autowired
    private CommentService commentService;
    @Autowired
    private RatingService ratingService;

    @Autowired
    private PlayerServiceJPA playerServiceJPA;

    @Autowired
    OccupationServiceJPA occupationServiceJPA;

    @PersistenceContext
    private EntityManager entityManager;


    /**
     * Reads line of text from the reader.
     *
     * @return line as a string
     */
    private String readLine() {
        try {
            return input.readLine();
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * Starts the game.
     *
     * @param field field of mines and clues
     */
    @Override
    public void newGameStarted(Field field) {
        System.out.println("Please enter your name or choose from the list");

//INPUT FROM USER
        System.out.println("Please enter userName(max.128): ");
        try {
            String userName = readLine();

            List<Player> playerlist = playerServiceJPA.getPlayersByUserName(userName);


            for (int i = 0; i < playerlist.size(); i++)
                System.out.println(i + " " + playerlist.get(i));
        } catch (Exception e) {
            System.err.print("Chyba pri nacitani" + e.getMessage());
        }

        System.out.println("Select one or insert new(enter)");
        try {
            String username = readLine();
            String fullName = "";
            String country = "";

            int selfEval;
            int occupation;

            if (username.length() == 0) {
                System.out.println("Enter Full Name (max.128)");
                fullName = readLine();
                System.out.println("Enter User Name (max.32)");
                username = readLine();
                System.out.println("Enter Country (max.32)");
                username = readLine();

                System.out.println("Enter Self Evaluation (1-10)");
                selfEval = Integer.parseInt(readLine());


                List<Occupation> occupationlist = occupationServiceJPA.getOccupations();


                for (int i = 0; i < occupationlist.size(); i++)
                    System.out.println(i + " " + occupationlist.get(i));
                System.out.println("Select occupation from the list");
                occupation = Integer.parseInt(readLine());
                int temp = 0;

                playerServiceJPA.addPlayer(new Player(username, fullName, selfEval, new Country(country), occupationlist.get(occupation)));
                playerName = username;
            } else {
                playerName = username;
            }
        } catch (Exception e)  {
            System.err.println("Chyba nacitania"+e.getMessage());

        }

        this.field = field;
        System.out.println("Choose player level?");
        System.out.println("(1) BEGINNER, (2) INTERMEDIATE, (3) EXPERT, (ENTER) DEFAULT");
        String level = readLine();
        if(level != null && !level.equals("")) {
            try {
                int intLevel = Integer.parseInt(level);
                Settings s = switch (intLevel) {
                    case 2 -> Settings.INTERMEDIATE;
                    case 3 -> Settings.EXPERT;
                    default -> Settings.BEGINNER;

                    //   setting.save();
                };
                this.setting = s;
                this.setting.save();



                this.field = new Field(s.getRowCount(), s.getColumnCount(), s.getMineCount());

            } catch (NumberFormatException e) {
                //intentional empty empty
            }

        }

        do {
            update();
            processInput();
            if (this.field.getState() == GameState.SOLVED) {

                System.out.println("Congrats, you winning");

               // Minesweeper.getInstance().getPlayingSeconds();


                // System.out.println("Your score is: "+ this.field.getScore());

                /**SCORE AT WINING TO RECORD*/
               // Score score = new Score("minesweeper", playerName, this.field.getScore(), new Date());
                //  ScoreService scoreService1 = new ScoreServiceJDBC();
                System.out.println("Hrac " + playerName + "Ukoncil vyhrou");
               // scoreService.addScore(score);
                scoreService.addScore(new Score("minesweeper", playerName, this.field.getScore(), new Date()));
System.out.println("Score added");

                List<Score> score1 = scoreService.getBestScores("minesweeper");
                System.out.println("Best scorre \n : " + score1);
                 try {
                while (true) {

                    System.out.println("Please enter your comment (max.1000 characters): ");
                    var comment = readLine();
                    if (comment.length() <= 1000 && comment.length() > 0) {

                        Comment comment1 = new Comment("minesweeper", playerName, comment, new Date());
                        // CommentService commentService = new CommentServiceJDBC();
                        commentService.addComment(comment1);

                        var comment_list = commentService.getComments("minesweeper");
                        for (Comment comments : comment_list)
                            System.out.println(comments);
                        break;
                    } else {
                        System.out.println("Comment incorrect");
                        continue;
                    }
                }
                 }catch (Exception e) {System.out.println(e.getMessage()); }
//Rating integration START

                while (true) {
                    try {

                        System.out.println("Please enter your rating in a range 1-5: ");
                        var rating = (readLine()).trim();
                        int rating_val = Integer.valueOf(rating).intValue();
                        if (rating_val > 0 && rating_val < 6) {

                            Rating rating1 = new Rating("minesweeper", playerName, Integer.valueOf(rating), new Date());
                           //  RatingService ratingService = new RatingServiceJDBC();
                            ratingService.setRating(rating1);

                            var ratings = ratingService.getAverageRating("minesweeper");
                            System.out.println("Average rating: " + ratings);

                            ratings = ratingService.getRating("minesweeper", playerName);
                            System.out.println(playerName + "  rating: " + ratings);
                            break;
                        } else {
                            System.out.println("Incorrect value, please enter number 1-5");
                            continue;
                        }

                    } catch (NumberFormatException ex) {
                        System.out.println("Incorrect input for rating");
                        continue;
                    }
                    catch (GameStudioException ex){
                        System.out.println("Error database connection" + ex.getMessage());
                    }
                }



//Rating integration END


                System.exit(0);
            } else if (this.field.getState() == GameState.FAILED) {

                System.out.println("Sorry, you losing");


                // ScoreService scoreService = new ScoreServiceJDBC();
                List<Score> score2 = scoreService.getBestScores("minesweeper");
                System.out.println("Best scorre \n : " + score2);

                while (true) {
                    try {
                        System.out.println("Please enter your comment (max.1000 characters): ");
                        var comment = readLine();
                        if (comment.length() <= 1000 && comment.length() > 0) {

                            Comment comment1 = new Comment("minesweeper", playerName, comment, new Date());
                           // CommentService commentService = new CommentServiceJDBC();
                            commentService.addComment(comment1);

                            var comment_list = commentService.getComments("minesweeper");
                            for (Comment comments : comment_list)
                                System.out.println(comments);
                            break;
                        } else {
                            System.out.println("Comment incorrect");
                            continue;
                        }
                    } catch (Exception ex) {
                        System.out.println("Error database connection" + ex.getMessage());
                    }
                }



               // CommentService commentService2 = new CommentServiceJDBC();

                var comment_list = commentService.getComments("minesweeper");
                for (Comment comments : comment_list)
                    System.out.println(comments);

                while (true) {
                    try {

                        System.out.println("Please enter your rating in a range 1-5: ");
                        var rating = (readLine()).trim();
                        int rating_val = Integer.valueOf(rating).intValue();
                        if (rating_val > 0 && rating_val < 6) {

                            Rating rating1 = new Rating("minesweeper", playerName, Integer.valueOf(rating), new Date());
                              //RatingService ratingService = new RatingServiceJDBC();
                            ratingService.setRating(rating1);

                            var ratings = ratingService.getAverageRating("minesweeper");
                            System.out.println("Average rating: " + ratings);

                            ratings = ratingService.getRating("minesweeper", playerName);
                            System.out.println(playerName + "  rating: " + ratings);
                            break;
                        } else {
                            System.out.println("Incorrect value, please enter number 1-5");
                            continue;
                        }

                    } catch (NumberFormatException ex) {
                        System.out.println("Incorrect input for rating");
                        continue;
                    } catch (Exception ex) {
                        System.out.println("Error database connection" + ex.getMessage());
                    }
                }

                System.exit(0);
            }


           // processInput();
            // throw new UnsupportedOperationException("Resolve the game state - winning or loosing condition.");
        } while (true);
    }

    /**
     * Updates user interface - prints the field.
     */
    @Override
    public void update() {
       /* System.out.printf("Cas hrania: %d%n",
                Minesweeper.getInstance().getPlayingSeconds()
        );*/
// Vykreslenie pola

   /* int row_c = field.getRowCount();
    int col_c = field.getColumnCount();

    char znak = 50;
    Tile t;
    for(int i = 0; i < row_c; i++)

    {
        System.out.printf( c%, znak );
        znak += 64;
        if (i == 0) {
            for (int j = 0; j < col_c; j++) {
                System.out.printf( % d, j);
            }
            System.out.println();
        }

        for (int j = 0; j < col_c; j++) {
            if (field.getTile(i, j).getState() == "OPEN" //&& instanceOf(field.getTile(i, j) == Mine))
            System.out.printf(c %; 'X');
            if (field.getTile(i, j).getState() == "OPEN" //&& instanceOf(field.getTile(i, j) == Clue))
            System.out.printf( % d; field.toString());
            if (field.getTile(i, j).getState() == "MARKED" //&& instanceOf(field.getTile(i, j) == Clue))
            System.out.printf( % ch; 'M' );
            if (field.getTile(i, j).getState() == "CLOSED") ;
            System.out.printf( % ch; '-');
        }
        System.out.println();

    }

            System.out.print(tiles[i][j].getState() + " ,");
        }
        System.out.println();
        */
/*
    System.out.print("  ");*/

        System.out.printf("    ");
        for (int i = 1; i < field.getColumnCount() + 1; i++) {
            System.out.printf("%4s", i);
        }

        System.out.println();

        char c = 'A';

        for (int i = 0; i < field.getRowCount(); i++) {
            c = (char) (i + 65);
            System.out.printf("%4s", c);

            for (int j = 0; j < field.getColumnCount(); j++) {
               /* if (j > 9) {
                    System.out.print(" ");
                }*/
                System.out.printf("%4s", field.getTile(i, j)); // netreba toString = automaticky
            }

            System.out.println();
        }
        System.out.println("Number of unmarked mines: " + field.getRemainingMineCount());

    }


    /**
     * Processes user input.
     * Reads line from console and does the action on a playing field according to input string.
     */
    private void processInput() {
        //throw new UnsupportedOperationException("Method processInput not yet implemented");
        String a = " X ";
        String b = "EXIT";
        String c = "MA1";
        String d = "MARK";
        String e = " OB4";
        String f = "OPEN";
        String g = "N";
        String h = "(nick)NAME";


        System.out.printf("Please select your location  <%s>  %s, <%s>  %s, <%s>  %s, <%s>  %s : ", a, b, c, d, e, f, g, h);

        try {

            String s = readLine();

            handleInput(s);

        } catch (WrongFormatException ex) {
            System.out.println(ex.getMessage());

            processInput();
        }
    }

    //Exception class

    private void handleInput(String input) throws WrongFormatException {
        //throw new UnsupportedOperationException("Method processInput not yet implemented");


        Pattern pat;
        Matcher mat;
        boolean found;

        if (input.length() > 3) {
            throw new WrongFormatException("Too many chracters! Please enter again");

        }

        if (input.equals("x") || input.equals("X"))
            System.exit(0);
//Name of the player
        if (input.equals("n") || input.equals("N")) {
            System.out.println("Please enter your (nick)Name: ");
            playerName = readLine();
            System.out.println("Hi "+ playerName+ " Good luck!\n");
            //Minesweeper.getInstance().setStartTime(System.currentTimeMillis());
        }


// Test MARKED
        pat = Pattern.compile("([Oo||Mm])([A-Ia-i)])([1-9])");
        mat = pat.matcher(input);
        found = mat.matches();

        if (found) {
            // Parsing line col to number

            char row = input.charAt(1);
            int row1 = row;
            if (row1 >= 65 && row1 < 97)
                row1 = row1 - 65;
            else row1 = row - 97;

            int col = Integer.parseInt(mat.group(3));

            if (input.charAt(0) == 'M' || input.charAt(0) == 'm') {
                System.out.println("Stlacene Marked");
                field.markTile(row1, col - 1);
            }
            if (input.charAt(0) == 'O' || input.charAt(0) == 'o') {
                System.out.println("Stlacene OPEN");
                field.openTile(row1, col - 1);
            }
        }
        //  update();

    }
    @Override
    public void play() {

        setting = Settings.load();

        Field field = new Field(setting.getRowCount(), setting.getColumnCount(), setting.getMineCount());

        newGameStarted(field);
    }

}

