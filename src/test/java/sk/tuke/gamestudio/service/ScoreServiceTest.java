package sk.tuke.gamestudio.service;

import sk.tuke.gamestudio.entity.Score;
import org.junit.jupiter.api.Test;
import sk.tuke.gamestudio.service.ScoreService;
import sk.tuke.gamestudio.service.ScoreServiceFile;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;//Treba rucne pridat

public class ScoreServiceTest {
    //private ScoreService scoreService = new ScoreServiceJDBC();
    private ScoreService scoreService = new ScoreServiceFile();

    @Test
    public void testReset() {
        scoreService.addScore(new Score("sk/tuke/gamestudio","Jano",123,new Date()));
        scoreService.reset();
        assertEquals(0, scoreService.getBestScores("sk/tuke/gamestudio").size());

    }
    @Test
    public void testAddScore(){
        scoreService.reset();
        var date = new Date();

        scoreService.addScore(new Score("sk/tuke/gamestudio","Jano",123,date));
        var scores = scoreService.getBestScores("sk/tuke/gamestudio");
        assertEquals(1, scores.size());
        assertEquals("sk/tuke/gamestudio", scores.get(0).getGame());
        assertEquals("Jano", scores.get(0).getUsername());
        assertEquals(123, scores.get(0).getPoints());
        assertEquals(date, scores.get(0).getPlayedOn());

    }


    @Test
    public void testGetBestScores() {
        scoreService.reset();
        var date = new Date();
        scoreService.addScore(new Score("sk/tuke/gamestudio", "Peto", 140, date));
        scoreService.addScore(new Score("sk/tuke/gamestudio", "Katka", 150, date));
        scoreService.addScore(new Score("tiles", "Zuzka", 290, date));
        scoreService.addScore(new Score("sk/tuke/gamestudio", "Jergus", 100, date));

        var scores = scoreService.getBestScores("sk/tuke/gamestudio");

        assertEquals(3, scores.size());

        assertEquals("sk/tuke/gamestudio", scores.get(0).getGame());
        assertEquals("Katka", scores.get(0).getUsername());
        assertEquals(150, scores.get(0).getPoints());
        assertEquals(date, scores.get(0).getPlayedOn());

        assertEquals("sk/tuke/gamestudio", scores.get(1).getGame());
        assertEquals("Peto", scores.get(1).getUsername());
        assertEquals(140, scores.get(1).getPoints());
        assertEquals(date, scores.get(1).getPlayedOn());

        assertEquals("sk/tuke/gamestudio", scores.get(2).getGame());
        assertEquals("Jergus", scores.get(2).getUsername());
        assertEquals(100, scores.get(2).getPoints());
        assertEquals(date, scores.get(2).getPlayedOn());
    }

}
