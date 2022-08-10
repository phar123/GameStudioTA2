package sk.tuke.gamestudio.service;

import sk.tuke.gamestudio.entity.Rating;
import org.junit.jupiter.api.Test;
import sk.tuke.gamestudio.service.RatingService;
import sk.tuke.gamestudio.service.RatingServiceJDBC;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RatingServiceTest {
    private RatingService ratingService = new RatingServiceJDBC();
    //private ScoreService scoreService = new ScoreServiceFile();

    @Test
    public void testReset() {
        ratingService.setRating(new Rating("sk/tuke/gamestudio", "Jano", 2, new Date()));
        ratingService.reset();
        assertEquals(0, ratingService.getRating("sk/tuke/gamestudio", "Jano"));

    }

    @Test
    public void testSetRating() {
        ratingService.reset();
        var date = new Date();

        ratingService.setRating(new Rating("sk/tuke/gamestudio", "Jano", 2, date));
        var ratings = ratingService.getRating("sk/tuke/gamestudio", "Jano");
        assertEquals(2, ratings);


    }


    @Test
    public void testDuplicity() {
        ratingService.reset();
        var date = new Date();
        ratingService.setRating(new Rating("sk/tuke/gamestudio", "Peto", 4, date));
        ratingService.setRating(new Rating("sk/tuke/gamestudio", "Peto", 5, date));


        var ratings = ratingService.getRating("sk/tuke/gamestudio", "Peto");

        assertEquals(5, ratings);

/*
        assertEquals("minesweeper", scores.get(0).getGame());
        assertEquals("Katka", scores.get(0).getUsername());
        assertEquals(150, scores.get(0).getPoints());
        assertEquals(date, scores.get(0).getPlayedOn());

        assertEquals("minesweeper", scores.get(1).getGame());
        assertEquals("Peto", scores.get(1).getUsername());
        assertEquals(140, scores.get(1).getPoints());
        assertEquals(date, scores.get(1).getPlayedOn());

        assertEquals("minesweeper", scores.get(2).getGame());
        assertEquals("Jergus", scores.get(2).getUsername());
        assertEquals(100, scores.get(2).getPoints());
        assertEquals(date, scores.get(2).getPlayedOn());
    }*/

    }

    @Test
    public void testRatingAverage() {
        ratingService.reset();
        var date = new Date();
        ratingService.setRating(new Rating("sk/tuke/gamestudio", "Jano", 4, date));
        ratingService.setRating(new Rating("sk/tuke/gamestudio", "Jozi", 5, date));
        ratingService.setRating(new Rating("sk/tuke/gamestudio", "Laci", 1, date));

        var ratings = ratingService.getAverageRating("sk/tuke/gamestudio");

        assertEquals(3, ratings);
    }

    @Test
    public void testgetRating() {
        ratingService.reset();
        var date = new Date();
        ratingService.setRating(new Rating("sk/tuke/gamestudio", "Jano", 4, date));
        ratingService.setRating(new Rating("sk/tuke/gamestudio", "Jozi", 5, date));
        ratingService.setRating(new Rating("sk/tuke/gamestudio", "Laci", 1, date));

        var ratings = ratingService.getRating("sk/tuke/gamestudio", "Laci");

        assertEquals(1, ratings);
    }


}

