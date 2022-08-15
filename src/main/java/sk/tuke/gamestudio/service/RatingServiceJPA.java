package sk.tuke.gamestudio.service;

import sk.tuke.gamestudio.entity.Rating;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Date;

@Transactional
public class RatingServiceJPA implements RatingService {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public void setRating(Rating rating) {
        Rating rating2Write = null;
        try {
            rating2Write = (Rating) entityManager.createQuery("select r from Rating r where r.username = :user and r.game = :game")
                    .setParameter("user", rating.getUsername())
                    .setParameter("game", rating.getGame())
                    .getSingleResult();

            rating2Write.setRating(rating.getRating());
            rating2Write.setRatedon(new Date());

        } catch (NoResultException e) {

            entityManager.persist(rating);


        }

       // entityManager.persist(rating);

    }

    @Override
    public int getAverageRating(String game) {
        return (int) entityManager
                .createQuery("select ROUND(AVG(ra)) from Rating ra where ra.game = :myGame")
                .setParameter("myGame", game)
                .getSingleResult();
    }
//SELECT ROUND(AVG(rating)) FROM rating WHERE game=?
    @Override
    public int getRating(String game, String username) {
        return (int) entityManager
                .createQuery("select ra from Rating ra where ra.game = :myGame and ra.username = :myUsername")
                .setParameter("myGame", game)
                .setParameter("myUsername", username)
                .getSingleResult();
    }

    @Override
    public void reset() {
        entityManager.createNativeQuery("DELETE FROM rating").executeUpdate();

    }
}
