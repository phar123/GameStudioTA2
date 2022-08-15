package sk.tuke.gamestudio.entity;

import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import java.io.Serializable;
import java.util.Date;
@Entity
@Table(uniqueConstraints =
        {@UniqueConstraint(name = "UniqueGameAndUsername", columnNames = { "game", "username" })})
public class Rating implements Serializable {


        @Id
        @GeneratedValue
        private long ident;

        @Column(nullable = false, length=64)
        private String game;

        @Column(nullable = false, length=64)
        private String username;

        @Column(columnDefinition = "INT CHECK(rating BETWEEN 1 AND 5) NOT NULL")
        private int rating;

        @Column(nullable = false)
        private Date ratedon;

        public Rating(){}
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

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setRatedon(Date ratedon) {
        this.ratedon = ratedon;
    }
}
