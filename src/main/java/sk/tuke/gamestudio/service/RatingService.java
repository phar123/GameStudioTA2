package sk.tuke.gamestudio.service;

import sk.tuke.gamestudio.entity.Rating;

public interface RatingService {
    void setRating(Rating rating);  //Prepise/prida zaznam
    int getAverageRating(String game); // Priemerne hodnotenie zaokruhlene na cele cislo
    int getRating(String game, String username); //Vrati hodnotenie hry s naz. game od hraca username
    void reset(); // vymaze rating


}
