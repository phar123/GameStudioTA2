package sk.tuke.gamestudio.service;

import sk.tuke.gamestudio.entity.Score;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class ScoreServiceJPA implements ScoreService{
    @PersistenceContext
private EntityManager entityManager;
    @Override
    public void addScore(Score score) {
        entityManager.persist(score);

    }

    @Override
    public List<Score> getBestScores(String game) {
        return entityManager
                .createQuery("select sc from Score sc where sc.game = :myGame order by s.points desc")
                .setParameter("myGame", game)
                .getResultList();

    }

    @Override
    public void reset() {
        entityManager.createNativeQuery("DELETE FROM score").executeUpdate();

    }
}
