package sk.tuke.gamestudio.entity;

import java.io.Serializable;
import java.util.Date;

public class Rating implements Serializable {
    private String game;
    private String username;
    private int rating;
    private Date ratedon;

    public Rating(String game, String username, int rating, Date ratedon) {
        this.game = game;
        this.username = username;
        this.rating = rating;
        this.ratedon = ratedon;
    }

    @Override
    public String toString() {
        return "Comments{" +
                "game='" + game + '\'' +
                ", username='" + username + '\'' +
                ", commented=" + rating +
                ", commented on=" + ratedon +
                '}'+"\n";
    }

    public String getGame() {
        return game;
    }

    public String getUsername() {
        return username;
    }


    public int getRating() {
        return rating;
    }


    public Date getRatedon() {
        return ratedon;
    }

}
