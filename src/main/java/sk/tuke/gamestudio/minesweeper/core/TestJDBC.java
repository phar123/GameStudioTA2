package sk.tuke.gamestudio.minesweeper.core;

import sk.tuke.gamestudio.entity.Score;
import sk.tuke.gamestudio.service.ScoreService;
import sk.tuke.gamestudio.service.ScoreServiceJDBC;
import java.util.Date;

public class TestJDBC {
public static void main(String[] args) throws Exception{
    ScoreService service = new ScoreServiceJDBC();

    service.addScore(new Score("sk/tuke/gamestudio","John",456,new Date()));
    service.getBestScores("sk/tuke/gamestudio");
   // service.reset();

    var scores = service.getBestScores("sk/tuke/gamestudio");
    System.out.println(scores);
    System.out.println("finished");
}



}
