package sk.tuke.gamestudio.service;

import sk.tuke.gamestudio.entity.Rating;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;


public class RatingServiceJDBC implements RatingService {
    /* CREATE TABLE rating(
					game VARCHAR(64) NOT NULL,
					username VARCHAR(64) NOT NULL UNIQUE,
					rating INT NOT NULL CHECK (rating > 0 AND rating < 6),
					ratedon TIMESTAMP NOT NULL,
					CONSTRAINT username_rating UNIQUE(username, rating)
					);*/

    private static final String JDBC_URL = "jdbc:postgresql://localhost/gamestudio";
    private static final String JDBC_USER = "postgres";
    private static final String JDBC_PASSWORD = "postgres";

    private static final String STATEMENT_SET_RATING = "INSERT INTO rating VALUES(?, ?, ?, ?)";
    private static final String STATEMENT_UPDATE_RATING = "UPDATE rating SET rating=?,ratedon=? WHERE username=? AND game=?";

    private static final String STATEMENT_RESET = "DELETE FROM rating";
    private static final String STATEMENT_GET_RATING = "SELECT rating FROM rating WHERE game=? AND username=?";
    private static final String STATEMENT_GET_AVERAGE_RATING = "SELECT ROUND(AVG(rating)) FROM rating WHERE game=?";

    @Override
    public void setRating(Rating rating) {

        if (getRating(rating.getGame(), rating.getUsername()) == 0) {
            try (var connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);

                 var statement = connection.prepareStatement(STATEMENT_SET_RATING);) {
                statement.setString(1, rating.getGame());
                statement.setString(2, rating.getUsername());
                statement.setInt(3, rating.getRating());
                statement.setTimestamp(4, new Timestamp(rating.getRatedon().getTime()));
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new GameStudioException(e);
            }


        } else {
            try (var connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);

                 var statement = connection.prepareStatement(STATEMENT_UPDATE_RATING);) {
                statement.setInt(1, rating.getRating());
                statement.setTimestamp(2, new Timestamp(rating.getRatedon().getTime()));
                statement.setString(3, rating.getUsername());
                statement.setString(4, rating.getGame());

                statement.executeUpdate();
            } catch (SQLException e) {
                throw new GameStudioException(e);
            }


        }
    }

    @Override
    public int getAverageRating(String game) {
        try(var connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
            var statement = connection.prepareStatement(STATEMENT_GET_AVERAGE_RATING);
        ) {
            //statement.setString(1, game);
            statement.setString(1, game);

            try (var rs = statement.executeQuery()) {

                var rating = 0;

                if (rs.next()){

                    rating = rs.getInt(1);
                }
                return rating;

            }
        } catch (SQLException e) {
            throw new GameStudioException(e);

        }

    }

    @Override
    public int getRating(String game, String username) {
        try(var connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
            var statement = connection.prepareStatement(STATEMENT_GET_RATING);
        ) {
            //statement.setString(1, game);
            statement.setString(1, game);
            statement.setString(2, username);
            try (var rs = statement.executeQuery()) {

                var rating = 0;

                if (rs.next()){

                    rating = rs.getInt(1);
                }
                return rating;

            }
        } catch (SQLException e) {
            throw new GameStudioException(e);

        }


    }

    @Override
    public void reset() {
        try (var connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             var statement = connection.createStatement();)
        {

            statement.executeUpdate(STATEMENT_RESET);


        } catch (SQLException e) {
            throw new GameStudioException(e);

        }


    }
}
